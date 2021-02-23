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
public class RegisterController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private GalleryService galleryService;


    @PostMapping("/register-form")
    public ModelAndView saveNewArtist(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "email") String email,
                                      @RequestParam(name = "password") String pass1,
                                      @RequestParam(name = "passwordAgain") String passAgain,
                                      @RequestParam(name = "studii") String studii,
                                      @RequestParam(name = "phoneNumber") String tellNumber)
    {
        ModelAndView modelAndView = new ModelAndView("register");
        try {
            artistService.saveArtistandGallery(username,email,pass1,passAgain,studii,tellNumber);
        }
        catch (Exception exception) {
            modelAndView.addObject("messageException", exception.getMessage());
            return modelAndView;
        }
        return new ModelAndView("redirect:/index.html");
    }
    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }



}
