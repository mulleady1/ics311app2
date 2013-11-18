package ics311km2;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphGetTest {

	@Test
	public void testGetArcNoData() {
        Graph g = new Graph();
        Vertex u = new Vertex("a");
        Vertex v = new Vertex("b");
        Arc a = g.insertArc(u, v);
        assertEquals(a, g.getArc(u, v));
	}
	
	@Test
	public void testGetArcWithData() {
        Graph g = new Graph();
        Vertex u = new Vertex("a");
        Vertex v = new Vertex("b");
        String data = "asdf";
        Arc a = g.insertArc(u, v, data);
        assertEquals(a, g.getArc(u, v));
	}
	
	@Test
	public void testGetVertexNoData() {
		Graph g = new Graph();
        String key = "key";
        Vertex v = g.insertVertex(key);
        assertEquals(v, g.getVertex(key));
	}
	
	@Test
	public void testGetVertexWithData() {
        Graph g = new Graph();
        String key = "key", data = "data";
        Vertex v = g.insertVertex(key, data);
        assertEquals(v, g.getVertex(key));
	}

}
