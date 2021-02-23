package ro.teodoramanescu.galeriateo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.teodoramanescu.galeriateo.Exceptii.ArtistException;
import ro.teodoramanescu.galeriateo.model.Artist;
import ro.teodoramanescu.galeriateo.model.Gallery;
import ro.teodoramanescu.galeriateo.model.Painting;

import java.util.List;

@RestController
public class PaintingRestController {
    @Autowired
    private GalleryRestService galleryRestService;
    @Autowired
    private ArtistRestService vizitArtistService;

    @GetMapping("/artists")
    public List<Artist> getArtists(){
        return vizitArtistService.getArtistList();
    }


    /*@GetMapping("/artists/{id}")
    public Artist getArtist(@PathVariable("id") long id) throws ArtistException {
        return vizitArtistService.getArtist(id);
    }

    @GetMapping("/artists/galleries/{id}")
    public Gallery getGallery(@PathVariable("id") long id){
        try {
            return galleryRestService.getGallery(id);
        } catch (ArtistException e) {
            e.getMessage();
        }
        return null;
    }*/
/*    @GetMapping("/galleries/{id}/paitings")
    public List<Painting> getGalleryPaitings(@PathVariable("id") long id){

    }*/

}
