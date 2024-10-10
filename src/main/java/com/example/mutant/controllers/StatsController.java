package com.example.mutant.controllers;

import com.example.mutant.dto.ResponseStats;
import com.example.mutant.services.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
// Se comunica con el Service y por ultimo envia el response con el formato del DTO
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseStats getStats() {
        return statsService.getStats(); // Llama al metodo que se encuentra en el Service y retorna el response con el formato del DTO
    }
}
