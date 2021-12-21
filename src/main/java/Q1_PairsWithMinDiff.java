import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *Day:1
 *Topic: Array
 *Topic Question:1
 *Total Questions:1
 *https://leetcode.com/problems/minimum-absolute-difference/submissions/
 */
public class Q1_PairsWithMinDiff {

    public static void main(String[] args) {

        int[] arr = {40, 11, 26, 27, -20};
//sort the array should be an O(nlogn) operation
        Arrays.sort(arr, 0, arr.length);

        List<List<Integer>> response = new ArrayList<>();
//find minimum
        int min = Integer.MAX_VALUE;

//start from 2nd element & check diff og n with n-1
        for (int ind = 1; ind < arr.length; ind++) {
            int nextPairDiff = arr[ind] - arr[ind - 1];
            List<Integer> newPair = new ArrayList<>();
            if (min == nextPairDiff && min > 0) {
                //gather all the elements with min diff
                newPair.add(arr[ind]);
                newPair.add(arr[ind - 1]);
                response.add(newPair);
                //wil land here in start -> max > min
            } else if (min > nextPairDiff && min > 0) {
                //if you get a new min all ald resuls are obselete & should be garbaged.
                response.clear();
                //get new min  add in the response
                min = nextPairDiff;
                newPair.add(arr[ind]);
                newPair.add(arr[ind - 1]);
                response.add(newPair);
            }

        }
        System.out.println(Arrays.toString(arr));
        System.out.println(response);
    }

}
