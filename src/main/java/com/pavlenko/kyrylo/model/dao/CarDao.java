package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.util.PaginationInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CarDao extends GenericDao<Car> {

    List<CarStatus> findAllStatuses() throws DataBaseException;

    PaginationInfo getPaginationResultData(Map<String, String> filterFieldMap, int limit, int offset, boolean adminRequest) throws DataBaseException;

    void editCarPrice(BigDecimal input, Long carId) throws DataBaseException;

    void editCarDescriptionEn(Long carId, String input) throws DataBaseException;

    void editCarDescriptionUa(Long carId, String input) throws DataBaseException;

    void editCarStatus(Long carId, Long inputId) throws DataBaseException;

    void editCarQuality(Long carId, Long inputId) throws DataBaseException;

    int checkStatus(Long carId) throws DataBaseException;


}
