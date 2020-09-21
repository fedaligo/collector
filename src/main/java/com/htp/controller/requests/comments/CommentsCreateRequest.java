package com.htp.controller.requests.comments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.collection.Collection;
import com.htp.entity.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

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
