package org.example;

import org.example.Incident;
import org.example.IncidentDao;

import java.util.Set;

public class IncidentDaoImpl implements IncidentDao {
    @Override
    public void inserer(Incident i) {
        System.out.println("Incident inséré : " + i);
    }

    @Override
    public void inserer(Set<Incident> incidents) {
        for (Incident incident : incidents) {
            inserer(incident);
        }
    }


}