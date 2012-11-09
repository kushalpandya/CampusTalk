package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.entity.CampusTalkBranch;
import org.campustalk.sql.dbBranch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateBranch
 */
@WebServlet("/CreateBranch")
public class CreateBranch extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateBranch()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		JSONObject resp = new JSONObject();
		Exception err = null;
		dbBranch objdbBranch = new dbBranch();

		String status = "fail";
		try
		{
			String req_type = request.getParameter("type");
			System.out.println(req_type);
			if (req_type.equalsIgnoreCase("SaveData")) // Request for Save Branch Data
			{
				String branchname = request.getParameter("branchName");
				int duration = Integer.parseInt(request
						.getParameter("duration"));
				System.out.println(branchname + "  " + duration);
				if (objdbBranch.AddBranch(branchname, duration))
					status = "success";
				else
					status = "fail";
			}

			else if (req_type.equalsIgnoreCase("GetData")) // Request for All Roles Data
			{
				System.out.println(request.getParameter("type"));
				/*
				 * ResultSet rs=objdbBranch.getBranchData();
				 * 
				 * 
				 * JSONArray branch_arr = new JSONArray(); JSONObject temp;
				 * 
				 * while(rs.next()) { temp = new JSONObject();
				 * 
				 * temp.put("branchid", rs.getInt("branchid"));
				 * temp.put("branchname", rs.getString("name"));
				 * temp.put("duration", rs.getInt("duration"));
				 * 
				 * branch_arr.put(temp); }
				 * 
				 * resp.put("Branch", branch_arr); status="success";
				 */

				//				dbBranch objdbBranch = new dbBranch();
				CampusTalkBranch ctBranch[] = objdbBranch.getBranchData();
				JSONArray branch_arr = new JSONArray();
				JSONObject temp;

				for (int i = 0; i < ctBranch.length; i++)
				{
					temp = new JSONObject();
					temp = ctBranch[i].toJSONObject();
					//branch_arr.put(ctBranch[i].toJSONObject());					
					branch_arr.put(temp);
				}

				resp.put("Branch", branch_arr);
				status = "success";
			}

			else if (req_type.equalsIgnoreCase("EditData")) // Request for Edit Data
			{
				int branchid = Integer.parseInt(request
						.getParameter("branchid"));
				String branchname = request.getParameter("name");
				int duration = Integer.parseInt(request
						.getParameter("duration"));
				if (objdbBranch.EditBranch(branchid, branchname, duration))
					status = "success";
				else
					status = "fail";
			}
			else if (req_type.equalsIgnoreCase("DeleteData")) // Request for Delete Data
			{

				int branchid = Integer.parseInt(request
						.getParameter("branchid"));
				System.out.println(branchid);
				objdbBranch.DeleteBranch(branchid);
				status = "success";
			}
			else
			{
				status = "fail";
			}

		}
		catch (Exception e)
		{
			status = "error";
			err = e;
			e.printStackTrace();
		}
		try
		{
			resp.put("status", status);
			resp.put("err", err);
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(resp);

	}

}
