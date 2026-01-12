package org.example.filter.parser;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ArtistFilterData implements IFilterData<List<String>> {
    private List<String> artists;
    @Override
    public List<String> getData() {
        return artists;
    }
}
