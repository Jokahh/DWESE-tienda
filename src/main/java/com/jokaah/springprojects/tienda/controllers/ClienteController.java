package com.jokaah.springprojects.tienda.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jokaah.springprojects.tienda.model.Cliente;
import com.jokaah.springprojects.tienda.services.ClientesService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    @Autowired
    ClientesService clientesService;

    @Value("${pagination.size}")
    int sizePage;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView("redirect:list/1/codigo/asc");
        return modelAndView;
    }

    @GetMapping(value = "/list/{numPage}/{fieldSort}/{directionSort}")
    public ModelAndView listPage(Model model,
            @PathVariable("numPage") Integer numPage,
            @PathVariable("fieldSort") String fieldSort,
            @PathVariable("directionSort") String directionSort) {

        Pageable pageable = PageRequest.of(numPage - 1, sizePage,
                directionSort.equals("asc") ? Sort.by(fieldSort).ascending() : Sort.by(fieldSort).descending());

        Page<Cliente> page = clientesService.findAll(pageable);

        List<Cliente> clientes = page.getContent();

        ModelAndView modelAndView = new ModelAndView("clientes/list");
        modelAndView.addObject("clientes", clientes);

        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

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
    public ModelAndView save(Cliente cliente) throws IOException {

        clientesService.insert(cliente);

        ModelAndView modelAndView = new ModelAndView("redirect:edit/" + cliente.getCodigo());

        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Cliente cliente)
            throws IOException {

        clientesService.update(cliente);

        ModelAndView modelAndView = new ModelAndView("redirect:edit/" + cliente.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        clientesService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView("redirect:/clientes/list");

        return modelAndView;
    }
}
