package springalbums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springalbums.model.Album;
import springalbums.model.Rate;
import springalbums.model.Review;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    default List<Album> findAllAlbums(){
        List<Album> albums = findAll();
        return albums.stream()
                .sorted((a1, a2) -> a1.getTitle().compareToIgnoreCase(a2.getTitle()))
                .sorted((a1, a2) -> Integer.compare(a2.ratesSize(), a1.ratesSize()))
                .collect(Collectors.toList());
    }



    default List<Album> searchAlbums(String searchText) {
        List<Album> albums = findAll();
        return albums.stream()
                .filter(album -> album.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                        album.getArtist().toLowerCase().contains(searchText.toLowerCase()))
                .sorted((a1, a2) -> a1.getTitle().compareToIgnoreCase(a2.getTitle()))
                .sorted((a1, a2) -> Integer.compare(a2.ratesSize(), a1.ratesSize()))
                .collect(Collectors.toList());
    }
    Album findAlbumById(Long id);


    List<Album> findAllByTitle(String title);

    default Review addReviewToAlbum(Long id, Review review) {
        Album album = findAlbumById(id);
        album.addReview(review);
        save(album);
        return review;
    }

    default Rate addRateToAlbum(Long id, Rate rate) {
        Album album = findAlbumById(id);
        album.addRate(rate);
        save(album);
        return rate;
    }

    void deleteById(Long id);

    void deleteAll();
}
