package controller;

import java.util.List;

import javax.inject.Inject;

import org.neo4j.graphdb.Node;

import model.Costumer;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import dao.CostumerDAO;

@Controller
public class IndexController {

	private final Result result;

	/**
	 * @deprecated CDI eyes only
	 */
	protected IndexController() {
		this(null);
	}
	
	@Inject
	public IndexController(Result result) {
		this.result = result;
	}

	@Path("/")
	public void index() {
		result.include("variable", "VRaptor!");
	}
	
	@Get("/add")
	public void form() {
	}
	
	@Post("/add")
	public void add(Costumer costumer) {
		CostumerDAO dao = CostumerDAO.getInstance();
		dao.add(costumer);
	}
	
	@Get("/find/{personId}")
	public void find(Long personId) {
		CostumerDAO dao = CostumerDAO.getInstance();
		List<Costumer> nodes = dao.find(personId);
		result.include("nodes", nodes);
	}
}