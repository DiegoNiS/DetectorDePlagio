package trying;

import java.io.File;
import models.Trie;

public class Controlador {
    private PlagiarismDetector contenedor;

    public Controlador() {
        this.contenedor = new PlagiarismDetector();
    }
    
    public boolean addDBArticle(File file){
        return this.contenedor.addFileToDB(file);
    }
    
    public double comparacion(File file, int minCoincidencias){
        return this.contenedor.resultadosdeComparacion(file, minCoincidencias);
    }

    public PlagiarismDetector getContenedor() {
        return contenedor;
    }
    
    public Trie obtener(String name){
        return contenedor.obten(name);
    }
}
