package com.jpvr.codechallenges.leetcode.challenge202006.week01;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * TWO CITY SCHEDULING
 *
 * There are 2N people a company is planning to interview.
 * The cost of flying the i-th person to city A is costs[i][0],
 * and the cost of flying the i-th person to city B is costs[i][1].
 *
 * Return the minimum cost to fly every person to a city
 * such that exactly N people arrive in each city.
 *
 * Example 1:
 * - Input: [[10,20],[30,200],[400,50],[30,20]]
 * - Output: 110
 * Explanation:
 * - The first person goes to city A for a cost of 10.
 * - The second person goes to city A for a cost of 30.
 * - The third person goes to city B for a cost of 50.
 * - The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110
 * to have half the people interviewing in each city.
 *
 * Note:
 * - 1 <= costs.length <= 100
 * - It is guaranteed that costs.length is even.
 * - 1 <= costs[i][0], costs[i][1] <= 1000
 */
public class Day03 {

    static Stream<Arguments> testParameters () { return Stream.of(

            Arguments.of(
                    new int[][] {{10,20},{30,200}},
                    50),
            Arguments.of(
                    new int[][] {{10,20},{30,200},{400,50},{30,20}},
                    110),
            Arguments.of(
                    new int[][] {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}},
                    1859),
            Arguments.of(
                    new int[][] {{515,563},{451,713},{537,709},{343,819},{855,779},{457,60},{650,359},{631,42}},
                    3086),
            Arguments.of(
                    new int[][] {{518,518},{71,971},{121,862},{967,607},{138,754},{513,337},{499,873},{337,387},{647,917},{76,417}},
                    3671),
            Arguments.of(
                    new int[][] {{70,311},{74,927},{732,711},{126,583},{857,118},{97,928},{975,843},{175,221},{284,929},{816,602},{689,863},{721,888}},
                    4723),
            Arguments.of(
                    new int[][] {{918,704},{77,778},{239,457},{284,263},{872,779},{976,416},{860,518},{13,351},{137,238},{557,596},{890,227},{548,143},{384,585},{165,54}},
                    4532)
    ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => costs = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_get_two_city_scheduling_costs(int[][] costs, int expectedOutput) {
        assertThat(twoCitySchedCost(costs), is(expectedOutput));
    } // end void should_get_two_city_scheduling_costs(int[][] costs, int expectedOutput)

    /**
     * Runtime: 1 ms
     * Memory Usage: 37.4 MB
     */
    public int twoCitySchedCost(int[][] costs) {

        Arrays.sort(costs, (a, b) -> {
            return (a[0] - a[1]) - (b[0] - b[1]);
        });

        final int length = costs.length;

        int cost = 0;
        for(int i=0; i<length/2; ++i)
            cost += costs[i][0];
        for(int i=length/2; i<length; ++i)
            cost += costs[i][1];

        return cost;
    } // end

    /**
     * Runtime: 7 ms
     * Memory Usage: 39.1 MB
     */
    public int twoCitySchedCostSlow(int[][] costs) {

        final int totalPeople = costs.length;
        int cost = 0;

        Map<Integer, Integer> costDifferencesMapUnsorted = new TreeMap<>(Collections.reverseOrder());

        for(int i=0; i<totalPeople; ++i) {
            costDifferencesMapUnsorted.put(i, Math.abs(costs[i][0]-costs[i][1]));
        } // end for(int i=0; i<totalPeople; ++i)

        LinkedHashMap<Integer, Integer> costDifferencesMapSorted = new LinkedHashMap<>();
        costDifferencesMapUnsorted.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> costDifferencesMapSorted.put(x.getKey(), x.getValue()));

        int cityAVisitCounter = 0;
        int cityBVisitCounter = 0;

        for( Map.Entry<Integer, Integer> entry : costDifferencesMapSorted.entrySet() ) {

            final int costCityA = costs[entry.getKey()][0];
            final int costCityB = costs[entry.getKey()][1];

            if ( cityAVisitCounter == totalPeople/2 ) {
                cost += costCityB;
                continue;
            }

            if ( cityBVisitCounter == totalPeople/2 ) {
                cost += costCityA;
                continue;
            }

            if ( costCityA <= costCityB ) {
                cityAVisitCounter++;
                cost += costCityA;
            } else {
                cityBVisitCounter++;
                cost += costCityB;
            }
        } // end iteration

        return cost;
    } // end int twoCitySchedCost(int[][] costs)

