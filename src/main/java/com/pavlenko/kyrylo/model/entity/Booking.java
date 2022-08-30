package com.pavlenko.kyrylo.model.entity;

import com.pavlenko.kyrylo.model.dto.BookingDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Booking implements Serializable {
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

    public Booking() {
    }

    public Booking(BookingDto bookingDto){
        this.user = bookingDto.getUser();
        this.car = bookingDto.getCar();
        this.userDetails = bookingDto.getUserDetails();
        this.withDriver = bookingDto.isWithDriver();
        this.startDate = LocalDate.parse(bookingDto.getStartDate());
        this.endDate = LocalDate.parse(bookingDto.getEndDate());
        this.price = calculatePrice(new BigDecimal(bookingDto.getPrice()), LocalDate.parse(bookingDto.getStartDate()),
                LocalDate.parse(bookingDto.getEndDate()), bookingDto.isWithDriver());
    }

    public Booking(Long id, User user, BookingStatus bookingStatus, Car car, String userDetails,
                   boolean withDriver, LocalDate startDate, LocalDate endDate,
                   BigDecimal price, String declineInfo, BigDecimal additionalFee) {
        this.id = id;
        this.user = user;
        this.bookingStatus = bookingStatus;
        this.car = car;
        this.userDetails = userDetails;
        this.withDriver = withDriver;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.declineInfo = declineInfo;
        this.additionalFee = additionalFee;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public static Booking.BookingBuilder builder() {
        return new BookingBuilder();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDeclineInfo() {
        return declineInfo;
    }

    public void setDeclineInfo(String declineInfo) {
        this.declineInfo = declineInfo;
    }

    public BigDecimal getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(BigDecimal additionalFee) {
        this.additionalFee = additionalFee;
    }

    public static class BookingBuilder {
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
        public BookingBuilder price(BigDecimal price){
            this.price = price;
            return this;
        }
        public BookingBuilder additionalFee(BigDecimal fee){
            this.additionalFee = fee;
            return this;
        }
        public BookingBuilder declineInfo(String declineInfo){
            this.declineInfo = declineInfo;
            return this;
        }

        public Booking build(){
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

    private BigDecimal calculatePrice(BigDecimal price, LocalDate startDate, LocalDate endDate, boolean withDriver){
        Period period = Period.between(startDate, endDate);
        BigDecimal days = new BigDecimal(period.getDays());

        BigDecimal result = price.multiply(days);
        if (withDriver) {
            result = result.add(days.multiply(BigDecimal.valueOf(10)));
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return isWithDriver() == booking.isWithDriver() && Objects.equals(getId(), booking.getId()) && Objects.equals(getUser(), booking.getUser()) && Objects.equals(getBookingStatus(), booking.getBookingStatus()) && Objects.equals(getCar(), booking.getCar()) && Objects.equals(getUserDetails(), booking.getUserDetails()) && Objects.equals(getStartDate(), booking.getStartDate()) && Objects.equals(getEndDate(), booking.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getBookingStatus(), getCar(), getUserDetails(), isWithDriver(), getStartDate(), getEndDate());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", bookingStatus=" + bookingStatus +
                ", car=" + car +
                ", userDetails='" + userDetails + '\'' +
                ", withDriver=" + withDriver +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}
