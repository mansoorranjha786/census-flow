package com.postwork.cursorflow.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RatingAreaService {
    private final Map<String, Integer> zipPrefixRatingArea = new HashMap<>();

    @PostConstruct
    public void init() {
        zipPrefixRatingArea.put("90", 1);
        zipPrefixRatingArea.put("10", 1);
        zipPrefixRatingArea.put("20", 1);
        zipPrefixRatingArea.put("30", 1);


        zipPrefixRatingArea.put("91", 2);
        zipPrefixRatingArea.put("11", 2);
        zipPrefixRatingArea.put("21", 2);
        zipPrefixRatingArea.put("31", 2);

        zipPrefixRatingArea.put("92", 3);
        zipPrefixRatingArea.put("12", 3);
        zipPrefixRatingArea.put("22", 3);
        zipPrefixRatingArea.put("32", 3);
        zipPrefixRatingArea.put("40", 3);
        zipPrefixRatingArea.put("50", 3);
        zipPrefixRatingArea.put("60", 3);
        zipPrefixRatingArea.put("70", 3);
        zipPrefixRatingArea.put("80", 3);


        zipPrefixRatingArea.put("93", 4);
        zipPrefixRatingArea.put("94", 4);
        zipPrefixRatingArea.put("95", 4);
        zipPrefixRatingArea.put("96", 4);
        zipPrefixRatingArea.put("97", 4);
        zipPrefixRatingArea.put("98", 4);
        zipPrefixRatingArea.put("99", 4);
        zipPrefixRatingArea.put("33", 4);
        zipPrefixRatingArea.put("34", 4);
        zipPrefixRatingArea.put("35", 4);
        zipPrefixRatingArea.put("36", 4);
        zipPrefixRatingArea.put("37", 4);
        zipPrefixRatingArea.put("38", 4);
        zipPrefixRatingArea.put("39", 4);
    }

    /**
     * @param zipCode -A valid 5-digit ZIP code
     * @return -the rating area
     */
    public int getRatingArea(String zipCode) {
        if (zipCode == null || zipCode.length() < 2) {
            throw new IllegalArgumentException("Zip code must be at least 2 digit");
        }

        String prefix = zipCode.substring(0, 2);
        return zipPrefixRatingArea.getOrDefault(prefix, 3);
    }
}
