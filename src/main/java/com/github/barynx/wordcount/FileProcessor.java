package com.github.barynx.wordcount;

import java.io.*;

/**
 * Class responsible for processing a single file and writing results of processing into a words occurrences map. It can
 * run in a separate thread allowing for multi thread processing of multiple files.
 */
public class FileProcessor implements Runnable {

    private File inputFile;
    private WordcountMap outputMap;

    public FileProcessor(File inputFile, WordcountMap outputMap){
        this.inputFile = inputFile;
        this.outputMap = outputMap;
    };

    @Override
    public void run() {

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String words[] = line.trim().split("\\s+");

                for (String word : words)
                {
                    outputMap.updateWordFrequenciesMap(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
