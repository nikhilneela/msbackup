package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AllOneV2 {
    class Node {
        Set<String> elements;
        int frequency;
        Node up;
        Node down;

        public Node(Set<String> elements, int frequency) {
            this.elements = elements;
            this.frequency = frequency;
            this.up = this.down = null;
        }
    }

    HashMap<String, Integer> elementToFrequency;
    HashMap<Integer, Node> frequencyToNode;
    Node min, max;

    public AllOneV2() {
        this.elementToFrequency = new HashMap<>();
        this.frequencyToNode = new HashMap<>();
    }

    public String getMinKey() {
        if (this.min != null) {
            return this.min.elements.stream().findFirst().orElse(null);
        }
        return null;
    }

    public String getMaxKey() {
        if (this.max != null) {
            return this.max.elements.stream().findFirst().orElse(null);
        }
        return null;
    }

    public void decrement(String key) {
        int currentFrequency = elementToFrequency.getOrDefault(key, 0);
        if (currentFrequency == 0) {
            return;
        }
        int newFrequency = currentFrequency - 1;
        Node srcNode = frequencyToNode.get(currentFrequency);
        //we do not need to create targetNode in this case, need to just update min/max and take care of srcNode deletion
        if (newFrequency == 0) {
            elementToFrequency.remove(key);
            srcNode.elements.remove(key);
            if (srcNode.elements.isEmpty()) {
                frequencyToNode.remove(currentFrequency);
                this.min = srcNode.down;
                if (this.max == srcNode) {
                    this.max = null;
                }
            }
            return;
        }

        Node targetNode = frequencyToNode.getOrDefault(newFrequency, new Node(new HashSet<>(), newFrequency));

        if (this.min == srcNode) {
            this.min = targetNode;
        }

        //srcNode is guaranteed to exist
        srcNode.elements.remove(key);

        if (srcNode.elements.isEmpty()) {
            frequencyToNode.remove(currentFrequency);
            Node down = srcNode.down;
            targetNode.down = srcNode.down;
            if (down != null) {
                down.up = targetNode;
            }
            if (this.min == srcNode) {
                this.min = targetNode;
            }

            if (this.max == srcNode) {
                this.max = targetNode;
            }
            srcNode = null;
        }

        //this means targetNode is a brand new node and need to linked into the chain
        if (srcNode != null && targetNode.down != srcNode) {
            Node up = srcNode.up;
            targetNode.down = srcNode;
            targetNode.up = up;
            if (up != null) {
                up.down = targetNode;
            }
        }
        elementToFrequency.put(key, newFrequency);
        frequencyToNode.put(newFrequency, targetNode);
        targetNode.elements.add(key);
    }

    public void increment(String key) {
        int currentFrequency = elementToFrequency.getOrDefault(key, 0);
        int newFrequency = currentFrequency + 1;
        elementToFrequency.put(key, newFrequency);
        Node srcNode = frequencyToNode.get(currentFrequency);
        Node targetNode = frequencyToNode.getOrDefault(newFrequency, new Node(new HashSet<>(), newFrequency));
        frequencyToNode.put(newFrequency, targetNode);
        targetNode.elements.add(key);

        if (this.max == srcNode) {
            this.max = targetNode;
        }

        if (srcNode == null) {
            targetNode.down = this.min;
            if (this.min != null) {
                this.min.up = targetNode;
            }
            this.min = targetNode;
            return;
        }

        srcNode.elements.remove(key);

        if (srcNode.elements.isEmpty()) {
            frequencyToNode.remove(currentFrequency);
            Node up = srcNode.up;
            Node down = srcNode.down;

            if (up != null) {
                up.down = down;
            }

            if (down != null) {
                down.up = up;
            }

            if (this.min == srcNode) {
                this.min = targetNode;
            }
            srcNode = null;
        }

        //if srcNode.down == targetNode, we are good, nothing needs to be done
        //this means targetNode is a brand new frequency group and needs to be inserted next/down to srcNode
        if (srcNode != null && srcNode.down != targetNode) {
            Node down = srcNode.down;
            srcNode.down = targetNode;
            targetNode.up = srcNode;
            if (down != null) {
                down.up = targetNode;
            }
            targetNode.down = down;
        }
    }

    public void printMinAndMax() {
        System.out.println("Min = " + this.getMinKey() + " Max = " + this.getMaxKey());
    }

    public static void main(String[] args) {
        AllOneV2 allOne = new AllOneV2();
        /*
        allOne.increment("box");
        //allOne.increment("box");
        allOne.printMinAndMax();

        allOne.decrement("box");
        allOne.printMinAndMax();
        */
        allOne.increment("cox"); //cox -> 1
        allOne.increment("cox"); //cox -> 2
        allOne.increment("cox"); //cox -> 3
        allOne.increment("cox"); //cox -> 4
        allOne.decrement("cox"); //cox -> 3
        allOne.decrement("cox"); //cox -> 2
        allOne.decrement("cox"); //cox -> 1
        allOne.decrement("cox");
        allOne.increment("box"); //box -> 1
        allOne.decrement("cox"); //box -> 1
        allOne.increment("box"); //box -> 2
        allOne.increment("box"); //box -> 3
        allOne.increment("box"); //box -> 4
        allOne.increment("cox"); //cox -> 1, box -> 4
        allOne.increment("cox"); //cox -> 2, box -> 4
        allOne.increment("cox"); //cox -> 3, box -> 4
        allOne.increment("cox"); //cox -> 4, box -> 4
        allOne.decrement("box"); //cox -> 4, box -> 3
        allOne.increment("box"); //cox -> 4, box -> 4
        allOne.decrement("cox"); //cox -> 3, box -> 4
        allOne.decrement("box"); //cox -> 3, box -> 3
        allOne.decrement("cox"); //cox -> 2, box -> 3
        allOne.decrement("box"); //cox -> 2, box -> 2
        allOne.decrement("cox"); //cox -> 1, box -> 2
        allOne.decrement("cox"); //box -> 2
        allOne.decrement("box"); //box -> 1
        allOne.decrement("box");
        allOne.printMinAndMax();








        /*
        allOne.increment("box");
        allOne.increment("box");
        allOne.increment("cake");
        allOne.increment("cake");


        allOne.increment("dog");
        allOne.increment("dog");
        allOne.increment("dog");
        allOne.increment("dog");

        allOne.increment("dog");
        allOne.increment("cup");
        allOne.increment("cake");
        allOne.increment("cake");
        allOne.increment("cake");
        allOne.increment("cake");
        allOne.increment("dog");
        allOne.increment("cake");
        allOne.increment("water");
        allOne.increment("dog");

        allOne.printMinAndMax();
        */

    }
}
