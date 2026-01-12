package org.example.christmas_song.builder;

import org.example.christmas_song.model.LyricData;
import org.example.christmas_song.model.LyricDayData;

public class LyricBuilder {
    public static LyricData buildLyricData() {
        return  new LyricData(12, new LyricDayData[]{
                new LyricDayData("first", "A partridge in a pear tree."),
                new LyricDayData("second", "Two turtle doves"),
                new LyricDayData("third", "Three french hens"),
                new LyricDayData("fourth", "Four calling birds"),
                new LyricDayData("fifth", "Five golden rings"),
                new LyricDayData("sixth", "Six geese a-laying"),
                new LyricDayData("seventh", "Seven swans a-swimming"),
                new LyricDayData("eight", "Eight maids a-milking"),
                new LyricDayData("ninth", "Nine ladies dancing"),
                new LyricDayData("tenth", "Ten lords a-leaping"),
                new LyricDayData("eleventh", "Eleven pipers piping"),
                new LyricDayData("twelfth", "Twelve drummers drumming")
        });
    }
}

