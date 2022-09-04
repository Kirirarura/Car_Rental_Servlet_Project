package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.util.PaginationInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CarDao extends GenericDao<Car>{

    List<Quality> findAllQualityClasses() throws DataBaseException;
    List<CarStatus> findAllStatuses() throws DataBaseException;
    List<Brand> findAllBrands() throws DataBaseException;
    PaginationInfo getPaginationResultData(Map<String, String> filterFieldMap, int limit, int offset, boolean adminRequest) throws DataBaseException;
    List<Car> findAllCarsWithFilters(Map<String, String> filterParam, boolean adminRequest) throws DataBaseException;
    Brand findBrandById(Long carId) throws DataBaseException;
    Quality findQualityById(Long carId) throws DataBaseException;
    void editCarPrice(BigDecimal input, Long carId) throws DataBaseException;
    void editCarDescription(Long carId, String input) throws DataBaseException;
    void editCarStatus(Long carId, Long inputId) throws DataBaseException;
    void editCarQuality(Long carId, Long inputId) throws DataBaseException;
    int checkCarStatusId(Long carId) throws DataBaseException;


}
