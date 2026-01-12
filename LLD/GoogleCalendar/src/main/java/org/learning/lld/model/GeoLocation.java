package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GeoLocation {
    private final double lat;
    private final double lon;
}
