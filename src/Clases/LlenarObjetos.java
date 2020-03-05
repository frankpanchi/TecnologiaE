/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import parcial.conexion;
import Clases.Vendedor;

/**
 *
 * @author Amilcar
 */
 
public class LlenarObjetos {
    Connection cn;
    conexion conn =new conexion();
    PreparedStatement s;
    ResultSet rs;

    public LlenarObjetos() {
    }
    
   public void mostrarVendedores(JComboBox<Vendedor> comboVendedor){
        try {
          // Conexion con = new Conexion();
            s=conn.getConnection().prepareStatement("SELECT id_vendedor, nombre, apellido FROM vendedor_d");
            rs=s.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
             while(rs.next())
            {
                 comboVendedor.addItem(
                        new Vendedor(
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                 rs.getString("id_vendedor")
                        )
                );
            }
        } catch (Exception ex) {
          //  Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL MOSTRAR LOS Vendedores");
        }
    }
}
