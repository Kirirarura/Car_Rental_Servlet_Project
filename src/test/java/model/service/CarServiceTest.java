package model.service;

import com.pavlenko.kyrylo.model.dao.CarDao;
import com.pavlenko.kyrylo.model.dto.CarDto;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class CarServiceTest {

    CarDao carDao = mock(CarDao.class);
    CarService carService = new CarService(carDao);


    private static final Brand CAR_BRAND = new Brand(Brand.BrandEnum.getRandomBrand());
    private static final Quality CAR_QUALITY = new Quality(Quality.QualityEnum.getRandomQuality());
    private static final String CAR_MODEL = "model";
    private static final String CAR_PRICE = "50";
    private static final String CAR_DESCRIPTION = "description";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String QUALITY = "quality";

    private static final CarDto CAR_DTO = new CarDto(
            CAR_BRAND,
            CAR_MODEL,
            CAR_PRICE,
            CAR_QUALITY,
            CAR_DESCRIPTION
            );

    @Test
    void testFindCarById() throws DataBaseException {
        carService.findById(1L);
        verify(carDao, times(1)).findById(1L);
    }

    @Test
    void testFindBrandById() throws DataBaseException {
        carService.findBrandById(1L);
        verify(carDao, times(1)).findBrandById(1L);
    }

    @Test
    void testFindQualityById() throws DataBaseException {
        carService.findQualityById(1L);
        verify(carDao, times(1)).findQualityById(1L);
    }

    @Test
    void testFindAllCarsAdmin() throws DataBaseException {
        Map<String, String> filterParam = new HashMap<>();

        carService.findAllCars(filterParam, ROLE_ADMIN, "", "");
        verify(carDao, times(1)).findAllCarsWithFilters(filterParam, true);
    }

    @Test
    void testFindAllCarsCustomer() throws DataBaseException {
        Map<String, String> filterParam = new HashMap<>();

        carService.findAllCars(filterParam, "", "", "");
        verify(carDao, times(1)).findAllCarsWithFilters(filterParam, false);
    }

    @Test
    void testFindAllQualityClasses() throws DataBaseException {
        carService.findAllQualityClasses();
        verify(carDao, times(1)).findAllQualityClasses();
    }

    @Test
    void testFindAllStatuses() throws DataBaseException {
        carService.findAllStatuses();
        verify(carDao, times(1)).findAllStatuses();
    }

    @Test
    void testFindAllBrands() throws DataBaseException {
        carService.findAllBrands();
        verify(carDao, times(1)).findAllBrands();
    }

    @Test
    void testEditCarInfoPriceCase() throws DataBaseException {
        String price = "50";
        carService.editCarInfo(PRICE, price, 1L, 1L);
        verify(carDao, times(1)).editCarPrice(new BigDecimal(price), 1L);
    }

    @Test
    void testEditCarInfoQualityCase() throws DataBaseException {
        carService.editCarInfo(QUALITY, "NEW", 1L, 1L);
        verify(carDao, times(1)).editCarQuality(1L, 1L);
    }

    @Test
    void testEditCarInfoStatusCase() throws DataBaseException {
        carService.editCarInfo(STATUS, "UNDER_REPAIR", 1L, 1L);
        verify(carDao, times(1)).editCarStatus(1L, 1L);
    }

    @Test
    void testEditCarInfoDescriptionCase() throws DataBaseException {
        String info = "info";
        carService.editCarInfo("", info , 1L, 1L);
        verify(carDao, times(1)).editCarDescription(1L, info);
    }

    @Test
    void testDeleteCar() throws DataBaseException {
        carService.deleteCarById(1L);
        verify(carDao, times(1)).delete(1L);
    }

    @Test
    void testRegisterNewCar() throws DataBaseException {
        carService.registerNewCar(CAR_DTO);
        Car car = new Car(CAR_DTO);
        verify(carDao, times(1)).create(car);
    }


}
