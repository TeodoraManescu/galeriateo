package ro.teodoramanescu.galeriateo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.teodoramanescu.galeriateo.Exceptii.LoginException;
import ro.teodoramanescu.galeriateo.dao.AdressDAO;
import ro.teodoramanescu.galeriateo.dao.ArtistDAO;
import ro.teodoramanescu.galeriateo.model.Adress;
import ro.teodoramanescu.galeriateo.model.Artist;
import ro.teodoramanescu.galeriateo.model.Painting;
import ro.teodoramanescu.galeriateo.service.ArtistService;
import ro.teodoramanescu.galeriateo.service.GalleryService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class GalleryController {

    @Autowired
    GalleryService galleryService;
    @Autowired
    ArtistDAO artistDAO;
    @Autowired
    AdressDAO adressDAO;
    @Autowired
    ArtistService artistService;




    @PostMapping("/upload")
    public String addPict(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "size") String dimension,
            @RequestParam(name = "technique") String technique,
            @RequestParam(name = "imagine") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes
    ) {

        name = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long sizeInBtyte = multipartFile.getSize();
        try {
            byte[] content = multipartFile.getBytes();
            galleryService.savePaiting(content, dimension, name, sizeInBtyte, technique);
            redirectAttributes.addFlashAttribute("message", "Imaginea a fost salvata cu succes");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LoginException e) {
           return  "redirect:/login";
        }
        return "redirect:/galerie";
    }

    @GetMapping("galerie")
    public ModelAndView showPaitingList()  {

        ModelAndView modelAndView = new ModelAndView("galerie");
        try {
            List<Painting> paintings = galleryService.paintingsArtistList();
            modelAndView.addObject("paitingList", paintings);
            return modelAndView;
        } catch (LoginException e) {
            modelAndView = new ModelAndView("/login");
            return modelAndView;
        }
    }

    @GetMapping("/afiseaza")
    public void showPaiting(long id, HttpServletResponse response) {
        try {
            Painting painting = galleryService.getPainting(id);
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(painting.getContent());
            outputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}