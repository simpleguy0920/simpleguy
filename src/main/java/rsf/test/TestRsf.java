package rsf.test;

import org.apache.commons.lang3.StringUtils;

public class TestRsf {
    public static void main(String[] args) {

        String str = StringUtils.leftPad("761208486", 18, '0');
        System.out.println(str);
//        ApiRemoteMapService apiRemoteMapService = ServiceLocator.getService(ApiRemoteMapService.class, "SYSC-ShoppingCartAdd");

    }
}
