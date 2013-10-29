package ics311km2;

import java.util.Iterator;
import java.util.Set;

public class DiGraph {

    private int numVertices;
    private int numArcs;
    private Set<Vertex> vertices;
    private Set<Arc> arcs;

    public int numVertices() { return this.numVertices; }
    public int numArcs() { return this.numArcs; }
    public Iterator vertices() { return this.vertices.iterator(); }
    public Iterator arcs() { return this.arcs.iterator(); }
    public Vertex getVertex(Object key) { return null; }
    public Vertex getArc(Object sources, Object target) { return null; }
    public Object getVertexData(Vertex v) { return null; }
    public Object getArcData(Arc a) { return null; }
    public int inDegree(Vertex v) { return 0; }
    public int outDegree(Vertex v) { return 0; }
    public Iterator inAdjacentVertices(Vertex v) { return null; }
    public Iterator outAdjacentVertices(Vertex v) { return null; }
    public Vertex origin(Arc a) { return null; }
    public Vertex destination(Arc a) { return null; }
    
    public Vertex insertVertex(Object key) { return null; }
    public Vertex insertVertex(Object key, Object data) { return null; }
    public Arc insertArc(Vertex u, Vertex v) { return null; }
    public Arc insertArc(Vertex u, Vertex v, Object data) { return null; }
    public void setVertexData(Vertex v, Object data) { }
    public void setArcData(Arc a, Object data) { }
    public Object removeVertex(Vertex v) { return null; }
    public Object removeArc(Arc a) { return null; }
    public void reverseDirection(Arc a) { }
    public void setAnnotation(Vertex v, Object k, Object o) { }
    public void setAnnotation(Arc a, Object k, Object o) { } 
    public Object getAnnotation(Vertex v, Object k) { return null; } 
    public Object getAnnotation(Arc a, Object k) { return null; } 
    public Object removeAnnotation(Vertex v, Object k) { return null; }
    public Object removeAnnotation(Arc a, Object k) { return null; }
    public void clearAnnotations(Object k) { }

}
