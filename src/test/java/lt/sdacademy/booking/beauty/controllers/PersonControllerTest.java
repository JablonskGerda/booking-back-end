package lt.sdacademy.booking.beauty.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import lt.sdacademy.booking.beauty.converters.PersonConverter;
import lt.sdacademy.booking.beauty.models.dto.PersonDTO;
import lt.sdacademy.booking.beauty.models.entities.PersonEntity;
import lt.sdacademy.booking.beauty.repositories.PersonRepository;
import lt.sdacademy.booking.beauty.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private PersonRepository personRepository;

    private PersonController personController;

    @BeforeEach
    void setUp() {
        PersonService personService = new PersonService(personRepository, new PersonConverter());
        personController = new PersonController(personService);
    }

    @Test
    void getPerson() {
        PersonEntity person = PersonEntity.builder()
            .name("Gerda")
            .surname("Jablonskiene")
            .birthday(LocalDate.now())
            .build();
        when(personRepository.findOneById(5)).thenReturn(person);

        PersonDTO result = personController.getPerson(5);

        assertEquals("Gerda", result.getName());
        assertEquals("Jablonskiene", result.getSurname());
    }
}
