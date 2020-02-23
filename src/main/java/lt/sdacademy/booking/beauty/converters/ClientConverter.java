package lt.sdacademy.booking.beauty.converters;

import org.springframework.stereotype.Component;

import lt.sdacademy.booking.beauty.models.dto.ClientDTO;
import lt.sdacademy.booking.beauty.models.entities.ClientEntity;

@Component
public class ClientConverter {

    public ClientDTO clientConverter(ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO(
                clientEntity.getPersonEntity().getName(),
                clientEntity.getPersonEntity().getSurname(),
                clientEntity.getTelephone()
        );
        return clientDTO;
    }
}
