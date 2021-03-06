package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.sql.dbUser;
import org.json.JSONArray;

/**
 * Servlet implementation class UserSeachForMsg
 */
@WebServlet("/User/ACUserListMsg")
public class UserSearchForMsg extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchForMsg()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		JSONArray jResponse = new JSONArray();
		if (session.getAttribute("UserId") != null)
		{
			dbUser objDbUser = new dbUser();
			jResponse = objDbUser.searchUserMsgAC(request.getParameter("query"));
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		JSONArray jResponse = new JSONArray();
		if (session.getAttribute("UserId") != null)
		{
			dbUser objDbUser = new dbUser();
			jResponse = objDbUser.searchUserMsgAC(request.getParameter("q"));
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);
	}
}
