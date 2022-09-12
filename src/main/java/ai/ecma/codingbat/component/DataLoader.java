package ai.ecma.codingbat.component;

import ai.ecma.codingbat.entity.Role;
import ai.ecma.codingbat.entity.enums.PermissionEnum;
import ai.ecma.codingbat.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import ai.ecma.codingbat.entity.User;
import ai.ecma.codingbat.entity.enums.RoleEnum;
import ai.ecma.codingbat.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(ddlMode, "create")) {

            Role role = new Role();
            role.setName("Admin");
            role.setDescription("Project egasi");
            role.setPermissions(Set.of(PermissionEnum.values()));
            roleRepository.save(role);

            User admin = new User(
                    adminUsername,
                    passwordEncoder.encode(adminPassword));
            admin.setRole(role);
            admin.setEnabled(true);

            userRepository.save(admin);
        }
    }

    //    @Scheduled(fixedDelay = 60_000L)
    @Scheduled(fixedDelay = 6_000L)
    public void sayHello() {
        System.out.println("Say hello");
    }

}
