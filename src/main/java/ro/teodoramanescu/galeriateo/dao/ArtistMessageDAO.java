package ro.teodoramanescu.galeriateo.dao;

import org.springframework.data.repository.CrudRepository;
import ro.teodoramanescu.galeriateo.model.ArtistMessage;

public interface ArtistMessageDAO extends CrudRepository <ArtistMessage, Long> {
}
