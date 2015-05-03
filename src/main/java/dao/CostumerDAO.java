package dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import model.Costumer;
import model.Labels;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringLogger;

import scala.collection.Iterator;

@RequestScoped
public class CostumerDAO {
	private static CostumerDAO instance;
	
	private GraphDatabaseService graphDb;

	private CostumerDAO() {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("./neo4j/");
		registerShutdownHook(graphDb);
	}
	
	public static CostumerDAO getInstance(){
        if(instance == null) {
             instance = new CostumerDAO();
        }
        return instance;
	}
	
	public void add(Costumer costumer) {
		Transaction tx = graphDb.beginTx();
		System.out.println("---" + costumer.getPersonId() + " - " + costumer.getName());
		Node node = graphDb.createNode(Labels.COSTUMER);
		node.setProperty("personId", costumer.getPersonId());
		node.setProperty("email", costumer.getEmail());
		node.setProperty("name", costumer.getName());

		tx.success();
		tx.close();
	}
	
	public List<Costumer> find(Long personId) {
		Transaction tx = graphDb.beginTx();
		ExecutionEngine execEngine = new ExecutionEngine(graphDb, StringLogger.DEV_NULL);
		ExecutionResult execResult = execEngine.execute(
				"MATCH (costumer:COSTUMER{ personId:" + personId + " }) return costumer");
		
		Iterator<Object> columnAs = execResult.columnAs("costumer");
		ArrayList<Node> nodes = new ArrayList<Node>();
		while(columnAs.hasNext()) {
			nodes.add((Node) columnAs.next());
		}		
		
		List<Costumer> costumers = toCostumer(nodes);
		
		tx.success();
		tx.close();
		
		return costumers;
	}
	
	private List<Costumer> toCostumer(List<Node> nodes) {
		ArrayList<Costumer> costumers = new ArrayList<Costumer>();
		
		for (Node node : nodes) {
			Costumer c = new Costumer();
			c.setPersonId((Long)node.getProperty("personId"));
			c.setName((String) node.getProperty("name"));
			c.setEmail((String) node.getProperty("email"));
			costumers.add(c);
		}
		
		return costumers;
	}

	private void registerShutdownHook(final GraphDatabaseService graphDb) {
		 Runtime.getRuntime().addShutdownHook( new Thread()
		    {
		        @Override
		        public void run()
		        {
		            graphDb.shutdown();
		        }
		    } );		
	}
}
