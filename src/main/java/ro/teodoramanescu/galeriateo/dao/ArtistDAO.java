package ro.teodoramanescu.galeriateo.dao;

import org.springframework.data.repository.CrudRepository;
import ro.teodoramanescu.galeriateo.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistDAO extends CrudRepository<Artist, Long> {
    // Artist save(Artist artist);
    List<Artist> findByEmail(String email);
    List<Artist> findByPassworld(String passworld);
    Optional<Artist> findById(long id);

}
