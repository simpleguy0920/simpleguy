package com.test.al;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class CombinationIterator {
    List<String> list = new ArrayList<>();
    int current = 0;

    public CombinationIterator(String characters, int combinationLength) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean[] visited = new boolean[characters.length()];
        backTrace(stringBuilder, combinationLength, visited, characters);
    }

    public void backTrace(StringBuilder stringBuilder, int combinationLength, boolean[] visited, String characters) {
        if (stringBuilder.length() == combinationLength) {
            list.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < characters.length(); i++) {
            char ch = characters.charAt(i);
            if (!visited[i]) {
                if (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) > ch) {
                    continue;
                }
                stringBuilder.append(characters.charAt(i));
                visited[i] = true;
                backTrace(stringBuilder, combinationLength, visited, characters);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                visited[i] = false;
            }
        }
    }

    public String next() {
        if (list.size() > 0) {
            current++;
            return list.get(current - 1);
        } else {
            return null;
        }

    }

    public boolean hasNext() {
        return current != list.size() - 1;
    }
}
