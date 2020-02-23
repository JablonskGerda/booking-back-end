package lt.sdacademy.booking.beauty.services;

import java.util.List;

import lt.sdacademy.booking.beauty.converters.ClientConverter;
import lt.sdacademy.booking.beauty.models.dto.ClientDTO;
import lt.sdacademy.booking.beauty.models.entities.ClientEntity;
import lt.sdacademy.booking.beauty.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    public ClientEntity create(ClientEntity client) {
        return clientRepository.save(client);
    }

    public List<ClientEntity> getClient() {
        return clientRepository.findAll();
    }

    public ClientDTO getOneByTelephone(Integer telephone_no) {
        ClientEntity clientEntity = clientRepository.getOneByTelephone(telephone_no);
        return clientConverter.clientConverter(clientEntity);
    }

    public ClientEntity getOneByTelephoneEntity(Integer telephone_no) {
        ClientEntity clientEntity = clientRepository.getOneByTelephone(telephone_no);
        return clientEntity;
    }
}
