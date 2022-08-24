package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.CarDao;
import com.pavlenko.kyrylo.model.dto.CarDto;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarService {

    private static final String PRICE = "price";
    private static final String NAME = "name";
    private static final String DESC = "desc";
    private final CarDao carDao;
    private final Logger logger = LogManager.getLogger(CarService.class);

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public Car findById(Long id) throws DataBaseException {
        return carDao.findById(id);

    }

    public Brand findBrandById(Long id) throws DataBaseException {
        return carDao.findBrandById(id);
    }

    public Quality findQualityById(Long id) throws DataBaseException {
        return carDao.findQualityById(id);
    }

    public List<Car> findAllCars(Map<String, String> filterParam, String role, String sort, String order) throws DataBaseException {
        List<Car> carList;

        if (role.equals("ADMIN")) {
            carList = carDao.findAllCarsWithFilters(filterParam, true);
        } else {
            carList = carDao.findAllCarsWithFilters(filterParam, false);
        }
        return sortedCarList(carList, sort, order);
    }

    public List<Quality> findAllQualityClasses() throws DataBaseException {
        return carDao.findAllQualityClasses();
    }

    public List<CarStatus> findAllStatuses() throws DataBaseException {
        return carDao.findAllStatuses();
    }

    public List<Brand> findAllBrands() throws DataBaseException {
        return carDao.findAllBrands();
    }

    public void editCarInfo(String label, String input, Long id, Long inputID) throws DataBaseException {
        switch (label) {
            case "status":
                carDao.editCarStatus(id, inputID);
                break;
            case "quality":
                carDao.editCarQuality(id, inputID);
                break;
            case PRICE:
                BigDecimal bigDecimalInput = new BigDecimal(input);
                carDao.editCarPrice(bigDecimalInput, id);
                break;
            default:
                carDao.editCarDescription(id, input);
                break;
        }
        logger.info("Car with id {} has been edited...", id);
    }

    public void deleteCarById(Long id) throws DataBaseException {
        carDao.delete(id);
        logger.info("Car with id {} has been deleted...", id);
    }


    public void registerNewCar(CarDto carDto) throws DataBaseException {
        Car car = new Car(carDto);
        carDao.create(car);
        logger.info("New car was added...");
    }

    private List<Car> sortedCarList(List<Car> input, String sort, String order) {
        List<Car> carList;
        if (sort.equals(PRICE)) {
            if (order.equals(DESC)) {
                carList = input.stream()
                        .sorted(Comparator.comparing(Car::getPrice).reversed())
                        .collect(Collectors.toList());
            } else {
                carList = input.stream()
                        .sorted(Comparator.comparing(Car::getPrice))
                        .collect(Collectors.toList());
            }
        } else if (sort.equals(NAME)) {
            if (order.equals(DESC)) {
                carList = input.stream()
                        .sorted(Comparator.comparing(Car::getModelName).reversed())
                        .collect(Collectors.toList());
            } else {
                carList = input.stream()
                        .sorted(Comparator.comparing(Car::getModelName))
                        .collect(Collectors.toList());
            }
        } else {
            if (order.equals(DESC)) {
                carList = input.stream()
                        .sorted(Comparator.comparing(Car::getCarId).reversed())
                        .collect(Collectors.toList());
            } else {
                carList = input.stream()
                        .sorted(Comparator.comparing(Car::getCarId))
                        .collect(Collectors.toList());
            }
        }
        return carList;
    }

    public boolean checkCarStatusAvailable(Long carId) throws DataBaseException {
        return carDao.checkCarStatusId(carId) == 1;
    }
}
