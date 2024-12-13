package org.example;

import java.util.Set;

public interface IncidentDao {
    void inserer(Incident i);
    void inserer(Set<Incident> incidents);
}