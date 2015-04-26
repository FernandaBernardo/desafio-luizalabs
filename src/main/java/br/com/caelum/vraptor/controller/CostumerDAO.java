package br.com.caelum.vraptor.controller;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


public class CostumerDAO extends MainDAO{
	GraphDatabaseService graphDb;
	Node firstNode;
	Node secondNode;
	Relationship relationship;

	public CostumerDAO() {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("./");
		registerShutdownHook(graphDb);
		
//		Transaction tx = graphDb.beginTx();
//		try {
//			firstNode = graphDb.createNode();
//			firstNode.setProperty( "message", "Hello, " );
//			secondNode = graphDb.createNode();
//			secondNode.setProperty( "message", "World!" );
//
//			relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
//			relationship.setProperty( "message", "brave Neo4j " );
//			
//			System.out.print( firstNode.getProperty( "message" ) );
//			System.out.print( relationship.getProperty( "message" ) );
//			System.out.print( secondNode.getProperty( "message" ) );
//			
//			tx.success();
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			tx.close();
//		}
	}
	
	public void add(Costumer costumer) {
		Transaction tx = graphDb.beginTx();
		Node node = graphDb.createNode();
		node.setProperty("costumer", costumer);
		tx.success();
		tx.close();
	}
	
	public Costumer find(Long id) {
		
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
