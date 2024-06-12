/*
package springalbums.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Integer id;

    @Column(columnDefinition = "varchar(22) default 'СаундСупер'")
    private String username;

    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rates;

}
*/