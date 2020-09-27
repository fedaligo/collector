package com.htp.controller.requests.tags;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TagsUpdateRequest extends TagsCreateRequest{
    private Long tagsId;
}
