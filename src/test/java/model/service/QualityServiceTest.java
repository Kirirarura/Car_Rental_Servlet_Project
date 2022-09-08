package model.service;

import com.pavlenko.kyrylo.model.dao.QualityClassDao;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.QualityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QualityServiceTest {

    QualityClassDao qualityClassDao = Mockito.mock(QualityClassDao.class);
    QualityService qualityService = new QualityService(qualityClassDao);

    @Test
    void testFindAllQualityClasses() throws DataBaseException {
        qualityService.findAllQualityClasses();
        verify(qualityClassDao, times(1)).findAllQualityClasses();
    }

    @Test
    void testFindQualityById() throws DataBaseException {
        qualityService.findQualityById(1L);
        verify(qualityClassDao, times(1)).findQualityById(1L);
    }
}
