package backTrace;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/7 5:28 下午
 */
public class TelephoneDigitCombine {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 2 -> abc
     * 3 -> def
     * 4 -> ghi
     * 5 -> jkl
     * 6 -> mno
     * 7 -> pqrs
     * 8 -> tuv
     * 9 -> wxyz
     * <p>
     * 示例:
     * <p>
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * <p>
     * 思路：
     */
    public static List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        list.add("");
        generate(0, digits, map, list);
        return list;
    }

    public static void generate(int i, String digits, Map<Character, List<Character>> map, List<String> list) {
        if (i >= digits.length()) {
            return;
        }
        List<Character> characters = map.get(digits.charAt(i));
        int size = list.size();
        while (size > 0) {
            String str = list.remove(0);
            for (Character ch : characters) {
                list.add(str + ch);
            }
            size--;
        }
        generate(i + 1, digits, map, list);
    }

}
