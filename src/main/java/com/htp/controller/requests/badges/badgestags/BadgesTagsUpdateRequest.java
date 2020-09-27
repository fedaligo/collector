package com.htp.controller.requests.badges.badgestags;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BadgesTagsUpdateRequest extends BadgesTagsCreateRequest{
    private Long badgesTagsId;
}
