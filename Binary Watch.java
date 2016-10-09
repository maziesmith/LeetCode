/*
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.
For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
*/
public class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> resultList = new ArrayList<>();
        if (num < 0) {
            return resultList;
        }
         Map<Integer, String[]> hourMap = new HashMap<>();
        String[] hour0 = {"0"};
        String[] hour1 = {"1", "2", "4", "8"};
        String[] hour2 = {"3", "5", "9", "6", "10"};
        String[] hour3 = {"7", "11"};
        
        hourMap.put(0, hour0);
        hourMap.put(1, hour1);
        hourMap.put(2, hour2);
        hourMap.put(3, hour3);
        
        Map<Integer, String[]> minMap = new HashMap<>();
        String[] min0 = {"00"};
        String[] min1 = {"01", "02", "04", "08", "16", "32"};
        String[] min2 = {"03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48"};
        String[] min3 = {"07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49", "50", "52", "56"};
        String[] min4 = {"15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58"};
        String[] min5 =  {"31", "47", "55", "59"};
        
        minMap.put(0, min0);
        minMap.put(1, min1);
        minMap.put(2, min2);
        minMap.put(3, min3);
        minMap.put(4, min4);
        minMap.put(5, min5);
        
        for (int i = 0; i <= num; i++) {
            if (!hourMap.containsKey(i) || !minMap.containsKey(num - i)) {
                continue;
            }
            for (String hour : hourMap.get(i)) {
                for (String min : minMap.get(num - i)) {
                    resultList.add(hour + ":" + min);
                }
            }
        }
        return resultList;
    }
}
