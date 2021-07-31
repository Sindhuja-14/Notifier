package com.notifier.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.LoginDao;
import com.notifier.dao.notifyDao;
import com.notifier.model.LoginBean;
import com.notifier.model.User;

@WebServlet("/index")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1 ;
    private LoginDao loginDao;
    private notifyDao ndao;
    public LoginController() {
    	super();
    }

    public void init() {
        loginDao = new LoginDao();
        ndao = new notifyDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			authenticate(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean(email,password);
        loginBean.setEmail(email);
        loginBean.setPassword(password);
         
       
        try {
            if (loginDao.validate(loginBean)) {
            	int id_ = loginDao.getId_(loginBean);
            	System.out.print(id_);
            	HttpSession session = request.getSession();
                session.setAttribute("id_", id_);
            	// HttpSession session1= request.getSession();
                 //session1.setAttribute("userName", username); 
                 //request.setAttribute("userName",username);
                 User u = new User(id_);
                 String nb_name= ndao.book(u);
                 request.setAttribute("nb_name", nb_name);
                RequestDispatcher dispatcher = request.getRequestDispatcher("noteBooks.jsp");
                dispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                 session.setAttribute("user", email);
                 response.sendRedirect("index.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}