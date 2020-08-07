package test.java;

import com.fasterxml.jackson.core.TreeNode;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class test extends Thread {

    public static void main(String[] args) {
        JFrame jf = new JFrame("饿汉单例模式测试");
        jf.setLayout(new GridLayout(1, 2));
        Container contentPane = jf.getContentPane();
        Bajie obj1 = Bajie.getInstance();
        contentPane.add(obj1);
        Bajie obj2 = Bajie.getInstance();
        contentPane.add(obj2);
        if (obj1 == obj2) {
            System.out.println("他们是同一人！");
        } else {
            System.out.println("他们不是同一人！");
        }
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static int minimumTotal(List<List<Integer>> triangle) {
        return 0;
    }

    public static int jc(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * jc(n - 1);
        }
    }

    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        double res = x;
        for (int i = 1; i < n; i++) {
            res *= x;
        }
        return n > 0 ? res : 1 / res;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) return strs[0];
        if (strs.length == 0) return "";
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < strs.length; i++) {
            for (int j = 1; j <= strs[i].length(); j++) {
                int x = 0;
                if (map.containsKey(strs[i].substring(0, j))) {
                    max = map.get(strs[i].substring(0, j));
                } else {
                    if (i == 0) map.put(strs[i].substring(0, j), j);
                    if (j == 1) max = 0;
                }
            }
        }
        return max == 0 ? "" : strs[0].substring(0, max);
    }

    public static int maxProfit1(int[] prices) {
        if (prices.length <= 1) return 0;
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int x = nums[0];
        return 0;
    }

    //67. 二进制求和
    public static String addBinary(String a, String b) {
        StringBuffer stringBuffer = new StringBuffer();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            stringBuffer.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            stringBuffer.append('1');
        }
        return stringBuffer.reverse().toString();
    }

    public static int trailingZeroes(int n) {
        System.out.println(n);
        if (n < 5) {
            return 0;
        } else {
            return n / 5 + trailingZeroes(n / 5);
        }
    }

    public static void fileList() throws IOException, InterruptedException {
//        File file = new File("/Users/wangye/Desktop/111.txt");
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.wait();
        FileWriter fileWriter = new FileWriter("/Users/wangye/Desktop/111.txt");
        FileReader fileReader = new FileReader("/Users/wangye/Desktop/111.txt");
        fileReader.read();
        fileWriter.write("sss");
        fileWriter.close();
    }

    public static void sortColors(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                temp = nums[i + 1];
                nums[i + 1] = nums[i];
                nums[i] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static int majorityElements(int[] nums) {
        int x = nums.length / 2;
        Arrays.sort(nums);
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                cnt++;
                if (cnt > x) {
                    return nums[i];
                }
            } else {
                cnt = 1;
            }
        }
        return nums[0];
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder stringBuilder = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            stringBuilder.append("-");
        }
        long x = Math.abs(Long.valueOf(numerator));
        long y = Math.abs(Long.valueOf(denominator));
        stringBuilder.append(x / y);//
        long remainder = x % y;
        if (remainder == 0) return stringBuilder.toString();
        stringBuilder.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                stringBuilder.insert(map.get(remainder), "(");
                stringBuilder.append(")");
                break;
            }
            map.put(remainder, stringBuilder.length());
            remainder *= 10;
            stringBuilder.append(remainder / y);
            remainder %= y;
        }
        return stringBuilder.toString();
    }

    @Override
    public void run() {

    }

//    public String convertTotitle(int n) {
//
//    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
//        return p == null && q == null ? true : p == null || q == null ? fa
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 1;
        for (int i = 0; i < gas.length; i++) {

        }
        getGas(gas, cost, 0, 0, 0);
        return index;
    }

    public static void getGas(int[] gas, int[] cost, int carGas, int index, int total) {
        if (index == gas.length) index = 0;
        if (total == gas.length) return;
        if (carGas + gas[index] >= cost[index]) {
            getGas(gas, cost, carGas + gas[index] - cost[index], index + 1, total + 1);
            index++;
            total++;
        } else {
            getGas(gas, cost, carGas, index + 1, total);
            index++;
        }

    }

