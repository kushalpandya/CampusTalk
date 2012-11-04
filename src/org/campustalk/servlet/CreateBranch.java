package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.sql.dbBranch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateBranch
 */
@WebServlet("/CreateBranch")
public class CreateBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBranch() {
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
		PrintWriter out=response.getWriter();
		JSONObject resp = new JSONObject();
		
		dbBranch objdbBranch=new dbBranch();
		
		String status="fail";		
		try
		{
			String req_type = request.getParameter("type");
			
			if (req_type.equalsIgnoreCase("SaveData")) // Request for Save Roles Data
			{
				String branchname = request.getParameter("name");
				int duration =Integer.parseInt(request.getParameter("duration"));
				objdbBranch.AddBranch(branchname, duration);
				status="success";
			}
			
			else if (req_type.equalsIgnoreCase("GetData")) // Request for All Roles Data
			{
				
				ResultSet rs=objdbBranch.getBranchData();
				
				
				JSONArray branch_arr = new JSONArray();
				JSONObject temp;
				
				while(rs.next())
				{
					temp = new JSONObject();
								
					temp.put("branchid", rs.getInt("branchid"));
					temp.put("name", rs.getString("name"));
					temp.put("duration", rs.getInt("duration"));				
					branch_arr.put(temp);					
				}
				
				resp.put("Branch", branch_arr);
				status="success";			
			}
			
			else if (req_type.equalsIgnoreCase("EditData")) // Request for Edit Data
			{
				int branchid = Integer.parseInt(request.getParameter("branchid"));
				String branchname = request.getParameter("name");
				int duration = Integer.parseInt(request.getParameter("duration"));
				objdbBranch.EditBranch(branchid,branchname,duration);
				status="success";
			}
			
			else if (req_type.equalsIgnoreCase("DeleteData")) // Request for Delete Data
			{
				int branchid = Integer.parseInt(request.getParameter("branchid"));
				objdbBranch.DeleteBranch(branchid);
				status="success";
			}
			else
			{
				status="fail";
			}
			
		} catch (Exception e) {
			status = "error";
			e.printStackTrace();
		}	
		try {
			resp.put("status", status);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(resp);	
	}

	
	
	}
	

