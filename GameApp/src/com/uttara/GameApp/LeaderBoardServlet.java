package com.uttara.GameApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LeaderBoardServlet
 */
public class LeaderBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaderBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("inside LDS ");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 System.out.println("inside DOGET() LDS ");
		 
		 //if the contaxt has the pointsmap
		 //if yes,, render it to user.
		 ServletContext sc=getServletContext();
		 
		 PrintWriter pw=response.getWriter();
		 if(sc.getAttribute("PM")==null)
		 {
			 pw.write("<html><body><h1>No 1 Played Yet</h1></body></html>");
			 pw.write("<html><body><h2>Session is Empty</h2><b>Click Here to go Back ..<a href='HomePage.html'>HomePage</b></body></html>");
		 }
		 else
		 {
			 pw.write("<html><body><h1>Points Map</h1> "+ sc.getAttribute("PM") +" </body></html><br/>");
			 pw.write("<html><body><h2>SupeR</h2><b>Click Here to go Back ..<a href='HomePage.html'>HomePage</b></body></html>");
		 }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		 System.out.println("inside DOPOST() LDS ");
	}

}
