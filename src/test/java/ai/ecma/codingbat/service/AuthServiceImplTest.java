package ai.ecma.codingbat.service;

import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.SignDTO;
import ai.ecma.codingbat.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class AuthServiceImplTest {


    @Autowired
    private AuthServiceImpl authServiceImpl;

    @Test
    @Order(100)
    public void signUpHappyTest() {
        SignDTO signDTO = new SignDTO("sirojiddinecma@gmail.com", "root123");

        ApiResult<Boolean> apiResult = authServiceImpl.signUp(signDTO);

        assertTrue(apiResult.isSuccess());
    }


    @Test
    @Order(200)
    public void signUpEmailAlreadyExistsTest() {
        SignDTO signDTO = new SignDTO("sirojiddinecma@gmail.com", "root123");

        assertThrows(RestException.class, () -> authServiceImpl.signUp(signDTO));
    }


}
