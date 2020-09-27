package com.htp.controller.requests.stamps.stampstags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StampsTagsCreateRequest {
    @PositiveOrZero
    private Long idStamps;

    @PositiveOrZero
    private Long idTags;
}