//    public List<String> restoreIpAddress(String s) {
//        //1.85.1.73 122.115.53.97
//        s = "1221155397";
//
//    }

    public static int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                res = prices[j] - prices[i] > res ? prices[j] - prices[i] : res;
            }
        }
        return res > 0 ? res : 0;
    }


    public static void reverseString(char[] s) {
        reverse(0, s);
        System.out.println(s);
    }

    public static void reverse(int index, char[] s) {
        if (s == null || index > s.length / 2 - 1) {
            return;
        }
        reverse(index + 1, s);
        char a = s[index];
        System.out.println(index);
        s[index] = s[s.length - index - 1];
        s[s.length - index - 1] = a;
    }

    public static int climbStairs(int n) {
        int[] nums = new int[n + 1];
        nums[1] = 1;
        nums[2] = 2;
        if (n <= 2) {
            return n;
        }
        for (int i = 3; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[n];

    }

    public static int[] plusOne(int[] digits) {
        int[] n = new int[digits.length + 1];
        int[] res = new int[digits.length];
        int x = digits.length - 1;
        int flag = 0;
        for (int i = x; i >= 0; i--) {
            if (i == x) {
                n[i + 1] = (digits[i] + 1) % 10;
                n[i] = (digits[i] + 1) / 10;
            } else {
                flag = n[i + 1];
                n[i + 1] = (digits[i] + flag) % 10;
                n[i] = (digits[i] + flag) / 10;
            }
        }
        if (n[0] == 0) {
            for (int i = 1; i < n.length; i++) {
                res[i - 1] = n[i];
            }
            return res;
        } else {
            return n;
        }
    }

    public static int lengthOfLastWord(String s) {
        int endIndex = s.length() - 1;
        while (s.charAt(endIndex) == ' ') {
            endIndex--;
        }
        if (endIndex < 0) return 0;
        int start = endIndex;
        while (start >= 0 && s.charAt(start) != ' ') start--;
        return endIndex - start;

    }

    public static int removeElement(int[] nums, int val) {
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[x] = nums[i];
                x++;
            }
        }
        return x;
    }

    public static List<List<Integer>> generate(int numRows) {
        //为0，直接返回空数组。
        if (numRows <= 0) {
            return new ArrayList();
        }

        List<List<Integer>> result = null;

        //递归退出条件，第一行，只有一个数字1
        if (numRows == 1) {
            result = new ArrayList<>();
            result.add(new ArrayList());
            result.get(0).add(1);
            return result;
        }

        //递归计算，每新增一行都是在上一行的结果上新增。
        result = generate(numRows - 1);

        // ------以下为新增一行的处理逻辑-------

        //拿到列表的最后一行数据，用来计算新增加的一行。
        List<Integer> lastRow = result.get(numRows - 2);

        List<Integer> newRow = new ArrayList();
        //开始为1
        newRow.add(1);

        //中间的数据通过上一行的数据得来。
        for (int i = 1; i < numRows - 1; i++) {
            newRow.add(lastRow.get(i - 1) + lastRow.get(i));
        }
        //结束为1
        newRow.add(1);

        //加入新行数据
        result.add(newRow);

        return result;
    }


    public static int mySqrt(int x) {
        if (x < 2) return x;

        long num;
        int left = 2, right = x / 2;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            num = (long) mid * mid;
            if (num > x) {
                right = mid - 1;
            } else if (num < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }

    public static int strStr(String haystack, String needle) {
        int h = haystack.length(), n = needle.length();
//        if (h == 0) return 0;
        int ph = 0;
        while (ph < h - n + 1) {
            while (ph < h - n + 1 && haystack.charAt(ph) != needle.charAt(0)) {
                ph++;
            }

            int curLen = 0, pn = 0;
            while (pn < n && ph < h && haystack.charAt(ph) == needle.charAt(pn)) {
                pn++;
                ph++;
                curLen++;
            }

            if (curLen == n) return ph - n;

            ph = ph - curLen + 1;
        }
        return -1;
    }

    public static int strStr3(String haystack, String needle) {
        int n = needle.length();
        for (int i = 0; i < haystack.length() - n + 1; i++) {
            if (haystack.substring(i, i + n).equals(needle)) {
                return i;
            }
        }
        return -1;

    }

    public static int strStr2(String haystack, String needle) {
        int res = 0;
        int hleft = 0;
        int nleft = 0;
        if (needle.length() == 0) {
            return res;
        }
        getStrIndex(haystack, needle, hleft, nleft, 0);
        return hleft;
    }

    public static void getStrIndex(String h, String n, int hleft, int nleft, int alreday) {
        int res = hleft;
        if (alreday == n.length()) {
            return;
        }

        if (String.valueOf(h.charAt(hleft)).equals(String.valueOf(n.charAt(nleft)))) {
            getStrIndex(h, n, hleft + 1, nleft + 1, alreday + 1);
            hleft++;
        } else {
            getStrIndex(h, n, hleft + 1, 0, 0);
        }

    }

    //518965427967
    public static void main18(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] == target) {
                List<Integer> integers = new ArrayList<>();
                integers.add(candidates[i]);
                lists.add(integers);
                continue;
            }
            if (candidates[i] > target) {
                continue;
            }
            int sum = 0;
            List<Integer> l1 = new ArrayList<>();
            while (sum < target) {
                l1.add(candidates[i]);
                if (sum == target) {
                    lists.add(l1);
                }
                sum = sum + candidates[i];
            }
        }
    }

    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;

        // 排序是为了提前终止搜索
        Arrays.sort(candidates);

        dfs(candidates, len, target, 0, new ArrayDeque<Integer>(), res);
        return res;
    }

    /**
     * @param candidates 数组输入
     * @param len        输入数组的长度，冗余变量
     * @param residue    剩余数值
     * @param begin      本轮搜索的起点下标
     * @param path       从根结点到任意结点的路径
     * @param res        结果集变量
     */
    private void dfs(int[] candidates,
                     int len,
                     int residue,
                     int begin,
                     Deque<Integer> path,
                     List<List<Integer>> res) {
        if (residue == 0) {
            // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {

            // 在数组有序的前提下，剪枝
            if (residue - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs(candidates, len, residue - candidates[i], i, path, res);
            path.removeLast();

        }
    }

    public void process(int start, int[] candidates, int target, List<Integer> list) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<Integer>(list));
        } else {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                process(start, candidates, target - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }
//        Deque<Integer> deque
    }

    public static void main17(String[] args) {
        System.out.println(multiply("881095803", "61"));
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    public int lengthOfLongestSubstring(String s) {
        int[] m = new int[256];
        Arrays.fill(m, -1);
        int res = 0, left = -1;
        for (int i = 0; i < s.length(); ++i) {
            left = Math.max(left, m[s.charAt(i)]);
            m[s.charAt(i)] = i;
            res = Math.max(res, i - left);
        }
        return res;
    }

    //2147483647
    //4800000000
    public static void main14(String[] args) {
        String a = "123";
//        Long.parseLong()
        System.out.println(a.charAt(0) - '1');
    }

    public static void main16(String[] args) {
        String num1 = "881095803";
        String num2 = "61";


        String result = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int m2 = 0;
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                stringBuilder.append(0);
            }
            int n2 = num2.charAt(i) - '0';
            for (int j = num1.length() - 1; j >= 0 || m2 != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int res = (n1 * n2 + m2) % 10;
                stringBuilder.append(res);
                m2 = (n1 * n2 + m2) / 10;
            }
            System.out.println(stringBuilder.reverse());
            result = addTwoStrings(result, stringBuilder.reverse().toString());
            System.out.println(result);
        }
        System.out.println(result);
    }


    //1099 + 999   999
    //         + 10000
    public static String addTwoStrings(String s1, String s2) {
        StringBuilder stringBuilder = new StringBuilder();
        int carry = 0;
        for (int i = s1.length() - 1, j = s2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int x = i < 0 ? 0 : s1.charAt(i) - '0';
            int y = j < 0 ? 0 : s2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            stringBuilder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return stringBuilder.reverse().toString();
    }

    public static void main13(String[] args) {
        int a = 5;
        System.out.println(a << 4);
    }

    public static void main12(String[] args) {
        int divedend = -2147483648;
        int divisor = -1;

        long d1 = divedend;
        long d2 = divisor;
        long divedend2 = Math.abs(d1);


        long divisor2 = Math.abs(d2);
        System.out.println(d1 + "----");
        System.out.println(d2);

        String flag = "";
        long res = 0;
        if (divedend == 0) {
            System.out.println(0);
            return;
        }
        if (divedend > 0) {
            if (divisor > 0) {
                flag = "";
            } else {
                flag = "-";
            }
        } else {
            if (divisor > 0) {
                flag = "-";
            } else {
                flag = "";
            }
        }
        while (divisor2 <= divedend2) {
//            System.out.println(res+"-----");
            divisor2 = divisor2 + Math.abs(divisor);
            res += 1;
//            System.out.println(res);
        }
        System.out.println(res);
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            flag = "";
            res = Integer.MAX_VALUE;
        }
        System.out.println(Integer.parseInt(flag + res));

    }

    public static void main11(String[] args) {
        int n = 3;
        String num = "1";
        for (int i = 0; i < n - 1; i++) {
            num = describle(num.toCharArray());
        }
        System.out.println(num);
    }

    public static void main10(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        int res = searchInsert(nums, target);
        int a = Integer.MAX_VALUE + Integer.MAX_VALUE;
        System.out.println(a);
        System.out.println(res);
    }

    public String countAndSay(int n) {
        String num = "1";
        for (int i = 0; i < n - 1; i++) {
            num = describle(num.toCharArray());
        }
        return num;
    }


    public static String describle(char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        char ch = chars[0];
        int temp = 1, i = 1;
        while (i < chars.length) {
            if (chars[i] == ch) {
                temp++;
            } else {
                stringBuilder.append(temp).append(chars[i - 1]);
                ch = chars[i];
                temp = 1;
            }
            i++;
        }
        stringBuilder.append(temp).append(chars[i - 1]);
        return stringBuilder.toString();
    }

    public static int searchInsert(int[] nums, int target) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
