package com.htp.controller.requests.badges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgesCreateRequest {
    @Size(min = 1, max = 30)
    private String material;

    @Size(min = 1, max = 30)
    private String badgesKind;

    @Size(min = 1, max = 50)
    private String fastening;
}
