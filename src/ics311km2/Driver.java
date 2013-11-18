package ics311km2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Driver implements Constants {

	private static Map<String, Object> data = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		Graph g = loadGraph(args[0]);
		analyzeGraph(g);
	}

	private static Graph loadGraph(String filename) {
		Graph g = new Graph();
		data.put(FILENAME, filename);
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			// Skip first two lines.
			br.readLine();
			br.readLine();
			String line;
			boolean readVertices = true;
			while ((line = br.readLine()) != null) {
				// If line begins with an asterisk, start reading edges.
				if (line.charAt(0) == '*') {
					readVertices = false;
					line = br.readLine();
					line = br.readLine();
				}
				if (readVertices) {
					// Insert a vertex.
					g.insertVertex(line.trim());
				}
				else {
					// Insert an arc.
					String[] tokens = line.trim().split(" ");
					Vertex u = g.getVertex(tokens[0]);
					Vertex v = g.getVertex(tokens[1]);
					g.insertArc(u, v, String.valueOf(u.getData())+"/"+String.valueOf(v.getData()));
				}
			}
			br.close();
		} catch(IOException e) {
			log("IO Error. Terminating app.");
			System.exit(1);
		} 
		return g;
	}
	
	private static void analyzeGraph(Graph g) {
		computeDegree(g);
		computeDensity(g);
		//computeSCC(g);
		printData(g);
	}
	
	private static void computeDegree(Graph g) {
		Iterator<Vertex> i = g.vertices();
		int inDegreeMin = Integer.MAX_VALUE;
		int inDegreeMax = Integer.MIN_VALUE;
		int inDegreeTot = 0;
		int outDegreeMin = Integer.MAX_VALUE;
		int outDegreeMax = Integer.MIN_VALUE;
		int outDegreeTot = 0;
		while (i.hasNext()) {
			Vertex v = i.next();
			inDegreeTot  += v.inDegree();
			outDegreeTot += v.inDegree();
			if (v.inDegree() < inDegreeMin) {
				inDegreeMin = v.inDegree();
			}
			else if (v.inDegree() > inDegreeMax) {
				inDegreeMax = v.inDegree();
			}
			if (v.outDegree() < outDegreeMin) {
				outDegreeMin = v.outDegree();
			}
			else if (v.outDegree() > outDegreeMax) {
				outDegreeMax = v.outDegree();
			}
		}
		data.put(IN_DEGREE_MIN,  inDegreeMin);
		data.put(IN_DEGREE_MAX,  inDegreeMax);
		data.put(IN_DEGREE_AVE,  inDegreeTot / g.numVertices());
		data.put(OUT_DEGREE_MIN, outDegreeMin);
		data.put(OUT_DEGREE_MAX, outDegreeMax);
		data.put(OUT_DEGREE_AVE, outDegreeTot / g.numVertices());
	}
	
	private static void computeDensity(Graph g) {
		data.put(DENSITY, ((double)g.numArcs() / (g.numVertices() * (g.numVertices() - 1))));
	}
	
	private static void computeSCC(Graph g) {
		dfs(g);
	}
	
	private static int time = 0, scc = 0;
	private static void dfs(Graph g) {
		Iterator<Vertex> i = g.vertices();
		while (i.hasNext()) {
			Vertex u = i.next();
			g.setAnnotation(u, COLOR, WHITE);
			g.setAnnotation(u, PARENT, NIL);
		}
		i = g.vertices();
		while (i.hasNext()) {
			Vertex u = i.next();
			if (g.getAnnotation(u, COLOR).equals(WHITE)) {
				scc++;
				dfsVisit(g, u);
			}
		}
	}
	
	private static void dfsVisit(Graph g, Vertex u) {
		time++;
		g.setAnnotation(u, D, time);
		g.setAnnotation(u, COLOR, GRAY);
		g.setAnnotation(u, SCC, scc);
		Iterator<Vertex> i = u.inAdjacentVertices();
		// ...
		i = u.outAdjacentVertices();
		// ...
	}

	private static void printData(Graph g) {
		NumberFormat fmt = new DecimalFormat("#.####");
		String sep = "------------------------------------------------------------";
		log(sep);
		log("Graph " + data.get(FILENAME));
		log(sep);
		log("|V| = " + g.numVertices());
		log("|A| = " + g.numArcs());
		log("Density: " + fmt.format(data.get(DENSITY)));
		log("Degree distribution: minimum  average  maximum");
		log("  inDegree            "
				+ data.get(IN_DEGREE_MIN) + "        "
				+ data.get(IN_DEGREE_AVE) + "        "
				+ data.get(IN_DEGREE_MAX));
		log("  outDegree           "
				+ data.get(OUT_DEGREE_MIN) + "        "
				+ data.get(OUT_DEGREE_AVE) + "        "
				+ data.get(OUT_DEGREE_MAX));
		log("Number of Strongly Connected Components: " + data.get(NUM_SCC));
		log("Percent Vertices in Largest Strongly Connected Component: " + data.get(PERCENT));
		
		data.clear();
	}
	
	private static void log(Object o) {
		System.out.println(String.valueOf(o));
	}
}
