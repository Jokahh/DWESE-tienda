package com.jokaah.springprojects.tienda.dao;
import com.jokaah.springprojects.tienda.model.Producto;
import java.util.List;

public interface ProductosDAO {
    public List<Producto> findAll();

    public Producto findById(int codigo);

    public void insert(Producto producto);

    public void update(Producto producto);

    public void delete(int codigo);
}
