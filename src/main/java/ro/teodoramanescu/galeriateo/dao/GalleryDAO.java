package ro.teodoramanescu.galeriateo.dao;

import org.springframework.data.repository.CrudRepository;
import ro.teodoramanescu.galeriateo.model.Artist;
import ro.teodoramanescu.galeriateo.model.Gallery;

import java.util.List;

public interface GalleryDAO extends CrudRepository<Gallery, Long> {
    List<Gallery> findByArtist(Artist artist);
}
