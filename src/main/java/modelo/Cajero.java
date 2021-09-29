package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bayito
 */
public class Cajero {
    ArrayList<Dinero> inventario;
    public Cajero() {
        this.inventario = new ArrayList<>();
    }

    public ArrayList<Dinero> getInventario() {
        return inventario;
    }   
    public void cargarDinero(){
        Conexion objetoConexion = new Conexion();
        CargarDinero(objetoConexion.establecerConexion());
    } 
    public void CargarDinero(Connection con) {
try {
   String SQL = "SELECT * FROM Dinero";
    try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
        
        while (rs.next()) {
            Dinero nuevoDinero = new Dinero ();
            nuevoDinero.setId(Integer.parseInt(rs.getString("Id")));
            nuevoDinero.setTipo(rs.getString("Tipo"));
            nuevoDinero.setCantidad(Integer.parseInt(rs.getString("Cantidad")));
            nuevoDinero.setDenominacion(Double.parseDouble(rs.getString("Denominacion")));
            inventario.add(nuevoDinero);
        }        
    }
}
catch (Exception e) {
  e.printStackTrace();
}
    }
    
    public ArrayList<Dinero> asignar(double monto){
        ArrayList<Dinero> entrega = new ArrayList<>();
        double faltaAsignar=monto;
        double asignado=0;
        for(Dinero dinero:this.inventario){
            int asigna=Math.min( (int)(faltaAsignar/dinero.getDenominacion()), dinero.getCantidad());
            faltaAsignar-=asigna*dinero.getDenominacion();
            asignado+=asigna*dinero.getDenominacion();
            Dinero contemplado=dinero.clonar();
            contemplado.setCantidad(asigna);
            if(asigna!=0){entrega.add(contemplado);}
        }
        return asignado-monto==0? entrega:new ArrayList<>();     
    }
    public double getDineroActual(){
         double dineroActual=0;
         dineroActual = inventario.stream().map(dinero -> dinero.getCantidad()*dinero.getDenominacion()).reduce(dineroActual, (accumulator, _item) -> accumulator + _item);
        return dineroActual;    
    }
    public void setCantidadSQL(int Id,int cantidad){
        Conexion objetoConexion = new Conexion();
        Connection con=objetoConexion.establecerConexion();
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Dinero Set Cantidad ="+cantidad+"WHERE Id="+Id);
            boolean execute = stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
    @Override
    public String toString() {
        return "Cajero{" + "inventario=" + inventario + '}';
    }
    
}
