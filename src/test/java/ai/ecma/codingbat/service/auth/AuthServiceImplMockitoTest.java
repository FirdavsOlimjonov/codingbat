package ai.ecma.codingbat.service.auth;

import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.repository.UserRepository;
import ai.ecma.codingbat.service.AuthServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@Execution(value = ExecutionMode.CONCURRENT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class
AuthServiceImplMockitoTest {
    @InjectMocks
    AuthServiceImpl underTest;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private static User user;
    @Mock
    private static SignDTO signDTO;

    @BeforeEach
    void setUnderTest() {
        underTest = new AuthServiceImpl(userRepository, passwordEncoder, javaMailSender);
    }
    @BeforeAll
    static void setMocks(){
        signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
        user = new User(signDTO.getEmail(), signDTO.getPassword());
    }

    @Test
    public void signUpHappyTest() {

        user = new User(signDTO.getEmail(), signDTO.getPassword());

        when(userRepository.save(any(User.class))).thenReturn(user);
        ApiResult<Boolean> apiResult = underTest.signUp(signDTO);

        assertTrue(apiResult.isSuccess());
    }


    @Test
    public void signUpWorthTest() {

        given(userRepository.existsByEmail("firdavsolimjonov25@gmail.com")).willReturn(true);

        assertThatThrownBy(() -> underTest.signUp(signDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Bunday email oldindan mavjud");

    }

    @Test
    public void verificationFindByEmailThrowTest() {

        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.verificationEmail(user.getEmail()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Bunday email mavjud emas");

    }

    @Test
    public void verificationIsEnabledTest() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThat(underTest.verificationEmail(user.getEmail())).satisfies(api ->
        {
            assertEquals( "Allaqachon tasqidlangan",api.getMessage());

        });
    }

    @Test
    public void singInThrowExceptionByEmailTest(){
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.signIn(signDTO))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(user.getEmail()+" email not found");
    }

    @Test
    public void singInThrowExceptionByPasswordEncoderTest(){
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        assertThatThrownBy(()->underTest.signIn(signDTO))
                .isInstanceOf(RestException.class)
                .hasMessageContaining("Password togri kelmadi");
    }
    @Test
    public void singInThrowExceptionByPermissionTest(){
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        given(passwordEncoder.matches(signDTO.getPassword(),user.getPassword())).willReturn(true);

        assertThatThrownBy(()->underTest.signIn(signDTO))
                .isInstanceOf(RestException.class)
                .hasMessageContaining("Userga ma'lum bir cheklovlar yuklangan");

    }

    @Test
    public void singInHappyTest(){
        user.setEnabled(true);

        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        given(passwordEncoder.matches(signDTO.getPassword(),user.getPassword())).willReturn(true);

        assertThat(underTest.signIn(signDTO))
                .satisfies( p ->{
                    assertEquals("Muvvafaqiyatli token yaratildi",p.getMessage());
                });
    }




}

