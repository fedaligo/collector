package com.htp.controller.requests.wine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineCreateRequest {
    private int alcohol;

    private int sugar;

    @Size(min = 1, max = 20)
    private String wineKind;
}
