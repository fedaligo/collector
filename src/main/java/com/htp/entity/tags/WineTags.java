package com.htp.entity.tags;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.wine.Wine;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "wine_tags")
public class WineTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_wine")
    private Wine wineWineTags;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tags")
    private Tags tagsWineTags;
}
