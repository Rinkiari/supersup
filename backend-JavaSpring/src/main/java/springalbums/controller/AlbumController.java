package springalbums.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springalbums.model.Album;
import springalbums.model.Review;
import springalbums.service.AlbumService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/albums")
@AllArgsConstructor
public class  AlbumController {
    private final AlbumService service;

    @CrossOrigin(origins = "https://localhost:5173")
    @GetMapping
    public List<Album> findAllAlbums() {
        return service.findAllAlbums();
    }

    @GetMapping("search/{text}")
    public List<Album> SearchAlbums(@PathVariable String text) {
        return service.searchAlbums(text);
    }

    @PostMapping("save_album")
    public Album saveAlbum(@RequestBody Album album) {
        service.saveAlbum(album);
        return album;
    }

    @PostMapping("save_albums")
    public ResponseEntity<List<Album>> saveAlbums(@RequestBody List<Album> albums) {
        for (Album album : albums){
            service.saveAlbum(album);
        }
        return ResponseEntity.ok(albums);
    }

    @GetMapping("find_by_title/{title}")
    public List<Album> findByTitle(@PathVariable String title) {
        return service.findByTitle(title);
    }

    @GetMapping("find_by_id/{id}")
    public Album findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("delete_album/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        service.deleteAlbum(id);
    }

    @DeleteMapping("delete_all_albums")
    public void deleteAllAlbums() {
        service.deleteAllAlbums();
    }

    @PutMapping("update_album")
    public Album updateAlbum(@RequestBody Album album) {
        return service.updateAlbum(album);
    }

    /*
    @PutMapping("update_rate/{id}/{rate}")
    public String updateRate(@PathVariable Long id, @PathVariable Integer rate) {
        return service.updateRate(id, rate);
    }
    */

}
