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
 * Servlet implementation class UserProfile
 */
@WebServlet("/User/Profile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		JSONObject jResponse = new JSONObject();
				
		String status="fail";
		String errormsg= "Something Wrong !!";
		
		if(session.getAttribute("UserId")==null){
			errormsg= "Session expired, Please Reload Page";
		}
		else{
			String type= request.getParameter("type");
			dbUser objUser= new dbUser();
			if(type.equalsIgnoreCase("email")){
				jResponse = objUser.UserProfileByEmail(request.getParameter("data"));
				status="success";
			}else if(type.equalsIgnoreCase("m")){
				if(objUser.MakeModerator(request.getParameter("data"))){
					status="success";
					errormsg="This user is now Moderator";
				}else{
					errormsg="Cant Make this user as Moderator";
				}
				
			}else{
				jResponse = objUser.UserProfileById(Integer.parseInt(request.getParameter("data")));
				status="success";
			}
			
			
		}
		try {
			jResponse.put("status", status);
			jResponse.put("message", errormsg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);
	}

}
