package com.example.assign2_dsa2_dbp_jc;

public class GraphLink {
    public GraphNode<?> destNode; //Could also store source node if required
    public int cost; //Other link attributes could be similarly stored
    public int line;

    public GraphLink(GraphNode<?> destNode, int cost, int line) {
        this.destNode = destNode;
        this.cost = cost;
        this.line = line;
    }

    public GraphNode<?> getDestNode() {
        return destNode;
    }

    public void setDestNode(GraphNode<?> destNode) {
        this.destNode = destNode;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }


}
