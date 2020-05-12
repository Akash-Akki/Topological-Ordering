
/**
 * @author  Akash P Akki      netid: apa190001
 * @author  Anant Srivastava  netid: aps180006
 */

package aps180006;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static aps180006.BFSOO.INFINITY;
import static aps180006.BFSOO.breadthFirstSearch;

public class OddLengthCycle {


    /**
     *
     * @param g Graph g
     * @param visited visited array to indicate whether the vertex is visited or not.
     * @return Odd length Cycle
     */

    public List<Graph.Vertex> isGraphContainsOddEdge(Graph g, boolean[] visited) {
        Graph.Edge oddEdge =null;
        List<Graph.Vertex> oddCycleList = new ArrayList<>();
        for(Graph.Vertex v:g) {
            if(visited[v.getName()]==false) {
                BFSOO b = breadthFirstSearch(g,v);
                visited[v.getName()] = true;

                List<Graph.Edge> edgesArray= Arrays.asList(g.getEdgeArray());


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


    /**
     *
     * @param b  BFS Object
     * @param oddEdge  Edge from where u and v have equal distance from source
     * @return OddLength Cycle
     */
    private List<Graph.Vertex> oddCycle(BFSOO b, Graph.Edge oddEdge) {
        Graph.Vertex uParent = b.getParent(oddEdge.from);
        Graph.Vertex vParent = b.getParent(oddEdge.to);

        List<Graph.Vertex> oddCycleList = new ArrayList();
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
    }


    /**
     * Prints the Odd Length Cycle if it exists
     * @param graphContainsOddEdge
     *
     */
    public void printOddCycle(List<Graph.Vertex> graphContainsOddEdge) {
        if(graphContainsOddEdge.size()==0)
            System.out.println("There is no odd length cycle it is a bipartite graph");
        else{
            System.out.println("Vertices with odd length cycle are ");
            for (int i=0;i<graphContainsOddEdge.size();i++)
                System.out.print(graphContainsOddEdge.get(i) + "->");
        }
    }


 public static  void main(String[] args) throws FileNotFoundException {

     String string = "8 7   1 2 3   2 4 9   1 3 5   4 5 7   6 7 7   7 8 8   8 6 5 1";
     Scanner in;
     // If there is a command line argument, use it as file from which
     // input is read, otherwise use input from string.
     in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
     // Read graph from input
     Graph g = Graph.readGraph(in,false);
     int s = in.nextInt();
     boolean[] visited = new boolean[g.n];
     OddLengthCycle oddLengthCycle  = new OddLengthCycle();

     // Call breadth-first search
     BFSOO b = breadthFirstSearch(g,s);
     //return the Odd length Cycle
     List<Graph.Vertex> graphContainsOddEdge=oddLengthCycle.isGraphContainsOddEdge(g,visited);
     //Prints the returned Odd length cycle;

     oddLengthCycle.printOddCycle(graphContainsOddEdge);
     System.out.println(" ");
     g.printGraph(false);

     System.out.println("Output of BFS:\nNode\tDist\tParent\n----------------------");
     for(Graph.Vertex u: g) {
         if(b.getDistance(u) == INFINITY) {
             System.out.println(u + "\tInf\t--");
         } else {
             System.out.println(u + "\t" + b.getDistance(u) + "\t" + b.getParent(u));
         }
     }


 }




}
