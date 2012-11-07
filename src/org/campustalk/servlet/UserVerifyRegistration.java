package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.sql.dbUser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class UserVerifyRegistration
 */
@WebServlet("/User/Registration/Verify")
public class UserVerifyRegistration extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserVerifyRegistration()
	{
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject jResponse = new JSONObject();
		dbUser objUser = new dbUser();
		String email, aString;
		try
		{
			jResponse.put("status", "fail");
			jResponse.put("message",
					"Oops! something went wrong. Please try again.");

			String responseMsg = "";
			if (request.getParameter("e") == null
					|| request.getParameter("q") == null)
			{
				responseMsg = "Invalid Parameters";
			}
			else
			{
				email = request.getParameter("e");
				aString = request.getParameter("q");
				int qResp = objUser.verifyNewUser(email, aString);
				switch (qResp)
				{
					case 0:
						jResponse.put("status", "success");
						responseMsg = "Email Verified, Please Login using your username and password.";
						break;
					case 1:
						// User Already Verified,Please Login
						responseMsg = "Email Already Verified, Please Login using your username and password.";
						break;
					case 2:
						// Invalid Url
						responseMsg = "Invalid Verification URL, Please check URL before sign-up.";
						break;
					case 3:
						responseMsg = "Verification URL Expired! Please Login to get verification mail.";
						break;
					default:
						responseMsg = "Oops! something went wrong. Please try again.";
				}
				jResponse.put("message", responseMsg);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);
	}

}
