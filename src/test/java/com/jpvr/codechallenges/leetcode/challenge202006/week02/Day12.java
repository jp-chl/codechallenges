package com.jpvr.codechallenges.leetcode.challenge202006.week02;


import com.jpvr.codechallenges.leetcode.Weekly2020Jun.week02.RandomizedSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * INSERT DELETE GetRandom O(1)
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements.
 * Each element must have the same probability of being returned.
 *
 * Example:
 *
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 *
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class Day12 {

    RandomizedSet randomSet = null;

    private void setUp() {

        randomSet = new RandomizedSet();
    } // end void setUp()

    @Test
    void should_insert_element() {

        setUp();

        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);
    } // end void should_insert_element()

    @Test
    void should_not_remove_not_existent_element() {

        setUp();

        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);

        boolean removeResult = randomSet.remove(2);
        assertFalse(removeResult);
    } // end void should_not_remove_not_existent_element()

    @Test
    void should_insert_two_elements() {

        setUp();

        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);

        insertResult = randomSet.insert(2);
        assertTrue(insertResult);
    } // end void should_insert_two_elements()

    @Test
    void should_get_random_element() {

        setUp();

        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);

        insertResult = randomSet.insert(2);
        assertTrue(insertResult);

        final int element = randomSet.getRandom();
        boolean isElementOneOrTwo = (element == 1  ||  element == 2);
        assertTrue(isElementOneOrTwo);
    } // end should_get_random_element()

    @Test
    void should_remove_existent_element() {

        setUp();

        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);

        insertResult = randomSet.insert(2);
        assertTrue(insertResult);

        boolean removeResult = randomSet.remove(1);
        assertTrue(removeResult);
    } // end void should_remove_existent_element()

    @Test
    void should_fail_after_failed_insertion() {

        setUp();

        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);

        insertResult = randomSet.insert(2);
        assertTrue(insertResult);

        insertResult = randomSet.insert(2);
        assertFalse(insertResult);
    } // end void should_fail_after_failed_insertion()

    @Test
    void should_get_unique_element() {

        setUp();

        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);

        insertResult = randomSet.insert(2);
        assertTrue(insertResult);

        boolean removeResult = randomSet.remove(1);
        assertTrue(removeResult);

        int element = randomSet.getRandom();
        assertNotEquals(1, element);
        assertEquals(2, element);
    } // end void should_get_unique_element()

    @Test
    void full_leetcode_test_set() {

        // Init an empty set.
        setUp();

        // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        boolean insertResult = randomSet.insert(1);
        assertTrue(insertResult);

        // Returns false as 2 does not exist in the set.
        boolean removeResult = randomSet.remove(2);
        assertFalse(removeResult);

        // Inserts 2 to the set, returns true. Set now contains [1,2].
        insertResult = randomSet.insert(2);
        assertTrue(insertResult);

        // getRandom should return either 1 or 2 randomly.
        int element = randomSet.getRandom();
        boolean isElementOneOrTwo = (element == 1  ||  element == 2);
        assertTrue(isElementOneOrTwo);

        // Removes 1 from the set, returns true. Set now contains [2].
        removeResult = randomSet.remove(1);
        assertTrue(removeResult);

        // 2 was already in the set, so return false.
        insertResult = randomSet.insert(2);
        assertFalse(insertResult);

        // Since 2 is the only number in the set, getRandom always return 2.
        element = randomSet.getRandom();
        assertEquals(2, element);
    } // end void full_leetcode_test_set()
} // end class Day12
