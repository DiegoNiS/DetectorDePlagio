package models;

public class Pair<T,E> {
    private T left;
    private E rigth;

    public Pair(T left) {
        this.left = left;
    }

    public T getLeft() {
        return left;
    }

    public E getRigth() {
        return rigth;
    }

    public void setRigth(E rigth) {
        this.rigth = rigth;
    }
}
