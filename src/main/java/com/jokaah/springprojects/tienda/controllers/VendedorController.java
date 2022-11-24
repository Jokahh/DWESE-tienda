package com.jokaah.springprojects.tienda.controllers;

import com.jokaah.springprojects.tienda.model.Vendedor;

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
@RequestMapping("/vendedores")
public class VendedorController {

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {

        ModelAndView modelAndView = new ModelAndView("vendedores/list");
        modelAndView.addObject("vendedores", getVendedores());
        modelAndView.addObject("title", "vendedores");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        ModelAndView modelAndView = new ModelAndView("vendedores/edit");
        modelAndView.addObject("vendedor", getVendedor(codigo));
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Vendedor vendedor) {

        ModelAndView modelAndView = new ModelAndView("vendedores/new");
        modelAndView.addObject("vendedor", new Vendedor());
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Vendedor vendedor) {

        int round = (int) (Math.random() * (100 + 5));

        vendedor.setCodigo(round);

        List<Vendedor> vendedores = getVendedores();
        vendedores.add(vendedor);

        ModelAndView modelAndView = new ModelAndView("vendedores/list");
        modelAndView.addObject("vendedores", vendedores);
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Vendedor vendedor) {

        List<Vendedor> vendedores = getVendedores();

        int indexOf = vendedores.indexOf(vendedor);

        vendedores.set(indexOf, vendedor);

        ModelAndView modelAndView = new ModelAndView("vendedores/list");
        modelAndView.addObject("vendedores", vendedores);
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        List<Vendedor> vendedores = getVendedores();
        vendedores.remove(getVendedor(codigo));
        ModelAndView modelAndView = new ModelAndView("vendedores/list");
        modelAndView.addObject("vendedores", vendedores);
        return modelAndView;
    }

    private Vendedor getVendedor(int codigo) {
        List<Vendedor> vendedores = getVendedores();
        int indexOf = vendedores.indexOf(new Vendedor(codigo));

        return vendedores.get(indexOf);

    }

    private List<Vendedor> getVendedores() {

        Vendedor p1 = new Vendedor(1, "Paco", "Paquero", "1");
        Vendedor p2 = new Vendedor(2, "Maria", "Mariana", "2");
        Vendedor p3 = new Vendedor(3, "Angel", "Angela", "3");
        Vendedor p4 = new Vendedor(4, "Miguel", "Angel", "4");

        List<Vendedor> listaVendedores = new ArrayList<Vendedor>();

        listaVendedores.add(p1);
        listaVendedores.add(p2);
        listaVendedores.add(p3);
        listaVendedores.add(p4);

        return listaVendedores;
    }
}
