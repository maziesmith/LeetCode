/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

public class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return  false;
        }
        int num = n;
        Set<Integer> visited = new HashSet<>();
        while (n != 1 && !visited.contains(n)) {
            visited.add(n);
            n = getNextNumber(n);
        }
        if (n == 1) {
            return true;
        }
        return false;
    }

    private int getNextNumber(int num) {
        int result = 0;
        while(num != 0) {
            int lastDigit = num % 10;
            result += lastDigit * lastDigit;
            num /= 10;
        }
        return result;
    }
}
