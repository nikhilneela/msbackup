package org.example;

public class UnionFindBySize {
    private int[] sizes;
    private int[] parents;

    public UnionFindBySize(int n) {
        this.sizes = new int[n + 1];
        this.parents = new int[n + 1];

        for (int i = 0; i < this.parents.length; ++i) {
            this.sizes[i] = 1;
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

        int size_upu = this.sizes[u];
        int size_upv = this.sizes[v];

        if (size_upu <= size_upv) {
            //it does not matter which component gets connected to which one
            this.parents[up_u] = up_v;
            this.sizes[up_v] += this.sizes[up_u];
        } else {
            //smaller component gets attached to larger component
            this.parents[up_v] = up_u;
            this.sizes[up_u] += this.sizes[up_v];
        }
    }

    public boolean isConnected(int u, int v) {
        return this.findParent(u) == this.findParent(v);
    }
}
