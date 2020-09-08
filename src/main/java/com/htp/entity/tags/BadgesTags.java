package com.htp.entity.tags;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.badges.Badges;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "badges_tags")
public class BadgesTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_badges")
    private Badges badgesBadgesTags;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tags")
    private Tags tagsBadgesTags;
}
