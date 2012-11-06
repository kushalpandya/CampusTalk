package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.sql.dbMessage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class MessagedGetMessageForUser
 */
@WebServlet("/Messages/Get")
public class MessagedGetMessageForUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagedGetMessageForUser() {
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
		int toUser;
		toUser= Integer.parseInt(request.getParameter("userid"));
		if(session.getAttribute("UserId")==null){
			errormsg= "Session expired, Please Reload Page";
		}
		else{
			
			dbMessage dbObjMessage = new dbMessage();
			
			try {
				jResponse.put("messages", dbObjMessage.getAllMessageOfUser((int)session.getAttribute("UserId"),toUser));
				status="success";
				errormsg="User Messages Loaded Sucessfully";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
