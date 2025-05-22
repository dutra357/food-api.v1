package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.metrics.VendaDiariaOutput;
import com.dutra.food_api.domain.repositories.filters.VendaDiariaFilter;
import com.dutra.food_api.domain.services.interfaces.VendasMetricasInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metricas")
public class MetricasController {

    public final VendasMetricasInterface vendasMetricasService;

    public MetricasController(VendasMetricasInterface vendasMetricasService) {
        this.vendasMetricasService = vendasMetricasService;
    }

    @GetMapping("/vendas-diarias")
    public ResponseEntity<List<VendaDiariaOutput>> consultarVendasDiarias(VendaDiariaFilter filtro) {
        return ResponseEntity.ok(vendasMetricasService.consultarVendasDiarias(filtro));
    }
}
