package org.example;

import org.example.Membre;

import java.util.List;

public interface MembreDao {
    void inserer(Membre m);
    List<Membre> chargerListeMembre(String nomFichier);
}