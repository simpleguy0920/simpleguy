package tool;

import com.java8.bean.BeanA;
import com.java8.bean.BeanB;
import org.apache.commons.collections.ListUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class CopyGetSetter {
    public static void main(String[] args) throws IntrospectionException {
        CopyGetSetter copyGetSetter = new CopyGetSetter();
        copyGetSetter.copy(BeanA.class, BeanB.class, "aaa", "bbb");
    }

    public void copy(Class src, Class dest, String srcBeanName, String destBeanName) throws IntrospectionException {
        Map<String, PropertyDescriptor> map = new HashMap<>();
        PropertyDescriptor[] srcPropertyDescriptors = Introspector.getBeanInfo(src).getPropertyDescriptors();
        for (PropertyDescriptor srcPropertyDescriptor : srcPropertyDescriptors) {
            map.put(srcPropertyDescriptor.getName(), srcPropertyDescriptor);
        }
        PropertyDescriptor[] destPropertyDescriptors = Introspector.getBeanInfo(dest).getPropertyDescriptors();
        List<String> srcList = Arrays.stream(srcPropertyDescriptors).map(PropertyDescriptor::getName).collect(Collectors.toList());
        List<String> destList = Arrays.stream(destPropertyDescriptors).map(PropertyDescriptor::getName).collect(Collectors.toList());
        List<String> retainList = ListUtils.retainAll(srcList, destList);
        retainList.remove("class");
        for (String s : retainList) {
            PropertyDescriptor propertyDescriptor = map.get(s);
            System.out.println(destBeanName + "." + propertyDescriptor.getWriteMethod().getName() + "(" + srcBeanName + "." + propertyDescriptor.getReadMethod().getName() + "())");
        }
    }
}
