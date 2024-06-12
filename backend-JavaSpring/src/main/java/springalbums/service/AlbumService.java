package springalbums.service;

import org.springframework.stereotype.Service;
import springalbums.model.Album;
import springalbums.model.Review;
import springalbums.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;


@Service
public interface AlbumService {

    List<Album> findAllAlbums();

    List<Album> searchAlbums(String text);

    Album saveAlbum(Album album);

    Album findById(Long id);

    List <Album> findByTitle(String title);

    void deleteAlbum(Long id);

    void deleteAllAlbums();

    Album updateAlbum(Album album);

    /*
    String updateRate(Long id, Integer rate);
*/

}
