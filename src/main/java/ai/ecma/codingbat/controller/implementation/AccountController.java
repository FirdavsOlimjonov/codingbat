package ai.ecma.codingbat.controller.implementation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @PreAuthorize(value = "hasAuthority('GET_SALARY')")
    @GetMapping("/salary")
    public String getSalary() {
        return "SALARY";
    }

    @PreAuthorize(value = "hasAuthority('GIVE_SALARY')")
    @PostMapping("/salary")
    public String giveSalary() {
        return "SALARY";
    }


    @PreAuthorize(value = "hasAuthority('GET_FOOD')")
    @GetMapping("/food")
    public String getFood() {
        System.out.println("FOOD ga keldi");
        return "FOOD";
    }


    @PreAuthorize(value = "hasAuthority('GIVE_FOOD')")
    @PostMapping("/food")
    public String giveFood() {
        return "FOOD";
    }
}
