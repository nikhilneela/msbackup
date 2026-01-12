
package org.example;
/*
import java.util.HashMap;
import java.util.Map;

public class AllOne {
    class Node {
        String data;
        int frequency;
        Node prev, next;

        public Node(String data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }
    }
    class MasterNode {
        Node childNode;
        int frequency;
        MasterNode next, prev;

        public MasterNode(Node childNode, int frequency) {
            this.childNode = childNode;
            this.frequency = frequency;
        }
    }
    private Map<Integer, MasterNode> frequencyToMasterNodeMap;
    private Map<String, Node> elementToNodeMap;
    private MasterNode min, max;

    public AllOne() {
        this.frequencyToMasterNodeMap = new HashMap<>();
        this.elementToNodeMap = new HashMap<>();
    }

    public String getMaxKey() {
        if (max == null) {
            return null;
        }

        return max.childNode.data;
    }

    public String getMinKey() {
        if (min == null) {
            return null;
        }

        return min.childNode.data;
    }

    public void inc(String key) {
        Node childNode = this.elementToNodeMap.getOrDefault(key, new Node(key, 0));
        MasterNode sourceMasterNode = this.frequencyToMasterNodeMap.get(childNode.frequency);
        MasterNode targetMasterNode = this.frequencyToMasterNodeMap.getOrDefault(childNode.frequency + 1, new MasterNode(childNode, childNode.frequency + 1));

        boolean isSourceMasterNodeDeleted = moveNodeFromSourceToTargetMasterNode(sourceMasterNode, targetMasterNode, childNode);
        childNode.frequency++;

        if (isSourceMasterNodeDeleted && this.min == sourceMasterNode) {
            this.min = targetMasterNode;
        }

        if (this.max == null || this.max == sourceMasterNode) {
            this.max = targetMasterNode;
        }

        if (this.min == null) {
            this.min = targetMasterNode;
        }

        if (this.min.frequency > childNode.frequency) {
            this.min = targetMasterNode;
        }

        this.frequencyToMasterNodeMap.put(childNode.frequency, targetMasterNode);
        this.elementToNodeMap.put(key, childNode);
    }

    public void dec(String key) {
        Node childNode = this.elementToNodeMap.getOrDefault(key, null);
        if (childNode == null) {
            return;
        }

        MasterNode sourceMasterNode = this.frequencyToMasterNodeMap.get(childNode.frequency);
        MasterNode targetMasterNode = this.frequencyToMasterNodeMap.getOrDefault(childNode.frequency - 1, new MasterNode(childNode, childNode.frequency - 1));
        childNode.frequency--;

        boolean isSourceMasterNodeDeleted = moveNodeFromSourceToTargetMasterNode(sourceMasterNode, targetMasterNode, childNode);

        if (isSourceMasterNodeDeleted && this.min == sourceMasterNode) {
            if (childNode.frequency == 0) {
                this.min = this.max
            }

        }



        if (childNode.frequency != 0) {
            this.frequencyToMasterNodeMap.put(childNode.frequency, targetMasterNode);
            this.elementToNodeMap.put(key, childNode);
        }
    }

    private boolean moveNodeFromSourceToTargetMasterNode(MasterNode sourceMasterNode, MasterNode targetMasterNode, Node childNode) {

        MasterNode sourceMasterNodeNext = sourceMasterNode != null ? sourceMasterNode.next : null;
        MasterNode sourceMasterNodePrevious = sourceMasterNode != null ? sourceMasterNode.prev : null;

        MasterNode targetMasterNodeNext = targetMasterNode.next;
        MasterNode targetMasterNodePrevious = targetMasterNode.prev;

        //remove childNode from source master node and put in targetMasterNode (at the head)
        //if sourceMasterNode is null -> nothing to remove, so just put it in targetMasterNode
        boolean returnValue = false;
        Node previous = childNode.prev;
        Node next = childNode.next;
        Node temp = targetMasterNode.childNode;
        targetMasterNode.childNode = childNode;
        if (temp != childNode) {
            childNode.next = temp;
        }

        if (temp != childNode) {
            temp.prev = childNode;
        }

        if (previous != null) {
            previous.next = next;
        }

        if (next != null) {
            next.prev = previous;
        }

        if (sourceMasterNode != null) {
            if (sourceMasterNode.childNode == childNode) {
                sourceMasterNode.childNode = next;
            }
            if (sourceMasterNode.childNode == null) {
                this.frequencyToMasterNodeMap.remove(childNode.frequency);
                returnValue = true;
            }
        }
        return returnValue;
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("box");
        allOne.inc("box");
        allOne.inc("box");
        allOne.inc("cake");
        allOne.inc("cake");


        allOne.inc("dog");
        allOne.inc("dog");
        allOne.inc("dog");
        allOne.inc("dog");

        allOne.inc("dog");
        allOne.inc("cup");
        allOne.inc("cake");
        allOne.inc("cake");
        allOne.inc("cake");
        allOne.inc("cake");
        allOne.inc("dog");
        allOne.inc("cake");
        allOne.inc("water");
        allOne.inc("dog");
        System.out.println("Max = " + allOne.getMaxKey());
        System.out.println("Min = " + allOne.getMinKey());

    }

}
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {
    private Node head = new Node("", 0);
    private Node tail = new Node("", 0);

    private Map<String, Integer> map = new HashMap<>();
    private Map<Integer, Node> count = new HashMap<>();

    public AllOne() {
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        int newCount = map.getOrDefault(key, 0) + 1;
        Node oldNode = count.get(newCount - 1);
        Node newNode = count.get(newCount);

        map.put(key, newCount);

        if (newNode != null) {
            newNode.keys.add(key);
        } else {
            newNode = new Node(key, newCount);
            addNodeAfter(oldNode == null ? head : oldNode, newNode);
            count.put(newCount, newNode);
        }

        if (oldNode != null) {
            oldNode.keys.remove(key);
            if (oldNode.keys.isEmpty()) {
                removeNode(oldNode);
                count.remove(oldNode.count);
            }
        }
    }

    public void dec(String key) {
        int newCount = map.get(key) - 1;

        Node oldNode = count.get(newCount + 1);
        Node newNode = count.get(newCount);

        if (newCount == 0) {
            map.remove(key);
        } else {
            map.put(key, newCount);
        }

        if (newNode != null) {
            newNode.keys.add(key);
        } else if (newCount > 0) {
            newNode = new Node(key, newCount);
            addNodeBefore(oldNode, newNode);
            count.put(newCount, newNode);
        }

        oldNode.keys.remove(key);
        if (oldNode.keys.isEmpty()) {
            removeNode(oldNode);
            count.remove(oldNode.count);
        }
    }

    private void addNodeBefore(Node parent, Node node) {
        Node prev = parent.prev;
        prev.next = node;
        node.next = parent;
        parent.prev = node;
        node.prev = prev;
    }

    private void addNodeAfter(Node parent, Node node) {
        Node next = parent.next;
        parent.next = node;
        node.next = next;
        next.prev = node;
        node.prev = parent;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
        node.next = null;
        node.prev = null;
    }


    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.keys.iterator().next();
    }

    static class Node {
        Node prev;
        Node next;

        int count;
        Set<String> keys = new HashSet<>();

        public Node(String key, int count) {
            this.count = count;
            keys.add(key);
        }
    }
}

