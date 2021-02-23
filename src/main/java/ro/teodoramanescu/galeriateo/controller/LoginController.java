package ro.teodoramanescu.galeriateo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.teodoramanescu.galeriateo.service.ArtistService;
import ro.teodoramanescu.galeriateo.service.GalleryService;

@Controller
public class LoginController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private GalleryService galleryService;

    @PostMapping("/login-form")
    public ModelAndView loginForm(
            @RequestParam(name = "email")String email,
            @RequestParam(name = "passworld")String passworld
    ){
        ModelAndView modelAndView = new ModelAndView("login.html");
        try {
            artistService.loginArtist(email, passworld);
            modelAndView = new ModelAndView("redirect:/pagina-artistului");

        } catch (Exception exception) {
            modelAndView.addObject("messageException", exception.getMessage());
        }
        return modelAndView;
    }
    @GetMapping("login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("pagina-artistului")
    public ModelAndView modelAndView(){
        if(artistService.sesiuneValida()){
            return new ModelAndView("pagina-artistului");
        }else {
            return new ModelAndView("login");
        }
    }

}
