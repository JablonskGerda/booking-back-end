package lt.sdacademy.booking.beauty.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lt.sdacademy.booking.beauty.models.dto.ServiceDTO;
import lt.sdacademy.booking.beauty.models.entities.ServiceEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceConverterTest {

    private ServiceConverter serviceConverter;
    @BeforeEach
    void setUp() {
        serviceConverter = new ServiceConverter();
    }

    @Test
    void testServiceConvert() {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setTitle("Dažymas");
        serviceEntity.setPrice(BigDecimal.valueOf(40));

        ServiceDTO result = serviceConverter.serviceConvert(serviceEntity);

        assertEquals("Dažymas", result.getServiceTitle());
        assertEquals(BigDecimal.valueOf(40), result.getPrice());
    }

    @Test
    void testServiceConvertList() {
        List<ServiceEntity> serviceEntityList = new ArrayList<>();
        List<ServiceDTO> result = serviceConverter.serviceListConvert(serviceEntityList);
        assertTrue(result.isEmpty());
    }
}
