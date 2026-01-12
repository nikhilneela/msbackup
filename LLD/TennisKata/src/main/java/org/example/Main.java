package org.example;

import org.example.christmas_song.LyricRunner;
import org.example.christmas_song.builder.LyricBuilder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LyricRunner runner = new LyricRunner(LyricBuilder.buildLyricData(), "and");
        String song = runner.getSong();
        System.out.println(song);
    }
}