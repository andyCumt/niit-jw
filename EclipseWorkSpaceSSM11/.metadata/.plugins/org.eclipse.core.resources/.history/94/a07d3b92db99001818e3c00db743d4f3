package com.niit.core.utils.uuid;

/**
 * @author andy
 */
public class UUIDGenerate {

    private static MySQLUUID mySQLUUID;


    /**
     * 可自定义实现类
     *
     * @param mySQLUUID
     */
    public static void setUuidImpl(MySQLUUID mySQLUUID) {
        UUIDGenerate.mySQLUUID = mySQLUUID;
    }

    /**
     * 生成UUID by time
     *
     * @return
     */
    public static String generateUuidByTime() {

        check();
        return mySQLUUID.generateUuidByTime();
    }

    /**
     * 生成uuid by byte
     *
     * @return
     */
    public static String generateUuidByByte() {

        check();
        return mySQLUUID.generateUuidByByte();
    }

    /**
     * 检查是否注入自定义实现
     */
    private static void check() {
        if (mySQLUUID == null) {
            mySQLUUID = new DefaultMySQLUUIDImpl();
        }
    }
}