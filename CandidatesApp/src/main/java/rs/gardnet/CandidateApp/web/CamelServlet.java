package rs.gardnet.CandidateApp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.postgresql.ds.PGSimpleDataSource;

/**
 * Servlet implementation class CamelServlet
 */
@WebServlet("/CamelServlet")
public class CamelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CamelServlet() throws Exception {
        
    	PGSimpleDataSource dataSource = new PGSimpleDataSource();
    	dataSource.setURL("jdbc:postgresql://localhost:5432/CandidatesDB");
    	dataSource.setUser("candidateApp");
    	dataSource.setPassword("Candidate0759!");
    	
    	SimpleRegistry registry = new SimpleRegistry();
    	registry.put("myDataSource", dataSource);
    	
    	CamelContext context = new DefaultCamelContext(registry);
    	
    	context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				
				from("direct:start")
				.to("jdbc:myDataSource")
				.marshal().csv()
				.to("file:D:\\?fileName=TEST.csv");
				
			}
		});
    	
    	context.start();
    	ProducerTemplate producerTemplate = context.createProducerTemplate();
    	producerTemplate.sendBody("direct:start", "SELECT * FROM candidates");
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
		doGet(request, response);
	}

}
