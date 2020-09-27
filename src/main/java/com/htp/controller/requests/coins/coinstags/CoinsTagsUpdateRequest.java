package com.htp.controller.requests.coins.coinstags;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CoinsTagsUpdateRequest extends CoinsTagsCreateRequest{
    private Long coinsTagsId;
}
