package com.radixlogos.clientapi.controller;

import com.radixlogos.clientapi.dto.ClientDTO;
import com.radixlogos.clientapi.dto.ClientInsertDTO;
import com.radixlogos.clientapi.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAllClients(Pageable pageable){
         Page<ClientDTO> response = service.findAllClients(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id){
        var response = service.findClientById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insertClient(@Valid @RequestBody ClientInsertDTO insertDTO){
        var response = service.insertClient(insertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,@Valid @RequestBody ClientInsertDTO insertDTO){
        var response = service.updateClient(id,insertDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        service.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
