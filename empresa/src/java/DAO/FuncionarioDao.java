package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Felipe
 */
public class FuncionarioDao {
        //a conexão com o banco de dados
    private Connection connection;
    public FuncionarioDao(){
    this.connection = new ConnectionFactory().getConnection();
}
    public void adiciona(Funcionario funcionario){
        String sql = "INSERT INTO funcionario VALUES(?, ?, ?, ?, ?, ?)";
        try {
             //prepara statement para inserção
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            //setas os valores
            stmt.setInt(1,funcionario.getNumat());
            stmt.setString(2,funcionario.getNome());
            stmt.setDouble(3,funcionario.getSalario());
            stmt.setString(4,funcionario.getSexo());
            stmt.setInt(5,funcionario.getNdepto());
            stmt.setInt(6,funcionario.getNsuper());
           
            //executa o comando
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }        
}
    public ArrayList <Funcionario> getLista(){
        String sql = "SELECT * from funcionario";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                //criando o objeto funcionario
                Funcionario funcionario = new Funcionario();
                funcionario.setNumat(rs.getInt("numat"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setNdepto(rs.getInt("ndepto"));
                funcionario.setNsuper(rs.getInt("nsuper"));
                
                //adicionando o objeto ao ArrayList
                funcionarios.add(funcionario);
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return funcionarios;
    }
    
    public Funcionario consulta(int id){
        String sql = "SELECT * FROM funcionario WHERE numat = ?";
         Funcionario funcionario = null;
         
         try{
             PreparedStatement stmt = this.connection.prepareStatement(sql);
             stmt.setInt(1, id);
             ResultSet rs = stmt.executeQuery();
             
             if(rs.next()){
                funcionario = new Funcionario();
                funcionario.setNumat(rs.getInt("numat"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSalario(rs.getDouble("salario"));
             }
             rs.close();
             stmt.close();
         }catch(SQLException e){
             throw new RuntimeException(e);
         }
         return funcionario;
    }
    
    public ArrayList <Funcionario> getSupervisor(){
        String sql = "SELECT * from funcionario";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                //criando o objeto funcionario
                Funcionario funcionario = new Funcionario();
                funcionario.setNumat(rs.getInt("numat"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setNdepto(rs.getInt("ndepto"));
                funcionario.setNsuper(rs.getInt("nsuper"));
                
                //adicionando o objeto ao ArrayList
                funcionarios.add(funcionario);
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return funcionarios;
    }
    
    public boolean remove(int id){
        String sql = "DELETE FROM funcionario WHERE NUMAT = ?";
        int funcionariosRemovidos = 0;
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1,id);
            //executeUpdate retorna a quantidade de linhas removidas
            funcionariosRemovidos = stmt.executeUpdate();
            stmt.close();         
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        //retorna true se removeu 1 funcionario
        return funcionariosRemovidos == 1;
    }
    
    public boolean atualiza(Funcionario funcionario){
        String sql = "UPDATE funcionario SET nome = ?, salario = ? WHERE numat = ?";
        int funcionariosAtualizados = 0;
        try{
            //prepared statement para inserção
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            //seta os valores
            stmt.setString(1,funcionario.getNome());
            stmt.setDouble(2,funcionario.getSalario());
            stmt.setLong(3,funcionario.getNumat());
            //executeUpdate retorna a quantidade de linhas atualizadas
            funcionariosAtualizados = stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        //retorna true se atualizou 1 funcionario
        return funcionariosAtualizados == 1;
    }
}
