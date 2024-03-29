/**
 *
 * @author Antony Carlos
 */
package geometry;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private final List<Aresta> arestas;
    private List<Vertice> ligacoes;
    private final int local_vertice;
    private String nome;
    private boolean isAdd;
    
    public Vertice(int local){
        this.arestas = new ArrayList<>();
        this.local_vertice = local;
        this.isAdd = true;
    }
    
    public Vertice(int local, String nome){
        this(local);
        this.nome = nome;
    }
    
    /**
     * 
     * @param origem
     * @param destino
     * @param peso 
     * 
     * define uma ligacao entre dois vertices, gerando uma aresta com novo peso
     */
    public void setLigacao(Vertice origem, Vertice destino, int peso) {
        if(origem.equals(destino)){
            return;
        }
        
        Aresta nova_aresta = new Aresta(origem, destino, peso);
        
        if(this.arestas.contains(nova_aresta) == true){
            return;
        }
        
        this.arestas.add(nova_aresta);
        
        this.isAdd = true;
    }
    
    /**
     * 
     * @return List 
     * 
     * retorna todos os vertices
     */
    public List<Vertice> getLigacoes(){
        if(this.isAdd == true) {
            ArrayList<Vertice> ligacoes_local = new ArrayList<>();

            for (Aresta aresta : arestas) {
                if(aresta.getDestino().equals(this)){
                    ligacoes_local.add(aresta.getOrigem());
                }
                else{
                    ligacoes_local.add(aresta.getDestino());
                }
            }
            
            this.ligacoes = ligacoes_local;
            this.isAdd = false;
        }
        return this.ligacoes;
    }
    
    public List<Aresta> getArestas() {
        return this.arestas;
    }
    
    public int getLocal(){
        return this.local_vertice;
    }

    public String getNome() {
        if(nome == null) return "Undefined";
        
        return nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vertice)) return false;
        
        Vertice vertice = (Vertice)obj;
        
        return (vertice.getLocal() == this.local_vertice);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.local_vertice;
        return hash;
    }
}
