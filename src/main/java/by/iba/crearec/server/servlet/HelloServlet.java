package by.iba.crearec.server.servlet;

import by.iba.crearec.annotation.CrearecWebServlet;
import by.iba.crearec.dao.CustomerDao;
import by.iba.crearec.model.Customer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@CrearecWebServlet("/test")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = -835091317331676452L;

	@Inject
	CustomerDao customerDao;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html><head>");
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>Hello, World</title></head>");
			out.println("<body>");
			out.println("<h1>Hello, world!</h1>");  // says Hello
			// Echo client's request information
			List<Customer> all = customerDao.findAll();
			all.forEach(item -> {
				out.println("<p>" + item.getSsn() + " " + item.getCustomerName() + " " + item.getAddress() + "</p>");
			});
			out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
			// Generate a random number upon each request
			out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
			out.println("</body>");
			out.println("</html>");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}