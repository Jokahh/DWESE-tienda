package com.jokaah.springprojects.tienda.dao;
import com.jokaah.springprojects.tienda.model.Cliente;
import java.util.List;

public interface ClientesDAO {
    public List<Cliente> findAll();

    public Cliente findById(int codigo);

    public void insert(Cliente cliente);

    public void update(Cliente cliente);

    public void delete(int codigo);
}
