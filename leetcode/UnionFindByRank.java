package org.example;

public class UnionFindByRank {
    private int[] parents;
    private int[] ranks;
    public UnionFindByRank(int n) {
        this.parents = new int[n + 1];
        this.ranks = new int[n + 1];

        for (int i = 0; i < this.parents.length; i++) {
            this.parents[i] = i;
        }
    }

    private int findParent(int node) {
        if (node == this.parents[node]) {
            return node;
        }
        return this.parents[node] = findParent(this.parents[node]);
    }

    public void union(int u, int v) {
        int up_u = this.findParent(u);
        int up_v = this.findParent(v);

        if (up_u == up_v) {
            return;
        }

        int rank_upu = this.ranks[up_u];
        int rank_upv = this.ranks[up_v];


        if (rank_upu == rank_upv) {
            //both ranks are same, can connect any component to any, does not matter
            this.parents[up_u] = up_v;
            this.ranks[up_v]++; //up_u is child to up_v, this increases the rank of up_v
        } else if (rank_upu < rank_upv) {
            this.parents[up_u] = up_v;
        } else {
            this.parents[up_v] = up_u;
        }
    }

    public boolean isConnected(int u, int v) {
        return this.findParent(u) == this.findParent(v);
    }
}
