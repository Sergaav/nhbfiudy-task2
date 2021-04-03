package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private int listSize = 0;
    private Node<Object> first = null;
    private Node<Object> last = null;

    @Override
    public void clear() {
        first = last = null;
        listSize = 0;
    }

    @Override
    public int size() {
        return listSize;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node<Object> currElement = first;

        @Override
        public boolean hasNext() {
            return currElement != null;
        }

        @Override
        public Object next() {
            Object o;
            if (currElement == null) {
                throw new NoSuchElementException();
            }
            o = currElement.item;
            currElement = currElement.next;
            return o;
        }
    }

    @Override
    public void addFirst(Object element) {
        final Node<Object> f = first;
        final Node<Object> newNode = new Node<>(null, element, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        listSize++;
    }

    @Override
    public void addLast(Object element) {
        final Node<Object> f = last;
        final Node<Object> newNode = new Node<>(f, element, null);
        last = newNode;
        if (f == null) {
            first = newNode;
        } else {
            f.next = newNode;
        }
        listSize++;
    }

    @Override
    public void removeFirst() {
        if (first == null)
            throw new NoSuchElementException();

        final Node<Object> next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        listSize--;
    }

    @Override
    public void removeLast() {
        if (last == null)
            throw new NoSuchElementException();

        final Node<Object> prev = last.prev;
        last.item = null;
        last.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        listSize--;
    }

    @Override
    public Object getFirst() {
        final Node<Object> l = first;
        if (l == null) {
            return null;
        }
        return l.item;
    }

    @Override
    public Object getLast() {
        final Node<Object> l = last;
        if (l == null)
            return null;
        return l.item;
    }

    @Override
    public Object search(Object element) {
        Object object = null;
        if (listSize != 0) {
            for (Node<Object> x = first; x != null; x = x.next) {
                if (!(element == null || x.item == null) && (x.item).equals(element)) {
                    object = x.item;
                }
            }
        }
        return object;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            for (Node<Object> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<Object> x = first; x != null; x = x.next) {
                if (element.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public void unlink(Node<Object> x) {
        final Node<Object> next = x.next;
        final Node<Object> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        listSize--;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String listStr = null;
        if (listSize != 0) {
            for (Node<Object> x = first; x != null; x = x.next) {
                stringBuilder.append(x.item).append(", ");
            }
            listStr = stringBuilder.substring(0, stringBuilder.toString().lastIndexOf(", "));
            listStr = '[' + listStr + ']';
        }
        return listStr;
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

        List list = new ListImpl();
        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");
        list.addFirst("AA");
        list.addLast(null);
        System.out.println(list.toString());
        list.remove("B");
        System.out.println(list.toString());
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.size());
        System.out.println(list.search(null));
        list.clear();
    }
}
