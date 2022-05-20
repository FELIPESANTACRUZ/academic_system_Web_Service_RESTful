package DAO;

/**
 *
 * @author Felipe
 */
public class Funcionario {
    private int numat;
    private String nome;
    private Double salario;
    private String sexo;
    private int ndepto;
    private int nsuper;
    
    public int getNumat(){
        return numat;
    }
    public String getNome(){
        return nome;
    }
    public Double getSalario(){
        return salario;
    }
    public String getSexo(){
        return sexo;
    }
    public int getNdepto(){
        return ndepto;
    }
    public int getNsuper(){
        return nsuper;
    }
    
    
    public void setNumat(int numat){
        this.numat = numat;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setSalario(Double salario){
        this.salario = salario;
    }
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
    public void setNdepto(int ndepto){
        this.ndepto = ndepto;
    }
    public void setNsuper(int nsuper){
        this.nsuper = nsuper;
    }
}
