package com.github.barynx.wordcount;

/**
 * Class used for storing a number of occurences of a certain word in a map. Mutable value allows for increasing
 * the counter without a need to create a new Integer each time the value is updated.
 */
class Counter {

    private volatile long value;

    Counter(){
        value = 1;
    }

    Counter increaseValue() {

        synchronized (this) {
            value = value + 1;
        }
        return this;
    }

    long getValue(){
        return value;
    }

}
