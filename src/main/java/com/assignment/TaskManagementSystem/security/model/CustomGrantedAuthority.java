package com.assignment.TaskManagementSystem.security.model;

import com.assignment.TaskManagementSystem.models.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

    private UserRole role;

    public CustomGrantedAuthority(UserRole role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getRole();
    }
}
