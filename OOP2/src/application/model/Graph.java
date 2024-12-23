package application.model;

import application.algorithm.Dijkstra;

import java.util.*;
public class Graph {
    private final LinkedList<Node> nodes = new LinkedList<>();

    public void addNode(double x, double y, String name){
        Node temp = new Node(x,y,name);
        nodes.add(temp);
    }
    public Node getNode(String from){
        for(Node i:nodes){
            if(i.name.equals(from)){
                return i;
            }
        }
        return null;
    }
    public String SearchNode(String node) {
        for(Node i:nodes){
            if(i.getName().equals(node)){
                return ("X coordinate:"+i.x+"\n"+"Y coordinate:"+i.y);
            }
        }
        return ("Node not Found");
    }
    private final Dijkstra adj = new Dijkstra(true);
    public void DeleteNode(String node){
        for(Node n:nodes){
            if(n.getName().equals(node)){
//                nodes.remove(n);
                adj.DeleteNo(n);
                nodes.remove(n);
                return;
            }
        }
    }

   /* public String ModifyNode(String node, double x, double y){
        for(Node i:nodes){
            if(i.getName().equals(node)){
                i.x = x;
                i.y = y;
                return ("Node Modified");
            }
        }
        return ("Node not Found");
    }*/
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (Node node : nodes) {
            edges.addAll(node.getEdge());
        }
        return edges;
    }

    public String addEdge(String from, String to, double weight){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null)
            return ("Form node not found");
        else if(toNode == null)
            return ("To node not Found");
        else {
            adj.addEdge(fromNode, toNode, weight);
            return ("Edge added Successfully");
        }
    }

    /*public String SearchEdge(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null) {
            return ("Edge Not Found");
        }
        else
        {
            if(adj.hasEdge(fromNode, toNode)){
                return ("Edge Found \n"+"Weight is "+adj.Weight(fromNode,toNode));
            }
            else
                return ("Edge Not Found");
        }
    }*/

    public void renameNode(String oldName, String newName) {
        Node targetNode = getNode(oldName);
        if (targetNode == null) {
            return;
        }

        if (getNode(newName) != null) {
            return;
        }

        targetNode.name = newName;
    }


    /*public String ModifyEdge(String from, String to, double weight){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null)
            return ("Edge not Found");
        else {
            adj.ModifyEdgeWeight(fromNode, toNode,weight);
            return ("Edge Modified Successfully");
        }
    }*/
    public String DeleteEdge(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null){
            return ("Edge not Found");
        }
        else if(fromNode == toNode){
            return ("Both Nodes are same!");
        }
        else {

            if(adj.DeleteEd(fromNode, toNode)){
                return ("Edge deleted");
            }
            else
                return ("Edge Not Found");
        }
    }

    public String getPath(String from, String to){
        String output;
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode =i;
            }
        }
        output = adj.DijkstraShortestPath(fromNode,toNode);
        adj.resetNodesVisited();
        return output;
    }

    public LinkedList<Node> getNodes(){
        return nodes;
    }
    public Dijkstra getAdj(){
        return adj;
    }

    public Stack<Node> getNodePath(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        return adj.animatePath(fromNode,toNode);
    }


}