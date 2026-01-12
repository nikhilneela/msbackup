package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {
    private Map<String, String> parent;
    private Map<String, Double> weight;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        parent = new HashMap<>();
        weight = new HashMap<>();


        for (int i = 0; i < equations.size(); i++) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);
            double value = values[i];

            if (!parent.containsKey(var1)) {
                parent.put(var1, var1);
                weight.put(var1, 1.0);
            }
            if (!parent.containsKey(var2)) {
                parent.put(var2, var2);
                weight.put(var2, 1.0);
            }

            union(var1, var2, value);
        }


        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            if (!parent.containsKey(var1) || !parent.containsKey(var2)) {
                results[i] = -1.0;
            } else {
                String root1 = find(var1);
                String root2 = find(var2);

                if (!root1.equals(root2)) {
                    results[i] = -1.0;
                } else {
                    results[i] = weight.get(var1) / weight.get(var2);
                }
            }
        }

        return results;
    }


    private void union(String var1, String var2, double value) {
        String root1 = find(var1);
        String root2 = find(var2);

        if (!root1.equals(root2)) {
            parent.put(root1, root2);
            weight.put(root1, value * weight.get(var2) / weight.get(var1));
        }
    }


    private String find(String var) {
        if (!var.equals(parent.get(var))) {
            String originParent = parent.get(var);
            parent.put(var, find(originParent));
            weight.put(var, weight.get(var) * weight.get(originParent));
        }
        return parent.get(var);
    }

    public static void main(String[] args) {
        EvaluateDivision evaluateDivision = new EvaluateDivision();
        List<List<String>> equations = new ArrayList<>();

        equations.add(new ArrayList<>());
        equations.add(new ArrayList<>());
        equations.add(new ArrayList<>());
        equations.add(new ArrayList<>());
        equations.add(new ArrayList<>());
        equations.add(new ArrayList<>());

        double[] values = new double[equations.size()];

        equations.get(0).add("a");
        equations.get(0).add("b");
        values[0] = 4.0;

        equations.get(1).add("a");
        equations.get(1).add("c");
        values[1] = 3.0;

        equations.get(2).add("a");
        equations.get(2).add("e");
        values[2] = 2.0;

        equations.get(3).add("b");
        equations.get(3).add("d");
        values[3] = 6.0;

        equations.get(4).add("c");
        equations.get(4).add("e");
        values[4] = 3.0;

        equations.get(5).add("c");
        equations.get(5).add("d");
        values[5] = 8.0;


        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<>());
        queries.add(new ArrayList<>());

        queries.get(0).add("a");
        queries.get(0).add("d");

        queries.get(1).add("c");
        queries.get(1).add("d");

        evaluateDivision.calcEquation(equations, values, queries);
    }
}
