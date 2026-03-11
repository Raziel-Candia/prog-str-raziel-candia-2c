package com.example.demolistviewfile.services;

import com.example.demolistviewfile.repositories.PersonFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    PersonFileRepository repo = new PersonFileRepository();

    public List<String> loadForListView() throws IOException {
        List<String> lines = repo.readAllLines();
        List<String> result= new ArrayList<>();
        for(String line : lines){
          if(line==null || line.isBlank()) continue;

          String[] parts= line.split(",");
          String name=parts[0];
          String email=parts[1];
          result.add(name+"-"+email);
        }
        return result;
    }

    public void addPerson(String name, String email, int age) throws IOException {
        // 1. PRIMERO llamamos a validar.
        // Si la edad es 10, aquí se lanzará la excepción y NO pasará a la siguiente línea.
        validate(name, email, age);

        // 2. Si pasó la validación, guardamos los TRES datos (incluyendo la edad)
        repo.addNewLine(name + "," + email + "," + age);
    }

    private void validate(String name, String email, int age) {

        if (name == null || name.isBlank() || name.length() < 3) {
            throw new IllegalArgumentException("El nombre es incorrecto");
        }


        String em = (email == null) ? "" : email.trim();
        if (em.isBlank() || !em.contains("@") || !em.contains(".")) {
            throw new IllegalArgumentException("El email es inválido");
        }


        if (age < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }

        if (age < 18) {
            throw new IllegalArgumentException("Solo aceptamos mayores de edad");
        }
    }
    }