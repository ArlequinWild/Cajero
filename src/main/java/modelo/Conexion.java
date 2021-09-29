/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bayito
 */
public class Conexion {
     Connection conectar = null;
    
    String usuario = "cajero";
    String pasword = "1234";
    String bd=  "Cajero";
    String puerto="1433";
    
    
    public Connection establecerConexion(){
        try {
            String cadena="jdbc:sqlserver://localhost"+";"+"dabaseName="+bd;
            conectar = DriverManager.getConnection(cadena,usuario,pasword);
            System.out.println("Se conecto correctamente a la base de datos");
        
        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos, error: "+e.toString());
        }
        return conectar;
    }
    
}
