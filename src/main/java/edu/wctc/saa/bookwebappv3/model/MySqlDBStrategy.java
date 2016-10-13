
package edu.wctc.saa.bookwebappv3.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Gladwin
 */
@Dependent
public class MySqlDBStrategy implements DBStrategy, Serializable {

    private Connection conn;

    public MySqlDBStrategy() {

    }

    /**
     * Opens a connection to DB
     *
     * @param driverClass - jdbc driver
     * @param url - url to db
     * @param userName
     * @param password
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void openConnection(String driverClass, String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    /**
     * Closes connection to DB
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void closeConnection()
            throws ClassNotFoundException, SQLException {

        conn.close();
    }

    /**
     * Finds all record from specified table
     *
     * @param tableName
     * @param maxRecords - if 0 return all records, otherwise return X records
     * @return - list of maps
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords)
            throws ClassNotFoundException, SQLException {

        String sql;
        if (maxRecords < 1) {
            sql = "select * from " + tableName;
        } else {
            sql = "select * from " + tableName + " limit " + maxRecords;
        }

        List<Map<String, Object>> recordList = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();

        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int i = 1; i <= colCount; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
            recordList.add(record);
        }

        stmt.close();
        conn.close();

        return recordList;
    }

    /**
     * Finds one records by PK
     *
     * @param tableName
     * @param pkName
     * @param pkValue
     * @return - Map
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public Map<String, Object> findById(String tableName, String pkName, Object pkValue)
            throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM " + tableName + " WHERE " + pkName + " = ?";
        final Map<String, Object> record = new HashMap();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, pkValue);
        ResultSet rs = stmt.executeQuery();
        final ResultSetMetaData metaData = rs.getMetaData();
        final int fields = metaData.getColumnCount();

        if (rs.next()) {
            for (int i = 1; i <= fields; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
        }

        stmt.close();
        conn.close();

        return record;
    }

    /**
     * Inserts record into DB
     *
     * @param tableName
     * @param colNames
     * @param colValues
     * @return - bool test of rec insert
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public boolean insertRecord(String tableName, List colNames, List colValues)
            throws ClassNotFoundException, SQLException {

        PreparedStatement pstmt = buildInsertStatement(conn, tableName, colNames);

        final Iterator i = colValues.iterator();
        int index = 1;
        while (i.hasNext()) {
            final Object obj = i.next();
            pstmt.setObject(index++, obj);
        }
        int recsUpdated = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        if (recsUpdated == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates records in DB
     *
     * @param tableName
     * @param colNames
     * @param colValues
     * @param whereField
     * @param whereValue
     * @return - int of records inserted
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public int updateRecords(String tableName, List colNames, List colValues, String whereField, Object whereValue)
            throws ClassNotFoundException, SQLException {

        PreparedStatement pstmt = buildUpdateStatement(conn, tableName, colNames, whereField);
        final Iterator i = colValues.iterator();
        int index = 1;
        Object obj = null;

        while (i.hasNext()) {
            obj = i.next();
            pstmt.setObject(index++, obj);
        }

        pstmt.setObject(index, whereValue);
        int recsUpdated = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return recsUpdated;
    }

    /**
     * Deletes record from DB
     *
     * @param tableName
     * @param pkName
     * @param pkValue
     * @return - int of records deleted
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public int deleteById(String tableName, String pkName, Object pkValue)
            throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE " + pkName + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, pkValue);

        return stmt.executeUpdate();
    }

    /**
     * Helper method to build sql statement
     *
     * @param conn
     * @param tableName
     * @param colNames
     * @return - PreparedStatement for insert
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private PreparedStatement buildInsertStatement(Connection conn, String tableName, List colNames)
            throws ClassNotFoundException, SQLException {

        StringBuffer sql = new StringBuffer("INSERT INTO ");
        (sql.append(tableName)).append(" (");
        final Iterator i = colNames.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(", ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ") VALUES (");
        for (int j = 0; j < colNames.size(); j++) {
            sql.append("?, ");
        }
        final String finalSQL = (sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ")";

        PreparedStatement psmt = conn.prepareStatement(finalSQL);

        return psmt;
    }

    /**
     * Helper method to build sql statement
     *
     * @param conn
     * @param tableName
     * @param colNames
     * @param pkName
     * @return - PreparedStatement for update
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private PreparedStatement buildUpdateStatement(Connection conn, String tableName, List colNames, String pkName)
            throws ClassNotFoundException, SQLException {

        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i = colNames.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
        ((sql.append(" WHERE ")).append(pkName)).append(" = ?");
        final String finalSQL = sql.toString();
        PreparedStatement psmt = conn.prepareStatement(finalSQL);

        return psmt;
    }





    /**
     * Test harness - Comment out unless testing...
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
////
//        public static void main(String[] args)throws Exception {
//        DBStrategy db = new MySqlDBStrategy();
//        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book","root", "admin");
//        List<Map<String, Object>> records = db.findAllRecords("author",500);
//            System.out.println(records);
//            db.closeConnection();
//        
//    }
}
