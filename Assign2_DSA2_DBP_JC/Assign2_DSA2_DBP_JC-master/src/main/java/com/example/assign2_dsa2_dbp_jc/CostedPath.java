package com.example.assign2_dsa2_dbp_jc;

import java.util.*;


//New class to hold a CostedPath object i.e. a list of GraphNodeAL2 objects and a total cost attribute
public class CostedPath {
    public int pathCost = 0;
    public List<GraphNode<?>> pathList = new ArrayList<>();

    public static <T> CostedPath searchGraphDepthFirstCosted(GraphNode<?> startNode, T lookingFor, List<GraphNode<Station>> avoidList) {
        Set<GraphNode<?>> visitedNodes = new HashSet<>();
        if (avoidList != null) {
            visitedNodes.addAll(avoidList);
        }
        return dfsHelper(startNode, visitedNodes, lookingFor, new CostedPath());
    }

    private static <T> CostedPath dfsHelper(GraphNode<?> currentNode, Set<GraphNode<?>> visitedNodes, T lookingfor, CostedPath currentPath) {
        if (currentNode.data.equals(lookingfor)) {
            currentPath.pathList.add(currentNode);
            for (int i = 0; i < currentPath.pathList.size() - 1; i++) {
                GraphNode<?> node = currentPath.pathList.get(i);
                GraphNode<?> nextNode = currentPath.pathList.get(i + 1);
                GraphLink link = null;
                for (GraphLink currentLink : node.adjList) {
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

        visitedNodes.add(currentNode);
        currentPath.pathList.add(currentNode);

        for (GraphLink link : currentNode.adjList) {
            GraphNode<?> nextNode = link.destNode;
            if (!visitedNodes.contains(nextNode)) {
                CostedPath newPath = new CostedPath();
                newPath.pathList.addAll(currentPath.pathList);

                CostedPath result = dfsHelper(nextNode, visitedNodes, lookingfor, newPath);
                if (result != null) {
                    return result;
                }
            }
        }

        currentPath.pathList.remove(currentNode);
        return null;
    }
}