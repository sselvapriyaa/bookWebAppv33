package edu.wctc.saa.bookwebappv33.repository;

import edu.wctc.saa.bookwebappv33.entity.Book;
import java.io.Serializable;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gladwin
 */
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable {
    
}
