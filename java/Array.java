import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Array {
    public static boolean repeated(String s) {
        // absabf
        // abcdeabcdeabcdeabcde
        // abcdeabcdeabcde
        // increment base compparison seqquentially 
        // for (int i = 0; i < s.length(); i++) {
        //     int lenBase = i+1;
        //     // loop through remaining string with incremenets of length of base character
        //     for (int k = lenBase; k + lenBase <= s.length(); k += lenBase) {
        //         // encountered a substring unequal
        //         if (!s.substring(0, lenBase).equals(s.substring(k, k + lenBase))) {
        //             break;
        //         }
        //         // // reached the final lenght
        //         if (k + lenBase == s.length()) {
        //             System.out.println(s.substring(0, lenBase));
        //             return true;
        //         }
        //     }
        // }
        // return false;

        // efficient solution (Using dvisors and quotients concept)
        // the repeating substring should be within the half of the string
        int l = s.length();
        for (int i = l / 2; i >= 1; i--) {
            // the length of repeating string is divisor of total length 
            if (l % i == 0) {
                // number of repitions needed
                int m = l / i;
                StringBuilder temp = new StringBuilder();
                // adding the base string m times and checking if equal to original
                while (m != 0) {
                    temp.append(s.substring(0, i));
                    m--;
                }
                if (s.equals(temp.toString()))
                    return true;
            }
        }
        return false;
    }

    public static int[] twoSum(int[] nums, int target) {
        // O(n^2) solution
        // int[] result = {0, 0};
        // int len = nums.length;
        // for (int i = 0; i < len; i++) {
        //     int toFind = target - nums[i];
        //     for (int k = i + 1; k < len; k++) {
        //         if (nums[k] == toFind) {
        //             result[0] = i; result[1] = k;
        //             return result; 
        //         }
        //     }
        // }
        // return result;
    
        // Efficient solution (HashMap)
        // key: number, value: index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(repeated("FEEFEE"));
        // System.out.println(("efwf").substring(0,1));
        // int[] nums = {1,2,3,4};
        // int target = 7;
        // System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}