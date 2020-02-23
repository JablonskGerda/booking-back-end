package lt.sdacademy.booking.beauty.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lt.sdacademy.booking.beauty.converters.TimeTableConverter;
import lt.sdacademy.booking.beauty.models.dto.FreeHoursDTO;
import lt.sdacademy.booking.beauty.models.dto.TimeTableDTO;
import lt.sdacademy.booking.beauty.models.entities.EmployeeEntity;
import lt.sdacademy.booking.beauty.models.entities.TimeTableEntity;
import lt.sdacademy.booking.beauty.repositories.TimeTableRepository;

@Service
public class TimeTableService {

    private TimeTableRepository timeTableRepository;
    private TimeTableConverter timeTableConverter;

    public TimeTableService(TimeTableRepository timeTableRepository, TimeTableConverter timeTableConverter) {
        this.timeTableRepository = timeTableRepository;
        this.timeTableConverter = timeTableConverter;
    }

    public TimeTableEntity save(TimeTableDTO timeTableDTO){
        TimeTableEntity timeTableEntity = timeTableConverter.timeTableConvertEntity(timeTableDTO);
        return timeTableRepository.save(timeTableEntity);
    }

    public List<TimeTableDTO> getAll() {
        List<TimeTableEntity> timeTableEntityList = timeTableRepository.findAll();
        List<TimeTableDTO> timeTableDTOList = timeTableConverter.timeTableListConvert(timeTableEntityList);
        return timeTableDTOList;
    }

    public List<TimeTableDTO> getByEmployee(EmployeeEntity employeeEntity) {
        List<TimeTableEntity> timeTableEntityList = timeTableRepository.findAllByEmployeeEntity(employeeEntity);
        List<TimeTableDTO> timeTableDTOList = timeTableConverter.timeTableListConvert(timeTableEntityList);
        return timeTableDTOList;
    }

    public List<String> getByEmployeeDate(EmployeeEntity employeeEntity, LocalDate wantedDate) {
        List<TimeTableEntity> timeTableEntityList = timeTableRepository.findAllByEmployeeEntity(employeeEntity);
        List<String> freeHoursDTOList =  timeTableConverter.timeTableFreeHoursConvert(timeTableEntityList, wantedDate);
        return freeHoursDTOList;
    }
}
