package com.i2bgod.kong.alg;

import com.i2bgod.kong.exception.KongClientException;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
public class Topological {

    public static <T, R> List<Node<T, R>> buildTopologicalSort(Collection<Node<T, R>> targets, Function<R, T> keyFunc, Function<R, Collection<T>> insKeyFunc) {
        if (CollectionUtils.isEmpty(targets)) {
            return Collections.emptyList();
        }
        final HashMap<T, Node<T, R>> nodeMap = new HashMap<>(targets.size());
        targets.forEach(target -> nodeMap.put(keyFunc.apply(target.getVal()), target));

        targets.parallelStream().forEach(target -> {
            Collection<T> insKey = insKeyFunc.apply(target.getVal());
            if (CollectionUtils.isEmpty(insKey)) {
                return;
            }
            Set<Node<T, R>> ins = insKey.parallelStream().map(nodeMap::get).collect(Collectors.toSet());
            target.addInComing(ins);
        });

        return topologicalSort(targets.parallelStream().collect(Collectors.toSet()));
    }

    public static <T, R> List<Node<T, R>> topologicalSort(Set<Node<T, R>> targets) {
        final LinkedList<Node<T, R>> sorted = new LinkedList<>();
        final Set<Node<T, R>> visited = new HashSet<>();
        final Set<Node<T, R>> marked = new HashSet<>();

        targets.forEach(target -> {
            if (!visited.contains(target) && ! marked.contains(target)) {
                visit(target, visited, marked, sorted);
            }
        });

        return sorted;
    }

    private static <T, R> void visit(Node<T, R> current, Set<Node<T, R>> visited, Set<Node<T, R>> marked, LinkedList<Node<T, R>> sorted) {
        if (visited.contains(current)) {
           return;
        }

        if (marked.contains(current)) {
            throw new KongClientException("Cycle detected");
        }

        marked.add(current);
        Set<Node<T, R>> ins = current.getIns();

        if (CollectionUtils.isNotEmpty(ins)) {
            ins.forEach(in -> visit(in, visited,marked, sorted));
        }
        marked.remove(current);
        visited.add(current);

        sorted.addFirst(current);
    }
}
