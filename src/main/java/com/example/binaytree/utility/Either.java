package com.example.binaytree.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Either<L, R> {

    private L left;
    private R right;

    public Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <T, R> Function<T, Either> carryErrors(Function<T, R> function) {
        return t -> {
            try {
                return new Either(null, function.apply(t));
            } catch (Exception e) {
                return new Either(new StringJoiner("Error: ")
                        .add(e.getMessage())
                        .add(t.toString()), null);
            }
        };
    }

    public static <T, U, R> BiFunction<T, U, Either> carryErrors(BiFunction<T, U, R> function) {
        return (t, u) -> {
            try {
                return new Either(null, function.apply(t, u));
            } catch (Exception e) {
                return new Either(new StringJoiner("Error: ")
                        .add(e.getMessage())
                        .add(t.toString())
                        .add(u.toString()), null);
            }
        };
    }

    public static <T> Consumer<T> carryErrors(Consumer<T> function/*, Runnable runnable*/) {
        return t -> {
            try {
                function.accept(t);
            } catch (Exception ignore) {
                /*runnable.run();*/
            }
        };
    }

}
