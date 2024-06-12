package springalbums.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    private String username;
    @Column(length = 2000)
    private String text;

    @Column
    private String imageUrl;

    private Integer positive; //-1 negative 0 neutral 1 positive


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;

    /*
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    */

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Review() {
        List<String> images = Arrays.asList(
                "https://sun9-80.userapi.com/impg/_WSxFlsXGbstCnPSrAZ10N9KFsE1efWKefsxUA/M5rujW9yJf0.jpg?size=300x300&quality=95&sign=a53ae3c5dd219efefb105cb406baf8f3&type=album",
                "https://sun9-64.userapi.com/impg/FRHLQ5yUMo14-N3NU0Gy4somCqW0oX9qeHpd_g/KC-3vOuXpGs.jpg?size=300x300&quality=95&sign=e6507578820fe57bdd16102cec2ecccd&type=album",
                "https://sun9-78.userapi.com/impg/iaW_8dEPxsY72GrFkAdwchMfVcSY7ZgvM-WfwA/YjiLunRRhZ0.jpg?size=300x300&quality=95&sign=db436f147bc473c24638463f83a56d9c&type=album",
                "https://sun9-66.userapi.com/impg/7mATMVF-j08PhV3aQYfiNkRFLEbWdaax36UWAg/9JIUAs3uPgk.jpg?size=300x300&quality=95&sign=c96bc765e53f16eef8f15a2bedb86970&type=album",
                "https://sun9-5.userapi.com/impg/X6TRu3-o-IIkJjB-Jvf4v_9Kvt680XEI46IM4g/vGgL-gB7B-s.jpg?size=300x300&quality=95&sign=e07181730a2d8a9afc9e4fce1e473d2d&type=album",
                "https://sun9-65.userapi.com/impg/6lQwsAtTJdto4ED_pVYG94EJ96Va6DYk2FeEww/44Z73eaikRk.jpg?size=300x300&quality=95&sign=27a4c5bbff6e755e41b2dff9569617a4&type=album",
                "https://sun9-25.userapi.com/impg/ngg3KqGZp7KKzzexmtUOu6jm8OUYucAaCPIPtg/_N8xMXhk8b0.jpg?size=300x300&quality=95&sign=76d6634ab18dad1cfc63848d3f17042d&type=album",
                "https://sun9-78.userapi.com/impg/wfHqFWstCpPJsOZv8vKwAeutVdb9VtBvDk1A-Q/KDhs78EELW8.jpg?size=300x300&quality=95&sign=5536064d0da667ee62121bbaa13a23ac&type=album",
                "https://sun9-10.userapi.com/impg/yn7fubl1VrLzhexQhtCsSPcq083kXj7EvcLu2A/kwuF9Hp9Y4k.jpg?size=300x300&quality=95&sign=949d2a791d94dd7aa60c693a56b24bfa&type=album",
                "https://sun9-39.userapi.com/impg/dsvYFqnBfomgJji9xH__XW_O409ccfkmzg2nAw/Nh9dSvNHoU0.jpg?size=300x300&quality=95&sign=a82c8b9ab1f3a6d4dd413c0c7071aa70&type=album",
                "https://sun9-67.userapi.com/impg/H0CmHYaATE2nsPvudK6NCk_8rQHWfUZ2u4_eyg/HjTQl5OO1rw.jpg?size=300x300&quality=95&sign=1f7ace1e1305af39c53f5df60a220be6&type=album",
                "https://sun9-67.userapi.com/impg/j2k8BTKEVDgfLwrWLOnrd0evkUdI6Dy7vHKkCw/FXbPYTawo58.jpg?size=300x300&quality=95&sign=892ca06411ecc2b5e3662e65215a0c63&type=album"
        );
        Random random = new Random();
        int index = random.nextInt(images.size());
        this.imageUrl = images.get(index);
    }
}
