package com.jpvr.codechallenges.leetcode.challenge202006.week02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * CHEAPEST FLIGHTS WITHIN K STOPS
 *
 * There are n cities connected by m flights. Each flight starts
 * from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting
 * city src and the destination dst, your task is to find the
 * cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * - n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * - src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *         0
 *        / \
 *       /   \
 *     100   500
 *     /       \
 *    <         \
 *   1 ---100--->2
 *
 * The cheapest price from city 0 to city 2 with at most
 * 1 stop costs 200, as marked red in the picture.
 *
 *
 * Example 2:
 * Input:
 * - n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * - src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *         0
 *        / \
 *       /   \
 *     100   500
 *     /       \
 *    <         \
 *   1 --------->2
 *
 * The cheapest price from city 0 to city 2 with at most
 * 0 stop costs 500, as marked blue in the picture.
 *
 *
 * Constraints:
 * - The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * - The size of flights will be in range [0, n * (n - 1) / 2].
 * - The format of each flight will be (src, dst, price).
 * - The price of each flight will be in the range [1, 10000].
 * - k is in the range of [0, n - 1].
 * - There will not be any duplicated flights or self cycles.
 */
public class Day14 {
    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(3, new int[][] {{0,1,100},{1,2,100},{0,2,500}},
                        0, 2, 1,
                        200),
                Arguments.of(3, new int[][] {{0,1,100},{1,2,100},{0,2,500}},
                        0, 2, 0,
                        500)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, flights = {1}, src = {2}, dst = {3}, K = {4}, expected = {5}")
    @MethodSource("testParameters")
    public void should_find_cheapest_flight(int n, int[][] flights, int src, int dst, int K, int expectedOutput) {
        assertThat(findCheapestPrice(n, flights, src, dst, K), is(expectedOutput));
    } // end void should_find_cheapest_flight(int n, int[][] flights, int src, int dst, int K, int expectedOutput)

    /**
     * Runtime: 8 ms
     * Memory Usage: 41.9 MB
     */
    private int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        List<List<PairNode>> graph = new ArrayList<>();

        for(int i=0; i<n; ++i) {
            graph.add(new ArrayList<>());
        }

        for(int[] path : flights) {
            int startVertex = path[0];
            int endVertex = path[1];
            int cost = path[2];

            graph.get(startVertex).add(new PairNode(endVertex, cost));
        } // end iteration

        PriorityQueue<AdjacencyNode> minHeap = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        minHeap.add(new AdjacencyNode(src, 0, 0));

        while( !minHeap.isEmpty() ) {
            final AdjacencyNode adjacencyNode = minHeap.poll();
            int city = adjacencyNode.city;
            int distance = adjacencyNode.distance;
            int cost = adjacencyNode.cost;

            if ( city == dst ) {
                return cost;
            } // end

            if ( distance <= K ) {
                for(PairNode node : graph.get(city)) {
                    minHeap.add(new AdjacencyNode(node.endVertex, distance + 1, cost + node.cost));
                } // end
            } // end
        } // end queue iteration

        return -1;
    } // end int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)

    class PairNode {
        int endVertex;
        int cost;

        public PairNode(int endVertex, int cost) {
            this.endVertex = endVertex;
            this.cost = cost;
        }
    } // end class PairNode

    class AdjacencyNode {
        int city;
        int distance;
        int cost;

        public AdjacencyNode(int city, int distance, int cost) {
            this.city = city;
            this.distance = distance;
            this.cost = cost;
        }
    } // end class AdjacencyNode

    /**
     * Runtime: 6 ms
     * Memory Usage: 42.2 MB
     */
    private int findCheapestPriceDP(int n, int[][] flights, int src, int dst, int K) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[src] = 0;

        for (int i = 0; i <= K; ++i) {
            int[] temp = dp.clone();

            for (int[] flight : flights) {
                if (temp[flight[0]] != Integer.MAX_VALUE) {
                    dp[flight[1]] = Math.min(dp[flight[1]], temp[flight[0]] + flight[2]);
                }
            } // end flights iteration
        } // end stops iteration

        return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
    } // end int findCheapestPriceDP(int n, int[][] flights, int src, int dst, int K)

} // end class Day14