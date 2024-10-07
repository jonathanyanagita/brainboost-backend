package com.brainboost.brainboost.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "frente")
    private String frente;

    @NotBlank
    @Column(name = "verso")
    private String verso;

    @Column(name = "data")
    private LocalDateTime localDateTime;

    @Column(name = "level")
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "baralho_id")
    private Baralho baralho;

    public Flashcard(Long id, String frente, String verso, LocalDateTime localDateTime, Integer level) {
        this.id = id;
        this.frente = frente;
        this.verso = verso;
        this.localDateTime = localDateTime;
        this.level = level;
    }
}
