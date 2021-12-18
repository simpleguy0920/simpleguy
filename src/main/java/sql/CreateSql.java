package sql;

import org.junit.Test;

public class CreateSql {

    @Test
    public void test() {
        for (int i = 0; i < 128; i++) {
            String sql = "ALTER TABLE po_order_id_0" + String.format("%03d", i) + " add column OLD_AMOUNT bigint NOT NULL  default '0' COMMENT '交易前金额(分)' after REVERSE_PAY_ID;";
            sql = "ALTER TABLE SY_IMS_LOCK_SERIAL_" + String.format("%03d", i) +
                    "  CHANGE `SUPPLIER_CODE` `SUPPLIER_CODE` VARCHAR(20)  NULL   COMMENT '供应商编码',\n" +
                    "  CHANGE `WAREHOUSE_CODE` `WAREHOUSE_CODE` VARCHAR(20)   NULL   COMMENT '仓库编码';\n";
            System.out.println(sql);


        }
    }

    @Test
    public void test1() {
        for (int i = 0; i < 128; i++) {
            String sql = "ALTER TABLE po_cf_order_ " + String.format("%03d", i) + " ADD COLUMN DELIVERY_TYPE VARCHAR (16) NULL COMMENT '配送方式' AFTER CMMDTY_MARK;";
            System.out.println(sql);
        }
    }

    @Test
    public void test8() {
        for (int i = 0; i < 50; i++) {
            String sql = "ALTER TABLE pr_outer_platform_refund_" + String.format("%02d", i) + "   CHANGE `PLATFORM_UPDATE_TIME` `PLATFORM_UPDATE_TIME` DATETIME NULL   COMMENT '更新时间(平台)';";
            System.out.println(sql);
        }
    }

    @Test
    public void test3() {
        for (int i = 0; i < 128; i++) {
            String sql = "alter table sy_settlecart_" + String.format("%03d", i) + "  drop column INSTALLMENT_NUM,  drop column ACTIVITY_NUM;";

            System.out.println(sql);
//            sql="alter table SY_GIFT_CARD_PAY_DETAIL_"+String.format("%03d",i)+" add column NEW_AMOUNT bigint NOT NULL  default '0' COMMENT '交易后金额(分)' after OLD_AMOUNT;";
//            System.out.println(sql);

        }
//        for(int i=0;i<50;i++) {
//
//            String sql = "alter table so_platform_ord_" + String.format("%02d", i) + "   add column ERROR_CODE varchar (64) null comment '异常码' after OUT_MEM_CARD_NO,\n" +
//                    "  add column ERROR_MSG varchar (512) null comment '异常信息' after ERROR_CODE,\n" +
//                    "  add column RETRY_COUNT int null comment '重试次数' after ERROR_MSG;";
//            System.out.println(sql);
//        }
    }
}
