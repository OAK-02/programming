import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Question {

    static int[] twoSum2(int[] numbers, int target) {
        boolean found = false;
        int start = 0;
        int end = numbers.length-1;
        int temp_sum = numbers[start] + numbers[end];
        while(!found) {
            if (temp_sum > target) {
                end--;
            }else if (temp_sum < target) {
                start++;
            } else {
                found = true;
            }
            temp_sum = numbers[start] + numbers[end];
        }
        return new int[]{start+1, end+1};
    }

    static void find2Sumfor3Sum(int start, int target, int[] nums, List<List<Integer>> returning) {
        int end = nums.length-1;
        int temp_sum = nums[start] + nums[end];
        while(start < end) {
            if (temp_sum > target) {
                end--;
            } else if (temp_sum < target) {
                start++;
            } else {
                returning.add(new ArrayList<>(Arrays.asList(target*-1, nums[start], nums[end])));
                start++;
                while (start < end && nums[start-1] == nums[start]) 
                    start++;
            }
            temp_sum = nums[start] + nums[end];
        }
    }

    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> returning = new ArrayList<>();
        for (int i = 0 ; i < nums.length-2; i++) {
            while (i > 0 && i < nums.length-2 && nums[i-1] == nums[i]) i++;
            if (i >= nums.length-2) return returning;
            int target = nums[i] *-1;
            find2Sumfor3Sum(i+1, target, nums, returning);
        }
        return returning;
    }

    static int maxArea(int[] heights) {
        // left = 0, right = length-1, maxAr = min(h[left], h[right]) * right - left
        // loop till left < rifht
            // calculatee area and update max are
            // if left > right: right--
            // if left < right: left++
            // else: right--
        if (heights.length == 0) return 0;       
        int left = 0; int right = heights.length - 1;
        int maxArea = 0;
        while(left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            if (area > maxArea) maxArea = area;
            if (heights[left] > heights[right]) right--;
            else if (heights[left] < heights[right]) left++;
            else right--;
        }
        return maxArea;
    }

    static int trap(int[] height) {
        /**
         * 
        // left: leftmost non-zero index
        // right: rightmost non-zereo index
        // max_elevated: min(height[left], height[right]) 
        // water_units = max_elevated * (right-left-1)
        // while right - left > 0:
            // if left > right: update right and update the water units
                    water_units -= min(heights[right], max_elevated)
            // else if left < right: update left and update the water units
            // else: update left and update the water units            
            // if (max_elevated < min(height[left], height[right])):
                // update water units
                temp = max_elevated
                max_elevated =  min(height[left], height[right])
                units_to_add_height = max_elevated - temp
                units_to_add_breadth = left - right - 1
                water_units += (units_to_add_height * units_to_add_breadth)
        */
        // if (height.length == 1 || height.length == 2) return 0;
        // int left = 0;
        // int right = height.length - 1;
        // while(left < right && height[left] == 0) left++;
        // while(left < right && height[right] == 0) right--;
        // int max_elevated = Math.min(height[left], height[right]);
        // int water_units = max_elevated * (right - left - 1);
        // while (left < right) {
        //     if (height[left] > height[right]){
        //         right--;
        //         if (right == left) break;
        //         water_units -= Math.min(height[right], max_elevated);
        //     } else {
        //         // here height[left] <= height[right]
        //         left++;
        //         if (right == left) break;
        //         water_units -= Math.min(height[left], max_elevated);
        //     }
        //     if (max_elevated < Math.min(height[left], height[right])) {
        //         System.out.println("(height[left], height[right]) - " + height[left] + " " + height[right]);
        //         int old_max_elevated = max_elevated;
        //         max_elevated = Math.min(height[left], height[right]);
        //         int units_to_add_height = max_elevated - old_max_elevated;
        //         int units_to_add_breadth = right - left - 1;
        //         water_units += (units_to_add_height * units_to_add_breadth);
        //     }
        // }
        // return water_units;


        // more efficitent
        /*
         * leftPtr = 0, leftMax = 0, rightPtr= len-1, rightMax = len-1
         * currentWater = 0
         * whiel leftPtr < rightPtr:
         * if leftMax < rightMax: 
         *  update leftptr, leftmax and the current water
         * else: 
         *  update rightPtr, rightmax and the current water
         */
        int left = 0;
        int leftMax = height[left];
        int right = height.length-1;
        int rightMax = height[right];
        int currentWater = 0;
        while(left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                currentWater += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                currentWater += rightMax - height[right];
            }
        }
        return currentWater;
    }

    static int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int buy = 0;
        int sell = 1;
        int profit = 0;
        while (sell < prices.length) {
            if(prices[sell] < prices[buy])
                buy = sell;
            else
                profit = Math.max(profit, prices[sell] - prices[buy]);
            sell++;
        }
        return profit;
    }

    static int lengthOfLongestSubstring(String s) {
        /*
         * first = 0, last = 1, substr = substr.add(s[0]), maxLen = substr.len()
         * while last < s.len:
         *  while s[last] in substr:
         *      substr.remove(first)
         *      first++
         *  substr.add(s[last])
         *  last++
         *  max_len = mmax(maxLen, substr.len)
         */
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int first = 0, last = 1, maxLen = 1, len = s.length();
        HashSet<Character> substr = new HashSet<>();
        substr.add(s.charAt(first));
        while (last < len) {
            while (substr.contains(s.charAt(last))) {
                substr.remove(s.charAt(first));
                first++;
            }
            substr.add(s.charAt(last));
            maxLen = Math.max(maxLen, substr.size());
            last++;
        }
        return maxLen;
    }

    static int characterReplacement(String s, int k) {
        // have an array of 26 characters holding the maximum count of each character in the substring
        /*
         * number of caharacters to replace = last - first + 1 - mostFreqCharacterInSubstr
         * first = 0, last = 0, mostFreqCharacterCount = 0, maxLength = 0;
         * characterCount[26] Alphabetically ordered index
         * for last in (0..len):
         *  characterCount[s[last] - 'A']++;
         *  mostFreqCharacterCount = max(characterCount[last-'A'], mostFreqCharacterCount)
         *  lettersToChange =  (last - first + 1) - mostFreqCharacterCount
         *  if (lettersToChange > k)
         *      characterCount[s[first] - 'A']--;
         *      first++;
         *  maxLength = max(maxLength, last-first+1)
         * return maxLenght 
         */
        int first = 0;
        int[] characterCountInSubstr = new int[26];
        int mostFreqCharacterCount = 0;
        int maxLength = 0;
        int len = s.length();
        for (int last = 0; last < len; last++) {
            characterCountInSubstr[s.charAt(last) - 'A']++;
            mostFreqCharacterCount = Math.max(mostFreqCharacterCount, characterCountInSubstr[s.charAt(last) - 'A']);
            if ((last - first + 1) - mostFreqCharacterCount > k) {
                characterCountInSubstr[s.charAt(first) - 'A']--;
                first++;
            }
            maxLength = Math.max(maxLength, last-first+1);
        }
        return maxLength;
    }

    static boolean checkInclusion(String s1, String s2) {
        // if (s1.length() > s2.length()) return false;
        // int first = 0;
        // int last = s1.length();
        // int len = s2.length();
        // // sort s1
        // char[] sort_s1 = s1.toCharArray();
        // Arrays.sort(sort_s1);
        // String s1Sorted = new String(sort_s1);
        // while (last <= len) {
        //     char[] sortSubstr = s2.substring(first, last).toCharArray();
        //     Arrays.sort(sortSubstr);
        //     String sortedSubstr = new String(sortSubstr);
        //     if (sortedSubstr.equals(s1Sorted)) {
        //         return true;
        //     } else 
        //         first++; last++;
        // }
        // return false;

        // more efficient - using character frequency array
        if (s1.length() > s2.length()) return false;
        int[] s1CharFreq = new int[26];
        int[] s2CharFreq = new int[26];
        for (char c : s1.toCharArray()) s1CharFreq[c-'a']++;
        int first = 0;
        int last = 0;
        int lenS2 = s2.length();
        int lenS1 = s1.length();
        while (last < lenS2) {
            s2CharFreq[s2.charAt(last)-'a']++;
            if (last-first+1 == lenS1) {
                // check all frequencies
                boolean equal = true;
                for (int i = 0; i < 26; i++) {
                    if (s1CharFreq[i] != s2CharFreq[i]) {
                        equal = false;
                        break;
                    } 
                }
                if (equal) return true;
                else {
                    s2CharFreq[s2.charAt(first)-'a']--;
                    first++;
                    last++;
                }
            } else {
                if (last-first+1 < lenS1) last++;
                else {
                    System.out.println("jhere");
                    // here substr so far has a greater len than s1
                    s2CharFreq[s2.charAt(first)-'a']--;
                    first++;
                    last++;
                }
            }
        }
        return false;
    }

    static boolean equalCharFreq(Map<Character, Integer> s, Map<Character, Integer> t) {
        for (Character keyChars: t.keySet())
            if (!s.containsKey(keyChars) || s.get(keyChars) < t.get(keyChars)) return false;
        return true;
    }

    static String minWindow(String s, String t) {
        int tLen = t.length(), sLen = s.length();
        if (tLen > sLen) return ""; 
        Map<Character, Integer> t_hm  = new HashMap<>();
        for (Character c : t.toCharArray()) {
            if (t_hm.containsKey(c)) t_hm.put(c, t_hm.get(c) + 1);
            else t_hm.put(c, 1);
        }
        int first = 0, last = 0, minLen = sLen;
        int minfirst = 0, minLast = 0;
        HashMap<Character, Integer> s_hm = new HashMap<>();
        boolean foundMatch = false;
        while (last < sLen) {
            Character charToAdd = s.charAt(last);
            if (s_hm.containsKey(charToAdd)) s_hm.put(charToAdd, s_hm.get(charToAdd) + 1);
            else s_hm.put(charToAdd, 1);
            // start reducing as much as possible if condition met
            boolean wasEqual = false;
            while (equalCharFreq(s_hm, t_hm)) {
                foundMatch = true;
                wasEqual = true;
                if (minLen >= last-first+1) {
                    minLen = last-first+1;
                    minfirst = first;
                    minLast = last;
                }
                s_hm.put(s.charAt(first), s_hm.get(s.charAt(first))-1);
                first++;
            }
            if (wasEqual) {
                first--;
                s_hm.put(s.charAt(first), 1);
            }
            last++;
        }
        if (!foundMatch) return "";
        return s.substring(minfirst, minLast+1);
    }


    static int maxProfitRev(int[] prices) {
        if (prices.length < 2) return 0;
        int l = 0;
        int r = 1;
        int max = 0;
        while (r < prices.length) {
            int buy = prices[l];
            int sell = prices[r];
            if (buy < sell) max = Math.max(max, sell - buy);
            else if (buy > sell) l = r;
            r++;
        }
        return max;
    }

    static boolean containsDuplicateRev(int[] nums) {
        // Arrays.sort(nums);
        // for (int i =0; i < nums.length-1; i++) if (nums[i] == nums[i+1]) return true;
        // return false;
        
        // hashset (average time is faster since hash collisions are looked after and we are not assuming an O(n^2) time complexity)
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) return true;
            set.add(i);
        }
        return false;
    } 
    
    static boolean isAnagramRev(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) return false;
        int[] freq = new int[26];
        for (int i = 0; i < sLen; i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for (int i : freq) if (i != 0) return false;
        return true;
    }

    static int lengthOfLongestSubstringRev(String s) {
        int stringLength = s.length();
        if (stringLength == 0) return 0;
        if (stringLength < 2) return 1;

        int start = 0;
        int end = 1;
        int maxLength = 1;
        Set<Character> charactersSoFar = new HashSet<Character>();
        charactersSoFar.add(s.charAt(start));
        while (end < stringLength) {
            char characterAtEnd = s.charAt(end);
            // check if current new index is same as 
            if (!charactersSoFar.contains(characterAtEnd)) {
                charactersSoFar.add(characterAtEnd);
            } else {
                while (charactersSoFar.contains(characterAtEnd)) {
                    charactersSoFar.remove(s.charAt(start));
                    start++;
                }
                // update the hash table to contain latest repeating character
                charactersSoFar.add(characterAtEnd);
            }
            // update maxLength and next pointer
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        } 
        return maxLength;
    }
    
    static int characterReplacementRev(String s, int k) {
        int stringLength = s.length();
        if (stringLength == 1) return 1;

        int left = 0; int right = 0;
        int[] characterFreq = new int[26];
        int maxCharacterCount = 0;
        int maxLength = 0;
        for (right = 0; right < stringLength; right++) {
            characterFreq[s.charAt(right) - 'A']++;
            maxCharacterCount = Math.max(maxCharacterCount, characterFreq[s.charAt(right) - 'A']);
            while ((right - left + 1) - maxCharacterCount > k) {
                characterFreq[s.charAt(left) - 'A']--;
                left++;
                maxCharacterCount = 0;
                for (int i: characterFreq) {
                    if (maxCharacterCount < i) maxCharacterCount = i;
                }
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    static boolean sameCharacterCount(int[] s1Freq, int[] s2Freq) {
        for (int i = 0; i < 26; i++) {
            if (s1Freq[i] != s2Freq[i])
                return false;
        }
        return true;
    }

    static boolean checkInclusionRev(String s1, String s2) {
        if (s2.length() < s1.length()) return false;
        if (s2.length() == 0 && s1.length() == 0) return true;
        if (s2.length() == 0 || s1.length() == 0) return false;

        int[] s1Freq = new int[26];
        int[] s2Freq = new int[26];
        int s2Len = s2.length();
        for (char c : s1.toCharArray()) s1Freq[c-'a']++;
        int s2First = 0;
        int s2End = s1.length();
        String s2Window = s2.substring(s2First, s2End);
        for (char c : s2Window.toCharArray()) s2Freq[c - 'a']++;
        if (sameCharacterCount(s1Freq, s2Freq)) return true;
        if (s2End == s2.length()) return false; 

        while (s2End < s2Len) {
            s2Freq[s2.charAt(s2First) - 'a']--;
            s2First++;
            s2Freq[s2.charAt(s2End) - 'a']++;
            boolean foundPermutation = sameCharacterCount(s1Freq, s2Freq);
            if (foundPermutation) return true;
            s2End++;
        }
        return false;
    }

    static boolean sameCharFreqMinWindow(int[] charFreqS, int[] charFreqT) {
        for (int i = 0; i < 128; i++) {
            if (charFreqT[i] != 0 && charFreqT[i] > charFreqS[i]) {
                return false;
            }
        }
        return true;
    }

    // static String minWindowRev(String s, String t) {
    //     int sLen = s.length();
    //     int tLen = t.length();
    //     if (sLen < tLen) return "";
        
    //     // populate charFreq of t
    //     int[] charFreqT = new int[128];
    //     for (char c : t.toCharArray()) {
    //         charFreqT[c]++;
    //     }

    //     int[] charFreqS = new int[128];
    //     int minFirst = 0; int minLast = 0;
    //     int minLength = sLen;
    //     int first = 0; int last = 0;
    //     int unmatchedCount = tLen;
    //     boolean foundMin = false;

    //     while (last < sLen) {
    //         charFreqT[]
    //         charFreqS[s.charAt(last)]++;

    //         // if same charFreq, keep reducing window till character count is not the same
    //         while (sameCharFreqMinWindow(charFreqS, charFreqT)) {
    //             foundMin = true;
    //             if (minLength >= (last - first + 1)) {
    //                 minLength = last-first+1;
    //                 minFirst = first; minLast = last; 
    //             }
    //             charFreqS[s.charAt(first)]--;
    //             first++;
    //         }

    //         last++;
    //     }

    //     if (!foundMin) return "";
    //     return s.substring(minFirst, minLast+1);
    // }

    static int[] maxSlidingWindow(int[] nums, int k) {
        // ArrayList<Integer> ans = new ArrayList<>(); 
    
        // // at the beginning find maximum and second maximum 
        // // when shifting the window:
        // // if element removed is maximum, compare new added element to second maximum
        // // if element removed is not maximum, compare new added element to maximum
        // int maxValueWindow = nums[0];
        // int secondMaxValueWindow = Integer.MIN_VALUE;
        // Map<Integer, Integer> numberOccurance = new HashMap<>();
        // int start = 0; int end = 0;       
        
        // for (; end < k; end++) {
        //     numberOccurance.put(nums[end], numberOccurance.getOrDefault(nums[end], 0) + 1);
        //     if (nums[end] > maxValueWindow) {
        //         // secondMaxValueWindow = maxValueWindow;
        //         maxValueWindow = nums[end];
        //     }
        // }
        // ans.add(maxValueWindow);

        // for (; end < nums.length; end++) {
        //     int addingElement = nums[end];
        //     int removingElement = nums[start];
        //     numberOccurance.put(removingElement, numberOccurance.get(removingElement)-1);
        //     numberOccurance.put(addingElement, numberOccurance.getOrDefault(addingElement, 0) + 1);
        //     start++;
            
        //     // the max of the previous window remains
        //     if (removingElement != maxValueWindow) {
        //         if (maxValueWindow < addingElement) {
        //             maxValueWindow = addingElement;
        //         }
        //     // the element removed is the max of the window
        //     } else {
        //         // there are duplicates of the max of the previous window so we can compare 
        //         if (numberOccurance.get(maxValueWindow) != 0) {
        //             if (maxValueWindow < addingElement) {
        //                 maxValueWindow = addingElement;
        //             }
        //         // the max of the previous wind
        //         } else {
        //             maxValueWindow = Integer.MIN_VALUE;
        //             for (int i = start; i <= end; i++) {
        //                 if (nums[i] > maxValueWindow) maxValueWindow = nums[i];
        //             }
        //         }
        //     }

        //     ans.add(maxValueWindow);
        // }

        // return ans.stream().mapToInt(Integer::intValue).toArray();


        // Use a Deque to have indices of monotnously decreasing deque
        Deque<Integer> deq = new ArrayDeque<>();
        int start = 0; int end = 0;
        ArrayList<Integer> ans = new ArrayList<>(); 
        for (; end < k; end++) {
            while (deq.peek() != null && nums[deq.peekLast()] < nums[end]) deq.removeLast();
            deq.addLast(end);
        }
        ans.add(nums[deq.getFirst()]);
        while (end < nums.length) {
            if (start == deq.peek()) deq.removeFirst();
            start++;
            while (deq.peek() != null && nums[deq.peekLast()] < nums[end]) deq.removeLast();
            deq.addLast(end);
            ans.add(nums[deq.getFirst()]);
            end++;
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    static boolean isValid(String s) {
        Stack<Character> bracketsStack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case ')':
                    if (bracketsStack.empty() || bracketsStack.peek() != '(') return false;
                    bracketsStack.pop();
                    break;
                case ']':
                    if (bracketsStack.empty() || bracketsStack.peek() != '[') return false;
                    bracketsStack.pop();
                    break;
                case '}':
                    if (bracketsStack.empty() || bracketsStack.peek() != '{') return false;
                    bracketsStack.pop();
                    break;
                default:
                    bracketsStack.push(c);
                    break;
            }
        }
        if (!bracketsStack.empty()) return false;
        return true;
    }  


    public static void main(String[] args) {
        System.out.println(isValid(new String("([)]")));
    }

}