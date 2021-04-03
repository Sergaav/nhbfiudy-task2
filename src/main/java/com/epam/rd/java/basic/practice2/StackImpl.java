package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private int stackSize = 0;
    private Node<Object> last = null;


    @Override
    public void clear() {
        stackSize = 0;
        last = null;
    }

    @Override
    public int size() {
        return stackSize;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node<Object> currentElement = last;

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public Object next() {
            Object o;
            if (currentElement == null) {
                throw new NoSuchElementException();
            }
            o = currentElement.item;
            currentElement = currentElement.prev;
            return o;
        }
    }

    @Override
    public void push(Object element) {
        Node<Object> l = last;
        Node<Object> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l != null) {
            l.next = newNode;
        }
        stackSize++;
    }

    @Override
    public Object pop() {
        Object o;
        if (last == null) {
            o = null;
        } else {
            o = last.item;
            last = last.prev;
            stackSize--;
        }
        return o;
    }

    @Override
    public Object top() {
        if (last == null) {
            return null;
        }
        return last.item;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Object[] masObjects = new Object[stackSize];
        String string = null;
        if (stackSize != 0) {
            int i = 0;
            for (Node<Object> x = last; x != null; x = x.prev) {
                masObjects[i] = x.item;
                i++;
            }
            for (int j = masObjects.length - 1; j >= 0; j--) {
                stringBuilder.append(masObjects[j]).append(", ");
            }
            string = stringBuilder.substring(0, stringBuilder.lastIndexOf(", "));
            string = "[" + string + "]";
        }
        return string;
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
        Stack stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println(stack.toString());
        System.out.println(stack.top());
        stack.pop();
        Iterator<Object> iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        stack.clear();
        System.out.println(stack.toString());
    }
}
