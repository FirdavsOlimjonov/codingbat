//package ai.ecma.codingbat.controller;
//
//import ai.ecma.codingbat.controller.cotract.AuthController;
//import ai.ecma.codingbat.entity.User;
//import ai.ecma.codingbat.payload.ApiResult;
//import ai.ecma.codingbat.payload.SignDTO;
//import ai.ecma.codingbat.payload.TokenDTO;
//import ai.ecma.codingbat.repository.UserRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class AuthControllerTest {
//
//    @Autowired
//    private AuthController authController;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void signUpHappyTest(){
//        SignDTO signDTO = new SignDTO("firdavsolimjonov25@gmail.com", "root123");
//
//        ApiResult<Boolean> apiResult = authController.signUp(signDTO);
//
//        assertTrue(apiResult.isSuccess());
//    }
//
//    @Test
//    public void signInHappyTest(){
//        SignDTO signDTO = new SignDTO("admin@codingbat.com", "root123");
//
//        ApiResult<TokenDTO> apiResult = authController.signIn(signDTO);
//
//        assertTrue(apiResult.isSuccess());
//        assertNotNull(apiResult.getData());
//    }
//
//    @Test
//    public void verifiedEmailTest(){
//        SignDTO signDTO = new SignDTO("firdavs@gmail.com", "root123");
//        User user = new User(signDTO.getEmail(), signDTO.getPassword());
//        userRepository.save(user);
//
//        ApiResult<?> apiResult = authController.verificationEmail(user.getEmail());
//
//        assertTrue(apiResult.isSuccess());
//        assertEquals("Tasdiqlandi",apiResult.getMessage());
//    }
//}
