package com.github.barynx.wordcount;

import java.util.Comparator;
import java.util.Map.Entry;

/**
 * Comparator used when sorting the map of words and and their occurrences by the occurrences number.
 */
public class WordcountEntryComparator implements Comparator {

    /**
     * Used for sorting in descending order (i.e. most common words first) in order to avoid reversing the list later on.
     *
     * @param o1 first map entry to compare
     * @param o2 second map entry to compare
     * @return 1 if the number of occurences in o1 is lesser than o2, -1 if the number of occurences in o1 is greater than o2, 0 if number of occurrences is equal
     */
    @Override
    public int compare(Object o1, Object o2) {
        Entry<String, Counter> entry1 = (Entry<String, Counter>) o1;
        Entry<String, Counter> entry2 = (Entry<String, Counter>) o2;

        boolean compare = entry1.getValue().getValue() > entry2.getValue().getValue();

        if(compare) return -1;
        if (entry1.getValue().getValue() == entry2.getValue().getValue()) return 0;
        return 1;
    }
}
