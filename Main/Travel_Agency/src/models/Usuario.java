package models;

// usuario.java

public class Usuario{
  private String nome;
  private String origem;
  private String destino;
  private int estrelasMin;


  //construtor
  
  public Usuario(String nome, String origem, String destino, int estrelasMin){
    this.nome = nome;
    this.origem = origem;
    this.destino = destino;
    this.estrelasMin = estrelasMin;
  }
  
  //métodos gets e sets
  
  public String getNome(){
    return nome;
  }
  
  public void setNome(String nome){
    this.nome = nome;
  }
  
  public String getOrigem(){
    return origem;
  }
  
  public void setOrigem(String origem){
    this.origem = origem;
  }
  
  public String getDestino(){
    return destino;
  }
  
  public void setDestino(String destino){
    this.destino = destino;
  }
  
  public int getEstrelasMin(){
    return estrelasMin;
  }
  
  public void setEstrelasMin(int estrelasMin){
    this.estrelasMin = estrelasMin;
  }
}
