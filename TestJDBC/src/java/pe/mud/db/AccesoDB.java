/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mud
 */
public class AccesoDB {
    
    private AccesoDB(){}
    
    public static Connection getConexion() throws SQLException
    {
        Connection con = null;
        //Datos
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/PokemonDB";
        String user = "root";
        String pass = ""; 
       
        try {
            //Cargando driver a memoria
            Class.forName(driver).newInstance();
            //Obtener objeto conexion
            con = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
        }
        
        return con;
    }
    
    
}
