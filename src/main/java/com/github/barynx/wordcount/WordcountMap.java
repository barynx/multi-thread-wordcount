package com.github.barynx.wordcount;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  Class responsible for keeping a current state of word counting and providing a way to retrieve most common words.
 */
class WordcountMap {

    // Map of words and its occurrences counter implemented as ConcurrentHashMap for the purpose of effective
    // multi-thread implementation of wordcount
    private ConcurrentHashMap<String, Counter> wordOccurrencesMap;


    WordcountMap(){

        wordOccurrencesMap = new ConcurrentHashMap<>();
    }

    /**
     * Updates a map of word occurrences with a given word
     * @param word a word to be inserted into a map of word occurences
     */
    void updateWordOccurrencesMap(String word) {

        if(wordOccurrencesMap.containsKey(word)){
            wordOccurrencesMap.put(word, wordOccurrencesMap.get(word).increaseValue());
        }
        else wordOccurrencesMap.put(word,new Counter());
    }

    /**
     * Returns top n most frequently occuring words in the word occurences map. If the number of words in the map is less
     * than a specified number, then return all words.
     *
     * @param number a number of most common word occurences to be returned
     * @return a list of top n words in the word occurences map
     */
    List<String> getTop(int number){

        List<Entry<String, Counter>> list = sortMapEntries(wordOccurrencesMap);
        int topWordsSize = number;
        if (list.size() < number) topWordsSize = list.size();

        List<String> topWords = new ArrayList<>();

        for(int i = 0; i < topWordsSize; i++){
            topWords.add(list.get(i).getKey());
        }
            return topWords;
    }

    /**
     * Helper method for sorting map entries and returning them as a sorted List of map entries
     *
     * @param wordOccurrencesMap map of word occurences
     * @return sorted list containing entries of the map
     */
    private List<Entry<String, Counter>> sortMapEntries(ConcurrentHashMap<String, Counter> wordOccurrencesMap){

        List<Entry<String, Counter>> list = new ArrayList<>(wordOccurrencesMap.entrySet());
        list.sort(new WordcountEntryComparator());

        return list;
    }
}
