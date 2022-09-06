package ai.ecma.codingbat.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.payload.TokenDTO;
import ai.ecma.codingbat.repository.UserRepository;
import ai.ecma.codingbat.util.MessageLang;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.access.key}")
    private String ACCESS_TOKEN_KEY;

    @Value("${jwt.refresh.key}")
    private String REFRESH_TOKEN_KEY;

    @Value("${jwt.access.expiration-time}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${jwt.refresh.expiration-time}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public ApiResult<Boolean> signUp(SignDTO signDTO) {

        if (userRepository.existsByEmail(signDTO.getEmail()))
            throw RestException.restThrow(
                    MessageLang.getMessageSource("EMAIL_ALREADY_EXIST"),
                    HttpStatus.CONFLICT);


        User user = new User(
                signDTO.getEmail(),
                passwordEncoder.encode(signDTO.getPassword()));

        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(sender);
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("");
        mailMessage.setText(MessageLang.getMessageSource("CLICK_LINK") + " http://localhost/api/auth/verification-email?email=" + user.getEmail());

        // Sending the mail
        javaMailSender.send(mailMessage);

        userRepository.save(user);
        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<?> verificationEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> RestException.restThrow(MessageLang.getMessageSource("EMAIL_NOT_EXIST"), HttpStatus.NOT_FOUND));

        if (user.isEnabled()) {
            return ApiResult.successResponse(MessageLang.getMessageSource("ALREADY_VERIFIED"));
        }

        user.setEnabled(true);
        userRepository.save(user);
        return ApiResult.successResponse(MessageLang.getMessageSource("SUCCESSFULLY_VERIFIED"));
    }


    @Override
    public ApiResult<TokenDTO> signIn(SignDTO signDTO) {
        //1. check User
        User user = userRepository
                .findByEmailEqualsIgnoreCase(signDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s email not found", signDTO.getEmail())));

        //2. root123 -> encode
        if (!passwordEncoder.matches(signDTO.getPassword(), user.getPassword()))
            throw RestException
                    .restThrow(
                            MessageLang.getMessageSource("PASSWORD_NOT_MATCHES"),
                            HttpStatus.UNAUTHORIZED);

        //4. 4 ta boolean check
        if (!user.isEnabled()
                || !user.isAccountNonExpired()
                || !user.isAccountNonLocked()
                || !user.isCredentialsNonExpired())
            throw RestException.restThrow(MessageLang.getMessageSource("USER_PERMISSION_RESTRICTION"), HttpStatus.UNAUTHORIZED);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()

        ));

        //5. token
        String accessToken = generateToken(user.getEmail(), true);
        String refreshToken = generateToken(user.getEmail(), false);


        TokenDTO tokenDTO = TokenDTO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ApiResult.successResponse(
                MessageLang.getMessageSource("SUCCESSFULLY_TOKEN_GENERATED"),
                tokenDTO);
    }


    public String generateToken(String email, boolean access) {

        Date expiredDate = new Date(new Date().getTime() +
                (access ? ACCESS_TOKEN_EXPIRATION_TIME : REFRESH_TOKEN_EXPIRATION_TIME));

        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, (
                        access ? ACCESS_TOKEN_KEY : REFRESH_TOKEN_KEY
                ))
                .compact();
    }
}
