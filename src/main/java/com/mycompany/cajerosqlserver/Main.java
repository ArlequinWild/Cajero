/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cajerosqlserver;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.Cajero;
import modelo.Dinero;

/**
 *
 * @author Bayito
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        //cargamos el cajero java con la informacion de la base de datos
        Cajero cajero = new Cajero();
       cajero.cargarDinero();
       
        boolean continua=true;
        Scanner sc;
        sc = new Scanner(System.in);
        double monto;
        String respuestaUsuario;
        while(continua){
        //Pedimos al usuario el monto a rtetirar
        System.out.println("==========      Buen dia Usuario      ===========");
        System.out.println("= Por favor escribe el monto que deseas retirar =");
        
        respuestaUsuario=sc.nextLine();
         if(respuestaUsuario.equals("exit")){
            break;        
        }
         //verificamos que lo que nos pasa es un numero bien construido
        if(validarNumeros(respuestaUsuario)){
            monto = Double.parseDouble(respuestaUsuario);
            //verificamos si alcanza
            if(monto>cajero.getDineroActual()){
                System.out.println("Lo sentimos no contamos con fondos suefientes para retirar ese monto");
            }
            //Intenta asignar con la cantidad de dinero en sus respectivas denominaciones
            else{
                ArrayList<Dinero> entregable = cajero.asignar(monto);
                if(entregable.isEmpty()){
                    System.out.println("Lo sentimos no pudimos asignar el dinero, intenta con un monto diferente");
                }
                else{
                for(Dinero dinero:entregable){
                    //entrega el dinero al usuario
                    System.out.println(dinero.toString());
                    //actualiza el estado del cajero en java
                    cajero.getInventario().get(dinero.getId()-1).restaCantidad(dinero.getCantidad());
                    cajero.setCantidadSQL(dinero.getId(), cajero.getInventario().get(dinero.getId()-1).getCantidad());   
                }
                }
            }
        }
        else{
            System.out.println("Entrada no permitida, por favor escribe un numero ");
        }
        if(respuestaUsuario.equals("exit")){
            continua = false;        
        }       
        }
        System.out.println(cajero.toString());
        
    }
        
       
        public static boolean validarNumeros(String datos ){
            return datos.matches("[0-9]+\\.?[0-9]*");
        }
        
    
    
}
