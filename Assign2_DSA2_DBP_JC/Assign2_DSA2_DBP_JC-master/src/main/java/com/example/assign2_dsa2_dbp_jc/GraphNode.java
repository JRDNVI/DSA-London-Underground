package com.example.assign2_dsa2_dbp_jc;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    public T data;
    public int nodeValue = Integer.MAX_VALUE;
    public List<GraphLink> adjList=new ArrayList<>(); //Adjacency list now contains link objects = key!
                                                      //Could use any concrete List implementation
    public GraphNode(T data) {
        this.data=data;
    }
    public void connectToNodeDirected(GraphNode<T> destNode,int cost, int line) {
        adjList.add( new GraphLink(destNode,cost, line) ); //Add new link object to source adjacency list
    }
    public void connectToNodeUndirected(GraphNode<T> destNode,int cost, int line) {
        adjList.add( new GraphLink(destNode, cost, line) ); //Add new link object to source adjacency list
        destNode.adjList.add( new GraphLink(this, cost, line) ); //Add new link object to destination adjacency list
    }

    public void connectToNodeUndirected(GraphNode<T> destnode, GraphLink connection) {
        adjList.add(connection);
        destnode.adjList.add(new GraphLink(this, connection.cost, connection.line));
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "data=" + data +
                ", adjList=" + adjList +
                '}';
    }
}
