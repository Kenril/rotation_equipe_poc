package munk.erik.ihm;

import munk.erik.Affrontement;
import munk.erik.CalculRencontre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Affichage extends JFrame {

    private JTextField jtfNbEquipe;
    private JTextField jtfNbEpreuve;
    private JLabel jlNbEqquipe;
    private JLabel jlNbEpreuve;
    private JButton jbExecute;
    private JTable jtRencontres;
    private JPanel jpanel;

    private final CalculRencontre calculateur;

    public Affichage(CalculRencontre calculateur) {
        this.calculateur = calculateur;
        setContentPane(jpanel);
        setTitle("Calculateur de rencontre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        jbExecute.addActionListener(creerTableauDesRencontres(this));
    }

    private ActionListener creerTableauDesRencontres(Affichage affichage) {
        return e -> {
            int nbEquipe;
            int nbEpreuve;
            try {
                nbEquipe = Integer.parseInt(jtfNbEquipe.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(affichage, "Mauvais format pour le nombre d'équipes. Un nombre entier est attendu.");
                return;
            }
            try {
                nbEpreuve = Integer.parseInt(jtfNbEpreuve.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(affichage, "Mauvais format pour le nombre d'épreuves. Un nombre entier est attendu.");
                return;
            }
            List<Affrontement> listVersus = calculateur.determinerVersus(nbEquipe);
            Map<Integer, List<Affrontement>> tableau = calculateur.determinerRotations(nbEpreuve, listVersus);

            createUIComponents(tableau);
        };
    }

    private void createUIComponents(Map<Integer, List<Affrontement>> tableau) {
        if (!(jtRencontres.getModel() instanceof DefaultTableModel tableModel)) {
            throw new IllegalArgumentException("Tableau non disponible");
        }

        int maxRencontre = tableau.values().stream().map(List::size).max(Integer::compareTo).orElseThrow();
        int nbEpreuve = tableau.keySet().size();
        List<String> headers = new ArrayList<>();
        tableModel.setColumnCount(0);
        for (int i = 0; i <= nbEpreuve; i++) {
            String header = "Épreuve n°" + (i + 1);
            tableModel.addColumn(header);
            headers.add(header);
        }

        tableModel.addRow(new Object[]{"Rotation\\Épreuve"});
        tableModel.setValueAt("Rotation\\Épreuve", 0, 0);
        for (int i = 1; i < headers.size(); i++) {
            if (i < tableModel.getColumnCount()) {
                tableModel.setValueAt(headers.get(i - 1), 0, i);
            }
        }

        tableModel.setRowCount(maxRencontre + 1);

        for (int i = 1; i <= maxRencontre; i++) {
            tableModel.setValueAt("Rotation n°" + i, i, 0);
        }
        for (Map.Entry<Integer, List<Affrontement>> entry : tableau.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                tableModel.setValueAt(entry.getValue().get(i).toString(), i + 1, entry.getKey());
            }
        }
    }
}
