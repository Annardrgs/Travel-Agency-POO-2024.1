import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Cidade;
import models.Hotel;
import models.Orcamento;
import models.Usuario;
import models.Voo;

public class Sistema implements Runnable {
    public Map<String,Cidade> cidades = new HashMap<>(); // Map criado no metodo de inicialização da classe
    public List<Usuario> usuarios = new ArrayList<>(); // Lista criada no metodo de inicialização da classe
    public List<Orcamento> orcamentosAceitos = new ArrayList<>(); // Lista preenchida por orcamentos aceitos(preenchidas pelas Threads)
    public List<Orcamento> orcamentosNegados = new ArrayList<>(); // Lista preenchida por orcamentos negados(preenchidas pelas Threads)
    public int gastoHoteis = 0; // int que guarda valor gasto em hoteis
    public int gastoVoos = 0; // int que guarda valor gasto com voos
    

    public synchronized void plus_gasto_hoteis(int val){ // função sincronizada para aumentar o valor gasto em hoteis
        this.gastoHoteis += val;
    }

    public synchronized void plus_gasto_voos(int val){ // função sincronizada para aumentar o valor gasto em voos
        this.gastoVoos += val;
    }
    public synchronized void adicionar_negados(Orcamento e){ // função sincronizada para aumentar o valor gasto em voos
        this.orcamentosNegados.add(e);
    }
    public synchronized void adicionar_aceitos(Orcamento e){ // função sincronizada para aumentar o valor gasto em voos
        this.orcamentosAceitos.add(e);
    }
    public synchronized void alterar_vagas_voo(Voo e){ // função sincronizada para aumentar o valor gasto em voos
        e.setVagas(e.getVagas()-1);
    }
    public synchronized void alterar_vagas_hotel(Hotel e){ // função sincronizada para aumentar o valor gasto em voos
        e.setVagas(e.getVagas()-1);
    }


    @Override
    public void run(){

    }
}
