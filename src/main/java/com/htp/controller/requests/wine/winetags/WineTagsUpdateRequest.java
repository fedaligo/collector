package com.htp.controller.requests.wine.winetags;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WineTagsUpdateRequest extends WineTagsCreateRequest{
    private Long wineTagsId;
}
