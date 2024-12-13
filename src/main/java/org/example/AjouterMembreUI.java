package org.example;
import org.example.Membre;
import org.example.MembreDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AjouterMembreUI extends JFrame {
    // Attributs pour les champs de saisie
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JTextField phoneField;

    private MembreDaoImpl membreDao; // Instance du DAO pour insérer les membres

    public AjouterMembreUI() {
        // Initialiser le DAO
        membreDao = new MembreDaoImpl();

        // Configuration de la fenêtre
        Frame frame = new Frame("Interface");
        setTitle("Ajouter un nouveau membre");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Ajouter les champs de saisie
        panel.add(new JLabel("Nom :"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Prénom :"));
        prenomField = new JTextField();
        panel.add(prenomField);

        panel.add(new JLabel("Email :"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Phone :"));
        phoneField = new JTextField();
        panel.add(phoneField);

        // Ajouter un bouton "Insérer"
        JButton insererButton = new JButton("Insérer");
        panel.add(new JLabel()); // Pour équilibrer la grille
        panel.add(insererButton);

        // Ajouter le panel au centre de la fenêtre
        add(panel, BorderLayout.CENTER);

        // Gérer l'événement clic sur le bouton "Insérer"
        insererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterMembre();
            }
        });
    }

    private void ajouterMembre() {
        // Récupérer les données des champs de saisie
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        // Vérifier que tous les champs sont remplis
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Créer un nouveau membre
        Membre membre = new Membre(null, nom, prenom, email, phone);

        // Insérer le membre dans la base de données
        membreDao.inserer(membre);

        // Afficher un message de succès
        JOptionPane.showMessageDialog(this, "Membre ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

        // Réinitialiser les champs
        nomField.setText("");
        prenomField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    public static void main(String[] args) {
        // Créer et afficher l'interface graphique
        SwingUtilities.invokeLater(() -> {
            AjouterMembreUI ui = new AjouterMembreUI();
            ui.setVisible(true);
        });

        MembreDaoImpl membreDao = new MembreDaoImpl();

        // Charger les membres à partir du fichier CSV
        List<Membre> membres = membreDao.chargerListeMembre("Membres.csv");

        // Vérifier si plusieurs membres sont bien chargés
        if (membres.isEmpty()) {
            System.out.println("Aucun membre chargé.");
        } else {
            for (Membre membre : membres) {
                System.out.println(membre);
            }
        }
    }

}