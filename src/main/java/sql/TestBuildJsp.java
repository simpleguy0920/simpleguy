package sql;//package sql;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * 〈一句话功能简述〉
// * 〈功能详细描述〉
// *
// * @author simpleguy
// * @see [相关类/方法]（可选）
// * @since [产品 /模块版本] （可选）
// */
//public class TestBuildJsp {
//    public static void main(String[] args) {
//        TestBuildJsp testJson = new TestBuildJsp();
//        testJson.parseOrderDetail();
//    }
//
//    public void parseOrderDetail() {
//        String json = "{\"status\":200,\"message\":\"成功\",\"data\":{\"id\":0,\"name\":\"MerchantOrderProductInfoView\",\"cnName\":\"订单商品信息\",\"description\":\"订单商品信息\",\"params\":[{\"id\":2152,\"paramName\":\"id\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"id\"},{\"id\":2153,\"paramName\":\"oid\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"订单id\"},{\"id\":2155,\"paramName\":\"skuId\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"快手商品skuid\"},{\"id\":2156,\"paramName\":\"relSkuId\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"服务商商品skuid\"},{\"id\":2157,\"paramName\":\"skuDesc\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"null\",\"required\":false,\"description\":\"sku描述\"},{\"id\":2158,\"paramName\":\"skuNick\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"苹果11-白128g\",\"required\":false,\"description\":\"sku编码，方便商家管理商品的编码，用于库存管理、多渠道销售分析等\"},{\"id\":2159,\"paramName\":\"itemId\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"快手商品id\"},{\"id\":2160,\"paramName\":\"relItemId\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"服务商商品id\"},{\"id\":2161,\"paramName\":\"itemTitle\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"西瓜\",\"required\":false,\"description\":\"商品名称\"},{\"id\":2162,\"paramName\":\"itemLinkUrl\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"https://...\",\"required\":false,\"description\":\"商品链接\"},{\"id\":2163,\"paramName\":\"itemPicUrl\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"https://...\",\"required\":false,\"description\":\"商品图片地址\"},{\"id\":2164,\"paramName\":\"num\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"11\",\"required\":false,\"description\":\"成交数量\"},{\"id\":2165,\"paramName\":\"originalPrice\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1290\",\"required\":false,\"description\":\"商品促销前单价快照 以分为单位\"},{\"id\":2166,\"paramName\":\"discountFee\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"100\",\"required\":false,\"description\":\"折扣金额\"},{\"id\":2167,\"paramName\":\"price\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1190\",\"required\":false,\"description\":\"商品单价快照，以分为单位\"},{\"id\":2168,\"paramName\":\"createTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"毫秒时间戳\"},{\"id\":2169,\"paramName\":\"updateTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"毫秒时间戳\"},{\"id\":2170,\"paramName\":\"refundId\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"退款订单id\"},{\"id\":2171,\"paramName\":\"refundStatus\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"10\",\"required\":false,\"description\":\"退款状态  枚举：[10, \\\"买家仅退款申请\\\"] [11, \\\"买家退货退款申请\\\"] [20, \\\"平台介入-买家仅退款申请\\\"] [21, \\\"平台介入-买家退货退款申请\\\"] [22, \\\"平台介入-已确认退货退款\\\"] [30, \\\"商品回寄信息待买家更新\\\"] [40, \\\"商品回寄信息待卖家确认\\\"] [50, \\\"退款执行中\\\"] [60, \\\"退款成功\\\"] [70, \\\"退款失败\\\"]\"},{\"id\":2172,\"paramName\":\"itemType\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"1\",\"required\":false,\"description\":\"1自建商品 2 闪电购商品\"},{\"id\":2173,\"paramName\":\"refundList\",\"paramType\":\"List<MerchantOrderProductRefundView>\",\"structureId\":187,\"example\":\"{}\",\"required\":false,\"description\":\"该skuId下的退款列表\"},{\"id\":2174,\"paramName\":\"prevInfo\",\"paramType\":\"MerchantOrderProductPrevView\",\"structureId\":188,\"example\":\"{}\",\"required\":false,\"description\":\"订单修改之前的原订单信息\"},{\"id\":2175,\"paramName\":\"cpsInfo\",\"paramType\":\"MerchantCpsInfoView\",\"structureId\":189,\"example\":\"{}\",\"required\":false,\"description\":\"cps信息，若订单未支付，则无值。\"}]},\"timestamp\":\"2021-05-28 14:17:17\"}";
//        JSONObject jsonObject = JSON.parseObject(json);
//        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("params");
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject object = jsonArray.getJSONObject(i);
//            String desc = object.getString("description");
//            String paramName = object.getString("paramName");
//            if (i % 3 == 0) {
//                System.out.println("<tr>");
//            }
//            System.out.println("<td style=\"width:100px;\" class=\"label\">" + desc + "</td>");
//            System.out.println("<td style=\"width:150px;\">" + "<input type=\"text\" readOnly class=\"layui-input forbidden\" name=\"" + paramName + "\" value=\"<#if orderFeeInfo??>${orderFeeInfo." + paramName + "}</#if>\"/>\n" + "\t\t\t\t\t\t</td> ");
//            if (i % 3 == 2) {
//                System.out.println("</tr>");
//            }
//        }
//    }
//
//    public void parseOrderFee() {
//        String json = "{\"status\":200,\"message\":\"成功\",\"data\":{\"id\":0,\"name\":\"MerchantOrderFeeInfoView\",\"cnName\":\"订单费用信息\",\"description\":\"订单费用信息\",\"params\":[{\"id\":2270,\"paramName\":\"oid\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"订单id\"},{\"id\":2271,\"paramName\":\"sellerId\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"123\",\"required\":false,\"description\":\"卖家id\"},{\"id\":2272,\"paramName\":\"totalFee\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"200\",\"required\":false,\"description\":\"货款, 商家维度，不包括运费 计算公式为：商品总金额(price * num)- 商家承担(merchantBearAmount)   \"},{\"id\":2273,\"paramName\":\"discountFee\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"100\",\"required\":false,\"description\":\"订单优惠金额，累加marketingInfoOuters所有类型的优惠\"},{\"id\":2274,\"paramName\":\"expressFee\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"2\",\"required\":false,\"description\":\"运费\"},{\"id\":2275,\"paramName\":\"platformAllowance\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"100\",\"required\":false,\"description\":\"优惠补贴，platformBearAmount + merchantBearAmount\"},{\"id\":2276,\"paramName\":\"sellerRecv\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"202\",\"required\":false,\"description\":\"商家实收，货款 + 运费\"},{\"id\":2277,\"paramName\":\"buyerPay\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"102\",\"required\":false,\"description\":\"买家实付，货款 + 运费 - 平台承担补贴（platformBearAmount）\"},{\"id\":2278,\"paramName\":\"marketingInfoOuters\",\"paramType\":\"List<MerchantOrderMarketingInfoView>\",\"structureId\":195,\"example\":\"{}\",\"required\":false,\"description\":\"订单优惠信息\"},{\"id\":2279,\"paramName\":\"productFeeInfoOuters\",\"paramType\":\"List<MerchantOrderProductFeeInfoView>\",\"structureId\":196,\"example\":\"{}\",\"required\":false,\"description\":\"订单商品详情\"},{\"id\":3902,\"paramName\":\"platformBearAmount\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"60\",\"required\":false,\"description\":\"平台补贴中平台承担的费用 新模型计算公式（marketingInfoOuters.bearInfoList不为null）：累加marketingInfoOuters.bearInfoList 中平台承担类型的bearAmount          旧模型计算公式（marketingInfoOuters.bearInfoList为null）：取marketingInfoOuters子类型1002的discountFee   \"},{\"id\":3903,\"paramName\":\"merchantBearAmount\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"40\",\"required\":false,\"description\":\"平台补贴中商家承担的费用 计算公式：累加marketingInfoOuters.bearInfoList 中商家承担类型的bearAmount\"}]},\"timestamp\":\"2021-05-28 10:57:47\"}";
//
//        JSONObject jsonObject = JSON.parseObject(json);
//        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("params");
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject object = jsonArray.getJSONObject(i);
//            String desc = object.getString("description");
//            String paramName = object.getString("paramName");
//            if (i % 3 == 0) {
//                System.out.println("<tr>");
//            }
//            System.out.println("<td style=\"width:100px;\" class=\"label\">" + desc + "</td>");
//            System.out.println("<td style=\"width:150px;\">" + "<input type=\"text\" readOnly class=\"layui-input forbidden\" name=\"" + paramName + "\" value=\"<#if orderFeeInfo??>${orderFeeInfo." + paramName + "}</#if>\"/>\n" + "\t\t\t\t\t\t</td> ");
//            if (i % 3 == 2) {
//                System.out.println("</tr>");
//            }
//        }
//    }
//
//
//    public void parseOrder() {
//        String json = "{\"status\":200,\"message\":\"成功\",\"data\":{\"id\":0,\"name\":\"MerchantOrderInfoView\",\"cnName\":\"订单详情\",\"description\":\"订单详情\",\"params\":[{\"id\":2178,\"paramName\":\"payTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"付款时间\"},{\"id\":2180,\"paramName\":\"oid\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"100011\",\"required\":false,\"description\":\"订单号\"},{\"id\":2183,\"paramName\":\"sellerId\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"12345\",\"required\":false,\"description\":\"卖家id\"},{\"id\":2184,\"paramName\":\"sellerNick\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"张三\",\"required\":false,\"description\":\"卖家昵称\"},{\"id\":2185,\"paramName\":\"expressFee\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"20\",\"required\":false,\"description\":\"运费（单位分）\"},{\"id\":2186,\"paramName\":\"discountFee\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"100\",\"required\":false,\"description\":\"折扣价格（单位分）\"},{\"id\":2187,\"paramName\":\"totalFee\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1200\",\"required\":false,\"description\":\"货款, 商家维度，不包括运费 计算公式为：商品总金额(price * num)- 商家承担(merchantBearAmount)\"},{\"id\":2188,\"paramName\":\"status\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"10\",\"required\":false,\"description\":\"订单状态：[0, \\\"未知状态\\\"],  [10, \\\"待付款\\\"],  [30, \\\"已付款\\\"],  [40, \\\"已发货\\\"],  [50, \\\"已签收\\\"],  [70, \\\"订单成功\\\"],  [80, \\\"订单失败\\\"];\"},{\"id\":2189,\"paramName\":\"sendTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"发货时间\"},{\"id\":2190,\"paramName\":\"refundTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"退款时间(只要买家有申请退款行为)\"},{\"id\":2191,\"paramName\":\"createTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"创建时间\"},{\"id\":2192,\"paramName\":\"updateTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"更新时间\"},{\"id\":2193,\"paramName\":\"num\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"1\",\"required\":false,\"description\":\"sku商品种类数\"},{\"id\":2194,\"paramName\":\"mobile\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"123456789\",\"required\":false,\"description\":\"收件人手机号（此字段与address 中mobile 意义相同，修改address 中的mobile，此字段值也会被修改）\"},{\"id\":2195,\"paramName\":\"address\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"{\\\"consignee\\\": \\\"不帅\\\",   \\\"mobile\\\": 15000000000,   \\\"provinceCode\\\": 125620,   \\\"province\\\": \\\"北京\\\",   \\\"cityCode\\\": 100000,   \\\"city\\\": \\\"北京\\\",   \\\"districtCode\\\": 123456,   \\\"district\\\": \\\"\\\",   \\\"address\\\": \\\"\\\" }\",\"required\":false,\"description\":\"{\\\"consignee\\\": \\\"不帅\\\", //收货人姓名   \\\"mobile\\\": 15000000000, //收货人手机   \\\"provinceCode\\\": 125620, //收货人省份编号   \\\"province\\\": \\\"北京\\\", //收货人省份   \\\"cityCode\\\": 100000, //收货人城市编码   \\\"city\\\": \\\"北京\\\", //收货人城市   \\\"districtCode\\\": 123456, //收货人区\\\\县编码   \\\"district\\\": \\\"\\\", //收货人区\\\\县   \\\"address\\\": \\\"\\\" //收货人详细地址 }\"},{\"id\":2196,\"paramName\":\"refund\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"0\",\"required\":false,\"description\":\"是否申请退款 0未申请 1已申请（包括申请后取消）\"},{\"id\":2197,\"paramName\":\"remark\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"尽快发货\",\"required\":false,\"description\":\"买家留言\"},{\"id\":2198,\"paramName\":\"sellerNoteList\",\"paramType\":\"List<String>\",\"structureId\":0,\"example\":\" [\\\"请给好评\\\",\\\"请给好评\\\"]\",\"required\":false,\"description\":\"卖家备注，新备注在前\"},{\"id\":2199,\"paramName\":\"theDayOfDeliverGoodsTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"0\",\"required\":false,\"description\":\"发货间隔时间，单位：天\"},{\"id\":2200,\"paramName\":\"promiseTimeStampOfDelivery\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"1543843735000\",\"required\":false,\"description\":\"仅表示承诺发货时间\"},{\"id\":2201,\"paramName\":\"orderProductInfoList\",\"paramType\":\"List<MerchantOrderProductInfoView>\",\"structureId\":186,\"example\":\"{}\",\"required\":false,\"description\":\"商品品信息列表\"},{\"id\":2202,\"paramName\":\"logisticsInfo\",\"paramType\":\"MerchantLogisticsInfoView\",\"structureId\":190,\"example\":\"{}\",\"required\":false,\"description\":\"物流信息（列表接口无此字段）\"},{\"id\":2203,\"paramName\":\"activityType\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"1\",\"required\":false,\"description\":\"活动类型\"},{\"id\":2204,\"paramName\":\"cpsType\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"1\",\"required\":false,\"description\":\"分销类型 0-全部 1-普通订单 2-分销订单\"},{\"id\":3475,\"paramName\":\"payType\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"1\",\"required\":false,\"description\":\"支付方式 1微信 2支付宝\"},{\"id\":22336,\"paramName\":\"flagTagCode\",\"paramType\":\"String\",\"structureId\":0,\"example\":\"grey_flag_tag_order\",\"required\":false,\"description\":\"插旗颜色。red_flag_tag_order：红  grey_flag_tag_order：灰  yellow_flag_tag_order：黄  green_flag_tag_order：绿  blue_flag_tag_order：蓝  purple_flag_tag_order：紫（该字段仅在获取订单详情API中返回，不在获取订单列表（游标方式）API返回）\"},{\"id\":22337,\"paramName\":\"validPromiseShipmentTimeStamp\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"0\",\"required\":false,\"description\":\"预售发货时间/承诺发货时间（该字段仅在获取订单详情API中返回，不在获取订单列表（游标方式）API返回）\"},{\"id\":22338,\"paramName\":\"preSale\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"0\",\"required\":false,\"description\":\"0：非预售 1:预售（该字段仅在获取订单详情API中返回，不在获取订单列表（游标方式）API返回）\"},{\"id\":22339,\"paramName\":\"recvTime\",\"paramType\":\"Long\",\"structureId\":0,\"example\":\"0\",\"required\":false,\"description\":\"确认收货时间（该字段仅在获取订单详情API中返回，不在获取订单列表（游标方式）API返回）\"},{\"id\":22340,\"paramName\":\"coType\",\"paramType\":\"Integer\",\"structureId\":0,\"example\":\"1\",\"required\":false,\"description\":\"区分虚拟订单字段。JD = 1; 券包 = 2;  话费充值 = 3； 话费充值 小米 = 5; （该字段仅在获取订单详情API中返回，不在获取订单列表（游标方式）API返回）\"}]},\"timestamp\":\"2021-05-27 19:54:53\"}";
//        JSONObject jsonObject = JSON.parseObject(json);
//        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("params");
//
//        JSONArray newArray = new JSONArray();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject object = jsonArray.getJSONObject(i);
//            String paramName = object.getString("paramName");
//            if ("orderProductInfoList".equals(paramName)) {
//                continue;
//            }
//            newArray.add(object);
//        }
//        jsonArray = newArray;
//
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject object = jsonArray.getJSONObject(i);
//            String desc = object.getString("description");
//            String paramName = object.getString("paramName");
//            if (i % 3 == 0) {
//                System.out.println("<tr>");
//            }
//            System.out.println("<td style=\"width:100px;\" class=\"label\">" + desc + "</td>");
//            System.out.println("<td style=\"width:150px;\">" + "<input type=\"text\" readOnly class=\"layui-input forbidden\" name=\"" + paramName + "\" value=\"<#if orderHead??>${orderHead." + paramName + "}</#if>\"/>\n" + "\t\t\t\t\t\t</td> ");
//
//            if (i % 3 == 2) {
//                System.out.println("</tr>");
//            }
//        }
//    }
//}
