package com.htp.entity.likes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.badges.Badges;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.Tags;
import com.htp.entity.users.Users;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_users")
    private Users usersLikes;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collection")
    private Collection collectionLikes;
}
