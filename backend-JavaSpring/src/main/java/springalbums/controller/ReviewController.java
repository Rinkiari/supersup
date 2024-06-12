package springalbums.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springalbums.model.Album;
import springalbums.model.Review;
import springalbums.service.AlbumService;
import springalbums.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService service;

    @CrossOrigin(origins = "https://localhost:5173")
    @GetMapping
    public List<Review> findAllReviews() {
        return service.findAllReviews();
    }

    @GetMapping("album_id/{id}")
    public ResponseEntity<List<Review>> getReviewsByAlbumId(@PathVariable Long id) {
        List<Review> reviews = service.getReviewsByAlbumId(id);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("add/{id}")
    public Review addReviewToAlbum(@PathVariable Long id, @RequestBody Review review) {
        return service.addReviewToAlbum(id, review);
    }

    @DeleteMapping("delete/{id}")
    public void deleteReview(@PathVariable Long id) {
        service.deleteReview(id);
    }

    @DeleteMapping("delete_all")
    public void deleteAllAlbums() {
        service.deleteAllReviews();
    }

    @PutMapping("update")
    public Review updateReview(@RequestBody Review review) {
        return service.updateReview(review);
    }
}
