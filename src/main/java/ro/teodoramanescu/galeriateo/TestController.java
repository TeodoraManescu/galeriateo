package ro.teodoramanescu.galeriateo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.teodoramanescu.galeriateo.dao.ArtistDAO;
import ro.teodoramanescu.galeriateo.dao.PaintingDAO;
import ro.teodoramanescu.galeriateo.model.Artist;
import ro.teodoramanescu.galeriateo.model.Painting;
import ro.teodoramanescu.galeriateo.service.GalleryService;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    ArtistDAO artistDAO;
    @Autowired
    PaintingDAO paintingDAO;
    @Autowired
    GalleryService galleryService;


    @GetMapping("test")
    @ResponseBody
    public List<Artist> test(){
        return artistDAO.findByEmail("alfa@alfa.com");

    }
/*    @GetMapping("test2")
    @ResponseBody
    public List<Painting> getPaiting(){
        return galleryService.paintingsArtistList();
    }*/
}
