package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.entity.CampusTalkUsers;
import org.campustalk.sql.dbUser;
import org.campustalk.util.EmailHelper;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/Login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println(session.getAttribute("loggedin"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		JSONObject resp = new JSONObject();
		String status = "fail";
		String returnMsg = "Somethin went wrong! Please Try After some time";
		try {
			String req_type = request.getParameter("type");
			if (req_type.equalsIgnoreCase("login")) // Request of Login
			{
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				System.out.println(request.getParameter("email"));
				boolean rememberMe = request.getParameter("remember")
						.equalsIgnoreCase("true");

				dbUser objdbUser = new dbUser();
				boolean loginFlag = objdbUser.userLogin(email, password);
				CampusTalkUsers ctUser = objdbUser.objUser;
				// Get Model Function call
				if (ctUser != null) {
					if (loginFlag) {
						session.setAttribute("UserId", ctUser.getId());
						session.setAttribute("UserEmail", ctUser.getEmail());
						session.setAttribute("user", ctUser);
						System.out.println(session.getAttribute("loggedin"));
						int time = 60 * 60 * 24 * 30;
						Cookie d = new Cookie("CampusTalkLogedIn", "true");
						d.setMaxAge(time);
						response.addCookie(d);
						if (rememberMe) {
							Cookie c = new Cookie("CampusTalkEmail",
									ctUser.getEmail());
							c.setMaxAge(time);
							response.addCookie(c);
						}
						status = "success";
					} else {
						if (ctUser.getStatus().equals("N")) {
							returnMsg = "Email is not registered, Please Register";
						} else if (ctUser.getStatus().equals("V")) {
							returnMsg = "Email is not Verified, Please Check Your email inbox";
							EmailHelper.registrationVerifyEmail(ctUser); // Send
																			// registration
																			// verification
																			// mail
																			// again
						} else {
							returnMsg = "Invalid Password";
						}

					}
				} else {
					returnMsg = "Email does not exits in System";
				}

			} else if (req_type.equalsIgnoreCase("sessionlogin")) {
				String useremail = (String) session.getAttribute("UserEmail");
				if (useremail != null)
					status = "success";
			} else if (req_type.equalsIgnoreCase("logout")) // Request for
			// Logout.
			{
				session.invalidate();
				Cookie[] c = request.getCookies();
				if (c != null) {
					for (int i = 0; i < c.length; i++) {
						Cookie curr = c[i];
						String cnm = curr.getName();
						if (cnm.equalsIgnoreCase("CampusTalkLogedIn")) {
							curr.setMaxAge(0);
							response.addCookie(curr);
						}
					}
				}
				status = "success";
			}
		} catch (Exception e) {
			status = "error";
			e.printStackTrace();
		}

		try {
			resp.put("status", status);
			resp.put("message", returnMsg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(resp);
	}

}
