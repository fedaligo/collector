package com.htp.controller.requests.likes;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LikesUpdateRequest extends LikesCreateRequest{
    private Long likesId;
}
