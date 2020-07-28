package com.example.binaytree.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DoTill<T> {

    Predicate<T> predicate;
    Consumer<T> func;
    Function<T, T> next;

    public DoTill(Predicate<T> predicate, Consumer<T> func, Function<T, T> next) {
        this.predicate = predicate;
        this.func = func;
        this.next = next;
    }

    public DoTill(Predicate<T> predicate, Function<T, T> next) {
        this.predicate = predicate;
        this.func = null;
        this.next = next;
    }

    BiFunction<T, Consumer<T>, T> peak = (value, consumer) -> {
        if (Objects.nonNull(func)) consumer.accept(value);
        return value;
    };

    BiFunction<BiFunction, T, T> iteration = (callback, value) -> predicate.test(value) ?
            (T) callback.apply(callback, peak.andThen(next).apply(value, func)) : value;

    public Function<T, T> init = x -> iteration.apply(iteration, x);

}
