package str;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/5/6 11:53 下午
 */
public class FirstSingleCharInString {

    /**
     * 问题：找出字符串中第一次只出现一次的字符，如何优化算法使遍历次数更少
     * 分析：要找出字符串中第一次只出现一次的字符，那么这个字符至少要和其他所有的字符比较一次。
     * 实现：用一个和字符串长度相等的int数组(初始值默认为0)来记录字符串中对应的字符是否重复，并用两个指针来遍历字符串。
     * 每当检测到字符串中i，j两个下标对应的字符相同时，就将int数组中i和j的值变成1.
     * 因为只需要找出“第一个”只出现一次的字符，所以每当j到达String的末尾时，立即判断int数组中i是否为0，
     * 如果为零，说明String数组中i位置的字符是第一个唯一出现的字符，则停止查找；
     * 否则i向右移，这时为了减少重复劳动，需要判断int数组中i位置的值是否为1，
     * 如果为1，则表示String中i位置的字符已经被检测出是重复的，而且所有与该字符相同的字符都已在int数组中被标记为1了。
     *
     * 最大时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int getFirstSingleChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        int[] bool = new int[str.length()];
        for (int i=0; i < str.length(); i++) {
            if (bool[i] != 0)
                continue;
            for (int j = i + 1; j < str.length(); j++) {
                if (bool[j] != 0)
                    continue;
                if (str.charAt(i) == str.charAt(j)) {
                    bool[i] = 1;
                    bool[j] = 1;
                }
            }
            if (bool[i] == 0) {
                return i;
            }
        }
        return -1;
    }



    /**
     * Create by yujing10 on 2020/10/6.
     * 在一个字符串(0<=字符串长度<=10000，
     * 全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
     * 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
     *
     * https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c?tpId=13&&tqId=11187&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */
    public class TheFirstSingleCharacter {
        public int FirstNotRepeatingChar(String str) {
            if(str == null || str.length() == 0) {
                return -1;
            }
            LinkedHashMap<Character, CharacterInfo> map = new LinkedHashMap<>();
            for(int i=0;i<str.length();i++){
                if(map.containsKey(str.charAt(i))){
                    map.get(str.charAt(i)).count += 1;
                } else {
                    map.put(str.charAt(i), new CharacterInfo(i));
                }
            }
            for(Map.Entry<Character, CharacterInfo> entry : map.entrySet()) {
                if(entry.getValue().count == 1) {
                    return entry.getValue().index;
                }
            }
            return -1;
        }
    }

    class CharacterInfo {
        int index;
        int count;

        public CharacterInfo(int index){
            this.index = index;
            this.count = 1;
        }
    }
}
