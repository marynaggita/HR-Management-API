package com.dfcu.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class ServiceUtils {
    private static final String INPUT_FORMAT_1 = "EEE MMM dd HH:mm:ss zzz yyyy"; // Input format
    private static final String OUTPUT_FORMAT = "yyyy-MM-dd"; // Desired output format

    // Convert from one format to another
    public String convertDateFormat(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(INPUT_FORMAT_1);
        SimpleDateFormat outputFormat = new SimpleDateFormat(OUTPUT_FORMAT);

        try {
            // Parse the input string into a Date object
            Date date = inputFormat.parse(dateString);
            // Convert the Date object to the desired string format
            String formattedDate = outputFormat.format(date);
            System.out.println("Outgoing date: " + formattedDate);
            return formattedDate;
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return null; // Return null if parsing fails
        }
    }

}
