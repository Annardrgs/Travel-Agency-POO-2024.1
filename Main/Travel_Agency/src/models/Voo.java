
package models;
//voo.java


public class Voo{
  private String origem;
  private String destino;
  private int valorVoo;
  private int vagas;
  private String data;

  //construtor
  
  public Voo(String origem, String destino, int valorVoo, int vagas, String data){
    this.origem = origem;
    this.destino = destino;
    this.valorVoo = valorVoo;
    this.vagas = vagas;
    this.data = data;
  }
  
  //gets e sets
  
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
  
  public int getValorVoo(){
    return valorVoo;
  }
  
  public void setValorVoo(int valorVoo){
    this.valorVoo = valorVoo;
  }
  
  public void setvagas(int vagas){
    this.vagas = vagas;
  }

  public int getvagas(){
    return this.vagas;
  }
  public String getdata(){
    return this.data;
  }
}