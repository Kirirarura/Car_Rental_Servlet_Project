package com.pavlenko.kyrylo.model.builder;

import com.pavlenko.kyrylo.model.entity.Role;
import com.pavlenko.kyrylo.model.entity.User;

public class UserBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private boolean blocked;

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder firstname(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder isBlocked(int blocked) {
        this.blocked = blocked != 1;
        return this;
    }

    public User build() {
        return new User(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.password,
                this.role,
                this.blocked
        );
    }

}