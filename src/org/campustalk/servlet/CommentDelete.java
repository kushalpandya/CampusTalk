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
 * Servlet implementation class CommentDelete
 */
@WebServlet("/Comment/Delete")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDelete() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int commentid;
		commentid = Integer.parseInt(request.getParameter("commentid"));

		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		JSONObject jResponse = new JSONObject();

		String status = "fail";
		String errormsg = "Something Wrong !!";

		if (session.getAttribute("UserId") == null) {
			errormsg = "Session expired, Please Reload Page";
		} else {
			dbComment objDbCommnet = new dbComment();
					
			
			if (objDbCommnet.deleteCommentOfPost(commentid,(int) session.getAttribute("UserId"))) {
				status = "success";
				errormsg = "Comment Deleted";
			} else {
				errormsg = "You can'n delete this comment";
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
