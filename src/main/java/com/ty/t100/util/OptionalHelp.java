package com.ty.t100.util;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class OptionalHelp<T> {
    private static final OptionalHelp<?> EMPTY = new OptionalHelp<>();

    private final T value;

    private OptionalHelp() {
        this.value = null;
    }

    public static <T> OptionalHelp<T> empty() {
        @SuppressWarnings("unchecked")
        OptionalHelp<T> t = (OptionalHelp<T>) EMPTY;
        return t;
    }

    private OptionalHelp(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> OptionalHelp<T> of(T value) {
        return new OptionalHelp<>(value);
    }

    public static <T> OptionalHelp<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable runnable) {
        if (value != null) {
            consumer.accept(value);
        } else {
            runnable.run();
        }
    }

    public OptionalHelp<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent()) {
            return this;
        } else {
            return predicate.test(value) ? this : empty();
        }
    }

    public <U> OptionalHelp<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return OptionalHelp.ofNullable(mapper.apply(value));
        }
    }

    public <U> OptionalHelp<U> flatMap(Function<? super T, OptionalHelp<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    @SuppressWarnings("unchecked")
    public <T> Stream<T> stream() {
        if (isPresent()) {
            return (Stream<T>) Stream.of(value);
        } else {
            return Stream.empty();
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }


    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }


    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Optional)) {
            return false;
        }

        OptionalHelp<?> other = (OptionalHelp<?>) obj;
        return Objects.equals(value, other.value);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }


    @Override
    public String toString() {
        return value != null
                ? String.format("Optional[%s]", value)
                : "Optional.empty";
    }
}
