package org.campustalk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.campustalk.sql.dbEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEvent() {
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
		
		
		PrintWriter out=response.getWriter();
		JSONObject resp = new JSONObject();
		
		
		
		dbEvent objdbEvent=new dbEvent();
		System.out.println(request.getParameter("type"));
		
		String status="fail";		
		try
		{
			String req_type = request.getParameter("type");
			
			if (req_type.equalsIgnoreCase("CEvent")) // Request for Save Roles Data
			{
				
				
				String eventDate = request.getParameter("eDate");
				String eventTime = request.getParameter("eTime");
				String eventDateTime = eventDate + " " + eventTime;
				System.out.print(eventDateTime);
				
				//String oldScheduledDate = "16-05-2011";
		        //DateFormat oldFormatter = new SimpleDateFormat("dd-MM-yyyy");
		        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
		        Date oldDate = (Date)formatter .parse(eventDateTime);
		       
		        System.out.println(formatter.format(oldDate));
		        
		        
				
				DateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
				Date oldTime = timeFormatter.parse(eventTime);
				//System.out.print(timeFormatter.format(oldTime));
				
				String eventName = request.getParameter("eName");
				
				String eventDesc = request.getParameter("eDesc");
				
				
				
				if(objdbEvent.AddEvent(oldDate, oldTime, eventName, eventDesc))
				{
					status="success";
				}
				
				//if(objdbEvent.AddEvent( description))
				
				//objdbRoles.AddRoles(rolesname);
				//status="success";
			}
			
			else if (req_type.equalsIgnoreCase("GetData")) // Request for All Roles Data
			{
				JSONArray event_arr = new JSONArray();
				
				String eDate = request.getParameter("eventDate");
				//System.out.println(eDate);
			    DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			    Date oldDate = formatter .parse(eDate);

			    event_arr = objdbEvent.getEventData(oldDate);
			    System.out.println(event_arr);
				
				
				//event_arr =objdbEvent.getEventData(eDate);
				
				resp.put("Event", event_arr);
				status="success";			
			}
			else if (req_type.equalsIgnoreCase("GetDetails")) // Request for All Roles Data
			{
				
				JSONArray event_arr = new JSONArray();
				// -- // event_arr =objdbEvent.getEventDetails(Integer.parseInt(request.getParameter("id")));
				
				resp.put("Event", event_arr);
				status="success";			
			}
			/*else if (req_type.equalsIgnoreCase("EditData")) // Request for Edit Data
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
			}*/
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
