package com.htp.entity.tags;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id","badgesTags","booksTags","coinsTags","stampsTags","wineTags"})
@ToString(exclude = {"badgesTags","booksTags","coinsTags","stampsTags","wineTags"})
@Entity
@Table(name = "tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "tagsBadgesTags")
    private Set<BadgesTags> badgesTags = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "tagsBooksTags")
    private Set<BooksTags> booksTags = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "tagsCoinsTags")
    private Set<CoinsTags> coinsTags = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "tagsStampsTags")
    private Set<StampsTags> stampsTags = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "tagsWineTags")
    private Set<WineTags> wineTags = Collections.emptySet();
}
