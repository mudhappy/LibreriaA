
import java.sql.Connection;
import pe.mud.db.AccesoDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mud
 */
public class prueba {

    public static void main(String[] args) {
    try {
      Connection cn = AccesoDB.getConexion();
      System.out.println("Bien.");
      cn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    
}
