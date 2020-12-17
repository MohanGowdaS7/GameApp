package com.uttara.GameApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartGameServlet
 */
public class StartGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartGameServlet() {
        super();
        // TODO Auto-generated constructor stub
        
        System.out.println("inside StartGame no args consructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("inside strtgame doget() method");
		/*
		 * 1.access email input from the request
		 * 2.perform input validation
		 * 3.generate random number between 0-9
		 * 4.ask ws to create the session for the class and store 
		 * genval,email,num of tries(3) in a session
		 * 5.forward the controller to guess.html through using RD!
		 */
		
		String email=request.getParameter("email");
		PrintWriter pw=response.getWriter();
		if(email==null || email.equals("") || !email.contains("@"))
			pw.write("<html><body><h2>Enter your Email with Identity (@)...IDIOT!</h2><b>if you couldn't rememeber your Email, how can you remember your wife name,, prss Here to ..<a href='StartGame.html'>StartGame</a></b></body></html>");
		
		else 
		{
			//email is valid
			//int val=1;
			int val=(int)(Math.random()*10);
			System.out.println("gen value is "+val);
			int tries=3;
			
			//ask ws to create the session for client
			HttpSession session=request.getSession(true); //true b,cos we can reuse it
			session.setAttribute("user", email);
			session.setAttribute("genval", val);
			session.setAttribute("tries", tries);
			
			
			
			//forward the controller to guess.html
		RequestDispatcher rd=request.getRequestDispatcher("/Guess.html");
				rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("stratgame dopost() method");
	}

}
