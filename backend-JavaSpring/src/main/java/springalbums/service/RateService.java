package springalbums.service;

import org.springframework.stereotype.Service;
import springalbums.model.Album;
import springalbums.model.Rate;
import springalbums.model.Review;

import java.util.List;

@Service
public interface RateService {
    Rate saveRate(Long id, Rate rate);

    List<Rate> getRatesByAlbumId(Long id);

    Rate updateRate(Long id, Integer rating);
}
