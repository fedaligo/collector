package com.htp.controller.requests.stamps;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StampsUpdateRequest extends StampsCreateRequest{
    private Long stampsId;
}
