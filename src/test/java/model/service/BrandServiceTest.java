package model.service;

import com.pavlenko.kyrylo.model.dao.BrandDao;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BrandService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BrandServiceTest {

    BrandDao brandDao = Mockito.mock(BrandDao.class);
    BrandService brandService = new BrandService(brandDao);

    @Test
    void testFindAllBrands() throws DataBaseException {
        brandService.findAllBrands();
        verify(brandDao, times(1)).findAllBrands();
    }

    @Test
    void testFindBrandById() throws DataBaseException {
        brandService.findBrandById(1L);
        verify(brandDao, times(1)).findBrandById(1L);
    }
}
