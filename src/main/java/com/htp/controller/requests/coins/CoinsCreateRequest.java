package com.htp.controller.requests.coins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinsCreateRequest {
    @Size(min = 1, max = 30)
    private String coinsKind;

    private int size;

    @Size(min = 1, max = 30)
    private String metal;
}
