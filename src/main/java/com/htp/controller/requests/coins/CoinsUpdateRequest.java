package com.htp.controller.requests.coins;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CoinsUpdateRequest extends CoinsCreateRequest{
    private Long coinsId;
}
