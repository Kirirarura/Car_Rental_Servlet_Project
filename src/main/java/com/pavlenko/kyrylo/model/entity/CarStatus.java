package com.pavlenko.kyrylo.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class CarStatus implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;
    private Long id;
    private CarStatus.CarStatusEnum value;

    public CarStatus() {
    }

    public CarStatus(CarStatusEnum value) {
        this.value = value;
    }

    public CarStatus(Long id, CarStatusEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarStatusEnum getValue() {
        return value;
    }

    public void setValue(CarStatusEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarStatus carStatus = (CarStatus) o;
        return Objects.equals(getId(), carStatus.getId()) && getValue() == carStatus.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public enum CarStatusEnum {
        AVAILABLE,
        RESERVED,
        UNDER_REPAIR
    }
}
