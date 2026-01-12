package org.example.christmas_song.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LyricData {
    private String formattedData = "On the {dayName} day of Christmas,\n" +
            "My true love sent to me:";
    public LyricData(int numDays, LyricDayData[] dayData) {
        this.numDays = numDays;
        this.lyricDayData = dayData;
    }
    private int numDays;
    private LyricDayData[] lyricDayData;
}
