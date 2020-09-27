package com.htp.controller.requests.coins.coinstags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinsTagsCreateRequest {

    @PositiveOrZero
    private Long idCoins;

    @PositiveOrZero
    private Long idTags;
}
