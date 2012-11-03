package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.entity.CampusTalkComment;
import org.campustalk.sql.dbComment;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CommentOnPost
 */
@WebServlet("/Comment/New")
public class CommentOnPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentOnPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String commentDetail;
		int postid;
		commentDetail= request.getParameter("commentdata").trim();
		postid= Integer.parseInt(request.getParameter("postid"));
		
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		JSONObject jResponse = new JSONObject();
				
		String status="fail";
		String errormsg= "Something Wrong !!";
		
		if(session.getAttribute("UserId")==null){
			errormsg= "Session expired, Please Reload Page";
		}else if(commentDetail.length() <3){
			errormsg="Min comment length is 3";
		}else{
			dbComment objDbComment = new dbComment();
			
			CampusTalkComment objComment= new CampusTalkComment();
			objComment.setPostId(postid);
			objComment.setDetail(commentDetail);
			objComment.setUserId((int)session.getAttribute("UserId"));
			if(objDbComment.createCommentOnPost(objComment)<1){
				errormsg="Comment insertion failed";
			}else{
				status="success";
				errormsg="Commented on post...";
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
