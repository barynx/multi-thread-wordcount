package com.github.barynx.wordcount;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MultiThreadWordcount {

    /**
     * Gets a comma separated list of filepaths to text files as arguments and processes each file in a separate thread.
     * Prints out the list of most common words in the input files.
     *
     * @param args space separated list of filepaths for files to be processed.
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        if (args.length == 0) {
            System.out.println("No files to be processed. Please provide space separated list of files to be processed.");
        } else {

            // Create a list of Files for filepaths passed in args
            List<File> filesList = new LinkedList<>();
            for (String filePath : args ) {
                filesList.add(new File(filePath));
            }

            // initialize a new word occurences map
            WordcountMap wordFrequencies = new WordcountMap();

            // create a thread for each file and start them
            List<Thread> threads = new ArrayList<>();

            for (int i = 0; i < filesList.size(); i++) {
                threads.add(new Thread(new FileProcessor(filesList.get(i), wordFrequencies)));
                threads.get(i).start();
            }

            //wait for all file processing threads to be finished.
            for (Thread thread : threads) {
                thread.join();
            }

            // retrieve top 10 words from the map
            List<String> topWords = wordFrequencies.getTop(10);

            //print to console top 10 most common words
            for (String word : topWords) {
                System.out.println(word);
            }
        }
    }
}
