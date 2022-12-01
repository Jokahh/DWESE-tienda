package com.jokaah.springprojects.tienda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jokaah.springprojects.tienda.model.Cliente;

public interface ClientesService {

    public Page<Cliente> findAll(Pageable page);

    public Cliente findById(int codigo);

    public void insert(Cliente cliente);

    public void update(Cliente cliente);

    public void delete(int codigo);
}
