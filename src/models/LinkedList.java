package models;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public Node<T> getHead() {
        return head ;
    }
    
    public void clear(){
        head = null;
        size = 0;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        this.size++;
    }

    public T findByValue(T value) {
        Node<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(value)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
    
    public int getIndex(T value){
        Node<T> current = this.head;
        int i = 0;
        while (current != null) {
            if (current.getData().equals(value)) {
                return i;
            }
            i++;
            current = current.getNext();
        }
        return -1;
    }

    public T findByIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }

    public String toString() {
        String str = "{";
        Node<T> aux = this.head;
        while (aux != null) {
            str += (aux.getData() + ", ");
            aux = aux.getNext();
        }
        str += "}";
        return str;
    }
}
