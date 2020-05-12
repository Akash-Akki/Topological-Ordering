Short Project 6

Author 1. Akash P Akki     netid: apa190001
       2. Anant Srivastava netid: aps180006




File Name: OddLengthCycle.java
Package name: aps180006

Open Intellij IDE
  1. Create a Java Project
  2. Unzip the uploaded folder
  3. Right click src folder and add file DFS.java and Graph.java from the unzipped folder
  4. Run DFS.java file by clicking on Run Button.
  
  
Steps for Execution 

 // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
		
The String input should given in the following format 

noumber of vertices number of edges  u_vertex v_vertex weight source	
		
Sample input is given below.
#########################################################################	
Sample Input
 8 7   1 2 3   2 4 9   1 3 5   4 5 7   6 7 7   7 8 8   8 6 5 1;	
		
		
###############################################################################		
Sample output

Vertices with odd length cycle are 
7->8->6-> 


______________________________________________
aps180006.Graph: n: 8, m: 7, directed: false, Edge weights: false
1 :  (1,2) (1,3)
2 :  (1,2) (2,4)
3 :  (1,3)
4 :  (2,4) (4,5)
5 :  (4,5)
6 :  (6,7) (8,6)
7 :  (6,7) (7,8)
8 :  (7,8) (8,6)
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
8	Inf	--
		
		

 

