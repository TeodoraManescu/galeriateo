package ro.teodoramanescu.galeriateo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.teodoramanescu.galeriateo.Exceptii.LoginException;
import ro.teodoramanescu.galeriateo.Exceptii.PaitingException;
import ro.teodoramanescu.galeriateo.dao.GalleryDAO;
import ro.teodoramanescu.galeriateo.dao.PaintingDAO;
import ro.teodoramanescu.galeriateo.model.Artist;
import ro.teodoramanescu.galeriateo.model.Gallery;
import ro.teodoramanescu.galeriateo.model.Painting;


import java.util.List;
import java.util.Optional;

@Component
public class GalleryService {

    @Autowired
    GalleryDAO galleryDAO;
    @Autowired
    PaintingDAO paintingDAO;
    @Autowired
    ArtistService artistService;


    public Gallery saveGallery(Artist artist) {
        Gallery gallery = new Gallery();
        galleryDAO.save(gallery);
        gallery.setArtist(artist);
        gallery.setName("Galeria artistului "+ artist.getUsername());

        return gallery;
    }

    private Gallery getArtistGallery() throws LoginException {
        Artist artist = artistService.getArtistLogat();
        Gallery gallery = artist.getGallery();
        return gallery;
    }

    public List<Painting> paintingsArtistList() throws LoginException {
        Gallery gallery = getArtistGallery();
        List<Painting> paintingList = gallery.getPaintings();
        return paintingList;

    }

    private void savePaintingInGallery(Painting painting) throws LoginException {
        Gallery gallery = getArtistGallery();
        painting.setGallery(gallery);
    }


    public void savePaiting(byte[]content, String dimension, String name, long sizeInBtyte, String technique) throws LoginException {
        Painting painting = new Painting();

        painting.setName(name);
        painting.setDimension(dimension);
        painting.setTechnique(technique);
        painting.setSizeInBtyte(sizeInBtyte);
        painting.setContent(content);

        savePaintingInGallery(painting);

        paintingDAO.save(painting);
    }

    public Painting getPainting(long id) throws Exception {
        Optional<Painting> optionalPainting = paintingDAO.findById(id);
        if(!optionalPainting.isPresent()){
            throw new PaitingException("nu am gasit imaginea");
        }else {
            Painting painting = optionalPainting.get();
            return painting;
        }
    }

}

