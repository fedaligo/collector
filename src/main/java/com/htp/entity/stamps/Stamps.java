package com.htp.entity.stamps;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.StampsTags;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id","stampsTags"})
@ToString(exclude = {"stampsTags"})
@Entity
@Table(name = "stamps")
public class Stamps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private boolean perforation;

    @Column
    private int value;

    @Column
    private String color;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collection")
    private Collection collectionStamps;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "stampsStampsTags")
    private Set<StampsTags> stampsTags = Collections.emptySet();
}
