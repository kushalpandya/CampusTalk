package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import org.campustalk.entity.CampusTalkUsers;
import org.campustalk.sql.dbUser;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet(description = "User Registration Servlet", urlPatterns = { "/User/Registration" })
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject resp = new JSONObject();
		dbUser objUser= new dbUser();
				
		try {
			int UserId=objUser.getUserIdFromEmail(request.getParameter("email"));
			if (UserId == 0){
				resp.put("status",false);
			}else{
				resp.put("status",true);
				CampusTalkUsers ctUser = new CampusTalkUsers();
				ctUser.setId(UserId);
				ctUser.setFirstName(request.getParameter("firstName"));
				ctUser.setLastName(request.getParameter("lastName"));
				ctUser.setGender(request.getParameter("gender"));
				ctUser.setPictureUrl(request.getParameter("pictureUrl"));
				ctUser.setRegisterWith(request.getParameter("registerWith"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		out.println(resp);
	}

}