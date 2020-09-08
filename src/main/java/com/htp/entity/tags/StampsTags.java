package com.htp.entity.tags;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.stamps.Stamps;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "stamps_tags")
public class StampsTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stamps")
    private Stamps stampsStampsTags;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tags")
    private Tags tagsStampsTags;
}
