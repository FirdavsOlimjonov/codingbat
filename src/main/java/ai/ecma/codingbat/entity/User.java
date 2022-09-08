package ai.ecma.codingbat.entity;

import ai.ecma.codingbat.entity.enums.RoleEnum;
import ai.ecma.codingbat.entity.template.AbsIntegerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class User extends AbsIntegerEntity implements UserDetails {
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @ManyToOne
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        accountNonExpired = accountNonLocked = credentialsNonExpired = true;
        enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
