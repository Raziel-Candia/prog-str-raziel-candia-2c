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
          String age=parts[2];
          result.add(name+"-"+email+"-"+age);
        }
        return result;
    }
    public void updatePerson(int index, String name, String email, String age) throws IOException {
        validate(name, email,age);
        List<String> listaoriginal;
        listaoriginal = repo.readAllLines();
        List<String> cleanLines = new ArrayList<>();
        for(String line : listaoriginal){
            if(line != null && !line.isBlank()){
                cleanLines.add(line); //esta linea esta buena ya que no es null y tampoco esta en blanco

            }
        }
        cleanLines.set(index,name+","+email+","+age);
        repo.saveFile(cleanLines); //sustituir la info del archivo dejandolo actualizado

    }
    public void delate(int index) throws IOException{
        List<String> listaoriginal;
        listaoriginal = repo.readAllLines();
        List<String> cleanLines = new ArrayList<>();
        for(String line : listaoriginal){
            if(line != null && !line.isBlank()){
                cleanLines.add(line); //esta linea esta buena ya que no es null y tampoco esta en blanco

            }
        }
        cleanLines.remove(index);
        repo.saveFile(cleanLines);
    }

    public void addPerson(String name, String email, String age) throws IOException {

        validate(name, email, age);

        repo.addNewLine(name + "," + email + "," + age);
    }

    private void validate(String name, String email, String age) {

        if (name == null || name.isBlank() || name.length() < 3) {
            throw new IllegalArgumentException("El nombre es incorrecto");
        }


        String em = (email == null) ? "" : email.trim();
        if (em.isBlank() || !em.contains("@") || !em.contains(".")) {
            throw new IllegalArgumentException("El email es inválido");
        }

        try{
            int a = Integer.parseInt(age);
            if (a < 0) {
                throw new IllegalArgumentException("La edad no puede ser negativa");
            }

            if (a < 18) {
                throw new IllegalArgumentException("Solo aceptamos mayores de edad");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("No se puyedo convertir el numero");
        }

    }
    }