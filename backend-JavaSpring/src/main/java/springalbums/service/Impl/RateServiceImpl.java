package springalbums.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import springalbums.model.Album;
import springalbums.model.Rate;
import springalbums.model.Review;
import springalbums.repository.AlbumRepository;
import springalbums.repository.RateRepository;
import springalbums.repository.ReviewRepository;
import springalbums.service.AlbumService;
import springalbums.service.RateService;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class RateServiceImpl implements RateService {
    private final AlbumRepository albumRepository;
    private final RateRepository rateRepository;

    @Override
    public Rate saveRate(Long id, Rate rate) {
        albumRepository.addRateToAlbum(id, rate);
        return rate;
    }

    @Override
    public Rate updateRate(Long id, Integer rating) {
        return rateRepository.updateRate(id, rating);
    }

    @Override
    public List<Rate> getRatesByAlbumId(Long albumId) {
        Album album = albumRepository.findAlbumById(albumId);
        if (album != null) {
            return rateRepository.findAllByAlbum_Id(albumId);
        }
        return Collections.emptyList(); // или null, или другой выбранный вами способ обработки ошибки
    }
}
