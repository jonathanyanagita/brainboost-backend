package com.brainboost.brainboost.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardDto {

    private Long id;

    @NotBlank
    private String frente;

    @NotBlank
    private String verso;

    private Integer level = 0;

    private LocalDateTime data = LocalDateTime.now();

    private Long baralho_id;
}
