package org.learning.lld.model;

import lombok.AllArgsConstructor;

/*
 * Location can be a physical address or virtual URL (like a zoom link)
 * How to model this so we can extend this to multiple types of locations
 */
@AllArgsConstructor
public class Location {
    private String id;
    private String title;
    private ILocationTypeData typeData;
    private LocationType locationType;
}
