package lt.sdacademy.booking.beauty.converters;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import lt.sdacademy.booking.beauty.models.dto.PersonDTO;
import lt.sdacademy.booking.beauty.models.entities.PersonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonConverterTest {

    private PersonConverter personConverter;

    @BeforeEach
    void setUp() {
        personConverter = new PersonConverter();
    }

    @Test
    void convert_passingPersonAsNull() {
        PersonDTO result = personConverter.personConvert(null);
        assertNull(result);
    }

    @Test
    void convert_emptyList() {
        List<PersonEntity> persons = new ArrayList<>();
        List<PersonDTO> result = personConverter.personListConvert(persons);
        assertTrue(result.isEmpty());
    }

    @Test
    void personCovert() {

    }

}
