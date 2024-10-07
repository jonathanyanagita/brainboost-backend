package com.brainboost.brainboost.Controller;

import com.brainboost.brainboost.Dao.FlashcardDao;
import com.brainboost.brainboost.Dto.AtualizarFlashcardDto;
import com.brainboost.brainboost.Dto.FlashcardDto;
import com.brainboost.brainboost.Entity.Baralho;
import com.brainboost.brainboost.Entity.Flashcard;
import com.brainboost.brainboost.Repository.BaralhoRepository;
import com.brainboost.brainboost.Repository.FlashcardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashcards")
public class FlashcardController {

    @Autowired
    private FlashcardRepository repository;

    @Autowired
    private BaralhoRepository baralhoRepository;

    @PostMapping("/{baralhoId}")
    private ResponseEntity<?> adicionarFlashcard(@PathVariable Long baralhoId, @Valid @RequestBody FlashcardDto flashcardDto) {

        Baralho baralho = baralhoRepository.findById(baralhoId)
                .orElseThrow(() -> new RuntimeException("Baralho não encontrado"));

        Flashcard novoFlashcard = new Flashcard(flashcardDto.getId(), flashcardDto.getFrente(), flashcardDto.getVerso(), flashcardDto.getData(), flashcardDto.getLevel());
        novoFlashcard.setBaralho(baralho);
        repository.save(novoFlashcard);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flashcard criado com sucesso!");
    }


    @GetMapping("/{id}")
    public ResponseEntity<FlashcardDao> getFlashcardById(@PathVariable Long id) {
        return repository.findById(id)
                .map(flashcard -> {
                    FlashcardDao responseDto = new FlashcardDao(flashcard.getFrente(), flashcard.getVerso());
                    return ResponseEntity.ok(responseDto);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/frente/{id}")
    public ResponseEntity<String> getFlashcardFrenteById(@PathVariable Long id) {
        return repository.findById(id)
                .map(flashcard -> ResponseEntity.ok(flashcard.getFrente()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/verso/{id}")
    public ResponseEntity<String> getFlashcardVersoById(@PathVariable Long id) {
        return repository.findById(id)
                .map(flashcard -> ResponseEntity.ok(flashcard.getVerso()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<?> ListarTodosFlashcards() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarFlashcard(@PathVariable Long id, @RequestBody @Valid AtualizarFlashcardDto flashcardDto) {
        return repository.findById(id)
                .map(flashcard -> {
                    flashcard.setFrente(flashcardDto.getFrente());
                    flashcard.setVerso(flashcardDto.getVerso());
                    repository.save(flashcard);
                    return ResponseEntity.ok("Flashcard atualizado com sucesso!");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarFlashcard(@PathVariable Long id) {
        return repository.findById(id)
                .map(flashcard -> {
                    repository.delete(flashcard);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



}
