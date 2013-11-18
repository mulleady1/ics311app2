package ics311km2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		Graph g = new Graph();
		BufferedReader br = new BufferedReader(new FileReader("scc_test.vna"));
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
				g.insertVertex(line.trim());
			}
			else {
				String[] tokens = line.trim().split(" ");
				Vertex u = g.getVertex(tokens[0]);
				Vertex v = g.getVertex(tokens[1]);
				g.insertArc(u, v);
			}
		}
		
		br.close();
	}

}
