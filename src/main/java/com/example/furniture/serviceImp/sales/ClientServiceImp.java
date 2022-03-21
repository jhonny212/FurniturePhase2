package com.example.furniture.serviceImp.sales;

import com.example.furniture.model.Client;
import com.example.furniture.repository.sales.ClientRepository;
import com.example.furniture.service.sales.ClientService;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImp implements ClientService {
    private ClientRepository clientRepository;

    @Override
    public Client getClient(int nit) {
        return this.clientRepository.findClientByNit(nit);
    }

    @Override
    public Client createClient(Client client) {
        return this.clientRepository.save(client);
    }
}