//                res = i;
                return i;
            } else {
                if (i == nums.length - 1) {
                    res = i + 1;
                }
            }
        }
        return res;
    }

    public static void main9(String[] args) {
        int n = 3;
        List<String> list = new ArrayList<>();
        int l = n, r = n;
        generate(list, n, l, r, "");
        System.out.println(list.toString());
    }

    public static void generate(List<String> list, int n, int l, int r, String str) {
        System.out.println("list========" + list.toString());
        System.out.println("n========" + n);
        System.out.println("l========" + l);
        System.out.println("r========" + r);
        System.out.println("str========" + str);
        if (str.length() == 2 * n) {
            list.add(str);
            return;
        }
        if (r > l) {
            if (l > 0) {
                System.out.println(111);
                generate(list, n, l - 1, r, str + "(");
            }
            if (r > 0) {
                System.out.println(222);
                generate(list, n, l, r - 1, str + ")");
            }
        } else {
            if (l > 0) {
                System.out.println(333);
                generate(list, n, l - 1, r, str + "(");
            }
        }
    }

    public static void main8(String[] args) {
        String digits = "233";
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {

        }
        Map<Integer, char[]> map = new HashMap<>();
        map.put(2, new char[]{'a', 'b', 'c'});
        map.put(3, new char[]{'d', 'e', 'f'});

        backtrack(result, map, digits, new char[digits.length()], 0);
        System.out.println(result.toString());


    }

    private static void backtrack(List<String> result, Map<Integer, char[]> map, String digits, char[] temp, int fir) {
        if (fir == digits.length()) {
            result.add(new String(temp));
            return;
        }
        char[] val = map.get(Integer.valueOf(digits.charAt(fir) - 48));
        for (int i = 0; i < val.length; i++) {
            temp[fir] = val[i];
            backtrack(result, map, digits, temp, fir + 1);

        }
    }

    public static void main7(String[] args) {
        int x = -121;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(x)).reverse();
        if (stringBuffer.toString().equals(String.valueOf(x))) ;
    }

    public static void main6(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        int res = 0;
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
//                area = (height[i] <= height[j] ? height[i] : height[j]) * (j - i);
//                res = area >= res ? area : res;

                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }

        System.out.println(res);
    }

    public static void main5(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        int min = 0;
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                min = strs[i].length();
            }
            if (min > strs[i].length()) {
                min = strs[i].length();
            }
        }
        for (int i = min; i > 1; i--) {

        }
    }

    public static void main4(String[] args) {
        String s = "IV";
        Map<Character, Integer> romaNumber = new HashMap<>();
        romaNumber.put('I', 1);
        romaNumber.put('V', 5);
        romaNumber.put('X', 10);
        romaNumber.put('L', 50);
        romaNumber.put('C', 100);
        romaNumber.put('D', 500);
        romaNumber.put('M', 1000);

        int first = 0;
        int next = 0;
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            first = romaNumber.get(s.charAt(i));
            if (i == s.length() - 1) {
                sum += first;
            } else {
                next = romaNumber.get(s.charAt(i + 1));
                if (first > next) {
                    sum += first;
                } else {
                    sum -= first;
                }
            }
        }

