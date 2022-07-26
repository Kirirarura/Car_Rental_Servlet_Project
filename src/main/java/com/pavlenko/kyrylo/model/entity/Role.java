package com.pavlenko.kyrylo.model.entity;

import java.util.Objects;

public class Role {

    private Byte id;
    private RoleEnum value;

    public Role() {
    }

    public Role(RoleEnum role) {
        this.value = role;
    }

    public Role(Byte id, RoleEnum role) {
        this.id = id;
        this.value = role;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public RoleEnum getValue() {
        return value;
    }

    public void setValue(RoleEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return value == role.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public enum RoleEnum {
        GUEST,
        CUSTOMER,
        MANAGER,
        ADMIN
    }
}
