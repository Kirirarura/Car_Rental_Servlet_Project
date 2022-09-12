package com.pavlenko.kyrylo.model.builder;

import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.entity.Quality;

import java.math.BigDecimal;

public class CarBuilder {
    private Long id;
    private String modelName;
    private BigDecimal price;
    private Brand brand;
    private Quality qualityClass;
    private CarStatus status;
    private String descriptionEn;
    private String descriptionUa;

    public CarBuilder id(Long id){
        this.id = id;
        return this;
    }
    public CarBuilder modelName(String modelName){
        this.modelName = modelName;
        return this;
    }
    public CarBuilder price(BigDecimal price){
        this.price = price;
        return this;
    }
    public CarBuilder brand(Brand brand){
        this.brand = brand;
        return this;
    }
    public CarBuilder qualityClass(Quality qualityClass){
        this.qualityClass = qualityClass;
        return this;
    }
    public CarBuilder status(CarStatus status){
        this.status = status;
        return this;
    }
    public CarBuilder descriptionEn(String descriptionEn){
        this.descriptionEn = descriptionEn;
        return this;
    }

    public CarBuilder descriptionUa(String descriptionUa){
        this.descriptionUa = descriptionUa;
        return this;
    }

    public Car build(){
        return new Car(
                this.id,
                this.modelName,
                this.price,
                this.brand,
                this.qualityClass,
                this.status,
                this.descriptionEn,
                this.descriptionUa
        );
    }
}