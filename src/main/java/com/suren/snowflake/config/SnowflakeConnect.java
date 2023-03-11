package com.suren.snowflake.config;

import java.sql.*;
import java.util.Properties;

/**
 * @author Surendra Kumar S
 */
public class SnowflakeConnect {


    private static String account = "snowflake-account"; //
//    private static String region = "<snowflake-region>";
    private static String user = "userName";
    private static String role = "DEV_ROLE_SNOWFLAKE";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:snowflake://" + account + ".snowflakecomputing.com/";
        Properties props = new Properties();
        props.put("user", user);
        props.put("role", role);

        // for RBAC, leave password blank and include authenticator in properties
        props.put("password", "");
        props.put("authenticator", "snowflake");
//        props.put("region", region);

        return DriverManager.getConnection(url, props);
    }

    public static void main(String[] args) throws SQLException {
        // establish connection
        Connection conn = getConnection();

        // work with connection
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Users");
        rs.next();
        int count = rs.getInt(1);
        System.out.println(String.format("Table has %d rows", count));

        stmt.close();

        // close connection
        conn.close();
    }


}
