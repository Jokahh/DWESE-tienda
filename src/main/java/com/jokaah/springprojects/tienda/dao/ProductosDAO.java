package com.jokaah.springprojects.tienda.dao;
import com.jokaah.springprojects.tienda.model.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductosDAO {
    public Page<Producto> findAll(Pageable page);

    public Producto findById(int codigo);

    public void insert(Producto producto);

    public void update(Producto producto);

    public void delete(int codigo);

    public void updateImage(Producto producto);
}
