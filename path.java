import java.util.Arrays; // import the Arrays class to use its methods
import java.util.Scanner; // import the Scanner class to read user input

public class path // define a public class named path
{
	public static void print_result(int[] arr1, String[] arr2) // define a static method to print the result of the algorithm
	{
		int len = arr1.length; // get the length of the first array, which is the number of nodes
		for (int i = 0; i < len; i++) // loop through each node
			System.out.println("The distance to the " + i + " node is " + // print the distance to the node
					arr1[i] + "\nAnd it's path is " + arr2[i]); // print the path to the node

	}
	public static int[][] create_graph() // define a static method to create a graph from user input
	{
		Scanner input = new Scanner(System.in); // create a Scanner object to read user input
		System.out.println("Enter the number of nodes: "); // prompt the user to enter the number of nodes
		int nodes = input.nextInt(); // read the number of nodes as an integer
		int graph[][] = new int[nodes][nodes]; // create a two-dimensional array to store the graph

		for (int i = 0; i < nodes; i++) // loop through each row of the graph
		{
			for (int j = 0; j < nodes; j++) // loop through each column of the graph
			{
				System.out.println("Enter the dist[" + i + "][" + j + "]:"); // prompt the user to enter the distance between node i and node j
				graph[i][j] = input.nextInt(); // read the distance as an integer and store it in the graph
			}
		}
		return graph; // return the graph as the output of the method
	}
	public static void dijkstra(int src, int[][] graph) // define a static method to implement the Dijkstra algorithm
	{
		int len = graph.length; // get the length of the graph, which is the number of nodes
		int dist[] = new int[len]; // create an array to store the shortest distance from the source to each node
		boolean is_solved[] = new boolean[len]; // create an array to mark whether a node is solved or not
		String paths[] = new String[len]; // create an array to store the shortest path from the source to each node
		for (int i = 0; i < len; i++) // loop through each node
			dist[i] = -1; // initialize the distance to -1, which means infinity
		dist[src] = 0; // set the distance to the source node to 0
		is_solved[src] = true; // mark the source node as solved
		paths[src] = String.valueOf(src); // set the path to the source node to itself
		int shortest[] = {-1, -1}; // create an array to store the shortest distance and the corresponding node

                for(int i = 0; i < len - 1; i++) // loop through each node except the source node
                {
                        for(int n = 0; n <= len - 1; n++) // loop through each node
                        {
                                if (is_solved[n] == false) // if the node is not solved, skip it
                                        continue;
                                for(int adj = 0; adj <= len - 1; adj++) // loop through each adjacent node
                                {
                                        if (graph[n][adj] == 0 || is_solved[adj] == true) // if the adjacent node is not connected or already solved, skip it
                                                continue;
                                        if (dist[adj] == -1 || (graph[n][adj] + dist[n]) < dist[adj]) // if the distance to the adjacent node is infinity or can be updated by a shorter path
					{
						dist[adj] = graph[n][adj] + dist[n]; // update the distance to the adjacent node
                                                paths[adj] = paths[n] + "->" + String.valueOf(adj); // update the path to the adjacent node
					}
					if (shortest[0] == -1 || dist[adj] < shortest[0]) // if the shortest distance is infinity or can be updated by a smaller value
                                        {
                                                shortest[0] = dist[adj]; // update the shortest distance
                                                shortest[1] = adj; // update the corresponding node
                                        }
                                }
                        }
                        is_solved[shortest[1]] = true; // mark the node with the shortest distance as solved
                        shortest[0] = -1; // reset the shortest distance to infinity
                        shortest[1] = -1; // reset the corresponding node to -1
                }
		print_result(dist, paths); // call the print_result method to print the final result
	}
	public static void main(String[] args) // define the main method to run the program
	{
		Scanner input = new Scanner(System.in); // create a Scanner object to read user input
		int [][]graph = create_graph(); // call the create_graph method to create a graph from user input
		System.out.println("input src point:");	// prompt the user to enter the source node
		int src = input.nextInt(); // read the source node as an integer
		dijkstra(src, graph); // call the dijkstra method to find the shortest paths from the source node
	}
}