package ro.teodoramanescu.galeriateo.model;

import javax.persistence.*;

@Entity
@Table(name = "paiting")
public class Painting{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    private Gallery gallery;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(nullable = false)
    private String dimension;

    private String technique;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] content;

    private long sizeInBtyte;


    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getSizeInBtyte() {
        return sizeInBtyte;
    }

    public void setSizeInBtyte(long sizeInBtyte) {
        this.sizeInBtyte = sizeInBtyte;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}
