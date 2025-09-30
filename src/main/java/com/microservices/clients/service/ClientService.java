package com.microservices.clients.service;

import com.microservices.clients.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAllClients();
    Optional<Client> findClientById(Long id);
    Client saveClient(Client client);
    void deleteClient(Long id);
    Client updateClient(Long id, Client clientDetails);
}
