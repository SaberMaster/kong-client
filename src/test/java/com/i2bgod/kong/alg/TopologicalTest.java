package com.i2bgod.kong.alg;

import com.i2bgod.kong.exception.KongClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class TopologicalTest {


    @Test
    void testBuildTopologicalSortSuccess() {
        LinkedList<Node<String, String>> list = new LinkedList<>();
        // Setup
        Node<String, String> service = new Node<>("service", "service", null);
        Node<String, String> route = new Node<>("route", "route", null);
        Node<String, String> plugin = new Node<>("plugin", "plugin", null);
        list.add(route);
        list.add(plugin);
        list.add(service);

        // Run the test
        final List<Node<String, String>> result = Topological.buildTopologicalSort(list, (v) -> v, (v) -> {
            if ("service".equals(v)) {
                return null;
            }
            if ("route".equals(v)) {
                return Collections.singletonList("service");
            }
            if ("plugin".equals(v)) {
                LinkedList<String> strings = new LinkedList<>();
                strings.add("service");
                strings.add("route");
                return strings;
            }
            return null;
        });

        // Verify the results
        Assertions.assertEquals(plugin.getKey(), result.get(0).getKey());
        Assertions.assertEquals(route.getKey(), result.get(1).getKey());
        Assertions.assertEquals(service.getKey(), result.get(2).getKey());
    }

    @Test
    void testTopologicalSortSuccess() {
        // Setup
        HashMap<String, Node<String, String>> stringNodeMap = new HashMap<>();
        Node<String, String> service = new Node<>("service", "service", null);
        Node<String, String> route = new Node<>("route", "route", null);
        Node<String, String> plugin = new Node<>("plugin", "plugin", null);

        stringNodeMap.put(service.getKey(), service);
        stringNodeMap.put(route.getKey(), route);
        stringNodeMap.put(plugin.getKey(), plugin);

        route.addInComing(Collections.singletonList(stringNodeMap.get("service")));
        plugin.addInComing(Collections.singletonList(stringNodeMap.get("route")));
        plugin.addInComing(Collections.singletonList(stringNodeMap.get("service")));

        Set<Node<String, String>> targets = new HashSet<>(stringNodeMap.values());

        // Run the test
        final List<Node<String, String>> result = Topological.topologicalSort(targets);

        // Verify the results
        Assertions.assertEquals(plugin.getKey(), result.get(0).getKey());
        Assertions.assertEquals(route.getKey(), result.get(1).getKey());
        Assertions.assertEquals(service.getKey(), result.get(2).getKey());
    }

    @Test
    void testTopologicalSortCycle() {
        Assertions.assertThrows(KongClientException.class,()-> {
            // Setup
            HashMap<String, Node<String, String>> stringNodeMap = new HashMap<>();
            Node<String, String> service = new Node<>("service", "service", null);
            Node<String, String> route = new Node<>("route", "route", null);
            Node<String, String> plugin = new Node<>("plugin", "plugin", null);

            stringNodeMap.put(service.getKey(), service);
            stringNodeMap.put(route.getKey(), route);
            stringNodeMap.put(plugin.getKey(), plugin);

            route.addInComing(Collections.singletonList(stringNodeMap.get("plugin")));
            plugin.addInComing(Collections.singletonList(stringNodeMap.get("route")));
            plugin.addInComing(Collections.singletonList(stringNodeMap.get("service")));

            Set<Node<String, String>> targets = new HashSet<>(stringNodeMap.values());

            // Run the test
            final List<Node<String, String>> result = Topological.topologicalSort(targets);

            // Verify the results
            Assertions.assertEquals(plugin.getKey(), result.get(0).getKey());
            Assertions.assertEquals(route.getKey(), result.get(1).getKey());
            Assertions.assertEquals(service.getKey(), result.get(2).getKey());
        });
    }
}
