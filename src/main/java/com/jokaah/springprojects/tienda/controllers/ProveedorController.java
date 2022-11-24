package com.jokaah.springprojects.tienda.controllers;

import com.jokaah.springprojects.tienda.model.Proveedor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {

        ModelAndView modelAndView = new ModelAndView("proveedores/list");
        modelAndView.addObject("proveedores", getProveedores());
        modelAndView.addObject("title", "proveedores");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        ModelAndView modelAndView = new ModelAndView("proveedores/edit");
        modelAndView.addObject("proveedor", getProveedor(codigo));
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Proveedor proveedor) {

        ModelAndView modelAndView = new ModelAndView("proveedores/new");
        modelAndView.addObject("proveedor", new Proveedor());
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Proveedor proveedor) {

        int round = (int) (Math.random() * (100 + 5));

        proveedor.setCodigo(round);

        List<Proveedor> proveedores = getProveedores();
        proveedores.add(proveedor);

        ModelAndView modelAndView = new ModelAndView("proveedores/list");
        modelAndView.addObject("proveedores", proveedores);
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Proveedor proveedor) {

        List<Proveedor> proveedores = getProveedores();

        int indexOf = proveedores.indexOf(proveedor);

        proveedores.set(indexOf, proveedor);

        ModelAndView modelAndView = new ModelAndView("proveedores/list");
        modelAndView.addObject("proveedores", proveedores);
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        List<Proveedor> proveedores = getProveedores();
        proveedores.remove(getProveedor(codigo));
        ModelAndView modelAndView = new ModelAndView("proveedores/list");
        modelAndView.addObject("proveedores", proveedores);
        return modelAndView;
    }

    private Proveedor getProveedor(int codigo) {
        List<Proveedor> proveedores = getProveedores();
        int indexOf = proveedores.indexOf(new Proveedor(codigo));

        return proveedores.get(indexOf);

    }

    private List<Proveedor> getProveedores() {

        Proveedor p1 = new Proveedor(1, "Paco", "Paquero");
        Proveedor p2 = new Proveedor(2, "Maria", "Mariana");
        Proveedor p3 = new Proveedor(3, "Angel", "Angela");
        Proveedor p4 = new Proveedor(4, "Miguel", "Angel");

        List<Proveedor> listaProveedores = new ArrayList<Proveedor>();

        listaProveedores.add(p1);
        listaProveedores.add(p2);
        listaProveedores.add(p3);
        listaProveedores.add(p4);

        return listaProveedores;
    }
}
