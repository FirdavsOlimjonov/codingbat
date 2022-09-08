package ai.ecma.codingbat.entity.enums;


import org.springframework.security.core.GrantedAuthority;

public enum PermissionEnum implements GrantedAuthority {

    ADD_LANGUAGE,
    EDIT_LANGUAGE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
