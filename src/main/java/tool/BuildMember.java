package tool;

import org.apache.commons.lang3.StringUtils;

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
public class BuildMember {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (long i = 1280000000l; i < 1280000000l + 128; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(StringUtils.join(list, ","));
    }
}
