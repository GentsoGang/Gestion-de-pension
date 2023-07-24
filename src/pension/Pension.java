/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pension;

import java.sql.Connection;

/**
 *
 * @author Gael
 */
public class Pension {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection connection = DatabaseConnection.getConnection();
        HOME obj = new HOME();
        obj.show();
    }
    
}
