package pl.sqer.authentication;

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

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		try {

			// check whether session variable is set
			final HttpServletRequest req = (HttpServletRequest) request;
			final HttpServletResponse res = (HttpServletResponse) response;
			final HttpSession ses = req.getSession(false);
			// allow user to proccede if url is login.xhtml or user logged in or
			// user is accessing any page in //public folder
			final String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/template/") >= 0
					|| reqURI.contains("javax.faces.resource")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/login.xhtml"); //
				// Anonymous
				// user.
				// Redirect
				// to
				// login
				// page
			}
		} catch (final Throwable t) {
			System.out.println(t.getMessage());
		}
	} // doFilter

	@Override
	public void destroy() {

	}
}