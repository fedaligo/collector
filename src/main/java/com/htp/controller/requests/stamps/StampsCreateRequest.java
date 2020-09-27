package com.htp.controller.requests.stamps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StampsCreateRequest {
    private String perforation;

    private int value;

    @Size(min = 1, max = 30)
    private String color;
}
