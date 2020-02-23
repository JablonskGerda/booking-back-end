package lt.sdacademy.booking.beauty.converters;

import lt.sdacademy.booking.beauty.models.dto.EmployeeDTO;
import lt.sdacademy.booking.beauty.models.dto.EmployeePriceDTO;
import lt.sdacademy.booking.beauty.models.entities.EmployeeEntity;
import lt.sdacademy.booking.beauty.models.entities.ServiceEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class EmployeeConverter {

    public List<EmployeeDTO> employeeListConvert(List<EmployeeEntity> employeeEntityList) {
        return employeeEntityList.stream().map(this::employeeConvert).collect(toList());
    }

    public EmployeeDTO employeeConvert(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeEntity.getPerson().getName(), employeeEntity.getPerson().getSurname(),
                employeeEntity.getServiceEntityList().stream().
                        map(s -> s.getTitle().concat(" " + s.getPrice()
                                .multiply(employeeEntity.getCoefficient()).setScale(2, RoundingMode.HALF_UP))).collect(toList()));
        return employeeDTO;
    }

    public EmployeePriceDTO employeePriceConvert(EmployeeEntity employeeEntity) {
        Map<String, BigDecimal> employeeServicePrice = employeeEntity.getServiceEntityList().stream()
                .collect(Collectors.toMap(ServiceEntity::getTitle, ServiceEntity::getPrice));

        employeeServicePrice.replaceAll((key, oldPrice)
                -> (oldPrice.multiply(employeeEntity.getCoefficient()).setScale(2, RoundingMode.HALF_UP)));
        EmployeePriceDTO employeePriceDTO = new EmployeePriceDTO(employeeEntity.getPerson().getName(), employeeEntity.getPerson().getSurname(),
                employeeServicePrice);
        return employeePriceDTO;
    }

    public List<EmployeePriceDTO> employeePriceListConvert(List<EmployeeEntity> employeeEntityList) {
        return employeeEntityList.stream().map(this::employeePriceConvert).collect(toList());
    }
}
