package ics311km2;

import java.util.Set;
import java.util.Iterator;

public class Vertex {
    private int inDegree;
    private int outDegree;
    private Object data;
    private Set<Vertex> inAdjacentVertices;
    private Set<Vertex> outAdjacentVertices;

    public int getInDegree() { return this.inDegree; }
    public int getOutDegree() { return this.outDegree; }
    public Object getData() { return this.data; }
    public Iterator<Vertex> inAdjacentVertices() { return this.inAdjacentVertices.iterator(); }
    public Iterator<Vertex> outAdjacentVertices() { return this.outAdjacentVertices.iterator(); }

    public void addInAdjacentVertex(Vertex v) {
        this.inAdjacentVertices.add(v);
        this.inDegree++;
    }

    public void addOutAdjacentVertex(Vertex v) {
        this.outAdjacentVertices.add(v);
        this.outDegree++;
    }
    
    public void addData(Object o) {
        this.data = o;
    }

    public void removeInAdjacentVertex(Vertex v) {
        this.inAdjacentVertices.remove(v);
        this.inDegree--;
    }

    public void removeOutAdjacentVertex(Vertex v) {
        this.outAdjacentVertices.remove(v);
        this.outDegree--;
    }
    
    public void removeData() {
        this.data = null;
    }
}
