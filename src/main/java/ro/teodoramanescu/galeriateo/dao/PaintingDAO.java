package ro.teodoramanescu.galeriateo.dao;

import org.springframework.data.repository.CrudRepository;
import ro.teodoramanescu.galeriateo.model.Gallery;
import ro.teodoramanescu.galeriateo.model.Painting;

import java.util.List;

public interface PaintingDAO extends CrudRepository<Painting, Long> {

    List<Painting> findAll();
    List<Painting> findByGallery(Gallery gallery);


}
