package springalbums.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springalbums.model.Album;
import springalbums.model.Rate;
import springalbums.model.Review;
import springalbums.service.AlbumService;
import springalbums.service.RateService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rates")
@AllArgsConstructor
public class RateController {
    private final RateService service;


    @PostMapping("save/{id}/{rate}")
    public Rate saveRate(@PathVariable Long id, @PathVariable Integer rate) {
        Rate newRate = new Rate();
        newRate.setId(id);
        newRate.setRating(rate);

        service.saveRate(id, newRate);
        return newRate;
    }

    @GetMapping("album_id/{id}")
    public ResponseEntity<List<Rate>> getRatesByAlbumId(@PathVariable Long id) {
        List<Rate> rates = service.getRatesByAlbumId(id);
        return ResponseEntity.ok(rates);
    }

    @PutMapping("update_rate/{id}/{rate}")
    public Rate updateRate(@PathVariable Long id, @PathVariable Integer rate) {
        return service.updateRate(id, rate);
    }

    /*
    @PostMapping("save/{id}/{rate}")
    public String rateAlbum(@PathVariable String id,
                            @PathVariable Integer rate,
                            HttpServletRequest request, HttpServletResponse response) {
        // Чтение куки
        Optional<Cookie> albumRatingCookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("album_rating_" + albumId))
                .findFirst();

        if (albumRatingCookie.isPresent()) {
            // Обновление существующей оценки
            rateService.updateAlbumRating(albumId, rating);
            // Обновление куки
            Cookie cookieToUpdate = albumRatingCookie.get();
            cookieToUpdate.setValue(rating);
            response.addCookie(cookieToUpdate);
        } else {
            // Добавление новой оценки
            Rate newRate = new Rate();
            newRate.setId(id);
            newRate.setRating(rate);

            service.saveRate(id, newRate);

            // Создание новой куки
            Cookie newCookie = new Cookie("album_rating_" + albumId, rating);
            newCookie.setMaxAge(7 * 24 * 60 * 60); // куки будет храниться 7 дней
            response.addCookie(newCookie);
        }

        return "redirect:/someView"; // Перенаправление на определенный вид
    }

     */
}
