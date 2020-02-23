package lt.sdacademy.booking.beauty.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class EmployeePriceDTO {

    private String name;
    private String surname;
    private Map<String, BigDecimal> servicePriceByEmployee;
}
