package com.savaz.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private int queueSize = 0;
    private Node<Object> first = null;
    private Node<Object> last = null;

    @Override
    public void clear() {
        queueSize = 0;
        first = last = null;
    }

    @Override
    public int size() {
        return queueSize;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node<Object> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object o;
            if (current == null) {
                throw new NoSuchElementException();
            }
            o = current.item;
            current = current.next;
            return o;
        }

    }

    @Override
    public void enqueue(Object element) {
        Node<Object> l = last;
        Node<Object> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        queueSize++;

    }

    @Override
    public Object dequeue() {
        Object o;
        if (first == null) {
            o = null;
        } else {
            o = first.item;
            first = first.next;
            queueSize--;
        }
        return o;
    }

    @Override
    public Object top() {
        if (first == null) {
            return null;
        }
        return first.item;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        String str="";
        if (queueSize != 0) {
            for (Node<Object> a = first; a != null; a = a.next){
                stringBuilder.append(a.item);
                stringBuilder.append(", ");
            }
            str = stringBuilder.substring(0, stringBuilder.lastIndexOf(", "));
            str = '['+str+']';
        }
        return str;
    }


    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        Queue queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println(queue.toString());
        queue.dequeue();
        System.out.println(queue.toString());
        System.out.println(queue.top());
        System.out.println(queue.toString());
        Iterator<Object> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        queue.clear();
        System.out.println(queue.toString());
        System.out.println(queue.size());
    }

}
