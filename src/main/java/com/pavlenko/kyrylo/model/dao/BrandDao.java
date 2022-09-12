package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.util.List;

public interface BrandDao {
    Brand findBrandById(Long id) throws DataBaseException;

    List<Brand> findAllBrands() throws DataBaseException;
}
