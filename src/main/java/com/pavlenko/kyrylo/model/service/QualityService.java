package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.QualityClassDao;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.util.List;

public class QualityService {

    private final QualityClassDao qualityClassDao;

    public QualityService(QualityClassDao qualityClassDao) {
        this.qualityClassDao = qualityClassDao;
    }

    public Quality findQualityById(Long id) throws DataBaseException {
        return qualityClassDao.findQualityById(id);
    }

    public List<Quality> findAllQualityClasses() throws DataBaseException {
        return qualityClassDao.findAllQualityClasses();
    }
}
