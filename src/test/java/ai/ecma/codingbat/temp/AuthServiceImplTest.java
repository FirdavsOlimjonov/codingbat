package ai.ecma.codingbat.temp;

import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.repository.UserRepository;
import ai.ecma.codingbat.service.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl underTest;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private  JavaMailSender javaMailSende;

    @Captor
    ArgumentCaptor<User> argumentCaptor;


    @BeforeEach
    void setUp(){
        underTest = new AuthServiceImpl(userRepository, passwordEncoder, javaMailSende);
    }

    @Test
    public void singUpHappyTest(){
        SignDTO signDTO = new SignDTO("sirojiddinecma@gmail.com", "root123");

        when(userRepository.existsByEmail(signDTO.getEmail())).thenReturn(false);


    }
}
