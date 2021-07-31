package com.notifier.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.LoginDao;
import com.notifier.dao.noteDao;
import com.notifier.dao.notifyDao;
import com.notifier.model.User;
import com.notifier.model.Note;
import com.notifier.model.NoteBook;

@WebServlet("/")
public class notifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private notifyDao dao;
      private LoginDao daol;
      private noteDao notedao;
   
    public notifyServlet() {
        super();
       
    }
    public void init() {
    	dao = new notifyDao();
    	daol = new LoginDao();
        notedao = new noteDao();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getServletPath();
		try {
			switch(action)
			{
			case "/createUser":
				createUser(request, response);
				break;
			case "/createNoteBook":
				createNoteBook(request, response);
				break;
			case "/updateUser":
				updateUser(request, response);
				break;
			case "/editUser":
				editUser(request, response);
				break;
			case "/notebooks":
				noteBooks(request,response);
				break;
			case "/createNote":
				createNote(request, response);
				break;
			case "/updateNoteBook":
				updateNoteBook(request,response);
				break;
			case "/updateNote":
				updateNote(request,response);
				break;
			case "/notes":
				notes(request,response);
				break;
			case "/showNote":
				showNote(request,response);
				break;
				
			
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
				
			}
		}
		catch(Exception e) {
		
		}
	}

	private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	
		String userName = request.getParameter("userName");
		String mobileNumber = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User(userName, mobileNumber, email, password);
		dao.inserUser(user);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	
	private void createNoteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	 
		System.out.println("New notebook created");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        System.out.println(id_);
		String notebook_name = request.getParameter("noteBookName");
		NoteBook notebook = new NoteBook(id_,notebook_name);
		dao.addNoteBook(notebook);
		request.setAttribute("nb_name", notebook_name);
		RequestDispatcher rd = request.getRequestDispatcher("noteBooks.jsp");
		rd.forward(request, response);
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String mobileNumber = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
		User user = new User(id_,userName, mobileNumber, email, password);
		dao.inserUser(user);
		RequestDispatcher rd = request.getRequestDispatcher("noteBooks.jsp");
		rd.forward(request, response);
	}
	
	private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
		System.out.println("editUser");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        User u = new User(id_);
        String uname = daol.getname(u);
		String mn = daol.getmobile(u);
		String em = daol.getemail(u);
		String pass = daol.getpass(u);
		request.setAttribute("id_", id_);
		request.setAttribute("uname", uname);
		request.setAttribute("mn", mn);
		request.setAttribute("em", em);
		request.setAttribute("pass", pass);
		
		RequestDispatcher rd = request.getRequestDispatcher("editUser.jsp");
		rd.forward(request, response);
	}
	private void noteBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
         User u = new User(id_);
         String nb_name= dao.book(u);
         request.setAttribute("nb_name", nb_name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("noteBooks.jsp");
        dispatcher.forward(request, response);
	}
	
  private void createNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	  System.out.println("in create note");
	  HttpSession session = request.getSession();
      int id_ = (int)session.getAttribute("id_");
      User u = new User(id_);
       int notebookId =notedao.notebookId(u);
        String note_name = request.getParameter("noteName"); 
        LocalDate start_date = LocalDate.parse(request.getParameter("startDate"));
        LocalDate end_date = LocalDate.parse(request.getParameter("endDate"));
        LocalDate remainder_date = LocalDate.parse(request.getParameter("remainderDate"));
        boolean status = Boolean.valueOf(request.getParameter("statusName")); 
        String description = request.getParameter("noteDescription"); 
        Note note = new Note(notebookId,end_date, description, note_name, remainder_date, start_date, status );
		notedao.insertNote(note);
		request.setAttribute("note_name", note_name);
		String sdate= notedao.startdate(notebookId);
		System.out.println(sdate);
		String edate= notedao.enddate(notebookId);
		String des= notedao.desc(notebookId);
		//request.setAttribute("note_name",note_name);
		 request.setAttribute("start_date", start_date);
		 request.setAttribute("end_date", edate);
		 request.setAttribute("desc", des);
		System.out.println(note_name);
		RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
		rd.forward(request, response);
	}
	
   private void updateNoteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
       String nb_name= request.getParameter("noteBookName");
       NoteBook nb = new NoteBook(id_,nb_name);
       dao.updatenb(nb);
       request.setAttribute("nb_name", nb_name);
      RequestDispatcher dispatcher = request.getRequestDispatcher("noteBooks.jsp");
      dispatcher.forward(request, response);
      //System.out.println("updatenb");
	}
   private void updateNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	   HttpSession session = request.getSession();
       int id_ = (int)session.getAttribute("id_");
       User u = new User(id_);
		String note_name = request.getParameter("noteName");
       LocalDate start_date = LocalDate.parse(request.getParameter("startDate"));
       LocalDate end_date = LocalDate.parse(request.getParameter("endDate"));
       LocalDate remainder_date = LocalDate.parse(request.getParameter("remainderDate"));
       boolean status = Boolean.valueOf(request.getParameter("statusName"));
		String description = request.getParameter("noteDescription");
		int notebookId =notedao.notebookId(u);
       Note note = new Note(notebookId,end_date, description, note_name, remainder_date, start_date, status );
		notedao.upNote(note, notebookId);
		String sdate= notedao.startdate(notebookId);
		String edate= notedao.enddate(notebookId);
		String des= notedao.desc(notebookId);
		request.setAttribute("note_name",note_name);
		 request.setAttribute("start_date", sdate);
		 request.setAttribute("end_date", edate);
		 request.setAttribute("desc", des);
		RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
		
		rd.forward(request, response);
	}
	
   private void notes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
	   HttpSession session = request.getSession();
       int id_ = (int)session.getAttribute("id_");
       User u = new User(id_);
		int id = notedao.notebookId(u);
		String nname = notedao.notename(id);
		String sdate = notedao.startdate(id);
		System.out.println(sdate);
		String edate = notedao.enddate(id);
		request.setAttribute("start_date", sdate);
		request.setAttribute("note_name", nname);
		request.setAttribute("end_date", edate);
		RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
		rd.forward(request, response);
	}
   private void showNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	   System.out.println("in show  note");
	   HttpSession session = request.getSession();
       int id_ = (int)session.getAttribute("id_");
       User u = new User(id_);
		int id = notedao.notebookId(u);
		String nname = notedao.notename(id);
		String sdate = notedao.startdate(id);
		String edate = notedao.enddate(id);
		String des = notedao.desc(id);
		String rdate = notedao.remdate(id);
		String stat = notedao.status(id);
		request.setAttribute("r_date", rdate);
		  System.out.println(rdate);
		request.setAttribute("status", stat);
		request.setAttribute("des", des);
		request.setAttribute("s_date", sdate);
		request.setAttribute("n_name", nname);
		request.setAttribute("e_date", edate);
		RequestDispatcher rd = request.getRequestDispatcher("showNote.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}