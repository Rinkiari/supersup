package springalbums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springalbums.model.Album;
import springalbums.model.Rate;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findAllByAlbum_Id(Long id);

    Rate findRateById(Long id);

    default Rate updateRate(Long id, Integer rating){
        findRateById(id).setRating(rating);
        save(findRateById(id));
        return findRateById(id);
    }

}
