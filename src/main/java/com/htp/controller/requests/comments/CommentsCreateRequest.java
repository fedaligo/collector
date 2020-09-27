package com.htp.controller.requests.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 256)
    private String comment;

    @PositiveOrZero
    private Long idCollection;

    @NotNull
    @NotEmpty
    private String authHeader;
}
