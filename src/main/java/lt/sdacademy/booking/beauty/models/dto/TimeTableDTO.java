package lt.sdacademy.booking.beauty.models.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TimeTableDTO {
    private LocalDateTime bookedTime;
    private String serviceTitle;
    private String EmployeeNameSurname;
    private Integer ClientTelephone;
}
