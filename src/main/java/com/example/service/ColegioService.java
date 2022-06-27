package com.example.service;

import com.example.model.Colegio;
import com.example.repository.ColegioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColegioService {

    @Autowired
    private ColegioRepository repository;

    //Método List
    public List<Colegio> list() {
        return repository.findAll();
    }

    //Método Get By Id
    public Colegio getById(int id) {
        return repository.findById(id).get();
    }

    //Método Save and Update
    public Colegio save(Colegio colegio) {
        return repository.save(colegio);
    }

    //Método Delete
    public void delete(int id) {
        repository.deleteById(id);
    }
}
