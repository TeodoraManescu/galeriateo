package ro.teodoramanescu.galeriateo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.teodoramanescu.galeriateo.Exceptii.ArtistException;
import ro.teodoramanescu.galeriateo.dao.GalleryDAO;
import ro.teodoramanescu.galeriateo.dao.PaintingDAO;
import ro.teodoramanescu.galeriateo.model.Gallery;
import ro.teodoramanescu.galeriateo.model.Painting;

import java.util.List;
import java.util.Optional;


@Component
public class GalleryRestService {
    @Autowired
    private GalleryDAO galleryDAO;
    @Autowired
    private PaintingDAO paintingDAO;

    public List<Gallery> getAllGalleries(){
        List<Gallery> galleryList = (List<Gallery>) galleryDAO.findAll();
        return galleryList;
    }

    public Gallery getGallery(long id) throws ArtistException {
        Optional<Gallery> optionalGallery = galleryDAO.findById(id);
        Gallery gallery = optionalGallery.orElseThrow(() ->  new ArtistException("Nu exista o galerie pt acest artist"));
        return gallery;
    }

    public List<Painting> getGAlleryPaitings(Gallery gallery){
        List<Painting> paintingList = paintingDAO.findByGallery(gallery);
        return paintingList;
    }
}
