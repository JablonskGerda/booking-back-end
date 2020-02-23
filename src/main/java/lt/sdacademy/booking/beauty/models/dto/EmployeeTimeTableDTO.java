package lt.sdacademy.booking.beauty.models.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeTimeTableDTO {

    private LocalDateTime reservedTime;
    private LocalDateTime freeTime;
}
