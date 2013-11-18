package ics311km2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Graph {

	// vertices maps a data object to a vertex object.
    private Map<Object, Vertex> vertices;
    
    // arcs maps a list of 2 vertices to an arc object.
    private Map<List<Vertex>, Arc> arcs;
    
    // vertexAnnotations maps a vertex object to a map that, in turn, maps a key to a value.
    private Map<Vertex, Map<Object, Object>> vertexAnnotations;
    
    // arcAnnotations maps an arc object to a map that, in turn, maps a key to a value.
    private Map<Arc, Map<Object, Object>> arcAnnotations;

    public Graph() {
        this.vertices = new HashMap<Object, Vertex>();
        this.arcs = new HashMap<List<Vertex>, Arc>();
    }

    public int numVertices() { return this.vertices.size(); }
    public int numArcs() { return this.arcs.size(); }
    public Iterator<Vertex> vertices() { return this.vertices.values().iterator(); }
    public Iterator<Arc> arcs() { return this.arcs.values().iterator(); }
    
    public Vertex getVertex(Object key) { return this.vertices.get(key); }
    public Arc getArc(Object source, Object target) {
    	List<Vertex> vertices = new ArrayList<Vertex>();
    	vertices.add((Vertex)source);
    	vertices.add((Vertex)target);
    	return this.arcs.get(vertices); 
    }
    public Object getVertexData(Vertex v) { return v.getData(); }
    public Object getArcData(Arc a) { return a.getData(); }
    public int inDegree(Vertex v) { return v.inDegree(); }
    public int outDegree(Vertex v) { return v.outDegree(); }
    public Iterator<Vertex> inAdjacentVertices(Vertex v) { return v.inAdjacentVertices(); }
    public Iterator<Vertex> outAdjacentVertices(Vertex v) { return v.outAdjacentVertices(); }
    public Vertex origin(Arc a) { return a.getOrigin(); }
    public Vertex destination(Arc a) { return a.getDestination(); }
    
    public Vertex insertVertex(Object key) { 
        Vertex v = new Vertex();
        this.vertices.put(key, v);
        return v;
    }

    public Vertex insertVertex(Object key, Object data) {
        Vertex v = this.insertVertex(key);
        v.setData(data);
        return v;
    }

    public Arc insertArc(Vertex u, Vertex v) { 
        Arc a = new Arc();
        List<Vertex> vertices = new ArrayList<Vertex>();
    	vertices.add(u);
    	vertices.add(v);
    	this.arcs.put(vertices, a);
        // Tell arc what its origin and destination vertices are.
        a.setOrigin(u);
        a.setDestination(v);
        // Add to in/out adjacent vertices.
        u.addOutAdjacentVertex(v);
        v.addInAdjacentVertex(u);
        return a;
    }
    public Arc insertArc(Vertex u, Vertex v, Object data) { 
    	Arc a = this.insertArc(u, v);
    	a.setData(data);
        return a;
    }
    public void setVertexData(Vertex v, Object data) { v.setData(data); }
    public void setArcData(Arc a, Object data) { a.setData(data); }
    public Object removeVertex(Vertex v) {
    	// Iterate over the set of vertices in the graph.
    	Iterator<Vertex> vertices = this.vertices();
    	while (vertices.hasNext()) {
    		Vertex u = vertices.next();
    		// For each vertex u, check u's inAdjacentVertices for v. If found, remove v.
    		Iterator<Vertex> inAdjVertices = u.inAdjacentVertices();
    		while (inAdjVertices.hasNext()) {
    			Vertex w = inAdjVertices.next();
    			if (w.equals(v)) {
    				inAdjVertices.remove();
    			}
    		}
    		// For each vertex u, check u's outAdjacentVertices for v. If found, remove v.
    		Iterator<Vertex> outAdjVertices = u.outAdjacentVertices();
    		while (outAdjVertices.hasNext()) {
    			Vertex w = outAdjVertices.next();
    			if (w.equals(v)) {
    				outAdjVertices.remove();
    			}
    		}
    		// Found v in the set.
    		if (u.equals(v)) {
    			// Remove all arcs incident to v.
    			this.removeIncidentEdges(v);
    			// Remove v from the set of vertices in the graph.
    			vertices.remove();
    			// Return client data.
    			return v.getData();
    		}
    	}
    	
    	// If we made it this far, v was not in the graph.
    	return null;
    }
    
    /**
     * Helper method for remove a vertex: Given a vertex u, removes all arcs coming into
     * or going out of u.
     * 
     * @param u the vertex of interest.
     */
    private void removeIncidentEdges(Vertex v) {
    	Iterator<Vertex> inAdjVertices = v.inAdjacentVertices();
    	while (inAdjVertices.hasNext()) {
    		Vertex u = inAdjVertices.next();
    		Arc a = this.getArc(u, v);
    		this.removeArc(a);
    		inAdjVertices.remove();
    		u.removeOutAdjacentVertex(v);
    	}
    	Iterator<Vertex> outAdjVertices = v.outAdjacentVertices();
    	while (outAdjVertices.hasNext()) {
    		Vertex u = outAdjVertices.next();
    		Arc a = this.getArc(v, u);
    		this.removeArc(a);
    		outAdjVertices.remove();
    		u.removeInAdjacentVertex(v);
    	}
    }
    
    public Object removeArc(Arc a) {
    	// Iterate over the set of arcs in the graph.
    	Iterator<Arc> arcs = this.arcs();
    	while (arcs.hasNext()) {
    		Arc arc = arcs.next();
    		if (a.equals(arc)) {
    			// Remove the arc of interest.
    			arcs.remove();
    			// Return client data.
    			return a.getData();
    		}
    	}
    	// If we made it this far, a was not in the graph.
    	return null; 
    }
    public void reverseDirection(Arc a) {
    	Vertex u = a.getDestination();
    	Vertex v = a.getOrigin();
    	Object o = this.removeArc(a);
    	this.insertArc(v, u, o);
    }

    public void setAnnotation(Vertex v, Object k, Object o) { }
    public void setAnnotation(Arc a, Object k, Object o) { } 
    public Object getAnnotation(Vertex v, Object k) { return null; } 
    public Object getAnnotation(Arc a, Object k) { return null; } 
    public Object removeAnnotation(Vertex v, Object k) { return null; }
    public Object removeAnnotation(Arc a, Object k) { return null; }
    public void clearAnnotations(Object k) { }

}
