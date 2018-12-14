package com.github.barynx.wordcount;
import java.util.*;
import java.util.Map.Entry;

/**
 *  Class responsible for keeping a current state of word counting and providing a way to retrieve most common words.
 */
class WordcountMap {

    // Map of words and its occurrences counter implemented as Hashtable because it is a synchronized collection for the purpose of multi-thread implementation of wordcount
    private Hashtable<String, Counter> wordOccurrencesMap;


    WordcountMap(){

        wordOccurrencesMap = new Hashtable<>();
    }

    /**
     * Updates a map of word occurrences with a given word
     * @param word a word to be inserted into a map of word occurences
     */
    void updateWordFrequenciesMap(String word) {

        if(wordOccurrencesMap.containsKey(word)){
            wordOccurrencesMap.put(word, wordOccurrencesMap.get(word).increaseValue());
        }
        else wordOccurrencesMap.put(word,new Counter());
    }

    /**
     * Returns top n most frequently occuring words in the word occurences map
     *
     * @param number a number of most common word occurences to be returned
     * @return a list of top n words in the word occurences map
     */
    String[] getTop(int number){

        List<Entry<String, Counter>> list = sortMapEntries(wordOccurrencesMap);
        int topWordsSize = number;
        if (list.size() < number) topWordsSize = list.size();

        String topWords[] = new String[topWordsSize];

        for(int i = 0; i < topWordsSize; i++){
            topWords[i] = list.get(i).getKey();
        }
            return topWords;
    }

    /**
     * Helper method for sorting map entries and returning them as a sorted List of map entries
     *
     * @param wordOccurerencesMap map of word occurences
     * @return sorted list containing entries of the map
     */
    private List<Entry<String, Counter>> sortMapEntries(Hashtable<String, Counter> wordOccurerencesMap){

        List<Entry<String, Counter>> list = new ArrayList<>();
        list.addAll(wordOccurerencesMap.entrySet());
        Collections.sort(list, new WordcountEntryComparator());

        return list;
    }
}
