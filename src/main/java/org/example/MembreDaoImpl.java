package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao {

    private static final String INSERT_SQL = "INSERT INTO Membre (nom, prenom, email, phone) VALUES (?, ?, ?, ?)";

    @Override
    public void inserer(Membre m) {
        // Insert into the database instead of printing
        try (Connection connection = DatabaseConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
                // Fill the parameters for the SQL query
                statement.setString(1, m.getNom());
                statement.setString(2, m.getPrenom());
                statement.setString(3, m.getEmail());
                statement.setString(4, m.getPhone());

                // Execute the query
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Membre ajouté avec succès !");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Membre> chargerListeMembre(String nomFichier) {
        List<Membre> membres = new ArrayList<>();

        // Utilisation de getClass().getClassLoader().getResourceAsStream pour charger le fichier depuis les ressources
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(nomFichier)))) {

            String line;
            br.readLine(); // Lire la première ligne qui est l'entête et l'ignorer

            // Lire toutes les lignes du fichier CSV
            while ((line = br.readLine()) != null) {
                // Séparer les valeurs par la virgule
                String[] parts = line.split(",");
                if (parts.length == 4) { // Assurez-vous que chaque ligne a bien 4 éléments
                    String nom = parts[0].trim();
                    String prenom = parts[1].trim();
                    String email = parts[2].trim();
                    String phone = parts[3].trim();

                    // Créer un objet Membre et l'ajouter à l'ensemble
                    Membre membre = new Membre(null, nom, prenom, email, phone);
                    membres.add(membre);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return membres;
    }


}