package ai.ecma.codingbat.entity;

import ai.ecma.codingbat.entity.enums.PermissionEnum;
import ai.ecma.codingbat.entity.template.AbsIntegerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role extends AbsIntegerEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private Set<PermissionEnum> permissions;

    @OneToMany(mappedBy = "role")
    private List<User> users;



}
