package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.entity.CampusTalkPost;
import org.campustalk.sql.dbPost;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class PostDelete
 */
@WebServlet("/Post/Delete")
public class PostDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int postid;
		postid = Integer.parseInt(request.getParameter("postid"));

		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		JSONObject jResponse = new JSONObject();

		String status = "fail";
		String errormsg = "Something Wrong !!";

		if (session.getAttribute("UserId") == null) {
			errormsg = "Session expired, Please Reload Page";
		} else {
			dbPost objDbPost= new dbPost();
			CampusTalkPost objPost =new CampusTalkPost();
			
			objPost.setPostid(postid);
			objPost.setUserid((int) session.getAttribute("UserId"));
			if (objDbPost.deletePost(objPost)) {
				status = "success";
				errormsg = "Post Deleted";
			} else {
				errormsg = "You can'n delete this post";
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
