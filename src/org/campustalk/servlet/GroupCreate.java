package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.sql.dbGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/Group/Process")
public class GroupCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		JSONObject resp = new JSONObject();
		dbGroup objdbGroup=new dbGroup();
		Exception err=null;
		String status="fail";
		try
		{
			String req_type = request.getParameter("type");
			
			if (req_type.equalsIgnoreCase("SaveData")) // Request for Save Group Data
			{
				String groupname = request.getParameter("name");
				String description = request.getParameter("description");
				if(objdbGroup.AddGroup(groupname,description)){
					status="success";
				}
				
			}
			else if (req_type.equalsIgnoreCase("GetData")) // Request for All Group Data
			{
		
				JSONArray group_arr = objdbGroup.getGroupData();
				resp.put("group", group_arr);
				status="success";			
			}
			
			else if (req_type.equalsIgnoreCase("EditData")) // Request for Edit Data
			{
				int groupid = Integer.parseInt(request.getParameter("groupid"));
				String gname = request.getParameter("name");
				String description = request.getParameter("description");
				String statu = request.getParameter("status");
				String ch;
				if(Integer.parseInt(statu)==0)
					ch="D";
				else
					ch="V";
				if(objdbGroup.EditGroup(groupid,gname,description,ch))
					status="success";
			}
			
			else if (req_type.equalsIgnoreCase("DeleteData")) // Request for Delete Data
			{
				int id = Integer.parseInt(request.getParameter("groupid"));
				objdbGroup.DeleteGroup(id);
				status="success";
			}
			else
			{
				status="fail";
			}
			
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
}


