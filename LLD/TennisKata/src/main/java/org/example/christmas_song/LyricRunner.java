package org.example.christmas_song;

import lombok.AllArgsConstructor;
import org.example.christmas_song.model.LyricData;
import org.example.christmas_song.model.LyricDayData;

@AllArgsConstructor
public class LyricRunner {
    private LyricData lyricData;
    private String appender;

    public String getSong() {
        int numDays = lyricData.getNumDays();
        StringBuilder builder = new StringBuilder();

        for (int currentDay = 1; currentDay <= 12; ++currentDay) {
            builder.append("\n");
            LyricDayData dayData = lyricData.getLyricDayData()[currentDay - 1];
            builder.append(lyricData.getFormattedData().replace("{dayName}", dayData.getDayName()));
            builder.append("\n");
            for (int day = currentDay; day >= 1; --day) {
                LyricDayData d = lyricData.getLyricDayData()[day - 1];
                builder.append(d.getContent());
                if (day == 2) {
                    builder.append(" ");
                    builder.append(appender);
                }
                builder.append("\n");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
