package ro.teodoramanescu.galeriateo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ro.teodoramanescu.galeriateo.Exceptii.ArtistException;
import ro.teodoramanescu.galeriateo.dao.ArtistDAO;
import ro.teodoramanescu.galeriateo.model.Artist;

import java.util.List;
import java.util.Optional;

@Component
public class ArtistRestService {

    @Autowired
    private ArtistDAO artistDAO;

    public List<Artist> getArtistList (){
        List<Artist> artistList = (List<Artist>) artistDAO.findAll();
        return artistList;
    }

    public Artist getArtist(long id) throws ArtistException {
        Optional<Artist> optionalArtist = artistDAO.findById(id);
        Artist artist = optionalArtist.orElseThrow(() -> new  ArtistException("no artist"));
        return artist;
    }
}
