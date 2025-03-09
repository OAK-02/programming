import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Tree {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }


    public static int height(Node t) {
        if (t == null || (t.left == null && t.right == null)) 
            return 0;
        int maxHeight =  Math.max(height(t.left), height(t.right));
        return maxHeight + 1;
    }

    static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq_counter = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            freq_counter.compute(nums[i], (key, v) -> (v == null) ? 1 : v+1);
        int[] ret_freq = new int[k];
        int[] ret_idx = new int[k];
        for (int i = 0 ; i<ret_freq.length; i++) ret_freq[i] = 0;
        for (Integer keys: freq_counter.keySet()) 
        {
            // get smallest index
            int smallest_idx = 0;
            for (int i = 1; i < ret_freq.length; i++) {
                if (ret_freq[smallest_idx] > ret_freq[i])
                    smallest_idx = i;
            }
            if (freq_counter.get(keys) > ret_freq[smallest_idx])
            {
                ret_freq[smallest_idx] = freq_counter.get(keys);
                ret_idx[smallest_idx] = keys;
            }
        }
        return ret_idx;
    }

    public static String encode(List<String> strs) {
        String s = "";
        for (String str: strs)
        {
            String delim = String.valueOf(str.length()) + "#";
            s += delim + str;
        }
        return s;
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */
    public static List<String> decode(String str) {
        List<String> ret = new ArrayList<>();
        while(str != "")
        {
            String num_chars_string = "";
            for (char c: str.toCharArray())
            {
                if (c == '#') break;
                num_chars_string += c;
            }
            Integer num_chars = Integer.parseInt(num_chars_string);
            // the beginning index of the next string
            str = str.substring(num_chars_string.length()+1);
            String word = str.substring(0, num_chars);
            str = str.substring(num_chars);
            ret.add(word);
        }
        return ret;
    }

    public static int[] productExceptSelf(int[] nums) {
        // int[] s = new int[nums.length];
        // int[] p = new int[nums.length];
        // int[] ret = new int[nums.length];
        // for (int i = 0; i < nums.length; i++)
        // {
        //     if (i == 0)
        //         p[i] = 1;
        //     else    
        //         p[i] = nums[i-1] * p[i-1];
        // }
        // for (int i = nums.length-1; i >= 0; i--)
        // {
        //     if (i == nums.length-1)
        //         s[i] = 1;
        //     else
        //         s[i] = s[i+1] * nums[i+1];
        //     ret[i] = s[i] * p[i];
        // }
        // return ret;

        // O(1) space complexity
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
        {
            if (i == 0)
                ret[i] = 1;
            else    
                ret[i] = nums[i-1] * ret [i-1];
        }
        int s = 1;
        for (int i = nums.length-1; i >= 0; i--)
        {
            ret[i] = ret[i] * s;
            s = s * nums[i];
        }
        return ret;
    }

    // static boolean isValidSudoku(char[][] board) {
    //     // encountered a fill cell
    //         // check if another one of this in the same row
    //         // check if another one of this in the same column
    //         // chceck if another one of this in the same square
    // }

    static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        // List<Integer> arrayInteger = Arrays.stream(nums).boxed().collect(Collectors.toList());
        // Set<Integer> set = new HashSet<>(arrayInteger);
        // int maxLength = 0;
        // for (Integer e: set)
        // {
        //     // not the starting point of a sequence
        //     if (set.contains(e-1)) continue;
        //     else {
        //         int tempLength = 1;
        //         int nextNum = e+1;
        //         while (true)
        //         {
        //             if (set.contains(nextNum)) 
        //             {
        //                 nextNum++;
        //                 tempLength++;
        //             }
        //             else break;
        //         }
        //         if (tempLength > maxLength) maxLength = tempLength;
        //     }
        // }
        // return maxLength;
            
        // sequence array answer
        int max = nums[0];
        int min = nums[0];
        for (int elem: nums)
        {
            if (elem > max) max = elem;
            if (elem < min) min = elem;
        }
        // build array seq (largest possible index is latgest_nmber_in_array - smalleste_number_in_array)
        byte[] arraySeq = new byte[max - min + 1];
        for (int elem: nums)
        {
            arraySeq[elem-min] = 1;
        }
        int maxSeq = 0;
        int tempSeq = 0;
        for (byte b: arraySeq) {
            if (b == 1) tempSeq++;
            //  reset
            else tempSeq = 0;
            if(tempSeq > maxSeq) maxSeq = tempSeq;
        }
        return maxSeq;
    }

    static int binSearchTwoSum(int[] numbers, int target, int currIdx) {
        int start = 0;
        int end = numbers.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (mid == currIdx) {
                if (target == numbers[currIdx] || target > numbers[currIdx])
                    start = mid+1;
                else
                    end = mid-1;
            } else {
                if (numbers[mid] > target) {
                    end = mid-1;
                } else if (numbers[mid] < target) {
                    start = mid+1;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    public static int[] twoSum(int[] numbers, int target) {
        // Approach 1: Bnary search O(n*logn))
        // for (int i = 0; i < numbers.length; i++) {
        //     int toFind = target - numbers[i];
        //     int retIdx = binSearchTwoSum(numbers, toFind, i);
        //     if (retIdx != -1)
        //         return new int[]{i+1, retIdx+1};
        // }
        // return new int[]{};

        // Approach 2: 2 indexes O(n)
        int left = 0;
        int right = numbers.length-1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target)
                left++;
            else if (sum > target)
                right--;
            else
                return new int[]{left+1,right+1};
        }
        return new int[]{};
    }

    static List<Integer> sortThreeNums(List<Integer> list) {
        int a = list.get(0);
        int b = list.get(1); 
        int c = list.get(2);
        if (a < b) {
            if (a < c) {
                list.set(0, a);
                list.set(1, b < c ? b : c);
                list.set(2, b > c ? b : c);
            } else {
                list.set(0, c);
                list.set(1, a);
                list.set(2, b);
                
            }
        } else {
            if (b < c) {
                list.set(0, b);
                list.set(1, a < c ? a : c);
                list.set(2, a > c ? a : c);
            } else {
                list.set(0, c);
                list.set(1, b);
                list.set(2, a);
                
            }
        }
        return list;
    }

    static void twoSumForThreeSum(int[] nums, int target, int start, List<List<Integer>> returning) {
        int end = nums.length-1;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum < target) {
                start++;
                continue;
            } else if (sum > target) {
                end--;
                continue;
            }
            returning.add(new ArrayList<>(Arrays.asList(-1*target, nums[start], nums[end])));
            start++;
            end--;
            // to say away from duplicates
            while (start < end && nums[end] == nums[end+1]) {
                end--;
            }
        }
    }

    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> returning = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // to stay away from duplicates
            if (i > 0 && nums[i] == nums[i-1]) 
                continue;
            twoSumForThreeSum(nums, nums[i] * -1, i+1, returning);
        }
        return returning;
    }

    public static void main(String[] args) {
        // Node rootNode = new Node(1);
        // rootNode.left = new Node(2);
        // rootNode.right = new Node(3);
        // rootNode.left.left = new Node(4);
        // rootNode.left.right = new Node(5);
        // /*
        //  *                  1
        //  *          2               3
        //  *      4       5  
        //  */
        // System.out.println(height(rootNode));
        // System.out.println(Arrays.toString(topKFrequent(new int[]{1}, 1)));
        // String encoded = encode(new ArrayList<>(Arrays.asList("")));
        // List<String> words = decode(encoded);
        // System.out.println(words.toString());
    
        // System.out.println(Arrays.toString(productExceptSelf(new int[]{2})));

        // System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));

        // System.out.println(Arrays.toString(twoSum(new int[]{-1,0}, -1)));

        System.out.println(threeSum(new int[]{-2,0,1,1,2}).toString());
        // System.out.println(sortThreeNums(new ArrayList<>(Arrays.asList())));
        // Set<List<Integer>> hs = new HashSet<>();
        // List<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3));
        // List<Integer> l2 = new ArrayList<>(Arrays.asList(2,1,3));
        // hs.add(l1);
        // hs.add(l2);
        // System.out.println(hs.toString());
    }
}
