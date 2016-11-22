
package edu.wctc.saa.bookwebappv33.controller;

import edu.wctc.saa.bookwebappv33.entity.Author;
import edu.wctc.saa.bookwebappv33.entity.Book;
import edu.wctc.saa.bookwebappv33.service.AuthorService;
import edu.wctc.saa.bookwebappv33.service.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Gladwin
 */
//@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {
     
    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String RESULTS_PAGE = "/ManageBooks.jsp";
    private static final String EDIT_PAGE = "/ManageBook.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    private static final String SUBMIT_ACTION = "submit";
    private static final String ADD_EDIT_ACTION = "Add/Edit";
    private static final String ACTION_PARAM = "action";
    private static final String SAVE_ACTION = "Save";
    private static final String CANCEL_ACTION = "Cancel";
    private static final String CHAR_SET = "text/html;charset=UTF-8";
    
    private BookService bookService;
    private AuthorService authService;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(CHAR_SET);
        
       String destination = RESULTS_PAGE;
        String action = request.getParameter(ACTION_PARAM);
        Book book = null;
        
        try {

            switch (action) {
                case LIST_ACTION:
                    this.refreshBookList(request, bookService);
                    this.refreshAuthorList(request, authService);
                    destination = RESULTS_PAGE;
                    break;

                case ADD_EDIT_DELETE_ACTION:
                    String subAction = request.getParameter(SUBMIT_ACTION);

                    if (subAction.equals(ADD_EDIT_ACTION)) {

                        String[] bookIds = request.getParameterValues("bookId");
                        if (bookIds == null) {
                            this.refreshAuthorList(request, authService);
                        } else {
                            String bookId = bookIds[0];
                            book = bookService.findById(bookId);
                            request.setAttribute("book", book);
                            this.refreshAuthorList(request, authService);
                        }

                        destination = EDIT_PAGE;

                    } else {
                        String[] bookIds = request.getParameterValues("bookId");
                        for (String id : bookIds) {
                            book = bookService.findById(id);
                            bookService.remove(book);
                        }

                        this.refreshBookList(request, bookService);
                        this.refreshAuthorList(request, authService);
                        destination = RESULTS_PAGE;
                    }
                    break;
                    
                case SAVE_ACTION:
                    String title = request.getParameter("title");
                    String isbn = request.getParameter("isbn");
                    String authorId = request.getParameter("authorId");
                    String bookId = request.getParameter("bookId");
                    
                    if(bookId == null) {
                        book = new Book(0);
                        book.setTitle(title);
                        book.setIsbn(isbn);
                       
                        if(authorId != null) {
                            Author author = authService.findById(authorId);
                            book.setAuthorId(author);
                        }
                    } else {
                        book = bookService.findById(bookId);;
                        book.setTitle(title);
                        book.setIsbn(isbn);
                        Author author = null;
                        if(authorId != null) {
                            author = authService.findById(authorId);;
                            book.setAuthorId(author);
                        }
                    }
                    
                    bookService.edit(book);
                    this.refreshBookList(request, bookService);
                    this.refreshAuthorList(request, authService);
                    destination = RESULTS_PAGE;
                    break;
                    
                case CANCEL_ACTION:
                    this.refreshBookList(request, bookService);
                    this.refreshAuthorList(request, authService);
                    destination = RESULTS_PAGE;
                    break;

                default:
                    request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                    destination = RESULTS_PAGE;
                    break;
            }

        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
        
    }
    
    private void refreshBookList(HttpServletRequest request, BookService bookService) throws Exception {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
    }
    
    private void refreshAuthorList(HttpServletRequest request, AuthorService authorService) throws Exception {
        List<Author> authors = authorService.findAll();
        request.setAttribute("authors", authors);
              
               
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authService = (AuthorService) ctx.getBean("authorService");
        bookService = (BookService) ctx.getBean("bookService");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   
        

}
