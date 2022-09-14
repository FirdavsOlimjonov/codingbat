package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.CurrencyDTO;
import ai.ecma.codingbat.service.implemention.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Integer id) {
        return userService.delete(id);
    }


    @GetMapping("/test")
    public ApiResult<?> getBla() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CurrencyDTO[]> forEntity = restTemplate.getForEntity(
                "https://cbu.uz/oz/arkhiv-kursov-valyut/json/",
                CurrencyDTO[].class);

        CurrencyDTO[] body = forEntity.getBody();

        return ApiResult.successResponse(body);
    }
}
