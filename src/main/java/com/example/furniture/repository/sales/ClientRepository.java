package com.example.furniture.repository.sales;

import com.example.furniture.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    public Client findClientById(Integer nit);
}
