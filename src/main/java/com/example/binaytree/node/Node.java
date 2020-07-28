package com.example.binaytree.node;

import com.example.binaytree.utility.DoTill;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    /* public Node<T> root;*/

    public Node(T value) {
        data = value;
        left = null;
        right = null;
    }

    private final Function<Node<T>, Node<T>> leftOrRight = keyNode
            -> keyNode.data.compareTo(this.data) >= 0 ? this.left : this.right;

    BiConsumer<Node<T>, T> assign = (node, value) -> node = new Node<T>(value);

    public Function<T, Node<T>> find = ((Function<T, Node<T>>) Node::new).andThen(keyNode
            -> new DoTill<>(node1 -> !node1.data.equals(keyNode.data), leftOrRight).init.apply(this));

    public Consumer<T> put = t -> assign.accept(((Function<T, Node<T>>) Node::new).andThen(keyNode
            -> new DoTill<>(node -> Objects.nonNull(leftOrRight.apply(node)), leftOrRight)
            .init.apply(this)).apply(t), t);

    /*
    public Node<T> getParent(T value, Node<T> node) {
        Node<T> child = leftOrRight.apply(value, node);
        return child.data.equals(value) ? node : getParent(value, child);
    }

    public void remove(T value) {
        Node<T> parent = getParent(value, root);
        Node<T> current = leftOrRight.apply(value, parent);

        leftOrRight.andThen(x -> x = null).apply(value, parent);
        Set.of(current.left, current.right).iterator()
                .forEachRemaining(x -> put(x, root));
    }
*/
}
