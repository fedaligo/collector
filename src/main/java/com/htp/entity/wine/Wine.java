package com.htp.entity.wine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.WineTags;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id","wineTags"})
@ToString(exclude = {"wineTags"})
@Entity
@Table(name = "wine")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private int alcohol;

    @Column
    private int sugar;

    @Column
    private String kind;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collection")
    private Collection collectionWine;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "wineWineTags")
    private Set<WineTags> wineTags = Collections.emptySet();
}
