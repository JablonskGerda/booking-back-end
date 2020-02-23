package lt.sdacademy.booking.beauty.repositories;

import lt.sdacademy.booking.beauty.models.entities.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void save() {
        List<PersonEntity> result1 = personRepository.findAll();
        PersonEntity person = PersonEntity.builder()
                .name("Kotryna")
                .surname("Milka")
                .birthday(LocalDate.now())
                .build();
        personRepository.save(person);
        List<PersonEntity> result2 = personRepository.findAll();
        assertEquals(1, result2.size()-result1.size());
    }
}