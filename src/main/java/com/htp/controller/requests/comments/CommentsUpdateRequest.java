package com.htp.controller.requests.comments;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentsUpdateRequest extends CommentsCreateRequest{
    private Long commentsId;
}
