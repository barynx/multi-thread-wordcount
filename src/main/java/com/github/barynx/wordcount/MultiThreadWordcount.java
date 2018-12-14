package com.github.barynx.wordcount;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

            // initialize a new word occurences map
            WordcountMap wordFrequencies = new WordcountMap();

            // create a thread for each file and start them
            ExecutorService executorService = Executors.newCachedThreadPool();
            for (String filePath : args) {
                executorService.execute(new FileProcessor(new File(filePath), wordFrequencies));
            }

            //wait for all file processing threads to be finished.
            executorService.shutdown();
            executorService.awaitTermination(60000, TimeUnit.MILLISECONDS);

            // retrieve top 10 words from the map
            List<String> topWords = wordFrequencies.getTop(10);

            //print to console top 10 most common words
            for (String word : topWords) {
                System.out.println(word);
            }
        }
    }
}
