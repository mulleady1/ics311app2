package ics311km2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testOrigin() {
		Graph g = new Graph();
		Vertex a = g.insertVertex("a");
		Vertex b = g.insertVertex("b");
		Arc arc = g.insertArc(a, b);
		assertEquals(a, g.origin(arc));
	}
	
	@Test
	public void testDestination() {
		Graph g = new Graph();
		Vertex a = g.insertVertex("a");
		Vertex b = g.insertVertex("b");
		Arc arc = g.insertArc(a, b);
		assertEquals(b, g.destination(arc));
	}
	
	@Test
	public void testInDegree() {
		Graph g = new Graph();
		Vertex a = g.insertVertex("a");
		Vertex b = g.insertVertex("b");
		Vertex c = g.insertVertex("c");
		Vertex d = g.insertVertex("d");
		g.insertArc(b, a);
		g.insertArc(c, a);
		g.insertArc(d, a);
		assertEquals(3, g.inDegree(a));
	}

	@Test
	public void testOutDegree() {
		Graph g = new Graph();
		Vertex a = g.insertVertex("a");
		Vertex b = g.insertVertex("b");
		Vertex c = g.insertVertex("c");
		Vertex d = g.insertVertex("d");
		g.insertArc(a, b);
		g.insertArc(a, c);
		g.insertArc(a, d);
		assertEquals(3, g.outDegree(a));
	}

	@Test
	public void testInAdjacentVertices() {
		Graph g = new Graph();
		Vertex a = g.insertVertex("a");
		Vertex b = g.insertVertex("b");
		Vertex c = g.insertVertex("c");
		Vertex d = g.insertVertex("d");
		g.insertArc(b, a);
		g.insertArc(c, a);
		g.insertArc(d, a);
		List<Vertex> vertices = new LinkedList<Vertex>();
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);
		Iterator<Vertex> testVertices = vertices.iterator();
		Iterator<Vertex> i = g.inAdjacentVertices(a);
		while (i.hasNext()) {
			Vertex u = testVertices.next();
			Vertex v = i.next();
			assertEquals(u, v);
		}
	}

	@Test
	public void testOutAdjacentVertices() {
		Graph g = new Graph();
		Vertex a = g.insertVertex("a");
		Vertex b = g.insertVertex("b");
		Vertex c = g.insertVertex("c");
		Vertex d = g.insertVertex("d");
		g.insertArc(a, b);
		g.insertArc(a, c);
		g.insertArc(a, d);
		List<Vertex> vertices = new LinkedList<Vertex>();
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);
		Iterator<Vertex> testVertices = vertices.iterator();
		Iterator<Vertex> i = g.outAdjacentVertices(a);
		while (i.hasNext()) {
			Vertex u = testVertices.next();
			Vertex v = i.next();
			assertEquals(u, v);
		}
	}
	
	@Test
	public void testRemoveVertex() {
		Graph g = new Graph();
		String key = "key", data = "a";
		Vertex u = g.insertVertex(key, data);
		Object retval = g.removeVertex(u);
		assertEquals(retval, data);
	}
	
	@Test
	public void testRemoveVertexRemovesOutIncidentEdges() {
		Graph g = new Graph();
		Vertex u = g.insertVertex("u", "u");
		Vertex v = g.insertVertex("v", "v");
		g.insertArc(u, v);
		g.removeVertex(v);
		assertEquals(u.outDegree(), 0);
	}

	@Test
	public void testRemoveArc() {
		Graph g = new Graph();
		Vertex u = g.insertVertex("a");
		Vertex v = g.insertVertex("b");
		String data = "a";
		Arc a = g.insertArc(u, v, data);
		Object retval = g.removeArc(a);
		assertEquals(retval, data);
	}
	
	@Test
	public void testReverseDirection() {
		Graph g = new Graph();
		Vertex u = g.insertVertex("u");
		Vertex v = g.insertVertex("v");
		Arc a = g.insertArc(u, v, "a");
		g.reverseDirection(a);
		assertEquals(g.origin(a).getData(), v.getData());
	}
}
