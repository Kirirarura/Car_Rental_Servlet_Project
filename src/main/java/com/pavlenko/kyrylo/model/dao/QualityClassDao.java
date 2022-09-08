package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.util.List;

public interface QualityClassDao {

    List<Quality> findAllQualityClasses() throws DataBaseException;
    Quality findQualityById(Long id) throws DataBaseException;
}
