package com.htp.entity.coins;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.CoinsTags;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id","coinsTags"})
@ToString(exclude = {"coinsTags"})
@Entity
@Table(name = "coins")
public class Coins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String kind;

    @Column
    private int size;

    @Column
    private String metal;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collection")
    private Collection collectionCoins;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "coinsCoinsTags")
    private Set<CoinsTags> coinsTags = Collections.emptySet();
}
