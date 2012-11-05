package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.sql.dbRoles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateRole
 */
@WebServlet("/CreateRole")
public class CreateRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRole() {
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
		
		dbRoles objdbRoles=new dbRoles();
		
		String status="fail";		
		try
		{
			String req_type = request.getParameter("type");
			
			if (req_type.equalsIgnoreCase("SaveData")) // Request for Save Roles Data
			{
				String rolesname = request.getParameter("name");
				
				objdbRoles.AddRoles(rolesname);
				status="success";
			}
			
			else if (req_type.equalsIgnoreCase("GetData")) // Request for All Roles Data
			{
				
				ResultSet rs=objdbRoles.getRolesData();
				
				
				JSONArray roles_arr = new JSONArray();
				JSONObject temp;
				
				while(rs.next())
				{
					temp = new JSONObject();
								
					temp.put("rolesid", rs.getInt("rolesid"));
					temp.put("name", rs.getString("name"));
										
					roles_arr.put(temp);					
				}
				
				resp.put("Roles", roles_arr);
				status="success";			
			}
			
			else if (req_type.equalsIgnoreCase("EditData")) // Request for Edit Data
			{
				int rolesid = Integer.parseInt(request.getParameter("rolesid"));
				String rolesname = request.getParameter("name");
				
				objdbRoles.EditRoles(rolesid,rolesname);
				status="success";
			}
			
			else if (req_type.equalsIgnoreCase("DeleteData")) // Request for Delete Data
			{
				int rolesid = Integer.parseInt(request.getParameter("rolesid"));
				objdbRoles.DeleteRoles(rolesid);
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
