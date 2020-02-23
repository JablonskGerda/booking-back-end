package lt.sdacademy.booking.beauty.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lt.sdacademy.booking.beauty.models.dto.FreeHoursDTO;
import lt.sdacademy.booking.beauty.models.dto.TimeTableDTO;
import lt.sdacademy.booking.beauty.models.entities.ClientEntity;
import lt.sdacademy.booking.beauty.models.entities.EmployeeEntity;
import lt.sdacademy.booking.beauty.models.entities.ServiceEntity;
import lt.sdacademy.booking.beauty.models.entities.TimeTableEntity;
import lt.sdacademy.booking.beauty.services.ClientService;
import lt.sdacademy.booking.beauty.services.EmployeeService;
import lt.sdacademy.booking.beauty.services.ServiceService;

@Component
public class TimeTableConverter {

    private final ServiceService serviceService;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    @Autowired
    public TimeTableConverter(ServiceService serviceService, EmployeeService employeeService, ClientService clientService) {
        this.serviceService = serviceService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    public TimeTableDTO timeTableConvert(TimeTableEntity timeTableEntity) {
        TimeTableDTO timeTableDTO = new TimeTableDTO(
                timeTableEntity.getBookedTime(),
                timeTableEntity.getServiceEntity().getTitle(),
                timeTableEntity.getEmployeeEntity()
                               .getPerson()
                               .getName()
                               .concat(" " + timeTableEntity.getEmployeeEntity().getPerson().getSurname()),
                timeTableEntity.getClientEntity().getTelephone()
        );
        return timeTableDTO;
    }

    public TimeTableEntity timeTableConvertEntity(TimeTableDTO timeTableDTO) {
        LocalDateTime bookedTime;
        ServiceEntity serviceEntity;
        EmployeeEntity employeeEntity;
        ClientEntity clientEntity;
        bookedTime = timeTableDTO.getBookedTime();
        serviceEntity = this.serviceService.getOneByTitle(timeTableDTO.getServiceTitle());
        String[] temp = timeTableDTO.getEmployeeNameSurname().split(" ");
        String name = temp[0];
        String surname = temp[1];
        employeeEntity = this.employeeService.getOneByNameSurname(name, surname);
        clientEntity = this.clientService.getOneByTelephoneEntity(timeTableDTO.getClientTelephone());

        TimeTableEntity timeTableEntity = new TimeTableEntity(
                bookedTime, serviceEntity, employeeEntity, clientEntity);
        return timeTableEntity;
    }

    public List<TimeTableDTO> timeTableListConvert(List<TimeTableEntity> timeTableEntityList) {
        List<TimeTableDTO> timeTableDTOList = new ArrayList<>();
        for (int i = 0; i < timeTableEntityList.size(); i++) {
            timeTableDTOList.add(timeTableConvert(timeTableEntityList.get(i)));
        }
        return timeTableDTOList;
    }

    public List<String> timeTableFreeHoursConvert(List<TimeTableEntity> timeTableEntityList, LocalDate wantedDate) {
        List<FreeHoursDTO> freeHoursDTOList = new ArrayList<>();
        List<LocalTime> possibleHours = new ArrayList<>();
        List<LocalTime> bookedHours = new ArrayList<>();
        for (int i = 0; i < timeTableEntityList.size(); i++) {
            if (timeTableEntityList.get(i).getBookedTime().getYear() == wantedDate.getYear()) {
                if (timeTableEntityList.get(i).getBookedTime().getMonth() == wantedDate.getMonth()) {
                    if (timeTableEntityList.get(i).getBookedTime().getDayOfMonth() == wantedDate.getDayOfMonth()) {
                        bookedHours.add(LocalTime.of(timeTableEntityList.get(i).getBookedTime().getHour(), 0));
                    }
                }
            }
        }
        if (bookedHours.size() > 0) {
            possibleHours = this.calculatePossibleHours(bookedHours);
        } else {
            possibleHours = this.allPossibleHours();
        }
        return this.convertHoursDTO(possibleHours);
    }

    public List<String> convertHoursDTO(List<LocalTime> hoursList) {
        List<FreeHoursDTO> freeHoursDTOList = new ArrayList<>();
        List<String> freeHoursStr = new ArrayList<>();
        for (int i = 0; i < hoursList.size(); i++) {
            String temp = hoursList.get(i).toString();
            freeHoursStr.add(temp);
        }
        return freeHoursStr;
    }

    public List<LocalTime> calculatePossibleHours(List<LocalTime> bookedHours) {
        List<LocalTime> possibleHours = this.allPossibleHours();
        for (int i = 0; i < possibleHours.size(); i++) {
            for (int j = 0; j < bookedHours.size(); j++) {
                if (possibleHours.get(i) == bookedHours.get(j)) {
                    possibleHours.remove(i);
                }
            }
        }
        return possibleHours;
    }

    public List<LocalTime> allPossibleHours() {
        List<LocalTime> possibleHoursList = new ArrayList<>();
        for (int i = 8; i <= 19; i++) {
            possibleHoursList.add(LocalTime.of(i, 0));
        }
        return possibleHoursList;
    }
}
