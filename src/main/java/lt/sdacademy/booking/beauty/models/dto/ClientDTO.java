package lt.sdacademy.booking.beauty.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDTO {

    private String name;
    private String surname;
    private Integer telephone;
}
