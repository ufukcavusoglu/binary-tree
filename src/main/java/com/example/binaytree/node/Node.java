package com.example.binaytree.node;

import java.util.*;
import java.util.function.BiFunction;

public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;

    public Node(T value) {
        data = value;
        left = null;
        right = null;
    }

    public BiFunction<T,Node<T>,Node<T>> pathDecide = (T value, Node<T> current)
            -> value.compareTo(current.data) >= 0  ? current.left : current.right;

    public Node<T> root;

    public Node<T> find(T key) {
        Node<T> current = root;
        while (Objects.nonNull(current) && current.data != key)
            current = pathDecide.apply(key,current);
        return current;
    }

    public void put(Node<T> value,Node<T> current) {
        if(current==null) current = value;
        else put(value, pathDecide.apply(value.data,current));
    }

    public Node<T> getParent(T value, Node<T> node){
        Node<T> child = pathDecide.apply(value, node);
        if(child.data.equals(value)) return node;
        else return getParent(value,child);
    }

    public void remove(T value){
        Node<T> parent = getParent(value,root);
        Node<T> current = pathDecide.apply(value,parent);

        pathDecide.andThen(x-> x = null).apply(value,parent);
        Set.of(current.left,current.right).iterator().forEachRemaining(x-> put(x,root));
    }

}
