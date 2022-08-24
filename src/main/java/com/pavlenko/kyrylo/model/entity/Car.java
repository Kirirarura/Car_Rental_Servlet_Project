package com.pavlenko.kyrylo.model.entity;

import com.pavlenko.kyrylo.model.dto.CarDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Car implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;
    private Long carId;
    private String modelName;
    private BigDecimal price;
    private Brand brand;
    private Quality qualityClass;
    private CarStatus status;
    private String description;
    public Car() {
    }

    public Car(Long carId, String modelName, BigDecimal price, Brand brand, Quality qualityClass, CarStatus status, String description) {
        this.carId = carId;
        this.modelName = modelName;
        this.price = price;
        this.brand = brand;
        this.qualityClass = qualityClass;
        this.status = status;
        this.description = description;
    }

    public Car(CarDto carDto){
        this.brand = carDto.getBrand();
        this.modelName = carDto.getModel();
        this.price = new BigDecimal(carDto.getPrice());
        this.qualityClass = carDto.getQualityClass();
        this.description = carDto.getDescription();
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Quality getQualityClass() {
        return qualityClass;
    }

    public void setQualityClass(Quality qualityClass) {
        this.qualityClass = qualityClass;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Car.CarBuilder builder() {
        return new CarBuilder();
    }
    public static class CarBuilder {
        private Long id;
        private String modelName;
        private BigDecimal price;
        private Brand brand;
        private Quality qualityClass;
        private CarStatus status;
        private String description;

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
        public CarBuilder description(String description){
            this.description = description;
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
                    this.description
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getCarId(), car.getCarId()) && Objects.equals(getModelName(), car.getModelName()) && Objects.equals(getPrice(), car.getPrice()) && Objects.equals(getBrand(), car.getBrand()) && Objects.equals(getQualityClass(), car.getQualityClass()) && Objects.equals(getStatus(), car.getStatus()) && Objects.equals(getDescription(), car.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId(), getModelName(), getPrice(), getBrand(), getQualityClass(), getStatus(), getDescription());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + carId +
                ", modelName='" + modelName + '\'' +
                ", price=" + price +
                ", brand=" + brand +
                ", qualityClass=" + qualityClass +
                ", status=" + status +
                '}';
    }
}
