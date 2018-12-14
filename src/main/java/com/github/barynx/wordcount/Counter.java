package com.github.barynx.wordcount;

/**
 * Class used for storing a number of occurences of a certain word in a map. Mutable value allows for increasing
 * the counter without a need to create a new Integer each time the value is updated.
 */
public class Counter {

    private volatile long value;

    public Counter(){
        value = 1;
    }

    public Counter increaseValue() {

        synchronized (this) {
            value = value + 1;
        }
        return this;
    }

    public long getValue(){
        return value;
    }

}
