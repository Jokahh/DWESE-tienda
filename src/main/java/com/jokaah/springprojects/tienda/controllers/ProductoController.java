package com.jokaah.springprojects.tienda.controllers;

import com.jokaah.springprojects.tienda.model.Producto;
import com.jokaah.springprojects.tienda.services.ProductosService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    @Autowired
    ProductosService productosService;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {

        List<Producto> productos = productosService.findAll();
        
        ModelAndView modelAndView = new ModelAndView("productos/list");
        modelAndView.addObject("productos", productos);
        modelAndView.addObject("title", "productos");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Producto producto = productosService.findById(codigo);

        ModelAndView modelAndView = new ModelAndView("productos/edit");
        modelAndView.addObject("producto", producto);
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Producto producto) {
        ModelAndView modelAndView = new ModelAndView("productos/new");
        modelAndView.addObject("producto", new Producto());
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Producto producto, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        byte[] image = multipartFile.getBytes();
        producto.setImagen(image);

        productosService.insert(producto);

        //List<Producto> productos = productosService.findAll();
        ModelAndView modelAndView = new ModelAndView("redirect:edit/" + producto.getCodigo());
        //modelAndView.addObject("productos", productos);

        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Producto producto, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        byte[] image = multipartFile.getBytes();
        producto.setImagen(image);
        
        productosService.update(producto);
        //List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView("redirect:edit/" + producto.getCodigo());
        //modelAndView.addObject("productos", productos);
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        productosService.delete(codigo);
        List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView("productos/list");
        modelAndView.addObject("productos", productos);
        return modelAndView;
    }

}
