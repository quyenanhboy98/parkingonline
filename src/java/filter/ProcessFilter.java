/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entity.EmployeeDTO;
import entity.UserDetailsDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yuu
 */
@WebFilter(filterName = "ProcessFilter", urlPatterns = {"/*"})
public class ProcessFilter implements Filter {

    private static final boolean debug = true;

    private static final String NOTFOUND = "404.html";
    private static final String ADDEMPLOYEE = "addemployee.jsp";
    private static final String ADDFEEDBACK = "addfeedback.jsp";
    private static final String ADDPARK = "addpark.jsp";
    private static final String ADDUSER = "adduser.jsp";
    private static final String BARCODE = "barcode.jsp";
    private static final String BILL = "bill.jsp";
    private static final String BOOKINGDETAIL = "bookingdetail.jsp";
    private static final String CHANGEPASS = "changepass.jsp";
    private static final String CHECKIN = "checkin.jsp";
    private static final String CHECKOUT = "checkout.jsp";
    private static final String FINDPARK = "findpark.jsp";
    private static final String INDEX = "index.jsp";
    private static final String LISTBILL = "listbill.jsp";
    private static final String LISTBOOKING = "listbooking.jsp";
    private static final String LISTEMPLOYEE = "listemployee.jsp";
    private static final String LISTFEEDBACK = "listfeedback.jsp";
    private static final String LISTPARK = "listpark.jsp";
    private static final String LISTSTAFF = "liststaff.jsp";
    private static final String LISTUSER = "listuser.jsp";
    private static final String LISTUSERBILL = "listuserbill.jsp";
    private static final String LISTUSERBOOKING = "listuserbooking.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String MAP = "map.jsp";
    private static final String PARKDETAIL = "parkdetail.jsp";
    private static final String PROFILEUSER = "profileuser.jsp";
    private static final String RECOVERYPASSWORD = "recoverypassword.jsp";
    private static final String USER = "user.jsp";
    private static final String USERDETAIL = "userdetails.jsp";
    private static final String USERREGISTER = "userregister.jsp";
    private static final String WORKINGTIME = "workingtime.jsp";
      
    private static final String AUTOCHECKBOOKINGLOCKED = "AutomationCheckBookingLockedPark";
    private static final String AUTCHECKEXPIREDBOOKING = "AutomationCheckExpiredBooking";
    
    private static final String FORGOTPASSSERVLET = "ForgotPasswordServlet";
    private static final String LOGINSERVLET = "LoginServlet";
    private static final String RECOVERPASSSERVLET = "RecoveryPasswordServlet";
    
    private static final int ROLEADMIN = 1;
    private static final int ROLEUSER = 2;
    private static final int ROLEMANAGER = 3;
    private static final int ROLESTAFF = 4;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private final List<String> guest;
    private final List<String> admin;
    private final List<String> user;
    private final List<String> manager;
    private final List<String> staff;
    private final List<String> general;

    public ProcessFilter() {
        general = new ArrayList<>();
        guest = new ArrayList<>();
        admin = new ArrayList<>();
        manager = new ArrayList<>();
        staff = new ArrayList<>();
        user = new ArrayList<>();

        general.add(PROFILEUSER);
        general.add(CHANGEPASS);
//        general.add(AUTOCHECKBOOKINGLOCKED);
//        general.add(AUTCHECKEXPIREDBOOKING);

        admin.add(INDEX);
        admin.add(ADDUSER);
        admin.add(LISTUSER);
        admin.add(USERDETAIL);
        admin.add(ADDPARK);
        admin.add(LISTPARK);
        admin.add(PARKDETAIL);
        admin.add(WORKINGTIME);
        admin.add(ADDEMPLOYEE);
        admin.add(LISTEMPLOYEE);
        admin.add(LISTBOOKING);
        admin.add(LISTBILL);
        admin.add(BILL);
        admin.add(LISTFEEDBACK);

        manager.add(INDEX);
        manager.add(ADDEMPLOYEE);
        manager.add(USERDETAIL);
        manager.add(LISTSTAFF);
        manager.add(PARKDETAIL);
        manager.add(WORKINGTIME);
        manager.add(CHECKIN);
        manager.add(CHECKOUT);
        manager.add(LISTBOOKING);
        manager.add(LISTBILL);
        manager.add(BILL);
        manager.add(LISTFEEDBACK);

        staff.add(INDEX);
        staff.add(CHECKIN);
        staff.add(CHECKOUT);
        staff.add(BILL);

        user.add(USER);
        user.add(MAP);
        user.add(BARCODE);
        user.add(FINDPARK);
        user.add(BOOKINGDETAIL);
        user.add(LISTUSERBOOKING);
        user.add(LISTUSERBILL);
        user.add(BILL);
        user.add(ADDFEEDBACK);

        guest.add(LOGIN);
        guest.add(USERREGISTER);
        guest.add(RECOVERYPASSWORD);
        guest.add(FORGOTPASSSERVLET);
        guest.add(LOGINSERVLET);
        guest.add(RECOVERPASSSERVLET);

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        String url = LOGIN;

        if (uri.matches(".*(css|jpg|jpeg|png|gif|js|map|ttf|woff|woff2|svg)")) {
            chain.doFilter(request, response);
            return;
        }

        try {

            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            if (!resource.isEmpty()) {

                
                if (resource.indexOf("jsessionid") > 0) {
                    resource = resource.substring(0, resource.indexOf("jsessionid") - 1);
                }

                url = resource.substring(0, 1).toUpperCase() + resource.substring(1) + "Servlet";

                if (resource.lastIndexOf(".html") > 0 || resource.lastIndexOf(".jsp") > 0) {
                    url = resource;
                }
            }

            Object obj = session.getAttribute("USERDTO");
            if (obj != null) {

                if (obj instanceof UserDetailsDTO) {
                    UserDetailsDTO userDTO = (UserDetailsDTO) session.getAttribute("USERDTO");
                    int roleID = userDTO.getRole();
                    if (roleID == ROLEADMIN) {
                        if (guest.contains(url)) {
                            url = INDEX;
                        } else if (!admin.contains(url) && !general.contains(url) && !url.contains("Servlet")) {
                            url = NOTFOUND;
                        }

                    } else if (roleID == ROLEUSER) {
                        if (guest.contains(url)) {
                            url = USER;
                        } else if (!user.contains(url) && !general.contains(url) && !url.contains("Servlet")) {
                            url = NOTFOUND;
                        }
                    }
                } else {
                    EmployeeDTO empDTO = (EmployeeDTO) session.getAttribute("USERDTO");
                    int roleID = empDTO.getRole();
                    if (roleID == ROLEMANAGER) {
                        if (guest.contains(url)) {
                            url = INDEX;
                        } else if (!manager.contains(url) && !general.contains(url) && !url.contains("Servlet")) {
                            url = NOTFOUND;
                        }
                    } else if (roleID == ROLESTAFF) {
                        if (guest.contains(url)) {
                            url = INDEX;
                        } else if (!staff.contains(url) && !general.contains(url) && !url.contains("Servlet")) {
                            url = NOTFOUND;
                        }
                    }
                }

            } else if (!guest.contains(url) && !url.contains("Servlet")) {
                url = NOTFOUND;
            }
        } catch (Exception e) {
            log("ProcessFilter + Exception " + e.getMessage());
        } finally {
            request.setCharacterEncoding("UTF-8");
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterDispatcher:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
