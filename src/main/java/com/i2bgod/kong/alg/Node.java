package com.i2bgod.kong.alg;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
@Setter
@Getter
public class Node<T, R> {
    private final Set<Node<T, R>> outs = new HashSet<>();
    private final Set<Node<T, R>> ins = new HashSet<>();
    private T key;
    private R val;

    public Node(T key, R val, Collection<Node<T, R>> ins) {
        this.key = key;
        this.val = val;
        addInComing(ins);
    }

    public void addInComing(Collection<Node<T, R>> ins) {
        if (CollectionUtils.isEmpty(ins)) {
           return;
        }
        this.ins.addAll(ins);
        ins.parallelStream().forEach(in -> in.outs.add(this));
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", val=" + val +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(key, node.key) &&
                Objects.equals(val, node.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, val);
    }
}
