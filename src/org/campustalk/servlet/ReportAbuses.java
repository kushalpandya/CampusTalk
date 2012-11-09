package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.sql.dbReportAbuse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ReportAbuses
 */
@WebServlet("/ReportAbuses")
public class ReportAbuses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportAbuses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		JSONObject resp = new JSONObject();
		Exception err=null;
		dbReportAbuse  objdbReport=new dbReportAbuse();
		System.out.print("helo");
		String status="fail";		
		try
		{
			String req_type = request.getParameter("type");
		//	System.out.println(req_type);
			
			
			if (req_type.equalsIgnoreCase("GetData")) // Request for All Roles Data
			{
				System.out.println(request.getParameter("type"));
				JSONArray jss=objdbReport.getReportAbusesData();
				
				
				resp.put("Report", jss);
				status="success";			
			}
			else if (req_type.equalsIgnoreCase("ViewBlock")) // Request for All Roles Data
			{
				//System.out.println(request.getParameter("type"));
				JSONArray jss=objdbReport.getBlockUser();
				
				
				resp.put("ViewBlockUser", jss);
				status="success";			
			}
			else if(req_type.equalsIgnoreCase("BlockPost"))
			{
				int id=Integer.parseInt(request.getParameter("pid"));
				objdbReport.BlockPost(id);
				status="success";
			}
			else if(req_type.equalsIgnoreCase("ViewUser"))
			{
				int id=Integer.parseInt(request.getParameter("pid"));
				//System.out.println(request.getParameter("type"));
				JSONArray jss=objdbReport.getViewUser(id);
				
				
				resp.put("UserReport", jss);
				status="success";			

			}

			else if(req_type.equalsIgnoreCase("BlockUser"))
			{
				int id=Integer.parseInt(request.getParameter("id"));
				int pid=Integer.parseInt(request.getParameter("pid"));
				objdbReport.BlockUser(id,pid);
				status="success";
			}
			else if(req_type.equalsIgnoreCase("UnblockUser"))
			{
				int id=Integer.parseInt(request.getParameter("id"));
				objdbReport.UnblockUser(id);
				System.out.print("Unblock "+id);
				status="success";
			}
			else if(req_type.equalsIgnoreCase("Check"))
			{
				//int id=Integer.parseInt(request.getParameter("id"));
				int pid=Integer.parseInt(request.getParameter("pid"));
				String rstatus=request.getParameter("rstatus");
				objdbReport.Check( pid, rstatus);
				status="success";
			}
			
			else
			{
				status="fail";
			}
		System.out.println(status);	
		} catch (Exception e) {
			status = "error";
			err=e;
			e.printStackTrace();
		}	
		try {
			resp.put("status", status);
			resp.put("err", err);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(resp);	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("hi");
	}

}
