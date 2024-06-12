package springalbums.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import springalbums.model.Review;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Integer id;
    private String title;
    private String artist;
    private String imageUrl;


    private String year;
    private List<String> genres;
    private List<String> tracklist;

    @JsonManagedReference
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @JsonManagedReference
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rates;

    @Column
    private Float rate;

    public void addReview(Review review) {
        reviews.add(review);
        review.setAlbum(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setAlbum(null);
    }

    public void addRate(Rate rate) {
        rates.add(rate);
        rate.setAlbum(this);
    }

    public void removeRate(Rate rate) {
        rates.remove(rate);
        rate.setAlbum(null);
    }

    public Float getRate() {
        if (rates == null || rates.isEmpty()) {
            return (float) 0;
        }
        float sum = (float) 0;
        for (Rate rate : rates) {
            sum += rate.getRating();
        }
        return sum / rates.size();
    }

    public Integer ratesSize() {
        return rates.size();
    }

    public Album() {
        this.tracklist = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public Album orElse(Object o) {
        return null;
    }
}
