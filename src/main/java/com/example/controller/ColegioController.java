package com.example.controller;

import com.example.model.Colegio;
import java.util.*;
import com.example.service.ColegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/colegio")
@CrossOrigin(origins = "http://localhost:8080")
public class ColegioController {

    @Autowired
    private ColegioService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Colegio>> list() {
        List<Colegio> salida = null;
        try {
            salida = service.list();
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(salida);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@RequestBody Colegio colegio) {
        Map<String, Object> salida = new HashMap<>();
        try {
            colegio.setId(0);
            Colegio obj = service.save(colegio);
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(salida);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Colegio> edit(@PathVariable int id, @RequestBody Colegio colegio) {
        Colegio colegioOptional = service.getById(id);
        try {
            if (colegioOptional == null) {
                return ResponseEntity.unprocessableEntity().build();
            }
            colegio.setId(colegioOptional.getId());
            service.save(colegio);
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
