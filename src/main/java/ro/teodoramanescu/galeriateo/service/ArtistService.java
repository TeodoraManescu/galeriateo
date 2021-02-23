package ro.teodoramanescu.galeriateo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.teodoramanescu.galeriateo.Exceptii.*;
import ro.teodoramanescu.galeriateo.dao.ArtistDAO;
import ro.teodoramanescu.galeriateo.model.Artist;
import ro.teodoramanescu.galeriateo.model.Gallery;
import ro.teodoramanescu.galeriateo.security.ArtistSesion;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class ArtistService {
    @Autowired
    private ArtistDAO artistDAO;
    @Autowired
    private ArtistSesion artistSesion;
    @Autowired
    GalleryService galleryService;
    private Artist artist;

    public boolean sesiuneValida(){
        if(artistSesion.getArtistId()==0){
            return false;
        }else return true;
    }

    public void loginArtist(String email, String passworld) throws EmailException, PassworldExceprion {
        List<Artist> artists = artistDAO.findByEmail(email);
        if(artists.isEmpty()){
            throw new EmailException("Ati introdus un email gresit");
        }else if(!artists.get(0).getPassworld().equals(passworld)){
            throw new PassworldExceprion("Parola incorecta");
        }else {
            artist = artists.get(0);
            artistSesion.setArtistId(artist.getId());
        }
    }

    public Artist getArtistLogat() throws LoginException{
        Optional<Artist> optionalArtist = artistDAO.findById(artistSesion.getArtistId());
        Artist artist = optionalArtist.orElseThrow(() -> new LoginException("trebuie sa va logati"));
        return artist;
    }

    private boolean verificaEmail(String email) throws EmailException{
        List<Artist> artists = artistDAO.findByEmail(email);
        if(artists.size()==0){
            return true;
        } else{
            throw new EmailException("Introduceti un alt email");
        }
    }

    private boolean parolaRespectaRegula(String passworld) throws PassworldExceprion {
        if(passworld.length()>3){
            return true;
        }else throw new PassworldExceprion("Parola treuie sa aiba minim 4 caractere");
    }

    private boolean paroleleCoincid(String parola1 , String parola2) throws PassworldExceprion {
        if(!parola1.equals(parola2)){
            throw new PassworldExceprion("Parolele nu coincid");
        }else {
            return true;
        }
    }

    private boolean telefonValid(String telephonNumber) throws TelephoneNumberException {
        if(telephonNumber.length()<10 || telephonNumber.length()>12){
            throw new TelephoneNumberException("Introduceti un numar de telefon valid");
        }else return true;
    }

    private void setSecurityCod(Artist artist){
        Random random = new Random();
        long cod = random.nextLong() + artist.getId();
        artist.setCodSecurity(cod);
    }

    public void saveArtistandGallery(String username, String email, String parola,
                                     String parolaAgain, String studies, String tellNumber)
            throws PassworldExceprion, EmailException, TelephoneNumberException{
          if(parolaRespectaRegula(parola)){
              if(paroleleCoincid(parola, parolaAgain)){
                  if(verificaEmail(email)){
                      if(telefonValid(tellNumber)){
                          Artist artist = new Artist();
                          artist.setUsername(username);
                          artist.setEmail(email);
                          artist.setPassworld(parola);
                          artist.setStudies(studies);
                          artist.setTelephonNumber(tellNumber);
                          setSecurityCod(artist);
                          artist.setGallery(galleryService.saveGallery(artist));
                          artistDAO.save(artist);

                      }
                  }
              }
          }
    }


}
