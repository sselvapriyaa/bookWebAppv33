
package edu.wctc.saa.bookwebappv3.model;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
/**
 *
 * @author Gladwin
 */
public interface AuthorDaoStrategy {
    
    void initDao(String driver, String url, String user, String password);
     void initDao(DataSource ds)throws ClassNotFoundException, SQLException ;
    List<Author> getAuthorList() 
            throws ClassNotFoundException, SQLException;
    
    Author getAuthorById(Integer authorId) 
            throws ClassNotFoundException, SQLException;
    
    boolean saveAuthor(Integer authorId, String authorName) 
            throws ClassNotFoundException, SQLException;
    
    boolean deleteAuthor(Integer authorId) 
            throws ClassNotFoundException, SQLException;
    
    public DBStrategy getDb();
    
    public void setDb(DBStrategy db);
    
    public String getDriver();
    
    public void setDriver(String driver);
    
    public String getUrl();

    public void setUrl(String url);

    public String getUser();

    public void setUser(String user);

    public String getPwd();

    public void setPwd(String pwd);
}

