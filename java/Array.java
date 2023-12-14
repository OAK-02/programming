import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

import javax.swing.text.html.HTMLDocument.Iterator;

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

    public static boolean isValidString(String s) {
        // Map<Character, Character> dic = new HashMap<Character, Character>();
        // dic.put('(', ')');
        // dic.put('{', '}');
        // dic.put('[', ']');
        // dic.put(')', '(');
        // dic.put('}', '{');
        // dic.put(']', '[');
        // // int[] counter = new int[]{0,0,0};
        // for (int i = 0; i < s.length(); i++) {
        //     char c = s.charAt(i);
        //     int checkIndex = i;
        //     if (c == ')' || c == '}' || c == ']') {
        //         checkIndex -= 1; 
        //     } else {
        //         checkIndex += 1; 
        //     }
            
        //     if (!(checkIndex != -1 && checkIndex != s.length() && dic.get(c) == s.charAt(checkIndex))) {
        //         return false;
        //     }
        // }
        // return true;
        Stack<Character> stack = new Stack<Character>();
        // Map<Character, Character> dic = new HashMap<Character, Character>();
        // dic.put(')', '(');
        // dic.put('}', '{');
        // dic.put(']', '[');
        // for (int i = 0; i < s.length(); i++) {
        //     char c = s.charAt(i);
        //     if (c == ')' || c == '}' || c == ']') {
        //         if(stack.empty() || dic.get(c) != stack.pop())
        //             return false;
        //     } else {
        //         stack.push(c);
        //     }
        // }
        // return stack.empty();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (stack.empty() || stack.pop() != '(') return false;
            } else if (c == '}') {
                if (stack.empty() || stack.pop() != '{') return false;
            } else if (c == ']') {
                if (stack.empty() || stack.pop() != '[') return false;
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public static int maxProfit(int[] prices) {
        // //  O(n^2) solution
        // // int max = 0;
        // // for (int i = 1; i < prices.length; i++) {
        // //     for (int j = 0; j < i; j++)
        // //         max = Integer.max(max, prices[i] - prices[j]); 
        // // }
        // // return max;

        // // O(n) solution usin kedane's algorithm
        // /* kadane's algortihm:
        //  * Break problem to subproblem
        //  * carry solution of subrpoblem
        //  * use carried to get optimized soltution
        //  */
        // /**
        //  * why holding the lowest dip?
        //  * eg - [1,21,7,40,24]
        //  * if we ecounter a pair (7,40) which is giving greater
        //  * result than initial max ( 40-7 = 33 > 20 = 21 -1 )
        //  * the lowest one will anyways be much greater hence giving "actual"
        //  * max possible by 39 = 40 - 1
        //  */
        // int lowestSoFar = Integer.MAX_VALUE;
        // int maxProfit = 0;
        // // loop through each price
        //     // check if this is the lowest dip
        //     // check current price give highest return with lowest dip so far
        //         // update optimal profit if does (carrying over part)
        // for (int i = 0; i < prices.length; i++) {
        //     if (prices[i] < lowestSoFar)
        //         lowestSoFar = prices[i];
        //     if (prices[i] - lowestSoFar > maxProfit) {
        //         maxProfit = prices[i] - lowestSoFar;
        //     }
        // }
        // return maxProfit;

        /**
         * [7,1,5,3,6,4]
         */
        if (prices.length == 0)
            return 0;
        int max_return = 0;
        int min_buy = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            if (min_buy > prices[i]) {
                min_buy = prices[i];
            } else if(max_return < (prices[i] - min_buy)){
                max_return = prices[i] - min_buy;
            } 
        }
        return max_return;
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hs.contains(nums[i])) {
                return true;
            } else {
                hs.add(nums[i]);
            }
        }
        return false;
    }


    public static int search(int[] nums, int target) {

        // int pivotIdx = findPiv(nums);
        // Integer rightPiv = binSearch(nums, pivotIdx, nums.length - 1, target);
        // Integer leftPiv = binSearch(nums, 0, pivotIdx, target);
        // return rightPiv != null ? rightPiv : leftPiv;
        // // return pivotIdx;

        // other solution
        /**
         * find index of the smallest valuer withing the array
         * gives number of places rotated and hence can use the circular queue method to find the real mid value
         * using binary search algorithm to find the target
         */
        int n = nums.length;
        int lo = 0; int hi = n - 1;
        //  find index of the smallest valuer withing the array (lo < hi since at lo==hi we are at teh smallest value (i.e. lo is at the smallest value))
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[hi]) {
                /*min is in the right half [mid + 1, ..., hi]*/
                lo = mid+1;
            } else {
                /*min is in left half [0, ..., mid]*/
                hi = mid;
            }
        }
        // normal binary search using circular queue rotation re-index evaluation for overshot mid values
        int actualLow = lo;
        lo = 0; hi = n-1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realMid = (mid + actualLow) % n; // since actual ascending array should be [nums[actualLow], ....]
            if (nums[realMid] == target) return realMid;
            else if (nums[realMid] < target) lo = mid+1;
            else hi = mid-1;
        }
        return -1;
    }
    public static Integer binSearch(int[] nums, int starting, int ending, int target) {
        int l = starting; int h = ending;
        while (l <= h) {
            int mid = (l+h) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                h = mid - 1;
            } else {
                return mid;
            }
        }
        return null;
    }
    public static int findPiv(int[] arr) {
    // [4,5,6,7,0,1,2]
    /**
     * if mid bigger search space to the right of mid
     * if mid smaller, search space to the left of mid 
     * piv = 4, l = 0, h = 7, mid = 3
     * 
     * */ 
        int h = arr.length-1;
        int l = 0;
        int pivIdx = 0;
        int piv = arr[pivIdx];
        while(l <= h) {
            int mid = (l + h) / 2;
            if (piv > arr[mid]) {
                piv = arr[mid]; pivIdx = mid;
                h = mid-1;
            } else {
                l = mid + 1;
            }
        }
        return pivIdx;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }

        void setNext(ListNode n) {
            next = n; 
        }
    }
    public static boolean hasCycle(ListNode head) {
        // Set<ListNode> uniques = new HashSet<ListNode>();
        List<ListNode> uniques = new ArrayList<>();
        ListNode traverse = head;
        while (traverse != null) {
            if (uniques.contains(traverse)) return true;
            uniques.add(traverse);
            traverse = traverse.next;
        }
        return false; 
    }

    // public static boolean subHasCycle(ListNode current, ListNode next) {
    //     if (next == null)
    //         return false;
    //     if (current == next) 
    //         return true;
    //     else {
    //         return subHasCycle(current, next.next) || subHasCycle(next, next.next);
    //     }
    // }
    
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return new int[]{};
    }


    /**120
     * [5,2,3,4] = [24,60,40,30]
     * @param nums
     * @return
     */
    static int[] productExceptSelf(int[] nums) {
        int prod = 1; 
        for (int num : nums)
            prod *= num;
        int[] result = new int[nums.length];
        Boolean neg = prod < 0;
        for (int k = 0; k < nums.length; k++) {
            neg = neg || nums[k] < 0;
            int temp_prod = Math.abs(prod);
            int divisor = Math.abs(nums[k]);
            int ans = 0;
            // bitwise division
            for (int i = 31; i > -1; i--) {
                int shift = Math.abs(divisor << i);
                int quo = shift <= Integer.MIN_VALUE || shift > Integer.MAX_VALUE ? 0 : shift;
                if (quo <= temp_prod) {
                    System.out.println(String.format("divisor: %d, quo: %d", k, quo));
                    temp_prod -= quo;
                    int temp_ans = Math.abs(1 << i);
                    ans += temp_ans <= Integer.MIN_VALUE || temp_ans > Integer.MAX_VALUE ? 0 : temp_ans;
                    // System.out.println(1 << 31);
                }
            }
            result[k] = (neg ? (-1 * ans) : ans);            
        }
        
        return result;
        // [1,2,3,4] -> [24,12,8,6]
        /**
         * [1,2,3,4] -
         * divi = div * q + r
         * divi - r / div = q
         * 24  2
         * 1) 2^3: 24 - (2* 2^3) = 8, q: 2^3 = 8
         * 8   2
         * 2) 2^2: 8 - (2* 2^2) = 0, 8 + (2^2) = 12
         */
        // int product
    }

    static void print_array(int[] arr) {
        System.out.print("[");
        for (int i =0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length-1)
                System.out.print(", ");
        } 
        System.out.println("]");
    }

    /**
     * Product except self
     * @param args
     */
    static int[] prefix_suffix_product(int[] nums) {
        // trial
        // int prod = 1; 
        // for (int num : nums)
        //     prod *= num;
        // int[] result = new int[nums.length];
        // Boolean neg = prod < 0;
        // for (int k = 0; k < nums.length; k++) {
        //     neg = neg || nums[k] < 0;
        //     int temp_prod = Math.abs(prod);
        //     int divisor = Math.abs(nums[k]);
        //     int ans = 0;
        //     // bitwise division
        //     for (int i = 31; i > -1; i--) {
        //         int shift = Math.abs(divisor << i);
        //         int quo = shift <= Integer.MIN_VALUE || shift > Integer.MAX_VALUE ? 0 : shift;
        //         if (quo <= temp_prod) {
        //             System.out.println(String.format("divisor: %d, quo: %d", k, quo));
        //             temp_prod -= quo;
        //             int temp_ans = Math.abs(1 << i);
        //             ans += temp_ans <= Integer.MIN_VALUE || temp_ans > Integer.MAX_VALUE ? 0 : temp_ans;
        //             // System.out.println(1 << 31);
        //         }
        //     }
        //     result[k] = (neg ? (-1 * ans) : ans);            
        // }
        // return result;
        
        // Ans 1
        // int len = arr.length;
        // int[] result = new int[len];
        // int[] prefix = new int[len];
        // prefix[0] = 1;
        // int[] suffix = new int[len];
        // suffix[len - 1] = 1;
        // for (int i = 0; i < len; ++i) {
        //     if (i != 0) {
        //         prefix[i] = prefix[i - 1] * arr[i - 1];
        //     }
        //     int last_idx = len - 1 - i;
        //     if (last_idx < len - 1) {
        //         suffix[last_idx] = suffix[last_idx + 1] * arr[last_idx + 1];
        //     }
        // }
        // for (int i = 0; i < len; ++i) {
        //     result[i] = suffix[i] * prefix[i];
        // }
        // return result;

        // Ans 2 (with O(1)) time complexity
        int suffix_prod = 1;
        int len = nums.length;
        int[] result = new int[len];
        result[0] = 1;
        for (int i = 1; i < len; ++i) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // [1, 10, 20, 60]
        for (int i = len - 2; i >= 0; --i) {
            suffix_prod = suffix_prod * nums[i + 1];
            result[i] = result[i] * suffix_prod;
        }
        return result;
    } 

    static List<Queue<Integer>> getZerosAndNegatives(int[] nums) {
        List<Queue<Integer>> ans = new ArrayList<>();
        Queue<Integer> q1 = new ArrayDeque<>(); 
        Queue<Integer> q2 = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < 0) {q2.add(i);}
            if (nums[i] == 0) {q1.add(i);}
        }
        ans.add(q1);
        ans.add(q2);
        return ans;
    }

    static boolean matches_remain_negative_condn(Integer v1, Integer v2) {
        return v1 != null && ( (v2 != null && v1 < v2)  || v2 == null );
    }

    static int maxProduct(int[] nums) {
        // O(n^2) solution
        // int ans = nums[0];
        // for (int i = 0; i < nums.length; i++) {
        //     int prod = nums[i];
        //     for (int k = i + 1; k < nums.length; ++k) {
        //         prod *= nums[k];
        //         if (prod > ans) { ans = prod; }
        //     }            
        //     if (prod > ans) { ans = prod; }
        // }
        // return ans;

        // int global_max = 0;
        // int local_max = 0;
        // List<Queue<Integer>> num_zeros_negatives = getZerosAndNegatives(nums);
        // Queue<Integer> zeros = num_zeros_negatives.get(0);
        // Queue<Integer> negatives = num_zeros_negatives.get(1);
        // global_max = local_max = nums[0];
        // if (global_max < 0) { negatives.remove(); }
        // if (global_max == 0) { zeros.remove(); }
        // for (int i = 1; i < nums.length; ++i) {
        //     int product  = local_max * nums[i];
        //     // here product can be >=0 or <0
        //     // if product is < 0  and the next coming negative integer is at an index less than
        //     // the next 0, then set the local maximum to to negative
        //     if (product < 0 && matches_remain_negative_condn(negatives.peek(), zeros.peek()) ) {
        //         local_max = product;
        //     // else continue the normal way
        //     } else {
        //         local_max = Integer.max(nums[i], product);
        //     }
        //     if (nums[i] < 0) { negatives.remove(); }
        //     if (nums[i] == 0) { zeros.remove(); }
        //     if (local_max > global_max)
        //         global_max = local_max;
        // }
        // return global_max;

        // // Kadane's algorithm O(n)
        // int local_max = 0;
        // int local_min = 0;
        // int global = 0;
        // local_min = local_max = global = nums[0];
        // for (int i = 1; i < nums.length; ++i) {
        //     // swapping since now the max and min since
        //     //  max > min => -1 * max < -1 * min 
        //     if (nums[i] < 0) {
        //         int temp = local_max;
        //         local_max = local_min;
        //         local_min = temp;
        //     }
        //     local_max = Math.max(nums[i], local_max * nums[i]);
        //     local_min = Math.min(nums[i], local_min * nums[i]);
        //     global = Math.max(local_max, global);
        // }
        // return global;

        // 2 pointer solution (Note - endings always included unless either are 0)
        int l = 0;
        int r = 0;
        int global = nums[0];
        for (int i = 0; i < nums.length; i++) {
            l = l == 0 ? 1 : l;
            r = r == 0 ? 1 : r;
            l *= nums[i];
            r *= nums[i];
            global = Math.max(global, Math.max(l, r));
        }
        return global;
    }

    static int maxSum(int[] nums) {
        /*
         * -2,1,-3,4,-1,2,1,-5,4
         * g = -2
         * g = 1
         * g = 1
         */
        int global_max = nums[0];
        // a running optimal subproblem carrier
        int local_max = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            local_max = Integer.max(nums[i], local_max + nums[i]);
            if (local_max > global_max)
                global_max  = local_max;
        }
        return global_max;
    }

    static int findSubMin(int[] nums, int l, int r) {
        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (mid == 0) {
                return 0;
            } else if (nums[mid - 1] > nums[mid]) {
                return mid;
            } else {
                r = mid;
            }
        }
        return mid;
    }

    // ans is at 0 or at the middle
    static int findMin(int[] nums) {
        int l = 0; 
        int r = nums.length;
        
        int left_min = findSubMin(nums, l, r/2);

        int right_min = findSubMin(nums, r/2, r); 

        return Math.min(nums[left_min], nums[right_min]);
    }

    public static void main(String[] args) {
        // ListNode n1 = new ListNode(2);
        // ListNode n2 = new ListNode(-1);
        // ListNode n3 = new ListNode(1);
        // ListNode n4 = new ListNode(2);
        // ListNode n5 = new ListNode(2);
        // n1.next = n2;
        // n2.next = n3;
        // System.out.println(hasCycle(n1));
        // System.out.println(findPiv(new int[]{4,5,6,7,0,1,2}));
        // System.out.println(search(new int[]{4,5,6,7,-1,0,1,2}, -1));
        // System.out.println(("efwf").substring(0,1));
        // int[] nums = {1,2,3,4};
        // int target = 7;
        // System.out.println(Arrays.toString(twoSum(nums, target)));
        // int[] arr = {7,6,4,3,1};
        // System.out.println(maxProfit(arr));
        System.out.println(findMin(new int[]{11,13,15,17}));
        //                                   0     3     7
    }
}