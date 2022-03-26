package com.example.furniture.service.sales;

import com.example.furniture.model.Client;

public interface ClientService {

    public Client getClient(int nit);
    public boolean createClient(Client client);
}
