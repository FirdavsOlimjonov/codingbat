package ai.ecma.codingbat.service.auth;

import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.repository.UserRepository;
import ai.ecma.codingbat.service.AuthServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
//@Execution(value = ExecutionMode.CONCURRENT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepositoryBean;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    AuthServiceImpl underTest;

    @Test
    public void verificationIsEnabledTest() {
        SignDTO signDTO = new SignDTO("admin@codingbat.com", "root123");
        User user = new User(signDTO.getEmail(), signDTO.getPassword());

        assertThat(underTest.verificationEmail(user.getEmail())).satisfies(api ->
        {
            assertEquals("Allaqachon tasqidlangan", api.getMessage());

        });
    }

    @Test
    public void verificationHappyTest(){
        SignDTO signDTO = new SignDTO("firdavs@gmail.com", "root123");
        User user = new User(signDTO.getEmail(), signDTO.getPassword());

        userRepositoryBean.save(user);

        assertThat(underTest.verificationEmail("admin@codingbat.com")).satisfies(api ->
        {
            assertEquals("Tasdiqlandi", api.getMessage());

        });

    }

    @Test
    @Order(40)
    public void singInThrowExceptionByPasswordEncoderTest() {
        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
        User user = new User(signDTO.getEmail(), signDTO.getPassword());

        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        assertThatThrownBy(() -> underTest.signIn(signDTO))
                .isInstanceOf(RestException.class)
                .hasMessageContaining("Password togri kelmadi");
    }

    @Test
    @Order(50)
    public void singInThrowExceptionByPermissionTest() {
        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
        User user = new User(signDTO.getEmail(), signDTO.getPassword());

        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        given(passwordEncoder.matches(signDTO.getPassword(), user.getPassword())).willReturn(true);

        assertThatThrownBy(() -> underTest.signIn(signDTO))
                .isInstanceOf(RestException.class)
                .hasMessageContaining("Userga ma'lum bir cheklovlar yuklangan");
    }

    @Test
    @Order(60)
    public void singInHappyTest() {
        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
        User user = new User(signDTO.getEmail(), signDTO.getPassword());

        userRepositoryBean.save(user);

        assertThat(underTest.signIn(signDTO))
                .satisfies(p -> {
                    assertEquals("Muvvafaqiyatli token yaratildi", p.getMessage());
                });
    }
}

