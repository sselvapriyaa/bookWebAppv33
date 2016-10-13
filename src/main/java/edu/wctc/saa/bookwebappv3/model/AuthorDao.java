
package edu.wctc.saa.bookwebappv3.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
/**
 *
 * @author Gladwin
 */

    @Dependent
public class AuthorDao implements AuthorDaoStrategy, Serializable {
   
    @Inject
    private DBStrategy db;
    
    
    private String driver;
    private String url;
    private String user;
    private String pwd;
    
    /* Default constructor required for injectable objects 
     */ 
    public AuthorDao() { 
    }
    
    /**
     * Initialize the DAO
     * @param driver
     * @param url
     * @param user
     * @param password 
     */
    @Override
    public void initDao(String driver, String url, String user, String password) {
        setDriver(driver);
        setUrl(url);
        setUser(user);
        setPwd(password);
    }
    
    /**
     * Gets Authors
     * @return - List
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List<Author> getAuthorList() 
            throws ClassNotFoundException, SQLException {
//        db.openConnection(driver, url, user, pwd);
//        List<Author> records = new ArrayList<>();
//
//        List<Map<String,Object>> rawData = db.findAllRecords("author",0);
//        for(Map rawRec : rawData) {
//            Author author = new Author();
//            Object obj = rawRec.get("author_id");
//            author.setAuthorId(Integer.parseInt(obj.toString()));
//            
//            String name = rawRec.get("author_name") == null ? "" : rawRec.get("author_name").toString();
//            author.setAuthorName(name);
//            
//            obj = rawRec.get("date_added");
//            Date dateAdded = (obj == null) ? new Date() : (Date)rawRec.get("date_added");
//            author.setDateAdded(dateAdded);
//            records.add(author);
//        }
//
//        return records;
//        
        db.openConnection(driver, url, user, pwd);
        List<Author> records = new ArrayList<>();

        List<Map<String,Object>> rawData = db.findAllRecords("author",500);
        
        for(Map<String,Object> rawRec : rawData) {
            Author author = new Author();
            Integer id = Integer.parseInt(rawRec.get("author_id").toString());
            author.setAuthorId(id);
            
            String name = rawRec.get("author_name").toString();
            author.setAuthorName(name != null ? name:"");
            
            Date dateAdded = (Date)rawRec.get("date_added");
            author.setDateAdded(dateAdded);
            
            records.add(author);
        }

        return records;
    }
    
    /**
     * Gets one Author
     * @param authorId
     * @return - Author object
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public Author getAuthorById(Integer authorId) 
            throws ClassNotFoundException, SQLException {
        
        db.openConnection(driver, url, user, pwd);
        
        Map<String,Object> rawRec = db.findById("author", "author_id", authorId);
        Author author = new Author();
        author.setAuthorId((Integer)rawRec.get("author_id"));
        author.setAuthorName(rawRec.get("author_name").toString());
        author.setDateAdded((Date)rawRec.get("date_added"));
        
        return author;
    }
    
    /**
     * Saves Author - Supports create and update CRUD actions
     * @param authorId
     * @param authorName
     * @return bool on save result
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean saveAuthor(Integer authorId, String authorName) 
            throws ClassNotFoundException, SQLException {
        
        db.openConnection(driver, url, user, pwd);
        boolean result = false;
        
        if(authorId == null || authorId.equals(0)) {
            result = db.insertRecord("author", Arrays.asList("author_name","date_added"), 
                                      Arrays.asList(authorName,new Date()));
        } else {
            int recsUpdated = db.updateRecords("author", Arrays.asList("author_name"), 
                                       Arrays.asList(authorName),
                                       "author_id", authorId);
            if(recsUpdated > 0) {
                result = true;
            }
        }
        return result;
    }
    
    /**
     * Deletes author
     * @param authorId
     * @return bool on delete result
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean deleteAuthor(Integer authorId) 
            throws ClassNotFoundException, SQLException {
        
        db.openConnection(driver, url, user, pwd);        
        int result = db.deleteById("author", "author_id", authorId);
        if(result == 0) {
            return false;
        } else {
            return true;
        }
    }
 

    //Getters and Setters
    @Override
    public DBStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DBStrategy db) {
        this.db = db;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Test harness - Comment out unless testing...
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
//    public static void main(String[] args) 
//            throws ClassNotFoundException, SQLException {
//        
//        AuthorDaoStrategy dao = new AuthorDao();
//        List<Author> authors = dao.getAuthorList();
//        System.out.println(authors);
//    }
}

