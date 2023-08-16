package models;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Articulo {
    private final String[] article;
    private int minCoincidencias; 
    private int palabrasRepetidas;
    private ObservableList<Resultado> listaResultados;
    
    public Articulo(String[] article, int minCoincidencias){
        this.article = article;
        this.minCoincidencias = minCoincidencias;
        this.palabrasRepetidas = 0;
        listaResultados = FXCollections.observableArrayList();
    }
    
    public void addResultado(Resultado res){
        this.listaResultados.add(res);
        setPalabrasRepetidas(0);
    }

    public ObservableList<Resultado> getListaResultados() {
        return listaResultados;
    }

    public String[] getArticle() {
        return article;
    }

    public int getMinCoincidencias() {
        return minCoincidencias;
    }

    public int getPalabrasRepetidas() {
        return palabrasRepetidas;
    }

    public void setPalabrasRepetidas(int palabrasRepetidas) {
        this.palabrasRepetidas = palabrasRepetidas;
    }

    public void setMinCoincidencias(int minCoincidencias) {
        this.minCoincidencias = minCoincidencias;
    }
    
    public void acumularPR(int numero){
        this.palabrasRepetidas = this.palabrasRepetidas + numero;
    }
}
