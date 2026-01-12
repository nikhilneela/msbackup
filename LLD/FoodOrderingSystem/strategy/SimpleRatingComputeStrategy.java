package org.FoodOrderingSystem.strategy;
import java.util.HashMap;
import java.util.Map;

public class SimpleRatingComputeStrategy implements IRatingComputeStrategy {

    private static final Map<Integer, Double> ratingWeights = new HashMap<>();

    static {
        ratingWeights.put(0, -0.5);
        ratingWeights.put(1, -0.25);
        ratingWeights.put(2, 0.1);
        ratingWeights.put(3, 0.5);
        ratingWeights.put(4, 0.75);
        ratingWeights.put(5, 1.0);
    }

    @Override
    public Integer computeRating(Integer currentRating, Integer inputRating) {
        int newRating = (int) (currentRating + ratingWeights.getOrDefault(inputRating, 0.0));
        return Math.max(0, Math.min(newRating, 5));
    }
}
