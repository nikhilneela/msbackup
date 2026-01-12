package org.example.filter.strategy;

import org.example.filter.Filter;
import org.example.filter.FilterCondition;
import org.example.filter.FilterType;
import org.example.filter.parser.ArtistFilterData;
import org.example.filter.parser.IFilterData;
import org.example.model.Artist;
import org.example.model.Concert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistFilterStrategy implements IFilterStrategy {
    @Override
    public List<Concert> filter(List<Concert> input, Filter<?> filter) {
        return input.stream().filter(
                concert -> evaluateCondition(
                        concert,
                        filter.getData(),
                        filter.getCondition()
                )).toList();
    }

    @Override
    public boolean matches(FilterType type) {
        return type == FilterType.ARTIST;
    }

    @Override
    public boolean evaluateCondition(Concert concert, IFilterData<?> filterData, FilterCondition condition) {
        ArtistFilterData artistFilterData = (ArtistFilterData) filterData;
        List<String> artists = artistFilterData.getData();
        return concert.getArtists().stream().map(Artist::getName).anyMatch(artists::contains);
    }

}
