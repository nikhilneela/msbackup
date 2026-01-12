package org.example;

public class UnionFindBySizeExample {
    public static void main(String[] args) {
        UnionFindBySize unionFind = new UnionFindBySize(7);

        unionFind.union(1,2);
        unionFind.union(2,3);
        unionFind.union(4,5);
        unionFind.union(6,7);
        unionFind.union(5,6);


        if (unionFind.isConnected(3, 7)) {
            System.out.println("3 & 7 are connected");
        } else {
            System.out.println("3 & 7 are not connected");
        }

        unionFind.union(3, 7);

        if (unionFind.isConnected(3, 7)) {
            System.out.println("3 & 7 are connected");
        } else {
            System.out.println("3 & 7 are not connected");
        }
    }
}
