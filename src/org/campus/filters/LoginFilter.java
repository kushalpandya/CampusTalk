package org.campus.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.Settings;
import org.campustalk.entity.CampusTalkBranch;
import org.campustalk.entity.CampusTalkUsers;
import org.campustalk.sql.dbBranch;
import org.campustalk.sql.dbUser;
import org.json.JSONException;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD,
		DispatcherType.INCLUDE, DispatcherType.ERROR }, urlPatterns = {
		"/home.jsp" })
public class LoginFilter implements Filter {
	private FilterConfig config;

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session = ((HttpServletRequest) request).getSession();
		ServletContext context = config.getServletContext();
		HttpServletResponse hres = (HttpServletResponse) response;
		String logged = (String) session.getAttribute("UserEmail");
		if (logged == null) {
			hres.sendRedirect(Settings.APPURL);

		} else {
			dbUser objDbUser = new dbUser();
			CampusTalkUsers objUser = objDbUser
					.getUserDetailFromEmail((String) session
							.getAttribute("UserEmail"));
			dbBranch objDbBranch = new dbBranch();

			CampusTalkBranch objBranch = objDbBranch.getBranchById(objUser
					.getBranchId());
			request.setAttribute("User", objUser);
			request.setAttribute("Branch", objBranch);

			try {
				request.setAttribute("userJSon", objUser.toJSONObject()
						.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
	}

}
