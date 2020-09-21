package com.htp.entity.comments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.collection.Collection;
import com.htp.entity.users.Users;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_users")
    private Users usersComments;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collection")
    private Collection collectionComments;

    @Column(name = "comment")
    private String comment;

    @Column
    private Timestamp created;
}
