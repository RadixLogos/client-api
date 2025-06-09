package com.radixlogos.clientapi.controller;

import com.radixlogos.clientapi.dto.ClientDTO;
import com.radixlogos.clientapi.dto.ClientInsertDTO;
import com.radixlogos.clientapi.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;
import org.springframework.web.service.invoker.UriBuilderFactoryArgumentResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientById(@Valid @PathVariable Long id){
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
}
