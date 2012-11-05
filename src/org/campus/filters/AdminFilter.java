package org.campus.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campustalk.Settings;
import org.campustalk.entity.CampusTalkUserRoles;
import org.campustalk.entity.CampusTalkUsers;
import org.campustalk.sql.dbBranch;
import org.campustalk.sql.dbUser;
import org.campustalk.sql.dbUserRole;
import org.json.JSONException;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/controlpanel.jsp")
public class AdminFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig config;

	/**
	 * Default constructor.
	 */
	public AdminFilter() {
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
		HttpSession session = ((HttpServletRequest) request).getSession();
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

			dbUserRole objDbUserRole = new dbUserRole();

			CampusTalkUserRoles objUserRole = new CampusTalkUserRoles();
			objUserRole.setUserid(objUser.getId());
			objDbUserRole.getRoleById(objUserRole);

			if (!objUserRole.role.getName().equalsIgnoreCase("admin")) {
				hres.sendRedirect(Settings.APPURL);
			} else {
				request.setAttribute("User", objUser);

				try {
					request.setAttribute("userJSon", objUser.toJSONObject()
							.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
