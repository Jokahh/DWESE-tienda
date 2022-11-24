package com.jokaah.springprojects.tienda.controllers;

import com.jokaah.springprojects.tienda.model.Cliente;
import com.jokaah.springprojects.tienda.services.ClientesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClientesService clientesService;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {

        List<Cliente> clientes = clientesService.findAll();

        ModelAndView modelAndView = new ModelAndView("clientes/list");
        modelAndView.addObject("clientes", clientes);
        modelAndView.addObject("title", "clientes");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Cliente cliente = clientesService.findById(codigo);

        ModelAndView modelAndView = new ModelAndView("clientes/edit");
        modelAndView.addObject("cliente", cliente);
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Cliente cliente) {
        ModelAndView modelAndView = new ModelAndView("clientes/new");
        modelAndView.addObject("cliente", new Cliente());
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Cliente cliente) {

        /*
         * int round = (int) (Math.random() * (100 + 5));
         * cliente.setCodigo(round);
         */

        clientesService.insert(cliente);
        List<Cliente> clientes = clientesService.findAll();

        ModelAndView modelAndView = new ModelAndView("clientes/list");
        modelAndView.addObject("clientes", clientes);
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Cliente cliente) {

        clientesService.update(cliente);
        List<Cliente> clientes = clientesService.findAll();

        // int indexOf = clientes.indexOf(cliente);
        // clientes.set(indexOf, cliente);

        ModelAndView modelAndView = new ModelAndView("clientes/list");
        modelAndView.addObject("clientes", clientes);
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        clientesService.delete(codigo);
        List<Cliente> clientes = clientesService.findAll();

        // clientes.remove(getCliente(codigo));
        ModelAndView modelAndView = new ModelAndView("clientes/list");
        modelAndView.addObject("clientes", clientes);
        return modelAndView;
    }

    /*
     * private Cliente getCliente(int codigo) {
     * List<Cliente> clientes = clientesService.findAll();
     * int indexOf = clientes.indexOf(new Cliente(codigo));
     * 
     * return clientes.get(indexOf);
     * 
     * }
     * 
     * 
     * private List<Cliente> getClientes() {
     * 
     * Cliente p1 = new Cliente(1, "Paco", "Paquero", "111111111", "Calle Paco 1",
     * "PacoPaquero1@gmail.com", false);
     * Cliente p2 = new Cliente(2, "Maria", "Mariana", "222222222", "Calle Maria 2",
     * "MariaMariana2@gmail.com", false);
     * Cliente p3 = new Cliente(3, "Angel", "Angela", "333333333", "Calle Angel 3",
     * "AngelAngela3@gmail.com", false);
     * Cliente p4 = new Cliente(4, "Miguel", "Angel", "444444444", "Calle Miguel 4",
     * "MiguelAngel4@gmail.com", false);
     * 
     * List<Cliente> listaClientes = new ArrayList<Cliente>();
     * 
     * listaClientes.add(p1);
     * listaClientes.add(p2);
     * listaClientes.add(p3);
     * listaClientes.add(p4);
     * 
     * return listaClientes;
     * }
     */
}
