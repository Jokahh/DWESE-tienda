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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jokaah.springprojects.tienda.model.Producto;
import com.jokaah.springprojects.tienda.services.ProductosService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    @Autowired
    ProductosService productosService;

    /*
     * @GetMapping(value = "/list")
     * public ModelAndView list(Model model) {
     * 
     * List<Producto> productos = productosService.findAll();
     * 
     * ModelAndView modelAndView = new ModelAndView("productos/list");
     * modelAndView.addObject("productos", productos);
     * modelAndView.addObject("title", "productos");
     * return modelAndView;
     * }
     */

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

        Page<Producto> page = productosService.findAll(pageable);

        List<Producto> productos = page.getContent();

        ModelAndView modelAndView = new ModelAndView("productos/list");
        modelAndView.addObject("productos", productos);

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

        // List<Producto> productos = productosService.findAll();
        ModelAndView modelAndView = new ModelAndView("redirect:edit/" + producto.getCodigo());
        // modelAndView.addObject("productos", productos);

        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Producto producto, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {

        byte[] image = multipartFile.getBytes();
        producto.setImagen(image);

        productosService.update(producto);
        // List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView("redirect:edit/" + producto.getCodigo());
        // modelAndView.addObject("productos", productos);
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int codigo) {

        productosService.delete(codigo);
        // List<Producto> productos = productosService.findAll();

        ModelAndView modelAndView = new ModelAndView("redirect:/productos/list");
        // modelAndView.addObject("productos", productos);
        return modelAndView;
    }

}
