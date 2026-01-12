package org.example.service;

import lombok.AllArgsConstructor;
import org.example.filter.Filter;
import org.example.filter.strategy.FilterStrategyRegistry;
import org.example.filter.strategy.IFilterStrategy;
import org.example.model.Concert;
import org.example.repository.IConcertsRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ConcertsSearchService {
    private final IConcertsRepository repository;
    private final FilterStrategyRegistry registry;

    public List<Concert> search(List<Filter<?>> filters) {
        List<Concert> concerts = repository.getConcerts();
        List<Concert> filteredConcerts = new ArrayList<>();

        for (Filter<?> filter : filters) {
            IFilterStrategy strategy = registry.getStrategy(filter.getType());
            filteredConcerts.addAll(strategy.filter(concerts, filter.getData()));
        }

        return filteredConcerts;
    }
}
