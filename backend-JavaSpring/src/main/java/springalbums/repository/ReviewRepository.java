package springalbums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springalbums.model.Album;
import springalbums.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAlbum_Id(Long id);

    void deleteAllByAlbum_Id(Long id);

    void deleteById(Long id);

    void deleteAll();
}
