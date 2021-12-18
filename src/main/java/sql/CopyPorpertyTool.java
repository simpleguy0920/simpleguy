package sql;

import com.test.clone.User;
import org.apache.commons.beanutils.BeanMap;

import java.lang.reflect.Field;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class CopyPorpertyTool {

    public static void copy(Object readObject, Object writerObject, String read, String write) {
        BeanMap map1 = new BeanMap(readObject);
        BeanMap map2 = new BeanMap(writerObject);
        for (Field field : writerObject.getClass().getDeclaredFields()) {
            String name = field.getName();
            if (!map1.containsKey(name)) {
                continue;
            }
            if (!map2.containsKey(name)) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append(write).append(".").append(map2.getWriteMethod(name).getName()).append("(").append(read).append(".").append(map1.getReadMethod(name).getName()).append("());");
            System.out.println(builder);
        }

    }

    public static void main(String[] args) {
        copy(new User(), new User(), "a", "b");
    }
}
