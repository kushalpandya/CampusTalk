package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.sql.dbGroupMember;
import org.json.JSONException;
import org.json.JSONObject;

//import org.campustalk.entity.CampusTalkGroup;

/**
 * Servlet implementation class CreateGroupMember
 */
@WebServlet("/CreateGroupMember")
public class CreateGroupMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGroupMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject jResponse=new JSONObject();
		dbGroupMember objdbGroupMember = new dbGroupMember();
		String status = "fail";
		try {
			String req_type = request.getParameter("type");

			if (req_type.equalsIgnoreCase("SaveData")) // Request for Save
														// GroupMember Data
			{
				int gid = Integer.parseInt(request.getParameter("name"));
				String email = request.getParameter("email");
				String position = request.getParameter("position");
				if (objdbGroupMember.AddGroupMember(gid, email, position))
					status = "success";
				else
					status = "fail";
			}

			else if (req_type.equalsIgnoreCase("GetData")) // Request for All
															// GroupMember Data
			{
				int id=  Integer.parseInt(request.getParameter("groupid"));
				System.out.println(id);
				dbGroupMember objGroupMember= new dbGroupMember();
				try {
					jResponse.put("groupmember", objGroupMember.getGroupMemberData(id));
					status="success";
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					jResponse.put("status", status);
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			} else if (req_type.equalsIgnoreCase("EditData")) // Request for
																// Edit
			{
				int groupid = Integer.parseInt(request.getParameter("groupid"));
				int userid = Integer.parseInt(request.getParameter("userid"));
				String position = request.getParameter("position");
				String statu = request.getParameter("status");

				objdbGroupMember.EditGroupMember(groupid, userid, position,
						statu);
				status = "success";
			}

			else if (req_type.equalsIgnoreCase("DeleteData")) // Request for
																// Delete Data
			{
				int groupid = Integer.parseInt(request.getParameter("groupid"));
				int userid = Integer.parseInt(request.getParameter("userid"));
				System.out.print("groupid="+groupid+" userid= "+userid);
				objdbGroupMember.DeleteGroupMember(groupid, userid);
				status="success";
			} else {
				status = "fail";
			}

		} catch (Exception e) {
			status = "error";
			e.printStackTrace();
		}
		try {
			jResponse.put("status", status);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(jResponse);

	}

}
