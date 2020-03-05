/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import parcial.conexion;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

/**
 *
 * @author Amilcar
 */
public class Procedimiento {

    Connection cn;
    conexion conn = new conexion();
    PreparedStatement s;
    ResultSet rs;

    public Procedimiento() {
    }

    public void InsertarVenta(int idVend, int idCliente, int[] productos, int[] cant, int dia, int mes, int year) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE ", "system", "admin");
       ArrayDescriptor cantArray = ArrayDescriptor.createDescriptor("ARRAY_INT", con);
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_PROD", con);
        ARRAY arrayProd = new ARRAY(des, con, productos);
        ARRAY arrayCant = new ARRAY(cantArray, con, cant);
        
       
        CallableStatement st = con.prepareCall("call system.genera_venta(?,?,?,?,?,?,?)");

        st.setInt(1, idVend);
        st.setInt(2, idCliente);
        st.setArray(3, arrayProd);
        st.setArray(4, arrayCant);
        st.setInt(5, dia);
        st.setInt(6, mes);
        st.setInt(7, year);
        st.executeUpdate();
    }

}
