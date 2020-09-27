package com.htp.controller.requests.likes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesCreateRequest {

    @PositiveOrZero
    private Long idCollection;

    @NotNull
    @NotEmpty
    private String authHeader;
}
