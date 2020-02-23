package lt.sdacademy.booking.beauty.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ServiceControllerTest {

    @Autowired
    private ServiceController serviceController;

    @Test
    void bookingControl() {
        assertNotNull(serviceController);
    }
}
