package ai.ecma.codingbat.service;

import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class AuthServiceImplMockitoTest {


    @InjectMocks
    private AuthServiceImpl authServiceImpl;
    @Mock
    UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    JavaMailSender javaMailSender;


    @Test
    @Order(100)
    public void signUpHappyTest() {
        SignDTO signDTO = new SignDTO("sirojiddinecma@gmail.com", "root123");

        User user = new User(signDTO.getEmail(), signDTO.getPassword());

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        ApiResult<Boolean> apiResult = authServiceImpl.signUp(signDTO);

        assertTrue(apiResult.isSuccess());
    }

}
