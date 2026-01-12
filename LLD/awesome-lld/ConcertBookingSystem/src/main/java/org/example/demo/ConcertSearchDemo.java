package org.example.demo;

import org.example.controller.ConcertsController;
import org.example.controller.ConcertsSearchController;
import org.example.filter.FilterType;
import org.example.filter.parser.*;
import org.example.filter.strategy.ArtistFilterStrategy;
import org.example.filter.strategy.DateRangeFilterStrategy;
import org.example.filter.strategy.DurationFilterStrategy;
import org.example.filter.strategy.FilterStrategyRegistry;
import org.example.model.Artist;
import org.example.model.Concert;
import org.example.model.Venue;
import org.example.repository.IConcertsRepository;
import org.example.repository.InMemoryConcertsRepository;
import org.example.service.ConcertsSearchService;
import org.example.service.ConcertsService;

import java.time.LocalDateTime;
import java.util.List;

public class ConcertSearchDemo {
    public static void main(String[] args) {
        FilterDataParserRegistry registry = new FilterDataParserRegistry();
        registry.register(FilterType.DATE, new DateRangeDataParser());
        registry.register(FilterType.DURATION, new DurationFilterDataParser());
        registry.register(FilterType.ARTIST, new ArtistFilterDataParser());

        FilterDataParser parser = new FilterDataParser(registry);

        IConcertsRepository concertsRepository = new InMemoryConcertsRepository();

        FilterStrategyRegistry filterStrategyRegistry = new FilterStrategyRegistry();
        filterStrategyRegistry.register(FilterType.DATE, new DateRangeFilterStrategy());
        filterStrategyRegistry.register(FilterType.DURATION, new DurationFilterStrategy());
        filterStrategyRegistry.register(FilterType.ARTIST, new ArtistFilterStrategy());

        ConcertsController controller = new ConcertsController(new ConcertsService(concertsRepository));
        addConcerts(controller);

        List<Concert> concerts = getConcerts(concertsRepository, filterStrategyRegistry, parser);

        for (Concert concert : concerts) {
            System.out.println(concert);
        }
    }

    private static void addConcerts(ConcertsController controller) {

        // === Concert 1 ===
        Venue venue1 = Venue.builder()
                .name("Royal Albert Hall")
                .build();

        List<Artist> artists1 = List.of(
                Artist.builder().name("Adele").build(),
                Artist.builder().name("Ed Sheeran").build()
        );

        controller.addConcert(
                "Acoustic Night",
                "A soulful evening of live acoustic performances.",
                venue1,
                artists1,
                LocalDateTime.of(2025, 11, 15, 19, 30),
                120
        );

        // === Concert 2 ===
        Venue venue2 = Venue.builder()
                .name("Madison Square Garden")
                .build();

        List<Artist> artists2 = List.of(
                Artist.builder().name("Imagine Dragons").build(),
                Artist.builder().name("The Killers").build()
        );

        controller.addConcert(
                "Rock Fest 2025",
                "An electrifying rock night with Imagine Dragons and The Killers.",
                venue2,
                artists2,
                LocalDateTime.of(2025, 12, 3, 20, 0),
                180
        );

        // === Concert 3 ===
        Venue venue3 = Venue.builder()
                .name("Sydney Opera House")
                .build();

        List<Artist> artists3 = List.of(
                Artist.builder().name("Hans Zimmer").build()
        );

        controller.addConcert(
                "Cinematic Symphony",
                "Experience Hans Zimmer performing his greatest film scores live.",
                venue3,
                artists3,
                LocalDateTime.of(2026, 1, 20, 18, 30),
                150
        );

        // === Concert 4 ===
        Venue venue4 = Venue.builder()
                .name("Wembley Stadium")
                .build();

        List<Artist> artists4 = List.of(
                Artist.builder().name("Coldplay").build()
        );

        controller.addConcert(
                "Music of the Spheres World Tour",
                "Coldplay’s spectacular live show filled with lights, colors, and energy.",
                venue4,
                artists4,
                LocalDateTime.of(2026, 2, 10, 19, 0),
                160
        );

        // === Concert 5 ===
        Venue venue5 = Venue.builder()
                .name("O2 Arena")
                .build();

        List<Artist> artists5 = List.of(
                Artist.builder().name("Taylor Swift").build()
        );

        controller.addConcert(
                "The Eras Tour",
                "A journey through Taylor Swift’s musical eras with her biggest hits.",
                venue5,
                artists5,
                LocalDateTime.of(2026, 3, 25, 20, 30),
                180
        );
    }

    private static List<Concert> getConcerts(IConcertsRepository concertsRepository, FilterStrategyRegistry filterStrategyRegistry, FilterDataParser parser) {
        ConcertsSearchService service = new ConcertsSearchService(concertsRepository, filterStrategyRegistry);

        ConcertsSearchController searchController = new ConcertsSearchController(parser, service);

        String json = """
                {
                  "filters": [
                    {
                      "filterName": "ARTIST",
                      "filterValue": ["Ed Sheeran"]
                    }
                  ]
                }
        """;

        return searchController.search(json);
    }
}
