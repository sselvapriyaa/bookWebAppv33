
package edu.wctc.saa.bookwebappv3.model;

import java.util.Date;
import java.util.Objects;
/**
 *
 * @author Gladwin
 */
public class Author {
    private int authorId;
    private String authorName;
    private Date dateAdded;
    
    //Contructors
    public Author() {
    
    }
    
    public Author(int authId) {
        authorId = authId;
    }
    
    public Author(int authId, String authName, Date dateAdd){
        authorId = authId;
        authorName = authName;
        dateAdded = dateAdd;
    }
    
    //toString
    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }

    //Hash code
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.authorId;
        hash = 41 * hash + Objects.hashCode(this.authorName);
        hash = 41 * hash + Objects.hashCode(this.dateAdded);
        return hash;
    }

    //Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }
    
    //Getters and Setters
    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }
    
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
}

