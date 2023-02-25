package com.coder.ws.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDataDTO {
    private Long id;
    private String codebook;
    private String title;
    private String description;
    private boolean published;
}
