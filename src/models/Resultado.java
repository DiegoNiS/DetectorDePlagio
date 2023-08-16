package models;

public class Resultado {
    private String title;
    private double percent;
    private Trie trie;

    public Resultado(String title, double percent, Trie trie) {
        this.title = title;
        this.percent = percent;
        this.trie = trie;
    }

    public String getTitle() {
        return title;
    }

    public double getPercent() {
        return percent;
    }

    public Trie getTrie() {
        return trie;
    }
}
