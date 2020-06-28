package com.jpvr.codechallenges.leetcode.challenge202006.week04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * RECONSTRUCT ITINERARY
 *
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * One must use all the tickets once and only once.
 *
 * Example 1:
 * - Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * - Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Example 2:
 * - Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * - Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
public class Day28 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                        Arguments.of(
                                Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO")),
                                Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC")),

                        Arguments.of(
                                Arrays.asList(Arrays.asList("JFK", "SFO"), Arrays.asList("JFK", "ATL"), Arrays.asList("SFO", "ATL"), Arrays.asList("ATL", "JFK"), Arrays.asList("ATL", "SFO")),
                                Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"))
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_reconstruct_itinerary(List<List<String>> tickets, List<String> expectedOutput) {
        assertThat(findItinerary(tickets), is(expectedOutput));
    } // end void should_reconstruct_itinerary(List<List<String>> tickets, List<String> expectedOutput)

    /**
     * Runtime: 4 ms
     * Memory Usage: 40.1 MB
     */
    private List<String> findItinerary(List<List<String>> tickets) {
        if ( tickets == null  ||  tickets.size() == 0) {
            throw new IllegalArgumentException();
        }

        final HashMap<String, PriorityQueue<String>> adjacencyList = buildAdjacencyList(tickets);

        LinkedList<String> itinerary = new LinkedList<>();
        dfs("JFK", adjacencyList, itinerary);

        return itinerary;
    } // end List<String> findItinerary(List<List<String>> tickets)

    private void dfs(String origin, HashMap<String, PriorityQueue<String>> adjacencyList, LinkedList<String> itinerary) {
        PriorityQueue<String> destinations = adjacencyList.get(origin);
        while( destinations != null  &&  !destinations.isEmpty() ) {
            dfs(destinations.poll(), adjacencyList, itinerary);
        } // end iteration

        itinerary.addFirst(origin);
    } // end void dfs(String origin, HashMap<String, List<String>> adjacencyList, LinkedList<String> itinerary)

    /**
     * Runtime: 5 ms
     * Memory Usage: 40.2 MB
     */
    private List<String> findItineraryUsingStack(List<List<String>> tickets) {
        if ( tickets == null  ||  tickets.size() == 0) {
            throw new IllegalArgumentException();
        }

        final HashMap<String, PriorityQueue<String>> adjacencyList = buildAdjacencyList(tickets);

        Stack<String> itinerary = new Stack<>();
        List<String> result = new ArrayList<>();

        dfsUsingStack("JFK", adjacencyList, itinerary);

        while(!itinerary.isEmpty()) {
            result.add(itinerary.pop());
        } // end iteration

        return result;
    } // end List<String> findItineraryUsingStack(List<List<String>> tickets)

    private void dfsUsingStack(String origin, HashMap<String, PriorityQueue<String>> adjacencyList, Stack<String> itinerary) {
        PriorityQueue<String> destinations = adjacencyList.get(origin);
        while( destinations != null  &&  !destinations.isEmpty() ) {
            final String destination = destinations.poll();
            dfsUsingStack(destination, adjacencyList, itinerary);
        } // end iteration

        itinerary.push(origin);
    } // end void dfsUsingStack(String origin, HashMap<String, List<String>> adjacencyList, Stack<String> itinerary)

    /**
     * Util function
     */
    private HashMap<String, PriorityQueue<String>> buildAdjacencyList(final List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> adjacencyList = new HashMap<>();

        String origin;
        String destination;
        for(List<String> ticket : tickets) {
            origin = ticket.get(0);
            destination = ticket.get(1);

            adjacencyList.putIfAbsent(origin, new PriorityQueue<>());
            adjacencyList.get(origin).add(destination);
        } // end iteration

        return adjacencyList;
    } // end HashMap<String, PriorityQueue<String>> buildAdjacencyList(final List<List<String>> tickets)
} // end class Day28






















































































