package com.htp.entity.collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.entity.badges.Badges;
import com.htp.entity.books.Books;
import com.htp.entity.coins.Coins;
import com.htp.entity.stamps.Stamps;
import com.htp.entity.users.Users;
import com.htp.entity.wine.Wine;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id","badges","books","coins","stamps","wine"})
@ToString(exclude = {"badges","books","coins","stamps","wine"})
@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private String topic;

    @Column
    private String picture;

    @Column
    private int cost;

    @Column
    @Type(type = "text")
    private String info;

    @Column
    private String country;

    @Column
    private int release;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Users userCollection;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "collectionBadges")
    private Set<Badges> badges = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "collectionBooks")
    private Set<Books> books = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "collectionCoins")
    private Set<Coins> coins = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "collectionStamps")
    private Set<Stamps> stamps = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "collectionWine")
    private Set<Wine> wine = Collections.emptySet();
}
