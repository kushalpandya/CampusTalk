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
import org.campustalk.sql.dbUser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class sendNewMessage
 */
@WebServlet("/Message/New")
public class MessagesSendNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagesSendNew() {
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
		
		String toUsers,msgDetail ;
		String status="fail";
		String errorMsg= "Something Wrong !! Please Try Again"; 
		try{
			toUsers=request.getParameter("tousers").trim();
			msgDetail= request.getParameter("message").trim();
			if(session.getAttribute("UserId")==null){
				errorMsg= "Session expired, Please Reload Page";
			}else if(toUsers=="" || toUsers.indexOf("@") < 0 ){
				errorMsg="Invalid To Users List,Please Check data ";
				
			}else if(msgDetail.length() < 2 ) {
				errorMsg="Invalid Message, Min Message length is 2";
				
			}else{
				dbUser objDbUser = new dbUser();
				String useridList =objDbUser.getUserIdListFromEmailList(toUsers);
				dbMessage dnObjMessage = new dbMessage();
				if(dnObjMessage.sendMessege((int)session.getAttribute("UserId"), useridList, msgDetail)){
					status="success";
					errorMsg="Message Sent ";
				}else {
					errorMsg= "Error in sending Message, Pleas try after some time ";
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			jResponse.put("status", status);
			jResponse.put("message", errorMsg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);
		
		
	}

}
