package application.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import application.model.*;

abstract class GraphAlgorithm {
	protected boolean directed;
    protected Set<Node> nodes;

    // Constructor chung
    public GraphAlgorithm() {
        this.nodes = new HashSet<>();
    }

    // Phương thức chung để thêm node
    public void addNode(Node... n) {
        // We're using a var arg method so we don't have to call
        // addNode repeatedly
        nodes.addAll(Arrays.asList(n));
    }

    public void addEdge(Node source, Node destination, double weight) {

        nodes.add(source);
        nodes.add(destination);

        // We're using addEdgeHelper to make sure we don't have duplicate getEdge()
        addEdgeHelper(source, destination, weight);

        if (!directed && source != destination) {
            addEdgeHelper(destination, source, weight);
        }
    }
    private void addEdgeHelper(Node a, Node b, double weight) {

        for (Edge edge : a.getEdge()) {
            if (edge.getSource() == a && edge.getDestination() == b) {
                // Update the value in case it's a different one now
                edge.setWeight(weight);
                return;
            }
        }
        // If it hasn't been added already
        a.getEdge().add(new Edge(a, b, weight));
    }
    void ModifyEdgeWeight(Node a, Node b, double weight) {

        for (Edge edge : a.getEdge()) {
            if (edge.getSource() == a && edge.getDestination() == b) {
                // Update the value
            	edge.setWeight(weight);
                return;
            }
        }
    }
    public void copyEdge(ArrayList<Edge> edges){
        for(Node node : nodes){
        	edges.addAll(node.getEdge());
        }
    }


    public boolean DeleteEd(Node a, Node b) {
        for (Edge edge : a.getEdge()) {
            if (edge.getSource() == a && edge.getDestination() == b) {
                // Update the value in case it's a different one now
                a.getEdge().remove(edge);
                return true;
            }
        }
        return false;
    }
    public void DeleteNo(Node from){
        for(Node node : nodes){
            for (Edge edge : node.getEdge()) {
//                System.out.println(edge.source.name+" "+edge.destination.name+" "+edge.weight);
                if (edge.getSource() == from || edge.getDestination() == from) {
                    node.getEdge().remove(edge);
//                    System.out.println(edge.source.name+" "+edge.destination.name+" "+edge.weight);
                }
            }
        }
        nodes.remove(from);
    }
 // Kiểm tra xem có cạnh giữa hai node không
    public boolean hasEdge(Node source, Node destination) {
        LinkedList<Edge> edges = source.getEdge();
        for (Edge edge : edges) {
            if (edge.getDestination() == destination) {
                return true;
            }
        }
        return false;
    }

    // Lấy trọng số cạnh giữa hai node
    public double Weight(Node source, Node destination) {
        LinkedList<Edge> edges = source.getEdge();
        for (Edge edge : edges) {
            if (edge.getDestination() == destination) {
                return edge.getWeight();
            }
        }
        return 0;
    }
    // Các phương thức chung có thể được định nghĩa ở đây.
    // Phương thức này sẽ được triển khai bởi các lớp con.
    //public abstract String getShortestPath(Node start, Node end);
}
