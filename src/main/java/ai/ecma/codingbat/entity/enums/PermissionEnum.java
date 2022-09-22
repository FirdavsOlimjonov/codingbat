package ai.ecma.codingbat.entity.enums;


import org.springframework.security.core.GrantedAuthority;

public enum PermissionEnum implements GrantedAuthority {

    ADD_LANGUAGE,
    EDIT_LANGUAGE,
    SOLVE_PROBLEM,
    EDIT_ROLE,
    ADD_ROLE,
    GET_USERS,
    DELETE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
