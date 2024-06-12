package springalbums.service;

import springalbums.model.Album;
import springalbums.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> findAllReviews();

    Review addReviewToAlbum(Long id, Review review);

    List <Review> getReviewsByAlbumId(Long id);

    void deleteReview(Long id);

    void deleteAllReviews();

    Review updateReview(Review review);
}
