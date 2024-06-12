package springalbums.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springalbums.model.Album;
import springalbums.model.Review;
import springalbums.repository.AlbumRepository;
import springalbums.repository.ReviewRepository;
import springalbums.service.AlbumService;
import springalbums.service.ReviewService;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final AlbumRepository albumRepository;
    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review addReviewToAlbum(Long id, Review review) {
        return albumRepository.addReviewToAlbum(id, review);
    }

    @Override
    public List<Review> getReviewsByAlbumId(Long albumId) {
        Album album = albumRepository.findAlbumById(albumId);
        if (album != null) {
            return reviewRepository.findAllByAlbum_Id(albumId);
        }
        return Collections.emptyList(); // или null, или другой выбранный вами способ обработки ошибки
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllReviews() {reviewRepository.deleteAll();};

    @Override
    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }
}
