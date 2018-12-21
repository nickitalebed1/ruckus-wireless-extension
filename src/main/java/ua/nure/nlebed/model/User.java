package ua.nure.nlebed.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    @Transient
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column
    private LocalDateTime lastConnectionTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<UserDetails> userDetails;

    public User(String email, String password, String name, String lastName, String photoUrl, Set<Role> roles, Set<UserDetails> userDetails, LocalDateTime lastConnectionTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.photoUrl = photoUrl;
        this.lastConnectionTime = lastConnectionTime;
        this.roles = roles;
        this.userDetails = userDetails;
    }
}
