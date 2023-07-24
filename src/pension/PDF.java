/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pension;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Beanjova
 */
public class PDF {
    
     public static void pdf(){
          boolean statut=true ;
              
        Document doc = new Document();
        
              DatabaseConnection c = new DatabaseConnection();
               Connection conn = null;
               Statement statement = null; 
               ResultSet resultSet = null;
               Statement statement1 = null; 
               ResultSet resultSet1 = null;
               Statement statement2 = null; 
               ResultSet resultSet2 = null;
                 Statement statement4 = null; 
               ResultSet resultSet4 = null;
               
               
        try{
            if(Payer.txtIm.getText() != null){
                PdfWriter.getInstance(doc, new FileOutputStream("RECU.pdf"));
                  //  PdfWriter.getInstance(doc, new FileOutputStream("output.pdf"));
                    doc.open();

                    conn = c.getConnection();

                    String query = "SELECT  * FROM personne WHERE IM = '"+Payer.txtIm.getText()+"';";

                    statement = conn.createStatement();
                    resultSet = statement.executeQuery(query);
                        String id = "";
                        String nom = "";
                        String prenom = "";
                        //boolean statut ;
              
                    while(resultSet.next()){

                        id = resultSet.getString("im");
                        nom = resultSet.getString("nom");
                        prenom = resultSet.getString("prenom");
                        statut = resultSet.getBoolean("statut");

                    }


                    resultSet.close();
                    statement.close();


                    /*------------------*/
                    String query1 = "SELECT  date FROM payer WHERE im = '"+Payer.txtIm.getText()+"';";
                     statement1 = conn.createStatement();
                    resultSet1 = statement1.executeQuery(query1);

                            /*------------------*/

                        String[] d  = {" "};
                        String mois = "";
                        String annee = "";
                        String jour = "";

                    while(resultSet1.next()){

                         String date = resultSet1.getString("date");

                         d = date.split("-");
                         annee = d[0];
                        mois = d[1];
                        jour = d[2];

                    }   


                    resultSet1.close();
                    statement1.close();




                        /*---------------------*/
                    String query2 = "SELECT  montant FROM tarif WHERE numTarif IN (SELECT numTarif FROM payer WHERE im = '"+Payer.txtIm.getText()+"');";

                    statement2 = conn.createStatement();
                    resultSet2 = statement2.executeQuery(query2);

                     String montant = null;

                          while(resultSet2.next()){

                           montant = resultSet2.getString("montant");
                    }   

                     resultSet2.close();
                    statement2.close();

                 //   conn.close();

                    /*------------------------------*/
                    if(statut == true){
                         doc.add(new Paragraph(" RECU DE PENSION "));
                        doc.add(new Paragraph(" "));
                        doc.add(new Paragraph("  DATE :  " +mois +" - "+annee + " - "+ jour + " "));
                        doc.add(new Paragraph(" "));
                        doc.add(new Paragraph("im  : " + id));
                        doc.add(new Paragraph("nom  : "+ nom ));
                        doc.add(new Paragraph("prenom  : "+ prenom ));
                        doc.add(new Paragraph("Mois  : " + mois ));
                        doc.add(new Paragraph("Année  : " + annee));   
                        doc.add(new Paragraph("montant  : " + montant + " Ar "));













                    doc.close();
                   JOptionPane.showMessageDialog(null, "PDF générée");
                   
                 // payement.affirmation1.setVisible(true);
                 }else{
                    
                         String query4 = "SELECT  nomConjoint, prenomConjoint, montant FROM conjoint WHERE nomConjoint IN (SELECT nomConjoint FROM personne  WHERE im = '"+Payer.txtIm.getText()+"');";

                    statement4 = conn.createStatement();
                    resultSet4 = statement4.executeQuery(query4);

                     String montant4 = null;

                          while(resultSet4.next()){

                           nom = resultSet4.getString("nomConjoint");
                             prenom = resultSet4.getString("prenomConjoint");
                             montant = resultSet4.getString("montant");
                             
                            doc.add(new Paragraph(" RECU DE PENSION "));
                        doc.add(new Paragraph(" "));
                        doc.add(new Paragraph(" DATE : " +mois +" - "+annee + " - "+ jour + " "));
                        doc.add(new Paragraph(" "));
                        doc.add(new Paragraph("im  : " + id));
                        doc.add(new Paragraph("nom  : "+ nom ));
                        doc.add(new Paragraph("prenom  : "+ prenom ));
                        doc.add(new Paragraph("Mois  : " + mois ));
                        doc.add(new Paragraph("Année  : " + annee));   
                        doc.add(new Paragraph("montant  : " + montant + " Ar "));
    
                             
                       
                    doc.close();
                   JOptionPane.showMessageDialog(null, "PDF pour le conjoint générée");       
                             
                    }   

                     resultSet2.close();
                    statement2.close();

                   // conn.close();
                        
                    }

            }
            else{
                  JOptionPane.showMessageDialog(null, "PDF non générée");
              // payement.alerte1.setVisible(true);
            }
                    
        }catch(DocumentException | FileNotFoundException | SQLException e){
            
             JOptionPane.showMessageDialog(null, "Erreur" +e);
         //   payement.alerte1.setVisible(true);
                    
        }
    
        
        
        
        
         SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                    try{
                       
                    TimeUnit.SECONDS.sleep(3);

                 /* payement.affirmation1.setVisible(false);
                  payement.alerte1.setVisible(false);*/
                    
               }catch(InterruptedException e){
            
               }

         
            }
    });
        
        
        
        
        
    }
    
    
    
}
