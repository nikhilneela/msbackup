package org.example;

import java.util.ArrayList;
import java.util.List;

public class CountNodesWithHighestScore {
    static int computeSubTreeSizes(int index, int[] sizes, List<Integer>[] tree) {
        if (sizes[index] != 0) {
            return sizes[index];
        }

        List<Integer> children = tree[index];

        if (children == null) {
            sizes[index] = 1;
            return sizes[index];
        }

        for (int i = 0; i < children.size(); ++i) {
            sizes[index] += computeSubTreeSizes(children.get(i), sizes, tree);
        }
        return ++sizes[index];
    }

    static void buildTree(int[] parents, List<Integer>[] tree) {
        for (int i = 1; i < parents.length; ++i) {
            if (tree[parents[i]] == null) {
                tree[parents[i]] = new ArrayList<>();
            }
            tree[parents[i]].add(i);
        }
    }
    public static int countHighestScoreNodes(int[] parents) {
        List<Integer> [] tree = new List[parents.length];
        int[] sizes = new int[parents.length];
        buildTree(parents, tree);
        computeSubTreeSizes(0, sizes, tree);
        long max = 0, score;
        int maxCount = 0;
        for(int i = 0; i < parents.length; ++i) {
            score = 1;
            List<Integer> children = tree[i];
            if (children != null) {
                if (i != 0) {
                    score = sizes[0] - sizes[i];
                }
                for (int x = 0; x < children.size(); ++x) {
                     score *= sizes[children.get(x)];
                }
            }
            else {
                score = sizes[0] - sizes[i];
            }
            if (score > max) {
                max = score;
                maxCount = 1;
            } else if (score == max) {
                maxCount++;
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int ans1 = CountNodesWithHighestScore.countHighestScoreNodes(new int[] {-1, 2, 0, 2, 0});
        System.out.println(ans1);

        int ans2 = CountNodesWithHighestScore.countHighestScoreNodes(new int[] {-1, 2, 0});
        System.out.println(ans2);
    }
}
