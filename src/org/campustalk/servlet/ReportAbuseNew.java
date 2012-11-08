package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.entity.CampusTalkReportAbuse;
import org.campustalk.sql.dbReportAbuse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ReportAbuseNew
 */
@WebServlet("/ReportAbuse/New")
public class ReportAbuseNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportAbuseNew() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reportDetail;
		int postid;
		reportDetail = request.getParameter("detail").trim();
		postid = Integer.parseInt(request.getParameter("postid"));

		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		JSONObject jResponse = new JSONObject();

		String status = "fail";
		String errormsg = "Something Wrong !!";

		if (session.getAttribute("UserId") == null) {
			errormsg = "Session expired, Please Reload Page";
		} else if (reportDetail.length() < 3) {
			errormsg = "Report Abuse Detail Min length is 3";
		} else {
			dbReportAbuse objDbRptAbuse = new dbReportAbuse();
			CampusTalkReportAbuse objRptAbuse = new CampusTalkReportAbuse();
			objRptAbuse.setPostId(postid);
			objRptAbuse.setDetail(reportDetail);
			objRptAbuse.setUserId((int) session.getAttribute("UserId"));
			if (objDbRptAbuse.insertReportAbuse(objRptAbuse)) {
				status = "success";
				errormsg = "Post Reported.";
			} else {
				errormsg = "Post Already Reported as Abused";
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
