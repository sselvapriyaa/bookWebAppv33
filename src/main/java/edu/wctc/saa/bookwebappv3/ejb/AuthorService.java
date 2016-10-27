
package edu.wctc.saa.bookwebappv3.ejb;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
/**
 *
 * @author Gladwin
 */
@SessionScoped
public class AuthorService implements Serializable {

    @Inject
    private AuthorDaoStrategy dao;
    
/* default constructor required for injectable objects */
   
    public AuthorService() {
        
        // dao = new InjectedObject that implements AuthorDaoStrategy
    }

    /**
     * Gets all authors
     * @return - List of authors
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
   
    public List<Author> getAllAuthors() 
            throws ClassNotFoundException, SQLException {
        return dao.getAuthorList(); // no assignment to a concrete object to dao = null pointer exception
    }
    
    /**
     * Gets one author
     * @param authorId
     * @return - One author object
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Author getAuthorById(String authorId) 
            throws ClassNotFoundException, SQLException {
        return dao.getAuthorById(Integer.parseInt(authorId));
    }

    /**
     * Creates or Updates authors
     * @param authorId
     * @param authorName
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void saveOrUpdateAuthor(String authorId, String authorName) 
            throws ClassNotFoundException, SQLException {
        Integer id = null;
        if (authorId == null || authorId.isEmpty()) {
            id = null;
        } else {
            id = Integer.parseInt(authorId);
        }
        dao.saveAuthor(id, authorName);
    }

    /**
     * Deletes and author
     * @param authorId
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void deleteAuthorById(String authorId) 
            throws ClassNotFoundException, SQLException {
     
    
         if (authorId == null || authorId.isEmpty()) {
             throw new IllegalArgumentException("Sorry,author id is required");
         }
              
         
        dao.deleteAuthor(Integer.parseInt(authorId));
    }

    //Getters and Setters
    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        
//        AuthorService srv = new AuthorService();
//        
//        List<Author> authors = srv.getAllAuthors();
//        System.out.println(authors);
//        
//    }

}
