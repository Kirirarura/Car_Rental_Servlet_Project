package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.CarDao;
import com.pavlenko.kyrylo.model.dto.CarDto;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.util.PaginationInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Manages business logic related to the car.
 */
public class CarService {

    private static final String PRICE = "price";
    private static final int PAGE_SIZE = 6;
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


    /**
     * Manages catalog loading.
     *
     * @param filterParam Map with filters, that can contains Brand, Quality or nothing.
     * @param role User role that effects output.
     * @throws DataBaseException Indicates that error occurred during database accessing.
     */
    public List<Car> findAllCars(Map<String, String> filterParam, String role) throws DataBaseException {
        List<Car> carList;

        if (role.equals("ADMIN")) {
            carList = carDao.findAllCarsWithFilters(filterParam, true);
        } else {
            carList = carDao.findAllCarsWithFilters(filterParam, false);
        }
        return carList;
    }

    public PaginationInfo getPaginationResultData(
            Map<String, String> filterParameters, int pageNum, String role)
            throws DataBaseException {

        boolean adminRequest = false;
        int offSet = PAGE_SIZE * (pageNum - 1);
        if (role.equals("ADMIN")){
            adminRequest = true;
        }
        PaginationInfo paginationResultData =
                carDao.getPaginationResultData(filterParameters, PAGE_SIZE, offSet, adminRequest);

        int pagesCount = (int) Math.ceil((double) paginationResultData.getCarsCount()  / PAGE_SIZE);
        paginationResultData.setPagesCount(pagesCount);

        return paginationResultData;
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

    /**
     * Manages editing of car details.
     *
     * @param label Indicates what information that is going to be edited.
     * @param input New information that is going to be edited.
     * @param id ID of car that is going to be edited.
     * @param inputID ID of Status, Quality to edit.
     * @throws DataBaseException Indicates that error occurred during database accessing.
     */
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

    public boolean checkCarStatusAvailable(Long carId) throws DataBaseException {
        return carDao.checkCarStatusId(carId) == 1;
    }
}
