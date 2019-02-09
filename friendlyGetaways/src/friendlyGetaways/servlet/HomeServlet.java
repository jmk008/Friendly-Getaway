package friendlyGetaways.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friendlyGetaways.dao.MethodsImpl;
import friendlyGetaways.dto.Property;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside home servlet");
		MethodsImpl mi = new MethodsImpl();
		String des=request.getParameter("destination");
		String min_p=request.getParameter("min_price");
		String max_p=request.getParameter("max_price");
		String property=request.getParameter("property");
		String beds=request.getParameter("beds");
		String rating=request.getParameter("rating");
		String check_in=request.getParameter("check-in");
		String check_out=request.getParameter("check-out");
		String guest=request.getParameter("guest");
		System.out.print(guest+" "+beds);
		Property searchFilters = new Property();
		searchFilters.setCity(des);
		searchFilters.setMin_price(min_p);
		searchFilters.setMax_price(max_p);
		searchFilters.setProperty_type(property);
		searchFilters.setBedrooms(beds);
		//TODO 100->5
		searchFilters.setReview_scores_rating(rating);
		//request.getParameter("");
		List<Property> propList=mi.searchProperty(searchFilters);
		//doGet(request, response);
		
		
	}

}
