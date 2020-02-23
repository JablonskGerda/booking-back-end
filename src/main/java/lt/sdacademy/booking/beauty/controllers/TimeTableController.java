package lt.sdacademy.booking.beauty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import lt.sdacademy.booking.beauty.models.dto.FreeHoursDTO;
import lt.sdacademy.booking.beauty.models.dto.TimeTableDTO;
import lt.sdacademy.booking.beauty.models.entities.EmployeeEntity;
import lt.sdacademy.booking.beauty.models.entities.TimeTableEntity;
import lt.sdacademy.booking.beauty.services.EmployeeService;
import lt.sdacademy.booking.beauty.services.TimeTableService;

@CrossOrigin
@RestController
@RequestMapping("/api/timetable")
public class TimeTableController {

    private final TimeTableService timeTableService;
    private final EmployeeService employeeService;

    @Autowired
    public TimeTableController(TimeTableService timeTableService, EmployeeService employeeService) {
        this.timeTableService = timeTableService;
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<TimeTableDTO> timeTableControl() {
        return timeTableService.getAll();
    }

    @GetMapping
    public List<TimeTableDTO> timeTableByEmployeeControl(@RequestParam String name, @RequestParam String surname) {
        return timeTableService.getByEmployee(employeeService.getOneByNameSurname(name, surname));
    }

    @GetMapping("/day")
    public List<String> timeTableByEmployeeDateControl(@RequestParam String name, @RequestParam String surname, @RequestParam("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return timeTableService.getByEmployeeDate(employeeService.getOneByNameSurname(name, surname), date);
    }

    @PostMapping("/save_booking")
    public TimeTableEntity create(@RequestBody TimeTableDTO timeTableDTO) {
        return timeTableService.save(timeTableDTO);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody TimeTableDTO timeTableDTO) {
        return timeTableService.save(timeTableDTO) != null ?
                ResponseEntity.status(HttpStatus.OK).body(true) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }
}
