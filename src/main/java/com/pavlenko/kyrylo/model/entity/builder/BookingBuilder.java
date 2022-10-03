package com.pavlenko.kyrylo.model.entity.builder;

import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.entity.BookingStatus;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingBuilder {
    private Long id;
    private User user;
    private BookingStatus bookingStatus;
    private Car car;
    private String userDetails;
    private boolean withDriver;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private String declineInfo;
    private BigDecimal additionalFee;

    public BookingBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public BookingBuilder user(User user) {
        this.user = user;
        return this;
    }

    public BookingBuilder bookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }

    public BookingBuilder car(Car car) {
        this.car = car;
        return this;
    }

    public BookingBuilder userDetails(String userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    public BookingBuilder withDriver(int withDriver) {
        this.withDriver = withDriver != 1;
        return this;
    }

    public BookingBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public BookingBuilder endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public BookingBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BookingBuilder additionalFee(BigDecimal fee) {
        this.additionalFee = fee;
        return this;
    }

    public BookingBuilder declineInfo(String declineInfo) {
        this.declineInfo = declineInfo;
        return this;
    }

    public Booking build() {
        return new Booking(
                this.id,
                this.user,
                this.bookingStatus,
                this.car,
                this.userDetails,
                this.withDriver,
                this.startDate,
                this.endDate,
                this.price,
                this.declineInfo,
                this.additionalFee
        );
    }
}