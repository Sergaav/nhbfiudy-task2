package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayImpl implements Array {
    private Object[] elementData;
    private int lastElement;

    public ArrayImpl(int capacity) {
        this.elementData = new Object[capacity];
        lastElement = 0;
    }

    @Override
    public void clear() {
        this.elementData = new Object[0];
    }

    @Override
    public int size() {
        return elementData.length;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currentElement = 0;


        @Override
        public boolean hasNext() {
            return currentElement < elementData.length;
        }

        @Override
        public Object next() {
            if (currentElement > elementData.length) {
                throw new NoSuchElementException();
            }
            return elementData[currentElement++];
        }

    }

    @Override
    public void add(Object element) {
        if (lastElement < elementData.length) {
            elementData[lastElement] = element;
            lastElement++;
        } else {
            Object[] temp = new Object[elementData.length + 1];
            for (int i = 0; i < elementData.length; i++) {
                temp[i] = elementData[i];
            }
            temp[temp.length - 1] = element;
            elementData = temp;
            lastElement = temp.length;
        }

    }

    @Override
    public void set(int index, Object element) {
        elementData[index] = element;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public int indexOf(Object element) {
        int index = -1;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] != null && elementData[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void remove(int index) {
        Object[] temp = new Object[elementData.length - 1];
        int j = 0;
        for (int i = 0; i < elementData.length; i++) {
            if (i != index) {
                temp[j] = elementData[i];
                j++;
            }
        }
        elementData = temp;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Object o : elementData) {
            string.append(o).append(", ");
        }
        String s = string.substring(0, string.toString().lastIndexOf(", "));
        return "[" + s + "]";
    }

    public static void main(String[] args) {
        Array array = new ArrayImpl(4);
        array.add("Hello");
        array.add("World");
        array.set(2, "D");
        array.add("E");
        array.add("SomeObject1");
        array.add("SomeObject2");
        array.add("SomeObject3");
        array.add("SomeObject4");
        array.add("SomeObject5");
        System.out.println(array.size());
        System.out.println(array.get(2));
        System.out.println(array.indexOf("World"));

        System.out.println(array.toString());
        Iterator<Object> iterator = array.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next().toString()).append(' ');
        }
        System.out.print(stringBuilder.toString());


    }

}
