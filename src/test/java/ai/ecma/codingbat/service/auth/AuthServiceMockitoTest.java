//package ai.ecma.codingbat.service.auth;
//
//import ai.ecma.codingbat.entity.User;
//import ai.ecma.codingbat.exceptions.RestException;
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.SignDTO;
//import ai.ecma.codingbat.repository.UserRepository;
//import ai.ecma.codingbat.service.implemention.AuthServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class AuthServiceMockitoTest {
//    @InjectMocks
//    AuthServiceImpl underTest;
//    @Mock
//    private UserRepository userRepository;
//    private UserRepository userRepositoryBean;
//    @Autowired
//    public void setUserRepositoryBean(UserRepository userRepository) {
//        this.userRepositoryBean = userRepositoryBean;
//    }
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Mock
//    private JavaMailSender javaMailSender;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//    @BeforeEach
//    void setUnderTest() {
//        underTest = new AuthServiceImpl(userRepository, passwordEncoder, javaMailSender,authenticationManager);
//    }
//
//    @Test
//    @Order(10)
//    public void signUpHappyTest() {
//        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//
//        ApiResult<Boolean> apiResult = underTest.signUp(signDTO);
//
//        assertTrue(apiResult.isSuccess());
//    }
//
//    @Test
//    @Order(20)
//    public void signUpWorthTest() {
//        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        given(userRepository.existsByEmail(user.getEmail())).willReturn(true);
//
//        assertThatThrownBy(() -> underTest.signUp(signDTO))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("Bunday email oldindan mavjud");
//
//    }
//
//    @Test
//    public void verificationFindByEmailThrowTest() {
//        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
//
//        assertThatThrownBy(() -> underTest.verificationEmail(user.getEmail()))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("Bunday email mavjud emas");
//
//    }
//
//    @Test
//    public void verificationIsEnabledTest() {
//        SignDTO signDTO = new SignDTO("admin@codingbat.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        assertThat(underTest.verificationEmail(user.getEmail())).satisfies(api ->
//        {
//            assertEquals("Allaqachon tasqidlangan", api.getMessage());
//
//        });
//    }
//
//    @Test
//    public void verificationHappyTest(){
//        SignDTO signDTO = new SignDTO("firdavs@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        userRepositoryBean.save(user);
//
//        assertThat(underTest.verificationEmail("admin@codingbat.com")).satisfies(api ->
//        {
//            assertEquals("Tasdiqlandi", api.getMessage());
//
//        });
//
//    }
//
//    @Test
//    @Order(30)
//    public void singInThrowExceptionByEmailTest() {
//        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
//
//        assertThatThrownBy(() -> underTest.signIn(signDTO))
//                .isInstanceOf(UsernameNotFoundException.class)
//                .hasMessageContaining(user.getEmail() + " email not found");
//    }
//
//    @Test
//    @Order(40)
//    public void singInThrowExceptionByPasswordEncoderTest() {
//        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
//
//        assertThatThrownBy(() -> underTest.signIn(signDTO))
//                .isInstanceOf(RestException.class)
//                .hasMessageContaining("Password togri kelmadi");
//    }
//
//    @Test
//    @Order(50)
//    public void singInThrowExceptionByPermissionTest() {
//        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
//
//        given(passwordEncoder.matches(signDTO.getPassword(), user.getPassword())).willReturn(true);
//
//        assertThatThrownBy(() -> underTest.signIn(signDTO))
//                .isInstanceOf(RestException.class)
//                .hasMessageContaining("Userga ma'lum bir cheklovlar yuklangan");
//    }
//
//    @Test
//    @Order(60)
//    public void singInHappyTest() {
//        SignDTO signDTO = new SignDTO("firdavsjhhjjjgghjholimjvvvyv25@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        User save = userRepositoryBean.save(user);
//
//        assertThat(underTest.signIn(signDTO))
//                .satisfies(p -> {
//                    assertEquals("Muvvafaqiyatli token yaratildi", p.getMessage());
//                });
//    }
//
//    @Test
//    @Order(10)
//    public void saveDBTest(){
//        SignDTO signDTO = new SignDTO("firdavsolimjonov@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//
//        assertTrue(userRepositoryBean.findByEmail(user.getEmail()).isEmpty());
//        userRepositoryBean.save(user);
//
//        Optional<User> optionalUser = userRepositoryBean.findByEmail(user.getEmail());
//        assertFalse(optionalUser.isEmpty());
//        assertFalse(optionalUser.get().isEnabled());
//
//        user.setEnabled(true);
//        userRepositoryBean.save(user);
//        assertTrue(userRepositoryBean.findByEmail(user.getEmail()).get().isEnabled());
//    }
//}
//
