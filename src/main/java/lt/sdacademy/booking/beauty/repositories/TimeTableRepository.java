package lt.sdacademy.booking.beauty.repositories;

import org.springframework.data.repository.Repository;
import java.util.List;

import lt.sdacademy.booking.beauty.models.entities.EmployeeEntity;
import lt.sdacademy.booking.beauty.models.entities.TimeTableEntity;

public interface TimeTableRepository extends Repository<TimeTableEntity, Integer> {

    List<TimeTableEntity> findAll();

    TimeTableEntity save(TimeTableEntity timeTableEntity);

    List<TimeTableEntity> findAllByEmployeeEntity(EmployeeEntity employeeEntity);
}
