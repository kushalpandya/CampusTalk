package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.sql.dbComment;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CommnetGetForPost
 */
@WebServlet("/Comment/Get")
public class CommnetGetForPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommnetGetForPost() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int skipRow,nRow;
		skipRow=0;
		nRow=10;
		int postid=Integer.parseInt(request.getParameter("postid"));
		String status="fail";
		String errorMsg="Something Wrong!!";
		
		JSONObject jResponse= new JSONObject();
		if(request.getParameter("skip") !=null){
			skipRow = Integer.parseInt(request.getParameter("skip"));
		}
		if(request.getParameter("row") !=null){
			nRow = Integer.parseInt(request.getParameter("row"));
		}
		HttpSession session = request.getSession(true);
		if(session.getAttribute("UserEmail") == null){
			errorMsg="Session expired, Please Reload page";
		}else{
			dbComment objDbComment= new dbComment();
			try {
				jResponse.put("comments", objDbComment.getCommentForPost(postid,  skipRow, nRow));
				status="success";
				errorMsg="Comment Loaded Sucessfully";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			jResponse.put("status", status);
			jResponse.put("message", errorMsg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);
		
	}

}
