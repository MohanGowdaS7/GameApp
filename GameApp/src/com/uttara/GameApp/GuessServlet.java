package com.uttara.GameApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessServlet
 */
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("inside guess no arg constructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("inside guess doget() mathod");
		
		/*
		 * 1.access existing session for client.
		 * if session doesn't exit, show some error message.
		 * 2.pull data - email,tries,genval from session.
		 * 3.access the user input guessnum from request.
		 * 4.if tries<0 show error message, gameover as attempts you are usedup!.
		 * 5.if tries>0 compare guess a number with genval, accordingly show a message, if unequal.
		 * 6.if equal, show success, srore in contaxt, in the leader board info about the winning client. 
		 */
		
		//HttpSession session=request.getSession(false);
		//String guessnum=request.getParameter("guessnum");
		
		//get guessing from request
		String guessnum = request.getParameter("guessnum");
		//int GuessNum = Integer.parseInt(GN);
		String GN = request.getParameter("guessnum");
		int GuessNum = Integer.parseInt(GN);
		
		PrintWriter pw=response.getWriter();
		if(guessnum==null)
		{
			pw.write("<html><body><h1>Error msg</h1><b>Guess The Proper ..<a href='Guess.html'>Integer Num</a></b></body></html>");
		}
		
		//PrintWriter pw=response.getWriter();
		HttpSession session=request.getSession(false);
		// ServletContext session1=getServletContext();
		if(session==null)
		{
			//we dont know who the user is show error msg	
			pw.write("<html><body><h1>Error msg</h1><b>Session are Deleted,,, start the New Game again ..<a href='StartGame.html'>StartGame</a></b></body></html>");
		}
		else
		{
			//pull the data from session
			String email = (String)session.getAttribute("user");
			int val = (Integer)session.getAttribute("genval");
			int tries = (Integer)session.getAttribute("tries");
			
			//get guessing from request
			//String GN = request.getParameter("guessnum");
			//int GuessNum = Integer.parseInt(GN);
			
			//check if user not cheating by verifying tries value
			if(tries==0)
			{
				session.removeAttribute("user");
				session.removeAttribute("tries");
				session.removeAttribute("genval");
				session.invalidate();
				
				pw.write("<html><body><h1>Enough Chance's</h1><h3>You Used all 3 Chance's ..<a href='Guess.html'>Continue the Game</a></h3></body></html>");
				//pw.write("<br/>");
				pw.write("<html><body><h3>Are else Start ..<a href='StartGame.html'>New Game</a></h3></body></html>");
				//pw.write("<br/>");
				pw.write("<html><body><h3>Are You looking for ..<a href='HomePage.html'>Homepage</a></h3></body></html>");
				//pw.write("<br/>");
				pw.write("<html><body><h3>click Here For ..<a href='LeaderBoard'>PointsTable</a></h3></body></html>");
				
			}
			else
			{
				//valid guess
				if(GuessNum==val)
				{
					/*user has guessed correctly, give him a points and added to leadrBoard.
					session.removeAttribute("user");
					session.removeAttribute("tries");
					session.removeAttribute("genval");
					session.invalidate();*/
					
					//update his score in LeaderBoard
					ServletContext sc=getServletContext();
					if(sc.getAttribute("PM")==null)
					{
						Map<String,Integer> PM=new HashMap<String,Integer>();
						PM.put(email, 5);
						sc.setAttribute("PM", PM);
						
					}
					else
					{
						Map<String,Integer> PM=(Map<String,Integer>)sc.getAttribute("PM");
						if(PM.get(email)!=null)
						{
							 val=PM.get(email);
							val=val+5;
							PM.put(email, val);
						}
						else
						{
							PM.put(email, 5);
						}
					}
					
					
					//pw.write("<html><body><h1>Success msg</h1><b>you are a magician, really great work ..<a href='Guess.html'>continue the Game</a></b></body></html><br/>");
					pw.write("<html><body><h1>Success msg</h1><h2>you are a magician, really great work ..</h2></body></html><br/>");
					//pw.write("<br/>");
					pw.write("<html><body><b>Are You looking for ..<a href='HomePage.html'>Homepage</a></b></body></html><br/>");
					pw.write("<br/>");
					pw.write("<html><body><b>click Here For ..<a href='LeaderBoard'>PointsTable</a></b></body></html>");
				}
				else
				{
					if(GuessNum>val)
					{
						pw.write("<html><body><h2>OOPS.. your Guess is = " +GuessNum+ " --Try in lower manner</h2><h3>Remianing only "+tries+" Tries ..<a href='Guess.html'>Back</a></h3></body></html>");
						
					}
					else
					{
						pw.write("<html><body><h2>OOPS.. your Guess is = " +GuessNum+ " -- Try in higher manner</h2><h3>Remianing only "+tries+" Tries ..<a href='Guess.html'>Back</a></h3></body></html>");
					}
					tries--;
					session.setAttribute("tries", tries);
					
				
				}
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("inside guess dopost() method");
	}

}
