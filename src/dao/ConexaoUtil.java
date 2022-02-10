/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author guita
 */
public class ConexaoUtil {
    private String caminho = "localhost";
    private String porta = "3306";
    private String banco = "bdtcc";
    private String usuario = "root";
    private String senha = "";
    
    private String URL = "jdbc:mysql://"+caminho+":"+porta+"/"+banco+"?useTimezone=true&serverTimezone=GMT";
    
    
    //Padrao de conexão Singleton
    public static ConexaoUtil getConnection(){
        ConexaoUtil conexaoUtil = null;
        if(conexaoUtil == null){
            conexaoUtil = new ConexaoUtil();
            return conexaoUtil;
        }
        else{
            return null;
        }
    }
    
    public Connection Conn() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conectado com sucesso\n");
            return DriverManager.getConnection(URL, usuario, senha);
    }
    
}
