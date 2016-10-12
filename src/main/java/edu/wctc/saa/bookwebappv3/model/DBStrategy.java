/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.saa.bookwebappv3.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Gladwin
 */

    public interface DBStrategy {
    
    void openConnection(String driverClass, String url, String userName, String password) 
            throws ClassNotFoundException, SQLException;
    
    public void closeConnection() 
            throws ClassNotFoundException, SQLException;
    
    List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) 
            throws ClassNotFoundException, SQLException;
    
    Map<String,Object> findById(String tableName, String pkName, Object pkValue) 
            throws ClassNotFoundException, SQLException;
    
    boolean insertRecord(String tableName, List colNames, List colValues) 
            throws ClassNotFoundException, SQLException;
    
    int updateRecords(String tableName, List colNames, List colValues, String pkName, Object value) 
            throws ClassNotFoundException, SQLException;
    
    int deleteById(String tableName, String pkName, Object pkValue) 
            throws ClassNotFoundException, SQLException;

}

