package com.pavlenko.kyrylo.model.entity;

import java.util.Objects;

public class BookingStatus {
    private Long id;
    private BookingStatus.BookingEnum value;

    public BookingStatus() {
    }

    public BookingStatus(BookingEnum value) {
        this.value = value;
    }

    public BookingStatus(Long id, BookingEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingEnum getValue() {
        return value;
    }

    public void setValue(BookingEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingStatus that = (BookingStatus) o;
        return Objects.equals(getId(), that.getId()) && getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public enum BookingEnum{
        PENDING_REVIEW,
        ON_REVIEW,
        ACTIVE,
        FINISHED,
        DECLINED,
        TERMINATED
    }
}