    /**
     * 4 ms
     */
//    public int twoCitySchedCost(int[][] costs) {
//        List<Pair> diff = new ArrayList<>();
//
//        for(int i = 0; i<costs.length;i++) {
//            int A = costs[i][0];
//            int B = costs[i][1];
//            diff.add(new Pair(A-B,i));
//        }
//
//        Collections.sort(diff, Comparator.comparing(Pair::getDiff));
//
//        int totalCost = 0;
//        int ctr = 0;
//
//        for(Pair p: diff)
//            totalCost += costs[p.index][(ctr++ > (costs.length / 2) -1) ? 1 : 0];
//
//
//        return totalCost;
//    }
//    static class Pair{
//        int diff;
//        int index;
//        Pair(int diff,int index) {
//            this.diff = diff;
//            this.index = index;
//        }
//        int getDiff() {
//            return diff;
//        }
//    }

    /**
     * 3 ms
     */
//    public int twoCitySchedCost(int[][] costs) {
//        int[][] dp = new int[costs.length + 1][costs.length + 1]; // i th person j city A
//        for(int i = 1; i < costs.length + 1; i ++) dp[i][0] = dp[i - 1][0] + costs[i - 1][1];
//        for(int i = 1; i < costs.length + 1; i ++){
//            for(int j = 1; j <= i && j <= costs.length; j ++){
//                if(i - 1 >= j){
//                    dp[i][j] = Math.min(dp[i - 1][j - 1] + costs[i - 1][0], dp[i - 1][j] + costs[i - 1][1]);
//                }else{
//                    dp[i][j] = dp[i - 1][j - 1] + costs[i - 1][0];
//                }
//            }
//        }
//        return dp[costs.length][costs.length / 2];
//    }

    /**
     * 2 ms
     */
//    public int twoCitySchedCost(int[][] costs) {
//        Arrays.sort(costs,(int[] costs1, int[] costs2) ->{
//            int k = Math.abs(costs1[0] - costs1[1]);
//            int l = Math.abs(costs2[0] - costs2[1]);
//            return l - k;
//        });
//        int countToA = 0;
//        int countToB = 0;
//        int mincost = 0;
//        for(int i=0; i<costs.length; i++){
//            if((costs[i][0] <= costs[i][1] && countToA < costs.length/2) || countToB >= costs.length/2){
//                mincost = mincost + costs[i][0];
//                countToA ++;
//            } else {
//                mincost = mincost + costs[i][1];
//                countToB ++;
//            }
//        }
//        return mincost;
//    }

    /**
     * 1ms
     */
    public int twoCitySchedCostOp(int[][] cost) {

        int N=cost.length;
        int ans=0;

        PriorityQueue<Integer> pq=new PriorityQueue<Integer>();

        for(int i=0;i<N;i++) {
            ans+=cost[i][0];
            pq.add(cost[i][1]-cost[i][0]);
            if(i>=N/2) {
                ans+=pq.poll();
            }
        }
        return ans;
    }

    /**
     * Best. 0 ms
     */
//    public int twoCitySchedCost(int[][] costs) {
//        quickSelect(costs, costs.length / 2);
//        int total = 0;
//        for (int i = 0; i < costs.length / 2; i++) {
//            total += costs[i][0];
//        }
//        for (int i = costs.length / 2; i < costs.length; i++) {
//            total += costs[i][1];
//        }
//        return total;
//    }
//
//    public void quickSelect(int[][] data, int k) {
//        int l = 0;
//        int r = data.length - 1;
//        int end = -1;
//        int currentK = k;
//        do {
//            end = quickSelect(data, currentK, l, r);
//            if (end < k) {
//                currentK = k - end - 1;
//                l = end + 1;
//            } else {
//                r = end - 1;
//            }
//        } while (end != k && end != k - 1);
//    }
//
//    public int nextPivot (int max, int min) {
//        return min + (int)(Math.random() * (max - min + 1));
//    }
//
//    public int quickSelect(int[][] data, int k, int l, int r) {
//        int end = l;
//        if (k < r - l + 1) {
//            int pivotIndex = nextPivot(r, l);
//            swap(data, l, pivotIndex);
//            for (int i = l + 1; i <= r; i++) {
//                if (compare(data[l], data[i]) < 0) {
//                    swap(data, ++end, i);
//                }
//            }
//            swap(data, end, l);
//        }
//        return end;
//    }
//
//    public void swap(int[][] data, int i, int j) {
//        int[] temp = data[i];
//        data[i] = data[j];
//        data[j] = temp;
//    }
//
//    public int compare(int[]a, int[]b) {
//        int comp = Integer.compare(a[1] - a[0], b[1] - b[0]);
//        return comp;
//    }
} // end class Day03