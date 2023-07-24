/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pension;


import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.proteanit.sql.DbUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author Gael
 */
public class Tarif extends javax.swing.JFrame {
    
    public Connection connection;
    public PreparedStatement pst;
    private int selectedRowIndex;
    private static final Logger LOGGER = Logger.getLogger(Tarif.class.getName());


    /**
     * Creates new form Tarif
     */
    public Tarif() throws SQLException {
        initComponents();
        connection = DatabaseConnection.getConnection();
        table_load();
        Showpiechart();
        TableTarif.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (TableTarif.getSelectedRow() != -1) {
                    selectedRowIndex = TableTarif.getSelectedRow();
                    // Appeler la méthode pour afficher les informations de la ligne sélectionnée
                    displaySelectedRowTarif();
                }
            }
        });
        
        
        
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        
    }
    
    
    private void table_load() {
        try {
            pst = connection.prepareStatement("SELECT numTarif, diplome, categorie, montant FROM tarif");
            ResultSet rs = pst.executeQuery();
            TableTarif.setModel(DbUtils.resultSetToTableModel(rs)); // Utilisation de la méthode buildTableModel
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void displaySelectedRowTarif() {
        // Récupérer les valeurs des colonnes de la ligne sélectionnée
        String diplome = TableTarif.getValueAt(selectedRowIndex, 1).toString();
        String categorie = TableTarif.getValueAt(selectedRowIndex, 2).toString();
        String montant = TableTarif.getValueAt(selectedRowIndex, 3).toString();

        // Afficher les valeurs dans les champs de texte correspondants
        txtDiplome.setText(diplome);
        txtCategorie.setText(categorie);
        txtMontant.setText(montant);
    }     
             public void Showpiechart() throws SQLException {
          String url ="jdbc:mysql://localhost:3308/pension";
String utilisateur = "root";
String motDePasse = "";

// Établir la connexion à la base de données
Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
          connection = DatabaseConnection.getConnection();
 String query1 = "SELECT * from tarif ";
      
        PreparedStatement pst2 = connection.prepareStatement(query1);

            ResultSet rs2 = pst2.executeQuery();

DefaultPieDataset barDataset = new DefaultPieDataset();

while (rs2.next()) {
    String label = rs2.getString("diplome");
    double valeur = rs2.getDouble("montant");
        
    barDataset.setValue(label, valeur);
}
/*resultat.close();
statement.close();
connection.close();*/ 

           
           
           
    // Créer le dataset
   // DefaultPieDataset barDataset = new DefaultPieDataset();
  /*  barDataset.setValue("Komijola",  valeur );
    barDataset.setValue("Dino", new Double( 20 ));
    barDataset.setValue("zoela", new Double( 40 ));
    barDataset.setValue("Gael", new Double( 10 ));*/

    // Créer le graphique circulaire
     JFreeChart piechart = ChartFactory.createPieChart("", barDataset, false, true, false);
    PiePlot piePlot = (PiePlot) piechart.getPlot();


    // Changer les couleurs des sections
  /*  piePlot.setSectionPaint("Komijola", new Color(255, 255, 102));
    piePlot.setSectionPaint("Dino", new Color(102, 255, 102));
    piePlot.setSectionPaint("zoela", new Color(255, 102, 153));
    piePlot.setSectionPaint("Gael", new Color(0, 204, 204));
*/
    piePlot.setBackgroundPaint(Color.white);

    // Créer le panel pour afficher le graphique
    ChartPanel barChartPanel = new ChartPanel(piechart);
    panelBarChart.setLayout(new BorderLayout());
    panelBarChart.removeAll();
    panelBarChart.add(barChartPanel, BorderLayout.CENTER);
    panelBarChart.validate();
}
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTarif = new javax.swing.JTable();
        txtNumTarif = new javax.swing.JTextField();
        numTarif = new javax.swing.JLabel();
        txtDiplome = new javax.swing.JTextField();
        diplome = new javax.swing.JLabel();
        categorie = new javax.swing.JLabel();
        txtCategorie = new javax.swing.JTextField();
        txtMontant = new javax.swing.JTextField();
        montant = new javax.swing.JLabel();
        bouttonPayer = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelBarChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pension/t.png"))); // NOI18N
        jLabel1.setText("TARIF");
        jLabel1.setOpaque(true);

        TableTarif.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableTarif);
        if (TableTarif.getColumnModel().getColumnCount() > 0) {
            TableTarif.getColumnModel().getColumn(0).setResizable(false);
            TableTarif.getColumnModel().getColumn(1).setResizable(false);
            TableTarif.getColumnModel().getColumn(2).setResizable(false);
            TableTarif.getColumnModel().getColumn(3).setResizable(false);
        }

        numTarif.setBackground(new java.awt.Color(0, 102, 102));
        numTarif.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        numTarif.setForeground(new java.awt.Color(0, 0, 0));
        numTarif.setText("Num Tarif");
        numTarif.setOpaque(true);

        diplome.setBackground(new java.awt.Color(0, 102, 102));
        diplome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        diplome.setForeground(new java.awt.Color(0, 0, 0));
        diplome.setText("DIPLOME :");
        diplome.setOpaque(true);

        categorie.setBackground(new java.awt.Color(0, 102, 102));
        categorie.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        categorie.setForeground(new java.awt.Color(0, 0, 0));
        categorie.setText("CATEGORIE :");
        categorie.setOpaque(true);

        txtMontant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontantActionPerformed(evt);
            }
        });

        montant.setBackground(new java.awt.Color(0, 102, 102));
        montant.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        montant.setForeground(new java.awt.Color(0, 0, 0));
        montant.setText("MONTANT : ");
        montant.setOpaque(true);

        bouttonPayer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bouttonPayer.setForeground(new java.awt.Color(0, 51, 51));
        bouttonPayer.setText("PAYER");
        bouttonPayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bouttonPayerActionPerformed(evt);
            }
        });

        buttonDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonDelete.setForeground(new java.awt.Color(0, 51, 51));
        buttonDelete.setText("DELETE");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonUpdate.setForeground(new java.awt.Color(0, 51, 51));
        buttonUpdate.setText("UPDATE");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        jButton1.setText("RETOUR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBarChartLayout = new javax.swing.GroupLayout(panelBarChart);
        panelBarChart.setLayout(panelBarChartLayout);
        panelBarChartLayout.setHorizontalGroup(
            panelBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );
        panelBarChartLayout.setVerticalGroup(
            panelBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiplome, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categorie, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(diplome, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(numTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(panelBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bouttonPayer, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(buttonDelete)
                                .addGap(44, 44, 44)
                                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jButton1))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 28, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(diplome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiplome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categorie, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(119, 119, 119))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(buttonUpdate)
                                .addComponent(buttonDelete)
                                .addComponent(bouttonPayer))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontantActionPerformed

    private void bouttonPayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bouttonPayerActionPerformed
        String diplome, categorie, montant, numTarif;
        numTarif = txtNumTarif.getText();
        diplome = txtDiplome.getText();
        categorie = txtCategorie.getText();
        montant = txtMontant.getText();
        try {
            pst = connection.prepareStatement("INSERT INTO tarif (numTarif,diplome, categorie, montant) VALUES (?, ?, ?, ?)");
            pst.setString(1, numTarif);
            pst.setString(2, diplome);
            pst.setString(3, categorie);
            pst.setString(4, montant);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ajout effectué");
            table_load();
            Showpiechart();
            
            txtDiplome.setText("");
            txtCategorie.setText("");
            txtMontant.setText("");
            txtDiplome.requestFocus();
        }
        catch (SQLException e) {
        e.printStackTrace();
        }
        
    }//GEN-LAST:event_bouttonPayerActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
    String diplome = txtDiplome.getText();
    String categorie = txtCategorie.getText();
    String montant = txtMontant.getText();  


    String id = TableTarif.getValueAt(selectedRowIndex, 0).toString();    
    
        try {

            pst = connection.prepareStatement("UPDATE tarif SET diplome = ?, categorie = ?, montant = ? WHERE numTarif = ?");
            pst.setString(1, diplome);
            pst.setString(2, categorie);
            pst.setString(3, montant);
            pst.setString(4, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mise à jour effectuée");
            table_load(); // Recharger la table avec les données mises à jour
            txtDiplome.setText("");
            txtCategorie.setText("");
            txtMontant.setText("");
            txtDiplome.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
    // TODO add your handling code here:
    // Vérifier si une ligne est sélectionnée
    if (TableTarif.getSelectedRow() != -1) {
        // Récupérer l'ID de l'employé sélectionné dans la table
        int selectedRowIndex = TableTarif.getSelectedRow();
        String id = TableTarif.getValueAt(selectedRowIndex, 0).toString();

        try {
            LOGGER.info("ID à Supprimé : " + id); // Vérifier la valeur de l'ID
            pst = connection.prepareStatement("DELETE FROM tarif WHERE numTarif = ?");
            pst.setString(1, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Suppression effectuée");
            table_load(); // Recharger la table avec les données mises à jour
            txtDiplome.setText("");
            txtCategorie.setText("");
            txtMontant.setText("");
            txtDiplome.requestFocus();
            selectedRowIndex = -1; // Réinitialiser la valeur de selectedRowIndex à -1
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression", e);
        }
    } 
    }//GEN-LAST:event_buttonDeleteActionPerformed

    

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                   this.hide();
                  HOME obj = new HOME();
        obj.show();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tarif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tarif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tarif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tarif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Tarif().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Tarif.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableTarif;
    private javax.swing.JButton bouttonPayer;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel categorie;
    private javax.swing.JLabel diplome;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel montant;
    private javax.swing.JLabel numTarif;
    private javax.swing.JPanel panelBarChart;
    private javax.swing.JTextField txtCategorie;
    private javax.swing.JTextField txtDiplome;
    private javax.swing.JTextField txtMontant;
    private javax.swing.JTextField txtNumTarif;
    // End of variables declaration//GEN-END:variables
}
