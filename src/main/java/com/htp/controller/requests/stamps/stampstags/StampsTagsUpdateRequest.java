package com.htp.controller.requests.stamps.stampstags;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StampsTagsUpdateRequest extends StampsTagsCreateRequest{
    private Long stampsTagsId;
}
