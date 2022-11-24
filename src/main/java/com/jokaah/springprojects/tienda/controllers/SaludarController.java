/* package com.jokaah.springprojects.tienda.controllers;

import com.jokaah.springprojects.tienda.model.Producto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/saludar")
public class SaludarController {

    @GetMapping(path = { "/hola", "/buenas" })
    public String hola() {
        return "hola";
    }

    @GetMapping(path = { "/adios", "/hastaluego" })
    public String adios() {
        return "adios";
    }

    @GetMapping(path = { "/indice" })
    public String inicio(Model model) {

        model.addAttribute("listaProductos", getProductos());
        return "indice";
    }

    // Ejemplo producto?codigo=3
    @GetMapping(path = { "/producto" })
    public String producto(@RequestParam(name = "codigo") int codigo, Model model) {

        model.addAttribute("productoSel", getProducto(codigo));
        return "producto";
    }

    // Metodo alternativo (solo cambia el RequestParam por PathVariable)
    // Ejemplo producto/3
    @GetMapping(path = { "/product/{codigo}" })
    public String product(@PathVariable(name = "codigo") int codigo, Model model) {

        model.addAttribute("productoSel", getProducto(codigo));
        return "producto";
    }

    private Producto getProducto(int codigo) {
        List<Producto> productos = (ArrayList<Producto>) getProductos();
        Producto productoSelecccionado = null;

        for (Producto i : productos) {
            if (i.getCodigo() == codigo) {
                productoSelecccionado = i;
                break;
            }
        }

        return productoSelecccionado;
    }

    private List<Producto> getProductos() {

        Producto p1 = new Producto(1, "Coca Cola", "Descripcion Coca Cola", "/img/cocacola.jpg", new Date());
        Producto p2 = new Producto(2, "Pepsi", "Descripcion Pepsi", "/img/pepsi.jpg", new Date());
        Producto p3 = new Producto(3, "Fanta", "Descripcion Fanta", "/img/fanta.jpg", new Date());
        Producto p4 = new Producto(4, "Sprite", "Descripcion Sprite", "/img/sprite.jpg", new Date());

        List<Producto> listaProductos = new ArrayList<Producto>();

        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        listaProductos.add(p4);

        return listaProductos;
    }
}
 */