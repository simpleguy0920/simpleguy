package sql;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 公共库分表路由类<br>
 *
 * @author 15040610 Guojianwei
 * @since V1.11.0
 */
public class PublicDBRouter {

    /**
     * 公共库库号
     */
    public static final String PUBLIC_DB = "publicDB";

    /**
     * 公共库分表数量
     */
    private static final int TABLE_QUANTITY = 50;

    /**
     * 公共库分表信息Map
     */

    /**
     * 读写锁
     */
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock(false);

    /**
     * 私有构造函数
     */
    private PublicDBRouter() {

    }


    public static int route(String id) {

        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(id);
        int number = hashCodeBuilder.toHashCode();

        int shardQuantity = 1;
        int tableQuantity = TABLE_QUANTITY;
        int mod = number % (shardQuantity * tableQuantity);

        return Math.abs(mod);

    }


}
