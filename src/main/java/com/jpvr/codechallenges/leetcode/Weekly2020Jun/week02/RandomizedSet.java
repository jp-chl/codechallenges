package com.jpvr.codechallenges.leetcode.Weekly2020Jun.week02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomizedSet {

    HashMap<Integer, Integer> map;
    List<Integer> list;
    Random random;
    int index;
    int lastElement;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    } // end RandomizedSetHashAndList()

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {

        if ( !map.containsKey(val) ) {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    } // end boolean insert(int val)

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {

        if ( map.containsKey(val) ) {
            index = map.remove(val);
            lastElement = list.get(list.size() - 1);
            if ( lastElement != val ) {
                list.set(index, lastElement);
                map.put(lastElement, index);
            }
            list.remove(list.size() - 1);
            return true;
        }
        return false;
    } // end boolean remove(int val)

    /** Get a random element from the set. */
    public int getRandom() {

        return list.get(random.nextInt(list.size()));
    } // end int getRandom()
} // end class RandomizedSet
