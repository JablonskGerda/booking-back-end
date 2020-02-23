package lt.sdacademy.booking.beauty.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import java.time.LocalDate;
import lt.sdacademy.booking.beauty.models.dto.PersonDTO;
import org.junit.jupiter.api.Test;

class PersonControllerTest extends AbstractIT {

    @Test
    void updatePerson() throws Exception {
        Integer id = 2;
        String name = "Gerda";
        String surname = "Jablonskiene";
        LocalDate birthday = LocalDate.of(1986, 07, 14);
        PersonDTO personDTO = sendGet("/api/persons/" + id, new TypeReference<PersonDTO>() {
        });
        assertEquals(id, personDTO.getId());
        assertNotEquals(name, personDTO.getName());
        assertNotEquals(surname, personDTO.getSurname());
        PersonDTO personUpdate = new PersonDTO(
            id,
            name,
            surname,
            birthday
        );

        PersonDTO result = sendPost("/api/persons", personUpdate, new TypeReference<PersonDTO>() {
        });

        assertEquals(name, result.getName());
        assertEquals(surname, result.getSurname());
        assertEquals(birthday, result.getBirthday());

        result = sendGet("/api/persons/" + id, new TypeReference<PersonDTO>() {
        });

        assertEquals(name, result.getName());
        assertEquals(surname, result.getSurname());
        assertEquals(birthday, result.getBirthday());
    }
}
