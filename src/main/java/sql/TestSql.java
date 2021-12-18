package sql;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TestSql {
    public static void main(String[] args) {
        TestSql testSql = new TestSql();

        testSql.test();
    }

    public void test() {
        List<String> list = new ArrayList<>();

        for (int i = 25; i < 50; i++) {
            String sql = "SELECT p.SALE_PLATFORM, c.* from po_cf_trade_" + String.format("%02d", i) + " p LEFT JOIN po_cf_order_" + String.format("%02d", i) + " c ON c.POTS_ID = p.POTS_ID WHERE c.STORE_CODE IN('383','385','384','358','704','331','334','325','322','989','701') AND p.CREATE_TIME >= '2021-06-01 01:00:00' AND p.CREATE_TIME < '2021-06-01 19:00:00'";
            list.add(sql);
        }
        String str = "SELECT a.SALE_PLATFORM,a.ROWID,a.POTS_ID,a.CF_ORDER_ID,a.SRC_ID,a.OMS_ID,a.OMS_ITEM_ID,a.POTS_ITEM_ID,a.POTS_SUB_ITEM_ID,a.CF_ORDER_ITEM_ID,a.SUB_BIZ_ORDER_ID,a.SRC_ITEM_ID,a.POS_ORDER_ID,a.BOL_NO,a.STORE_CODE,a.CMMDTY_CODE,a.CMMDTY_NAME,a.ORDER_ITEM_TYPE,a.PRICE,a.CSC_PRICE,a.REFERENCE_PRICE,a.ADV_PRICE,a.SALE_QTY,a.TOTAL_AMOUNT,a.COUPON_TOTAL_MONEY,a.MANAGER_CARD_MONEY,a.POINT_AMOUNT,a.POINT_MONEY,a.VOUCHER_TOTAL_MONEY,a.GIFT_TYPE,a.DELIVERY_TYPE,a.WEIGHT,a.BASIC_FEE,a.CONTINUOUS_FEE,a.SRV_FEE,a.TRANSPORT_FEE,a.ITEM_TAX_FARE,a.HOPE_ARRIVAL_DATE,a.HOPE_ARRIVAL_TIME,a.VERIFY_CODE,a.INBOX_CMMDTY_CODE,a.INBOX_CMMDTY_NAME,a.INBOX_SALE_PRICE,a.INBOX_SALE_QTY,a.STOCK_AREA,a.SUPPLIER_CODE,a.INVENTORY_SUPPLIER,a.PROVIDE_ADDRESS,a.SUPPROVIDE_ADDRESS,a.PURCHASE_FLAG,a.CMMDTY_MARK,a.CREATE_TIME,a.UPDATE_TIME  FROM (" + StringUtils.join(list, " union all ") + ") a";
        System.out.println(str);
    }
}
