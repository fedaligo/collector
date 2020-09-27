package com.htp.controller.requests.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 6)
    private String topic;

    private String picture;

    private int cost;

    private String info;

    @Size(min = 1, max = 30)
    private String country;

    private int release;

    @NotNull
    @NotEmpty
    private String userName;

    @Size(min = 1, max = 30)
    private String material;

    @Size(min = 1, max = 30)
    private String badgesKind;

    @Size(min = 1, max = 50)
    private String fastening;

    @Size(min = 1, max = 100)
    private String author;

    private int pages;

    @Size(min = 1, max = 100)
    private String publishingHouse;

    @Size(min = 1, max = 30)
    private String coinsKind;

    private int size;

    @Size(min = 1, max = 30)
    private String metal;

    private String perforation;

    private int value;

    @Size(min = 1, max = 30)
    private String color;

    private int alcohol;

    private int sugar;

    @Size(min = 1, max = 20)
    private String wineKind;

    private String[] tagsName;

    @PositiveOrZero
    private Long idCollection;

    @PositiveOrZero
    private Long idBadges;

    @PositiveOrZero
    private Long idBooks;

    @PositiveOrZero
    private Long idCoins;

    @PositiveOrZero
    private Long idStamps;

    @PositiveOrZero
    private Long idWine;

    @PositiveOrZero
    private Long idTags;
}
/*
*
    private String name;
    private String topic;
    private String picture;
    private int cost;
    private String info;
    private String country;
    private int release;
    private String userName;
    private String material;
    private String badgesKind;
    private String fastening;
    private String[] badgesTags;
    private String author;
    private int pages;
    private String publishingHouse;
    private String[] booksTags;
    private String coinsKind;
    private int size;
    private String metal;
    private String[] coinsTags;
    private String perforation;
    private int value;
    private String color;
    private String[] stampsTags;
    private int alcohol;
    private int sugar;
    private String wineKind;
    private String[] wineTags;
    * */
