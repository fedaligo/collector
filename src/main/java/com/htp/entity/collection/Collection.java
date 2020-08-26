package com.htp.entity.collection;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private String topic;

    @Column
    private String picture;

    @Column
    private int cost;

    @Column
    @Type(type="text")
    private String info;

    @Column
    private String country;

    @Column
    private int release;
}
