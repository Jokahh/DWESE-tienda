package com.jokaah.springprojects.tienda.dao.impl;

import com.jokaah.springprojects.tienda.dao.ProductosDAO;
import com.jokaah.springprojects.tienda.dao.mappers.ProductoMapper;
import com.jokaah.springprojects.tienda.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class ProductosDAOImpl extends JdbcDaoSupport implements ProductosDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Producto> findAll() {

        String query = "select * from Productos";

        List<Producto> productos = getJdbcTemplate().query(query, new ProductoMapper());

        return productos;
    }

    @Override
    public Producto findById(int codigo) {
        String query = "select * from Productos where codigo = ?";

        Object params[] = { codigo };
        int types[] = { Types.INTEGER };

        Producto producto = (Producto) getJdbcTemplate().queryForObject(query, params, types, new ProductoMapper());

        return producto;
    }
    /*
     * @Override
     * public void insert(Producto producto) {
     * String query = "insert into Productos (nombre, descripcion, precio, imagen)"
     * + " values (?, ?, ?, ?)";
     * 
     * Object[] params = {
     * producto.getNombre(),
     * producto.getDescripcion(),
     * producto.getPrecio(),
     * producto.getImagen()
     * };
     * 
     * final int[] types = {
     * Types.VARCHAR,
     * Types.VARCHAR,
     * Types.FLOAT,
     * Types.BLOB
     * };
     * 
     * int update = getJdbcTemplate().update(query, params, types);
     * }
     */

    @Override
    public void insert(Producto producto) {

        String query = "insert into Productos (nombre," +
                " descripcion," +
                " precio," +
                " imagen)" +
                " values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getDescripcion());
                ps.setFloat(3, producto.getPrecio());
                InputStream is = new ByteArrayInputStream(producto.getImagen());

                ps.setBlob(4, is);
                return ps;
            }
        }, keyHolder);

        producto.setCodigo(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Producto producto) {
        String query = "update Productos set nombre = ?, descripcion = ?, precio = ? where codigo = ?";

        Object[] params = {
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCodigo()
        };

        final int[] types = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.FLOAT,
                Types.INTEGER
        };

        int update = getJdbcTemplate().update(query, params, types);

    }

    @Override
    public void delete(int codigo) {
        String query = "delete from Productos where codigo = ?";

        Object[] params = {
                codigo
        };

        final int[] types = {
                Types.INTEGER
        };

        getJdbcTemplate().update(query, params, types);
    }

    @Override
    public void updateImage(Producto producto) {
        String query = "update Productos set imagen = ? where codigo = ?";

        Object[] params = {
                producto.getImagen(),
                producto.getCodigo()
        };

        final int[] types = {
                Types.BLOB,
                Types.INTEGER
        };

        int update = getJdbcTemplate().update(query, params, types);
    }
}
