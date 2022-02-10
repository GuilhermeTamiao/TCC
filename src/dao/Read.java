 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Vagas;

/**
 *
 * @author guita
 */
public class Read {
    public static ArrayList read() throws ClassNotFoundException, SQLException{
        ArrayList<Vagas> listavagas = new ArrayList();
        Vagas vagas = null;
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "SELECT * FROM tbvagasteste";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet linha = stmt.executeQuery();
        
        while(linha.next()){
            vagas = new Vagas(linha.getInt("id"),
                    linha.getString("Vagas")); 
                             
            
            listavagas.add(vagas);
        }
        con.close();
        return listavagas;
    }
}
