package com.htp.controller.requests.badges;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BadgesUpdateRequest extends BadgesCreateRequest{
    private Long badgesId;
}
