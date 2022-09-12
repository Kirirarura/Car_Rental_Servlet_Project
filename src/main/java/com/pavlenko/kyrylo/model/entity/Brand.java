package com.pavlenko.kyrylo.model.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Brand implements Serializable {

    private Long id;
    private Brand.BrandEnum value;

    public Brand() {
    }

    public Brand(BrandEnum value) {
        this.value = value;
    }

    public Brand(Long id, BrandEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BrandEnum getValue() {
        return value;
    }

    public void setValue(BrandEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(getId(), brand.getId()) && Objects.equals(getValue(), brand.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public enum BrandEnum {
        Nissan,
        Mercedes,
        Hyundai,
        Honda,
        Tesla,
        Audi,
        Bentley,
        BMW;

        public static Brand.BrandEnum getRandomBrand() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
}
