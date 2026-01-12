package org.example;

import java.util.ArrayList;
import java.util.List;

public class RecursiveChunking {


    static void print(List<String> input) {
        for (int i = 0; i < input.size(); ++i) {
            System.out.print(input.get(i) + " ");
        }
    }

    static List<String> splitString(String text, String[] delimiters, int maxSize, int splitBy) {
        List<String> result = new ArrayList<>();
        if (text.length() <= maxSize) {
            result.add(text);
            return result;
        }

        for (int i = 0; i < delimiters.length; ++i) {
            String[] parts = text.split(delimiters[i]);
            List<String> _r = new ArrayList<>();
            if (parts.length > 1) {
                for (String part : parts) {
                    List<String> __r = splitString(part, delimiters, maxSize, i);
                    _r.addAll(__r);
                }
                result.addAll(mergeChunks(_r, maxSize, delimiters[i]));
                break;
            }
        }
        return result;
    }

    static List<String> mergeChunks(List<String> chunks, int maxSize, String delimiter) {
        List<String> output = new ArrayList<>();

        StringBuilder buffer = new StringBuilder();
        buffer.append(chunks.get(0));

        for (int i = 1; i < chunks.size(); ++i) {
            String current = chunks.get(i);

            if (current.length() + buffer.length() + delimiter.length() <= maxSize) {
                buffer.append(delimiter);
                buffer.append(current);
            } else {
                output.add(buffer.toString());
                buffer = new StringBuilder();
                buffer.append(current);
            }
        }
        output.add(buffer.toString());
        return output;
    }

    public static void main(String[] args) {
        String text = "I'm interviewing at Salesforce.  Nithin is asking a question.  I will try to solve it, Love this question";
        String[] delimiters = new String[] {"  ", " "};

        List<String> result =  splitString(text, delimiters, 19, -1 );
        List<String> _result = mergeChunks(result, 19, delimiters[1]);

        for (String entry : _result) {
            System.out.println(entry);
        }
    }

    /*
    I'm
    interviewing
    at
    Salesforce.
    Nithin is
    asking a
    question, I
    will try to
    solve it
     */

}
