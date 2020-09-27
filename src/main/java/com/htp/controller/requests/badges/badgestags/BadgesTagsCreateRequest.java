package com.htp.controller.requests.badges.badgestags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgesTagsCreateRequest {
    @PositiveOrZero
    private Long idBadges;

    @PositiveOrZero
    private Long idTags;
}
