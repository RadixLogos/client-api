package com.radixlogos.clientapi.service;

import com.radixlogos.clientapi.dto.ClientDTO;
import com.radixlogos.clientapi.dto.ClientInsertDTO;
import com.radixlogos.clientapi.entities.Client;
import com.radixlogos.clientapi.repositories.ClientRepository;
import com.radixlogos.clientapi.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllClients(Pageable pageable){
        return repository.findAll(pageable).map(ClientDTO::fromClient);
    }

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

    @Transactional
    public ClientDTO updateClient(Long id, ClientInsertDTO insertDTO){
       try {
           var entity = repository.getReferenceById(id);
           copyDtoToEntity(insertDTO, entity);
           entity = repository.save(entity);
           return ClientDTO.fromClient(entity);
       }catch(EntityNotFoundException e){
           throw new ResourceNotFoundException("Cliente não encontrado");
       }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteClient(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        repository.deleteById(id);
    }
    private void copyDtoToEntity(ClientInsertDTO insertDTO, Client entity) {
        entity.setName(insertDTO.name());
        entity.setCpf(insertDTO.cpf());
        entity.setIncome(insertDTO.income());
        entity.setBirthDate(insertDTO.birthDate());
        entity.setChildren(insertDTO.children());
    }
}
