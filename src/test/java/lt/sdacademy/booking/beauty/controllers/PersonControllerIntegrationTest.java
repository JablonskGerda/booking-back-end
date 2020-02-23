package lt.sdacademy.booking.beauty.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import lt.sdacademy.booking.beauty.models.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PersonControllerIntegrationTest {

    @Autowired
    private PersonController personController;

    @Test
    void getPerson() {
        PersonDTO result = personController.getPerson(5);

        assertNotNull(result);
    }
}
