package com.example.furniture.serviceImp.sales;

import com.example.furniture.model.Client;
import com.example.furniture.repository.sales.ClientRepository;
import com.example.furniture.service.sales.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getClient(int nit) {
        return this.clientRepository.findById(nit).orElse(null);
    }

    @Override
    public boolean createClient(Client client) {
        this.clientRepository.save(client);
        return this.clientRepository.existsById(client.getId());
    }
}
