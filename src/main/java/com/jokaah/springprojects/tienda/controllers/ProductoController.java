package com.jokaah.springprojects.tienda.controllers;

import com.jokaah.springprojects.tienda.model.Producto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @RequestMapping(value = "/list")
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView("productos/list");
        modelAndView.addObject("productos", getProductos());

        return modelAndView;
    }

    @RequestMapping(path = "/edit")
    public ModelAndView edit(
            @RequestParam(name = "codigo", required = true) int codigo) {
        ModelAndView modelAndView = new ModelAndView("productos/edit");
        modelAndView.addObject("producto", getProducto(codigo));

        return modelAndView;
    }


    private Producto getProducto(int codigo) {
        List<Producto> productos = getProductos();
        int indexOf = productos.indexOf(new Producto(codigo));

        return productos.get(indexOf);
    }

    private List<Producto> getProductos() {

        Producto p1 = new Producto(1, "Coca Cola", "Descripcion Coca Cola", "/tienda/img/cocacola.jpg", new Date());
        Producto p2 = new Producto(2, "Pepsi", "Descripcion Pepsi", "/tienda/img/pepsi.jpg", new Date());
        Producto p3 = new Producto(3, "Fanta", "Descripcion Fanta", "/tienda/img/fanta.jpg", new Date());
        Producto p4 = new Producto(4, "Sprite", "Descripcion Sprite", "/tienda/img/sprite.jpg", new Date());

        List<Producto> listaProductos = new ArrayList<Producto>();

        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        listaProductos.add(p4);

        return listaProductos;
    }
}
