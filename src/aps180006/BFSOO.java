package aps180006; /** Breadth-first search: Object-oriented version
 *  @author rbk
 *  Version 1.0: 2018/10/16
 */

import aps180006.Graph.Vertex;
import aps180006.Graph.Edge;
import aps180006.Graph.Factory;

import java.io.File;
import java.util.*;

public class BFSOO extends Graph.GraphAlgorithm<BFSOO.BFSVertex> {
    public static final int INFINITY = Integer.MAX_VALUE;
    Vertex src;

    // Class to store information about vertices during BFS
    public static class BFSVertex implements Factory {

    boolean seen;
	Vertex parent;
	int distance;  // distance of vertex from source
	public BFSVertex(Vertex u) {
	    seen = false;
	    parent = null;
	    distance = INFINITY;
	}
	public BFSVertex make(Vertex u) { return new BFSVertex(u); }
    }

    // code to initialize storage for vertex properties is in GraphAlgorithm class
    public BFSOO(Graph g) {
	super(g, new BFSVertex(null));
    }


    // getter and setter methods to retrieve and update vertex properties
    public boolean getSeen(Vertex u) {
	return get(u).seen;
    }

    public void setSeen(Vertex u, boolean value) {
	get(u).seen = value;
    }

    public Vertex getParent(Vertex u) {
	return get(u).parent;
    }

    public void setParent(Vertex u, Vertex p) {
	get(u).parent = p;
    }

    public int getDistance(Vertex u) {
	return get(u).distance;
    }

    public void setDistance(Vertex u, int d) {
	get(u).distance = d;
    }

    public void initialize(Vertex src) {
	for(Vertex u: g) {
	    setSeen(u, false);
	    setParent(u, null);
	    setDistance(u, INFINITY);
	}
	setDistance(src, 0);
    }

    public void setSource(Vertex src) {
	this.src = src;
    }

    public Vertex getSource() {
	return this.src;
    }
    
    // Visit a node v from u
    void visit(Vertex u, Vertex v) {
	setSeen(v, true);
	setParent(v, u);
	setDistance(v, getDistance(u)+1);
    }

    public void bfs(Vertex src) {
	setSource(src);
	initialize(src);

	Queue<Vertex> q = new LinkedList<>();
	q.add(src);
	setSeen(src, true);

	while(!q.isEmpty()) {
	    Vertex u = q.remove();
	    for(Edge e: g.incident(u)) {
		Vertex v = e.otherEnd(u);
		if(!getSeen(v)) {
		    visit(u,v);
		    q.add(v);
		}
	    }
	}
    }
    
    // Run breadth-first search algorithm on g from source src
    public static BFSOO breadthFirstSearch(Graph g, Vertex src) {
	BFSOO b = new BFSOO(g);
	b.bfs(src);
	return b;
    }

    public static BFSOO breadthFirstSearch(Graph g, int s) {
	return breadthFirstSearch(g, g.getVertex(s));
    }








/*	private static List<Vertex> isGraphContainsOddEdge(Graph g, boolean[] visited) {
		Edge oddEdge =null;
    	List<Vertex> oddCycleList = new ArrayList<>();
    	for(Vertex v:g) {
          if(visited[v.getName()]==false) {
			  BFSOO b = breadthFirstSearch(g, v);
			  visited[v.getName()] = true;

			 List<Edge> edgesArray= Arrays.asList(g.getEdgeArray());


			  for (int i = 0; i < edgesArray.size(); i++) {
				  if (b.getDistance(edgesArray.get(i).from) != INFINITY || b.getDistance(edgesArray.get(i).to) != INFINITY){
				  if (b.getDistance(edgesArray.get(i).from) == b.getDistance(edgesArray.get(i).to)) {
					  oddEdge = edgesArray.get(i);

					  oddCycleList = oddCycle(b, oddEdge);
					  return oddCycleList;
				  }
			  }
			  }
		  }
		}

		return oddCycleList;
	}

	private static List<Vertex> oddCycle(BFSOO b, Edge oddEdge) {
		Vertex uParent = b.getParent(oddEdge.from);
		Vertex vParent = b.getParent(oddEdge.to);

		List<Vertex> oddCycleList = new ArrayList();
		if(vParent.equals(uParent))
		{
			oddCycleList.add(oddEdge.from);
			oddCycleList.add(oddEdge.to);
			oddCycleList.add(uParent);
			return  oddCycleList;
		}
		oddCycleList.add(oddEdge.from);
		oddCycleList.add(oddEdge.to);
		oddCycleList.add(uParent);
		oddCycleList.add(vParent);
		while (!(uParent.equals(vParent))) {
			uParent = b.getParent(uParent);
			vParent = b.getParent(vParent);
			if(!(uParent.equals(vParent))) {
				oddCycleList.add(uParent);
				oddCycleList.add(vParent);
			}
			else
				oddCycleList.add(uParent);
		}
		   return oddCycleList;
	}*/


	public static void main(String[] args) throws Exception {

    }


}

/* Sample run:
______________________________________________
aps180006.Graph: n: 7, m: 8, directed: true, Edge weights: false
1 :  (1,2) (1,3)
2 :  (2,4)
3 :  (3,4)
4 :  (4,5)
5 :  (5,1)
6 :  (6,7)
7 :  (7,6)
______________________________________________
Output of BFS:
Node	Dist	Parent
----------------------
1	0	null
2	1	1
3	1	1
4	2	2
5	3	4
6	Inf	--
7	Inf	--
*/
