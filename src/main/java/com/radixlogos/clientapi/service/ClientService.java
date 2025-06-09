package com.radixlogos.clientapi.service;

import com.radixlogos.clientapi.dto.ClientDTO;
import com.radixlogos.clientapi.dto.ClientInsertDTO;
import com.radixlogos.clientapi.entities.Client;
import com.radixlogos.clientapi.repositories.ClientRepository;
import com.radixlogos.clientapi.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id){
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return ClientDTO.fromClient(entity);
    }
    @Transactional
    public ClientDTO insertClient(ClientInsertDTO insertDTO){
        var entity = new Client();
        copyDtoToEntity(insertDTO, entity);
        entity = repository.save(entity);
        return ClientDTO.fromClient(entity);
    }

    private void copyDtoToEntity(ClientInsertDTO insertDTO, Client entity) {
        entity.setName(insertDTO.name());
        entity.setCpf(insertDTO.cpf());
        entity.setIncome(insertDTO.income());
        entity.setBirthDate(insertDTO.birthDate());
        entity.setChildren(insertDTO.children());
    }
}
