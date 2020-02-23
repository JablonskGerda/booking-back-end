package lt.sdacademy.booking.beauty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lt.sdacademy.booking.beauty.models.dto.ServiceDTO;
import lt.sdacademy.booking.beauty.services.ServiceService;

@CrossOrigin
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceDTO> serviceControl(){
        return serviceService.getAll();
    }

/*    @PostMapping
    public ServiceEntity create(@RequestBody ServiceDTO) {
        return bookingService.save(ServiceDTO);
    }*/
}
