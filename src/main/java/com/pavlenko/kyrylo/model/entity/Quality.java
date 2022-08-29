package com.pavlenko.kyrylo.model.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Quality implements Serializable {

    private Long id;
    private Quality.QualityEnum value;

    public Quality() {
    }

    public Quality(QualityEnum value) {
        this.value = value;
    }

    public Quality(Long id, QualityEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QualityEnum getValue() {
        return value;
    }

    public void setValue(QualityEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quality quality = (Quality) o;
        return Objects.equals(getId(), quality.getId()) && getValue() == quality.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public enum QualityEnum {
        NEW,
        NORMAL,
        OLD;

        public static Quality.QualityEnum getRandomQuality(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
}
