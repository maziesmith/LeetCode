/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

public class Solution {
     public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String str = '+' + s + "+";

        int index = 0;
        int total = 0;
        int term = 0;
        char op;
        int n = 0;

        while (index < str.length()) {
            index = getNextNoEmptyChar(str, index);
            op = str.charAt(index);
            index++;
            if (op == '+' || op == '-') {
                total += term;
                if (index == str.length()) {
                    break;
                }
                index = getNextNoEmptyChar(str, index);
                String subString = getSubStringNum(str, index);
                index += subString.length();
                term = Integer.parseInt(subString);
                if (op == '-') {
                    term *= -1;
                }
            }
            else {  
                index = getNextNoEmptyChar(str, index);
                String subString = getSubStringNum(str, index);
                index += subString.length();
                n = Integer.parseInt(subString);
                if (op == '*') {
                    term *= n;
                }
                else {
                    term /= n;
                }
            }
        }
        return total;
    }

    private String getSubStringNum(String str, int index) {
        int start = index;
        int end = start + 1;
        while (end < str.length()) {
            if (str.charAt(end) >= '0' && str.charAt(end) <= '9') {
                end++;
            }
            else {
                break;
            }
        }
        return str.substring(start, end);
    }


    private int getNextNoEmptyChar(String str, int index) {
        while (index < str.length()) {
            if (str.charAt(index) == ' ') {
                index++;
            }
            else {
                return index;
            }
        }
        return -1;  
    }
}


// Better Solution
public class Solution {
     public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int mulDiv = -1;
        int pre = -1;
        int sign = 1;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + Character.getNumericValue(s.charAt(i));
                    i++;
                }   
                i--;
                if (mulDiv == -1) {
                    pre = num;
                }
                else if (mulDiv == 1) {
                    pre = pre * num;
                    mulDiv = -1;
                }
                else {
                    pre = pre / num;
                    mulDiv = -1;
                }
            }
            else if (s.charAt(i) == '+') {
                result += sign * pre;
                sign = 1;
            }
            else if (s.charAt(i) == '-') {
                result += sign * pre;
                sign = -1;
            }
            else if (s.charAt(i) == '*') {
                mulDiv = 1;
            }
            else if (s.charAt(i) == '/') {
                mulDiv = 0;
            }
        }
        result += pre * sign;
        return result;
    }
}
// General Solution Using two stack

public class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + Character.getNumericValue(s.charAt(i));
                    i++;
                }
                // System.out.println(num);
                numStack.push(num);
                i--;
            }
            else if (s.charAt(i) == '(') {
                opStack.push(s.charAt(i));
            }
            else if (s.charAt(i) == ')') {
                while (opStack.peek() != '(') {
                    numStack.push(apply(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.pop();
            }
            else if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                while (opStack.size() != 0 && hasPrecedence(s.charAt(i), opStack.peek())) {
                    numStack.push(apply(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.push(s.charAt(i));  
            } 
        }

        while(opStack.size() != 0) {
            int numTwo = numStack.pop();
            int numOne = numStack.pop();
            char op = opStack.pop();
            // System.out.println("Test: " + numTwo + " " + numOne + " " +  op);
            numStack.push(apply(numTwo, numOne, op));
        }
        return numStack.pop();
    }

    private boolean hasPrecedence(char curChar, char preChar) {
        if (curChar == '+' || curChar == '-') {
            return true;
        }
        else if (preChar == '*' || preChar == '/') {
            return true;
        }
        return false;
    }

    private int apply(int numTwo, int numOne, char op) {
        if (op == '+') {
            return numTwo + numOne;
        }
        else if (op == '-') {
            return numOne - numTwo;
        }
        else if (op == '*') {
            return numOne * numTwo;
        }
        else {
            return numOne / numTwo;
        }
    }
}