//        return sum;

        System.out.println(sum);
    }

    public static void main3(String[] args) {
        int x = 121;
        int res = 0;
        if (x < 0) {
            System.out.println(false);
        } else {
            while (x != 0) {
                res = res * 10 + x % 10;
                x = x / 10;
            }
            System.out.println(res);
            if (x == res) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        }
    }

    public static void main2(String[] args) {
        int x = -2147483412;
        int flag = x > 0 ? 1 : -1;
        int res = 0;
        x = x > 0 ? x : -1;
        if (x != (x * 10) / 10) {
            System.out.println(0);
        } else {
            while (x != 0) {
                System.out.println(res);
                res = res * 10 + x % 10;
                x = x / 10;
            }
            System.out.println(res * flag);
        }


    }

    public static void main1(String[] args) {
        int[] m = new int[256];
        Arrays.fill(m, -1);
        String s = "1123";
        int res = 0, left = -1;
        for (int i = 0; i < s.length(); ++i) {
            System.out.println("i=" + i);
            System.out.println("left=" + left);
            System.out.println(" m[s.charAt(i)]=" + m[s.charAt(i)]);
            System.out.println(" s.charAt(i)=" + s.charAt(i));

            left = Math.max(left, m[s.charAt(i)]);
            m[s.charAt(i)] = i;
            res = Math.max(res, i - left);
            System.out.println("res=" + res);
            System.out.println(Arrays.toString(m));
            System.out.println("================");
        }
    }
}
