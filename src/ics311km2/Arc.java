package ics311km2;

public class Arc {
    private Object data;
    private Vertex origin;
    private Vertex destination;

    public Object getData() { return this.data; }
    public Vertex getOrigin() { return this.origin; }
    public Vertex getDestination() { return this.destination; }

    public void addData(Object o) { this.data = o; }
    public void removeData() { this.data = null; }
}
