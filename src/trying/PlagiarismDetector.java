package trying;

import models.Articulo;
import models.NodeTrie;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.Trie;
import java.text.Normalizer;
import models.LinkedList;
import models.Node;
import models.Resultado;

public class PlagiarismDetector {

    private final LinkedList<Trie> tries;
    private Articulo articulo;

    public Articulo getArticulo() {
        return articulo;
    }

    public LinkedList<Trie> getTries() {
        return tries;
    }

    public PlagiarismDetector() {
        this.tries = new LinkedList<>();
    }

    public boolean addFileToDB(File file) {
        String lectura = loadFile(file);
        if (lectura != null) {
            Trie triedb = new Trie(file.getName());
            String[] texto = procesarTexto(lectura);
            for (int i = 0; i < texto.length; i++) {
                triedb.insert(texto[i], i);
            }
            tries.insert(triedb);
            return true;
        }
        return false;
    }

    public boolean loadArticle(File file, int minCoincidencias) {
        String texto = loadFile(file);
        if (texto != null) {
            this.articulo = new Articulo(procesarTexto(texto), minCoincidencias);
            return true;
        }
        System.out.println("No se puede leer el archivo");
        return false;
    }

    private String loadFile(File file) {
        String line = "";
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                line = line + scanner.nextLine() + " ";
            }
            scanner.close();
            System.out.println("Archivo leido correctamente");
            return line.trim();
        } catch (FileNotFoundException e) {
            System.out.println("error al leer el archivo");
            return null;
        }
    }

    //a pesar que en realidad no eliminarmos nada esta opcion sirve para reemplazar el documento de una DB
    public void deleteThis(Trie trie) {
        Node<Trie> aux = this.tries.getHead();
        if (aux.getData() == trie) {
            this.tries.setHead(aux.getNext());
            aux.setNext(null);
        } else {
            while (aux.getNext() != null) { //se hace la comparacion del siguiente nodo, (se salta un nodo)
                if (aux.getNext().getData() == trie) {
                    Node<Trie> del = aux.getNext();
                    aux.setNext(aux.getNext().getNext());
                    del.setNext(null);
                }
                aux = aux.getNext();
            }
        }
    }

    public String[] procesarTexto(String str) {
        String normalizedText = Normalizer.normalize(str, Normalizer.Form.NFD);
        normalizedText = normalizedText.replaceAll("\\p{M}", "");
        normalizedText = normalizedText.replaceAll("ñ", "n");
        String[] words = normalizedText.toLowerCase().split("\\W+");
        return words;
    }

    public double resultadosdeComparacion(File file, int minComparaciones) {
        if (loadArticle(file, minComparaciones)) {
            double result = 0;
            for (Trie trie : tries) {
                result += resultadosdeComparacion(file, minComparaciones, trie);
            }
            return result;
        } else {
            return -1.0;
        }
    }

    public double resultadosdeComparacion(File file, int minComparaciones, Trie trie) {
        double res;

        LinkedList<Integer> previousPos = new LinkedList<>();
        int[] rep;
        int[] repAnt = new int[0];
        
        for (String word : this.articulo.getArticle()) {
            
            NodeTrie aux = trie.found(word);
            if (aux != null) { //existe palabra repetida
                
                rep = comparar(previousPos, aux.getPositions(), repAnt);
                previousPos = clone(aux.getPositions());
                repAnt = (int[]) rep.clone();
            } else {
                previousPos.clear();
                repAnt = new int[0];
            }
        }
        res = this.articulo.getPalabrasRepetidas() * 100.00 / this.articulo.getArticle().length;
        if (res != 0) {
            this.articulo.addResultado(new Resultado(removeExt(trie.getFileName()), Math.round(res*100)/100.0, trie));
        }
        return res;

    }
    
    public String removeExt(String name){
        int position = name.indexOf(".txt");
        return position !=-1?name.substring(0, position):name;
    }

    public int[] comparar(LinkedList<Integer> antes, LinkedList<Integer> ahora, int[] repAnt) {
        int[] rep = new int[ahora.size()];
        Node<Integer> aux = ahora.getHead();

        if (antes.size() != 0) {
            //recorremos ahora LinkedLinst
            for (int i = 0; i < rep.length; i++, aux = aux.getNext()) {
                int index = antes.getIndex(aux.getData() - 1); //indice de incidencia en antes
                if (index != -1) {
                    rep[i] = repAnt[index] + 1;
                    if (rep[i] >= this.articulo.getMinCoincidencias()) {
                        checarcoincidencias(rep[i]);
                    }
                } else {
                    rep[i] = 1;
                }
            }
        } else {
            for (int i = 0; i < rep.length; i++) {
                rep[i] = 1;
            }
        }
        return rep;
    }

    public String showA(int[] array) {
        String arr = "[";
        for (int a : array) {
            arr += (String.valueOf(a) + ", ");
        }
        arr += "]";
        return arr;
    }

    //al presionar boton deberia leer el nuevo valor de la lectura de las configuraciones
    public void checarcoincidencias(int coinc) {
        if (coinc == this.articulo.getMinCoincidencias()) {
            this.articulo.acumularPR(this.articulo.getMinCoincidencias());
        } else {
            this.articulo.acumularPR(1);
        }
    }

    public Trie obten(String name) {
        
        for (Trie trie : tries) {
            if (trie.toString().equals(name)) {
                return trie;
            }
        }
        return null;
    }

    private LinkedList<Integer> clone(LinkedList<Integer> positions) {
        LinkedList<Integer> clonado = new LinkedList<>();
        for (Integer numero : positions) {
            clonado.insert(numero);
        }
        return clonado;
    }
}
