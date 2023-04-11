package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.CarDao;
import com.pavlenko.kyrylo.model.dto.CarDto;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
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
    private static final String DESCRIPTION_EN = "descriptionEn";
    private static final String DESCRIPTION_UA = "descriptionUa";
    private static final int PAGE_SIZE = 6;
    private final CarDao carDao;
    private final Logger logger = LogManager.getLogger(CarService.class);

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public Car findById(Long id) throws DataBaseException {
        return carDao.findById(id);

    }


    /**
     * Manages catalog loading with filtering and sorting.
     *
     * @param filterParameters Map with filters, that can contains Brand, Quality or nothing.
     * @param pageNum          Integer that indicates current page.
     * @param role             User role that effects output.
     * @throws DataBaseException Indicates that error occurred during database accessing.
     */

    public PaginationInfo getPaginationResultData(
            Map<String, String> filterParameters, int pageNum, String role)
            throws DataBaseException {

        boolean adminRequest = false;
        int offSet = PAGE_SIZE * (pageNum - 1);
        if (role.equals("ADMIN")) {
            adminRequest = true;
        }
        PaginationInfo paginationResultData = carDao.getPaginationResultData(
                filterParameters, PAGE_SIZE, offSet, adminRequest);

        int pagesCount = (int) Math.ceil((double) paginationResultData.getCarsCount() / PAGE_SIZE);
        paginationResultData.setPagesCount(pagesCount);

        return paginationResultData;
    }

    public List<CarStatus> findAllStatuses() throws DataBaseException {
        return carDao.findAllStatuses();
    }

    /**
     * Manages editing of car details.
     *
     * @param label   Indicates what information that is going to be edited.
     * @param input   New information that is going to be edited.
     * @param id      ID of car that is going to be edited.
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
            case DESCRIPTION_EN:
                carDao.editCarDescriptionEn(id, input);
                break;
            case DESCRIPTION_UA:
                carDao.editCarDescriptionUa(id, input);
                break;
            default:
                logger.info("An error occurred");
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
        return carDao.checkStatus(carId) == 1;
    }
}
