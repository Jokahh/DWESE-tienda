package com.jokaah.springprojects.tienda.controllers;

import com.jokaah.springprojects.tienda.model.Usuario;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/login")
    public ModelAndView login(Model model) {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }


    //@PostMapping(value = "/signin")
    @GetMapping(value = "/signin")
    public ModelAndView signin(Usuario usuario, HttpSession session) {

        usuario = new Usuario();
        usuario.setUsuario("Admin");

        String message = messageSource.getMessage("saludar.usuario", new String[] { usuario.getUsuario() },
                LocaleContextHolder.getLocale());

        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("greetings", message);
        modelAndView.addObject("usuario", usuario);

        session.setAttribute("usuario", usuario);

        return modelAndView;
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView("login");
        session.setAttribute("usuario",null);
        session.invalidate();
        return modelAndView;
    }

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "welcome";
    }

}
