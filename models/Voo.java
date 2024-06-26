
package Hotels.models;
//voo.java

public class Voo{
  private String origem;
  private String destino;
  private int valorVoo;
  private int codigoAereo;

  //construtor
  
  public Voo(String origem, String destino, int valorVoo, int codigoAereo){
    this.origem = origem;
    this.destino = destino;
    this.valorVoo = valorVoo;
    this.codigoAereo = codigoAereo;
  }
  
  //gets e sets
  
  public String getOrigem(){
    return origem;
  }
  public void setOrigem(String origem){
    this.origem = origem;
  }
  
  public String getDestino(String destino){
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
  
  public int getCodigoAereo{
    return codigoAereo;
  }
  
  public void setCodigoAereo(int codigoAereo){
    this.codigoAereo = codigoAereo;
  }
}