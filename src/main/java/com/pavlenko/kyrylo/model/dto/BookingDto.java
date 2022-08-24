package com.pavlenko.kyrylo.model.dto;

import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.User;

import java.util.Objects;

public class BookingDto {
    private User user;
    private Car car;
    private String userDetails;
    private boolean withDriver;
    private String startDate;
    private String endDate;
    private String price;

    public BookingDto(User user, Car car, String userDetails, boolean withDriver, String startDate, String endDate,
                      String price) {
        this.user = user;
        this.car = car;
        this.userDetails = userDetails;
        this.withDriver = withDriver;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCarId(Car car) {
        this.car = car;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public boolean isWithDriver() {
        return withDriver;
    }

    public void setWithDriver(boolean withDriver) {
        this.withDriver = withDriver;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return isWithDriver() == that.isWithDriver() && Objects.equals(getUser(), that.getUser()) && Objects.equals(getCar(), that.getCar()) && Objects.equals(getUserDetails(), that.getUserDetails()) && Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getCar(), getUserDetails(), isWithDriver(), getStartDate(), getEndDate(), getPrice());
    }
}
