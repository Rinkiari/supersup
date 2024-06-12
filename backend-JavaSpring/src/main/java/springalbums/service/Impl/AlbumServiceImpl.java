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

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<Album> findAllAlbums() {
        return albumRepository.findAllAlbums();
    }

    @Override
    public List<Album> searchAlbums(String text) {
        return albumRepository.searchAlbums(text);
    }

    @Override
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public List <Album> findByTitle(String title) {
        return albumRepository.findAllByTitle(title);
    }

    @Override
    public Album findById(Long id) {
        return albumRepository.findAlbumById(id);
    }

    @Override
    @Transactional
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
        reviewRepository.deleteAllByAlbum_Id(id);
    }
    @Override
    @Transactional
    public void deleteAllAlbums() {albumRepository.deleteAll();};
    @Override
    public Album updateAlbum(Album album) {
        return albumRepository.save(album);
    }

    /*
    @Override
    public String updateRate(Long id, Integer rate) {

        Album album = albumRepository.findAlbumById(id);

        album.rateSum += rate;
        album.numOfRates += 1;
        albumRepository.save(album);
        return "Updated successfully";

    }


     */



}
