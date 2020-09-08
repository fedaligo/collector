package com.htp.entity.tags;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.coins.Coins;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "coins_tags")
public class CoinsTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_coins")
    private Coins coinsCoinsTags;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tags")
    private Tags tagsCoinsTags;
}
