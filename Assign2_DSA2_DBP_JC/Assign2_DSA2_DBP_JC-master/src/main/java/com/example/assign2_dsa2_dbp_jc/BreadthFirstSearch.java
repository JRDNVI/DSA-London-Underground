package com.example.assign2_dsa2_dbp_jc;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    public static <T> CostedPath searchGraphBreadthFirstCheapestPath(GraphNode<?> from, T lookingfor, List<GraphNode<Station>> nodesToAvoid) {
        Queue<CostedPath> queue = new LinkedList<>();
        CostedPath initialPath = new CostedPath(); // Create an initial empty path
        initialPath.pathList.add(from); // Add the starting node to the initial path
        queue.offer(initialPath); // Add the initial path to the queue

        while (!queue.isEmpty()) { // Loop while there are still paths to check
            CostedPath currentPath = queue.poll(); // Get the next path from the queue
            GraphNode<?> lastNodeInPath = currentPath.pathList.get(currentPath.pathList.size() - 1); // Get the last node in the current path
            System.out.println("Visiting node: " + lastNodeInPath.data);

            if (lastNodeInPath.data.equals(lookingfor)) { // If the last node in the current path is the target node, return the path
                // Calculate the path cost by summing the costs of all links in the path
                for (int i = 0; i < currentPath.pathList.size() - 1; i++) {
                    GraphNode<?> currentNode = currentPath.pathList.get(i);
                    GraphNode<?> nextNode = currentPath.pathList.get(i + 1);
                    GraphLink link = null;
                    for (GraphLink currentLink : currentNode.adjList) {
                        if (currentLink.destNode.equals(nextNode)) {
                            link = currentLink;
                            break;
                        }
                    }
                    assert link != null;
                    currentPath.pathCost += link.getCost();
                }
                return currentPath;
            }

            for (GraphLink link : lastNodeInPath.adjList) { // For each adjacent node of the last node in the current path
                if (currentPath.pathList.contains(link.destNode)) { // If the adjacent node has already been visited in the current path, skip it
                    continue;
                }

                if(nodesToAvoid != null) {
                    if (nodesToAvoid.contains(link.destNode)) { // If the adjacent node is in the list of nodes to avoid, skip it
                        continue;
                    }
                }

                CostedPath newPath = new CostedPath(); // Create a new path by copying the current path and adding the adjacent node
                newPath.pathList.addAll(currentPath.pathList);
                newPath.pathList.add(link.destNode);
                queue.offer(newPath); // Add the new path to the queue
            }
        }
        return null; // If no path was found, return null
    }
}

//    The function starts by creating a Queue object queue of CostedPath objects, and initializing it with an initial empty path initialPath.
//    The starting node from is added to the initialPath, which is then added to the queue.

//    The function then enters a loop that continues as long as the queue is not empty. In each iteration of the loop, the function removes the
//    first CostedPath object from the queue, and gets the last node in the path. If the last node in the path is the target node lookingfor,
//    the function calculates the cost of the path and returns it.

//    If the last node in the path is not the target node, the function examines each adjacent node of the last node.
//    If an adjacent node has already been visited in the current path, it is skipped. Otherwise, a new path is created by copying the
//    current path and adding the adjacent node. This new path is then added to the queue.
//    If the queue becomes empty and no path has been found, the function returns null.
//    The CostedPath class is not shown in this code snippet, but it presumably represents a path in the graph and stores
//    the path as a list of GraphNode objects, and the cost of the path as a double value.
