package munk.erik.ihm;

import munk.erik.Affrontement;
import munk.erik.CalculRencontre;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        setTitle("Calculateur de rencontre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(jpanel);
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        jpanel = new JPanel();
        jpanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 6, new Insets(0, 0, 0, 0), -1, -1));
        jpanel.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        jpanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        jbExecute = new JButton();
        jbExecute.setText("Créer tableau");
        jpanel.add(jbExecute, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jlNbEpreuve = new JLabel();
        jlNbEpreuve.setHorizontalAlignment(0);
        jlNbEpreuve.setHorizontalTextPosition(0);
        jlNbEpreuve.setText("Nombre d'épreuve");
        jpanel.add(jlNbEpreuve, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jlNbEqquipe = new JLabel();
        jlNbEqquipe.setHorizontalTextPosition(0);
        jlNbEqquipe.setText("Nombre d'équipe");
        jpanel.add(jlNbEqquipe, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jtfNbEquipe = new JTextField();
        jpanel.add(jtfNbEquipe, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        jtfNbEpreuve = new JTextField();
        jpanel.add(jtfNbEpreuve, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        jpanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(10, 10), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        jpanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        jpanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        jpanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        jtRencontres = new JTable();
        scrollPane1.setViewportView(jtRencontres);
        jlNbEpreuve.setLabelFor(jtfNbEpreuve);
        jlNbEqquipe.setLabelFor(jtfNbEquipe);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jpanel;
    }
}
