package application.algorithm;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import application.model.*;

public class Dijkstra extends GraphAlgorithm{
	public Dijkstra() {
        super();  // Gọi constructor của lớp cha
    }
    //private Set<Node> nodes;
    //private boolean directed;

    public Dijkstra(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void resetNodesVisited() {
        for (Node node : nodes) {
            node.unvisited();
        }
    }
    public String DijkstraShortestPath(Node start, Node end) {
        String output ="";
        HashMap<Node, Node> changedAt = new HashMap<>();
        changedAt.put(start, null);

        HashMap<Node, Double> shortestPathMap = new HashMap<>();

        for (Node node : nodes) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }

        for (Edge edge : start.getEdge()) {
            shortestPathMap.put(edge.getDestination(), edge.getWeight());
            changedAt.put(edge.getDestination(), start);
        }

        start.visit();

        while (true) {
            Node currentNode = closestReachableUnvisited(shortestPathMap);

            if (currentNode == null) {
                return ("There isn't a path between " + start.getName() + " and " + end.getName());
            }

            if (currentNode == end) {

                Node child = end;

                StringBuilder path = new StringBuilder(end.getName());
                while (true) {
                    Node parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }

                    path.insert(0, parent.getName() + "->");
                    child = parent;
                }
                output += path;
//                System.out.println(path);
//                output += ("\nThe path costs: " + shortestPathMap.get(end));
                return output;
            }
            currentNode.visit();

            for (Edge edge : currentNode.getEdge()) {
                if (edge.getDestination().isVisited())
                    continue;

                if (shortestPathMap.get(currentNode)
                        + edge.getWeight()
                        < shortestPathMap.get(edge.getDestination())) {
                    shortestPathMap.put(edge.getDestination(),
                            shortestPathMap.get(currentNode) + edge.getWeight());
                    changedAt.put(edge.getDestination(), currentNode);
                }
            }
        }
    }
    public Stack<Node> animatePath(Node start, Node end) {

        Stack<Node> path = new Stack<>();
        HashMap<Node, Node> changedAt = new HashMap<>();
        changedAt.put(start, null);

        HashMap<Node, Double> shortestPathMap = new HashMap<>();

        for (Node node : nodes) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }

        for (Edge edge : start.getEdge()) {
            shortestPathMap.put(edge.getDestination(), edge.getWeight());
            changedAt.put(edge.getDestination(), start);
        }

        start.visit();

        while (true) {
            Node currentNode = closestReachableUnvisited(shortestPathMap);

            if (currentNode == null) {
                return null;
            }

            if (currentNode == end) {

                Node child = end;
                path.push(child);
                while (true) {
                    Node parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }

                    path.push(parent);
                    child = parent;
                }
                return path;
            }
            currentNode.visit();

            for (Edge edge : currentNode.getEdge()) {
                if (edge.getDestination().isVisited())

                if (shortestPathMap.get(currentNode)
                        + edge.getWeight()
                        < shortestPathMap.get(edge.getDestination())) {
                    shortestPathMap.put(edge.getDestination(),
                            shortestPathMap.get(currentNode) + edge.getWeight());
                    changedAt.put(edge.getDestination(), currentNode);
                }
            }
        }
    }
    private Node closestReachableUnvisited(HashMap<Node, Double> shortestPathMap) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        Node closestReachableNode = null;
        for (Node node : nodes) {
            if (node.isVisited())
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }

}