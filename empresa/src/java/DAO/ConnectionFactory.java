package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class ConnectionFactory {
     public Connection getConnection() {
        try{
     Connection conexao = DriverManager.getConnection(
            "jdbc:derby://localhost:1527/empresa",
            "app",
            "app");
            return conexao;
        }catch(SQLException ex){
            System.out.println("Falha ao conectar com o banco de dados sistema_academico");
        }
        return null;
    }
}
