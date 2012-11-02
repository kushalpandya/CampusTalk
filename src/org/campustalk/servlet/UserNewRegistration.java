package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.Settings;
import org.campustalk.entity.CampusTalkUsers;
import org.campustalk.sql.dbUser;
import org.campustalk.util.EmailHelper;
import org.campustalk.util.FieldValidator;
import org.campustalk.util.OtherUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet(description = "User Registration Servlet", urlPatterns = { "/User/Registration/New" })
public class UserNewRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserNewRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject jResponse = new JSONObject();
		dbUser objUser = new dbUser();

		try {
			boolean errorFlag = false;
			String responseMessage = "";
			String errorMsgSep = "";
			FieldValidator objValidation = new FieldValidator();
			String email, firstName, lastName, gender, pictureUrl, registerWith, password;
			jResponse.put("status", "fail");
			jResponse.put("message", "Something Wrong!, Please Try Again");
			email = request.getParameter("email");
			if (!objValidation.isEmail(email)) {
				errorFlag = true;
				responseMessage += errorMsgSep + "Invalid Email";
				errorMsgSep = ",";
			}
			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName");
			if (request.getParameter("gender") != null) {
				gender = request.getParameter("gender").toUpperCase();
				if (!(gender.equals("MALE") || gender.equals("FEMALE"))) {
					errorFlag = true;
					responseMessage += errorMsgSep + "Invalid Gender";
					errorMsgSep = ",";
				}
			}
			pictureUrl = request.getParameter("pictureUrl");
			registerWith = request.getParameter("registerWith");
			password = request.getParameter("password");
			if (!objValidation.isPassword(password)) {
				errorFlag = true;
				responseMessage += errorMsgSep + "Invalid Password";
				errorMsgSep = ",";
			}

			if (errorFlag) {

			} else {
				CampusTalkUsers ctUser = new CampusTalkUsers();
				ctUser = objUser.getUserDetailFromEmail(request
						.getParameter("email"));

				if (ctUser.getId() == 0) {
					responseMessage = "Invalid Email, Please Contact System Administrator";

				} else {
					System.out.println(ctUser.getStatus());
					if (ctUser.getStatus().equals("N")) {
						ctUser.setFirstName(request.getParameter("firstName"));
						ctUser.setLastName(request.getParameter("lastName"));
						ctUser.setGender(request.getParameter("gender"));
						ctUser.setPictureUrl(request.getParameter("pictureUrl"));
						ctUser.setRegisterWith(request
								.getParameter("registerWith"));
						ctUser.setAuthString(OtherUtil.getRandomeString());
						ctUser.setPassword(request.getParameter("password"));
						objUser.registerNewuserDetail(ctUser);
						jResponse.put("status", "success");
						// send Email
						
						EmailHelper.registrationVerifyEmail(ctUser);
						responseMessage = "Please Check Email To Verify";

					} else {
						if (ctUser.getStatus().equals("R")) {
							responseMessage = "A/C is already Created but Not Verified, Please Check your email";
						} else {
							responseMessage = "A/C is already Created, Please Login";
						}
					}
				}
			}
			jResponse.put("message", responseMessage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);
	}
}