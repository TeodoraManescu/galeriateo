package ro.teodoramanescu.galeriateo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JsonIgnore
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Adress adress;

    @OneToOne
    @JsonIgnore
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Gallery gallery;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "artist_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<ArtistMessage> messageList;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String passworld;
    @Column(nullable = false, unique = true)
    private long codSecurity;

    private String telephonNumber;
    private String studies;


    public long getCodSecurity() {
        return codSecurity;
    }

    public void setCodSecurity(long codSecurity) {
        this.codSecurity = codSecurity;
    }

    public List<ArtistMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<ArtistMessage> messageList) {
        this.messageList = messageList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getTelephonNumber() {
        return telephonNumber;
    }

    public void setTelephonNumber(String telephonNumber) {
        this.telephonNumber = telephonNumber;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}
