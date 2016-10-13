
package edu.wctc.saa.bookwebappv3.controller;


import edu.wctc.saa.bookwebappv3.model.Author;
import edu.wctc.saa.bookwebappv3.model.AuthorService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;

/**
 *
 * @author Gladwin
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
    // No Magic Numbers
    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String RESULTS_PAGE = "/authorTablePage.jsp";
    private static final String Edit_PAGE = "/ManageAuthor.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    private static final String SUBMIT_ACTION = "submit";
    private static final String ADD_EDIT_ACTION = "Add/Edit";
    private static final String ACTION_PARAM = "action";
    private static final String SAVE_ACTION = "Save";
    private static final String CANCEL_ACTION = "Cancel";
    
    // db config init params from web.xml
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    
    @Inject
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
        response.setContentType("text/html;charset=UTF-8");
        String destination = RESULTS_PAGE;
       
        String action = request.getParameter(ACTION_PARAM);
        
         configDbConnection();
        try{
                      
                switch (action) {
                case LIST_ACTION:
                
                    this.refreshList(request, authService);
                    destination = RESULTS_PAGE;
                    
                    break;

                case ADD_EDIT_DELETE_ACTION:
                 
                    String subAction = request.getParameter(SUBMIT_ACTION);
                        
                    if (subAction.equals(ADD_EDIT_ACTION)) {
                       
                        String[] authorIds = request.getParameterValues("authorId");
                        if (authorIds == null) {

                        } else {
                            String authorId = authorIds[0];
                            Author author = authService.getAuthorById(authorId);
                            request.setAttribute("author", author);
                        }

                        destination = Edit_PAGE;
                       
                    } else {
                        String[] authorIds = request.getParameterValues("authorId");
                        for (String id : authorIds) {
                            authService.deleteAuthorById(id);
                        }

                        this.refreshList(request, authService);
                        //destination = RESULTS_PAGE;
                         destination =  "/authorTablePage.jsp";
                    }
                    break;
                    
                case SAVE_ACTION:
                    String authorName = request.getParameter("authorName");
                    String authorId = request.getParameter("authorId");
                    authService.saveOrUpdateAuthor(authorId, authorName);
                    this.refreshList(request, authService);
                    destination = RESULTS_PAGE;
                    break;
                        
                case CANCEL_ACTION:
                    this.refreshList(request, authService);
                    destination = RESULTS_PAGE;
                    break;

                default:
                    request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                    destination = RESULTS_PAGE;
                    break;
            }
       
            
        } catch (Exception e) {
          
           
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
            dispatcher.forward(request, response); 
        

    }

    private void refreshList(HttpServletRequest request, AuthorService authService) throws Exception {
        List<Author> authors = authService.getAllAuthors();
        request.setAttribute("authors", authors);
    }
    
    private void configDbConnection() { 
        authService.getDao().initDao(driverClass, url, userName, password);   
    }
//    @Override
//    public void init() throws ServletException{
//        driverClass = "com.mysql.jdbc.Driver";
//        url="jdbc:mysql://localhost:3306/book";
//        userName ="root";
//        password ="admin";
//        
        //webmasterEmail = getServletContext().getInitParameter("webmaster-email");
        
//    }
@Override
    public void init() throws ServletException {
        //Get init params from web.xml
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        userName = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
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
