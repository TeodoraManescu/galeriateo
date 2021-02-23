package ro.teodoramanescu.galeriateo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.teodoramanescu.galeriateo.Exceptii.LoginException;
import ro.teodoramanescu.galeriateo.model.Artist;
import ro.teodoramanescu.galeriateo.service.ArtistService;

@Controller
public class ContulArtistuluiController {
    @Autowired
    ArtistService artistService;

    @GetMapping("contul-artistului")
    public ModelAndView showCoont()  {
        ModelAndView modelAndView = new ModelAndView("contul-artistului");
        try {
            Artist artist = artistService.getArtistLogat();
            modelAndView.addObject("artist", artist);
        } catch (LoginException e) {
            modelAndView = new ModelAndView("/login");
        }

        return modelAndView;
    }
}
