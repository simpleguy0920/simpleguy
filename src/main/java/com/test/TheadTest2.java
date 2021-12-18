package com.test;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TheadTest2 {
    public static void main(String[] args) {
        System.out.println((char) 95);


        TheadTest2 theadTest2 = new TheadTest2();
        System.out.println(theadTest2.canConstruct("ab", "az"));
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] ransomNoteArray = new int[52];
        int[] magazineArray = new int[52];
        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'A';
            ransomNoteArray[index] = ransomNoteArray[index] + 1;
        }
        for (int i = 0; i < magazine.length(); i++) {
            int index = magazine.charAt(i) - 'A';
            magazineArray[index] = magazineArray[index] + 1;
        }
        for (int i = 0; i < 52; i++) {
            if (magazineArray[i] < ransomNoteArray[i]) {
                return false;
            }
        }
        return true;
    }
}
