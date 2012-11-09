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
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateRole
 */
@WebServlet("/AccountSettings")
public class AccountSettings extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountSettings()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
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
		HttpSession session = request.getSession(true);

		String status = "Fail";
		int userId;

		JSONObject resp = new JSONObject();

		try
		{

			String req_type = request.getParameter("type");
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String gender = request.getParameter("gender");
			String bdate = request.getParameter("birthdate");
			String city = request.getParameter("city");

			dbUser objdbUser = new dbUser();

			System.out.println("fname : " + fname + " lname : " + lname
					+ " bdate : " + bdate + " gender : " + gender);
			userId = (int) session.getAttribute("UserId");

			if (req_type.equalsIgnoreCase("EditProfile")) // Request for Branch name
			{
				System.out.println(" Id= " + userId);

				Boolean RetVal = false;

				RetVal = objdbUser.EditUserProfile(userId, fname, lname, bdate,
						gender, city);
				if (RetVal == true)
				{
					status = "success";
				}
				else
				{
					status = "fail";
				}
			}

			else if (req_type.equalsIgnoreCase("DeactivateAccount"))
			{
				Boolean RetVal = false;
				System.out.println("Id= " + userId);
				RetVal = objdbUser.DeactivateAccount(userId);
				if (RetVal == true)
				{
					status = "success";
				}
				else
				{
					status = "fail";
				}
			}
			else if (req_type.equalsIgnoreCase("ChangePassword"))
			{
				String currpass = request.getParameter("currpassword");
				String newpass = request.getParameter("newpassword");
				int userids = userId;
				System.out.println("userid=++" + userids);
				System.out.println("inside c pass" + currpass + "newpas"
						+ newpass);
				dbUser objdbUserPassChange = new dbUser();
				if (objdbUserPassChange.changePassword(userids, currpass,
						newpass))
				{
					status = "success";
					System.out.println("success");

				}
				else
				{
					System.out.println("fail");
					status = "fail";
				}

			}

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			resp.put("status", status);
		}
		catch (JSONException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(resp);
	}

}
