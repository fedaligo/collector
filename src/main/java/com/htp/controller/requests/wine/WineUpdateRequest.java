package com.htp.controller.requests.wine;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WineUpdateRequest extends WineCreateRequest{
    private Long wineId;
}
