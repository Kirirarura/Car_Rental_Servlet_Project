package com.pavlenko.kyrylo.model.dto;

import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Quality;

import java.util.Objects;

public class CarDto {
    private Brand brand;
    private String model;
    private String price;
    private Quality qualityClass;
    private String description;

    public CarDto() {
    }

    public CarDto(Brand brand, String model, String price, Quality qualityClass, String description) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qualityClass = qualityClass;
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Quality getQualityClass() {
        return qualityClass;
    }

    public void setQualityClass(Quality qualityClass) {
        this.qualityClass = qualityClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return Objects.equals(getBrand(), carDto.getBrand()) && Objects.equals(getModel(), carDto.getModel()) && Objects.equals(getPrice(), carDto.getPrice()) && Objects.equals(getQualityClass(), carDto.getQualityClass()) && Objects.equals(getDescription(), carDto.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getModel(), getPrice(), getQualityClass(), getDescription());
    }
}
