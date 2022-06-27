package com.example.controller;

import com.example.model.Alumno;
import com.example.model.Colegio;
import java.util.*;
import com.example.service.AlumnoService;
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
@RequestMapping("/api/alumno")
@CrossOrigin(origins = "http://localhost:8080")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private ColegioService colegioService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Alumno>> list() {
        List<Alumno> salida = null;
        try {
            salida = alumnoService.list();
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(salida);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Alumno> save(@RequestBody Alumno alumno) {
        Colegio colegioOptional = colegioService.getById(alumno.getColegio().getId());
        try {
            if (colegioOptional == null) {
                return ResponseEntity.unprocessableEntity().build();
            }
            alumno.setColegio(colegioOptional);
            alumno.setId(0);
            Alumno obj = alumnoService.save(alumno);
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Alumno> edit(@PathVariable int id, @RequestBody Alumno alumno) {
        Colegio colegioOptional = colegioService.getById(alumno.getColegio().getId());
        if (colegioOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Alumno alumnoOptional = alumnoService.getById(id);
        if (alumnoOptional == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        alumno.setColegio(colegioOptional);
        alumno.setId(alumnoOptional.getId());
        alumnoService.save(alumno);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        alumnoService.delete(id);
    }
}
