package com.htp.controller.requests.collection;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollectionUpdateRequest extends CollectionCreateRequest{
    private Long collectionId;
    private Long badgesId;
    private Long booksId;
    private Long coinsId;
    private Long stampsId;
    private Long wineId;
    private String tagsIterate;
}
