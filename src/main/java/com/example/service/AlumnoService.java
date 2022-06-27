package com.example.service;

import com.example.model.Alumno;
import com.example.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository repository;

    //Método List
    public List<Alumno> list() {
        return repository.findAll();
    }

    //Método Get By Id
    public Alumno getById(int id) {
        return repository.findById(id).get();
    }

    //Método Save and Update
    public Alumno save(Alumno alumno) {
        return repository.save(alumno);
    }

    //Método Delete
    public void delete(int id) {
        repository.deleteById(id);
    }
}
