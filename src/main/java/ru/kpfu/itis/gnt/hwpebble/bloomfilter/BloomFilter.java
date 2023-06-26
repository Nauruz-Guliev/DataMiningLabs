package ru.kpfu.itis.gnt.hwpebble.bloomfilter;

import java.util.BitSet;
import java.util.Random;

public class BloomFilter {

    private final BitSet bitSet;
    private final int bitSetSize;
    private final int numHashFunctions;
    private int amountOfWords;

    public BloomFilter(int expectedNumEntries, double falsePositiveRate) {
        int bitSetSize = (int) Math.ceil(-1 * (expectedNumEntries * Math.log(falsePositiveRate)) /
                Math.pow(Math.log(2.0), 2.0));
        this.bitSet = new BitSet(bitSetSize);
        this.numHashFunctions = (int) Math.round((bitSetSize / (double) expectedNumEntries) * Math.log(2.0));
        this.bitSetSize = bitSetSize;
    }

    private int[] getHashValues(String input) {
        int[] hashValues = new int[numHashFunctions];
        int hash1 = input.hashCode();
        int hash2 = new Random(hash1).nextInt();
        for (int i = 0; i < numHashFunctions; i++) {
            hashValues[i] = Math.abs((hash1 + i * hash2) % bitSetSize);
        }
        return hashValues;
    }

    public void add(String input) {
        int[] hashValues = getHashValues(input);
        for (int hash : hashValues) {
            bitSet.set(hash, true);
        }
    }

    public boolean mightContain(String input) {
        int[] hashValues = getHashValues(input);
        for (int hash : hashValues) {
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    public int getBitSetSize(){
        return bitSetSize;
    }
    public int getNumHashFunctions() {
        return numHashFunctions;
    }

    public int getAmountOfWords() {
        return amountOfWords;
    }

    public void setAmountOfWords(int amountOfWords) {
        this.amountOfWords = amountOfWords;
    }
}

