/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author Bayito
 */
public class Dinero{
    private int id;
    private String tipo;
    private int cantidad;
    private double denominacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(double denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public String toString() {
        return "Dinero{" + "id=" + id + ", tipo=" + tipo + ", cantidad=" + cantidad + ", denominacion=" + denominacion + '}';
    }

    public Dinero clonar() {
       Dinero clon = new Dinero();
       clon.setId(this.getId());
       clon.setTipo(this.getTipo());
       clon.setCantidad(this.getCantidad());
       clon.setDenominacion(this.getDenominacion());
        
       
       return clon;
    }
    
    public void restaCantidad(int quita){
        this.setCantidad(this.getCantidad()-quita);
    }
}
