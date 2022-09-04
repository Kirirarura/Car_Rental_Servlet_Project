package com.pavlenko.kyrylo.model.service.util;

import com.pavlenko.kyrylo.model.entity.Car;

import java.util.List;

public class PaginationInfo {

    private List<Car> carListPage;
    private int carsCount;
    private int pagesCount;

    public PaginationInfo() {
    }

    public PaginationInfo(List<Car> carList, int carsCount, int pagesCount) {
        this.carListPage = carList;
        this.carsCount = carsCount;
        this.pagesCount = pagesCount;
    }

    public List<Car> getCarListPage() {
        return carListPage;
    }

    public void setCarListPage(List<Car> carListPage) {
        this.carListPage = carListPage;
    }

    public int getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(int carsCount) {
        this.carsCount = carsCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }
}
