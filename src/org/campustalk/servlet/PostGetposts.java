package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.sql.dbPost;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class PostGetposts
 */
@WebServlet("/Post/Get")
public class PostGetposts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostGetposts() {
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
		
		String status="fail";
		String errorMsg="Something Wrong!!";
		
		JSONObject jResponse= new JSONObject();
		if(request.getParameter("skip") !=null){
			skipRow = Integer.parseInt(request.getParameter("skip"));
		}
		if(request.getParameter("row") !=null){
			nRow = Integer.parseInt(request.getParameter("row"));
		}
		System.out.println(skipRow + " " +nRow);
		HttpSession session = request.getSession(true);
		if(session.getAttribute("UserEmail") == null){
			errorMsg="Session expired, Please Reload page";
		}else{
			String email=  (String) session.getAttribute("UserEmail");
			dbPost objDbPost= new dbPost();
			try {
				jResponse.put("posts", objDbPost.getPostForUser(email, skipRow, nRow));
				status="success";
				errorMsg="Post Loaded Sucessfully";
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
