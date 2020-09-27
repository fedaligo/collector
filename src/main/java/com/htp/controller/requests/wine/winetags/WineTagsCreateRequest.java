package com.htp.controller.requests.wine.winetags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineTagsCreateRequest {
    @PositiveOrZero
    private Long idWine;

    @PositiveOrZero
    private Long idTags;
}
