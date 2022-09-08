package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.BrandDao;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.util.List;

public class BrandService {

    private final BrandDao brandDao;

    public BrandService(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    public Brand findBrandById(Long id) throws DataBaseException {
        return brandDao.findBrandById(id);
    }

    public List<Brand> findAllBrands() throws DataBaseException {
        return brandDao.findAllBrands();
    }
}
