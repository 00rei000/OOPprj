package application.algorithm;

import java.util.*;

import application.model.*;

public class BellmanFord extends GraphAlgorithm {
    
    public BellmanFord() {
        super();  // Gọi constructor của lớp cha
    }
    
    public  BellmanFord (boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }
    
    public Stack<String> getNodePath(application.model.Node source, application.model.Node target) {
        Stack<String> path = new Stack<>();
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Node> predecessor = new HashMap<>();

        // Đặt khoảng cách ban đầu cho tất cả các node là vô cùng
        for (Node node : nodes) {
            distance.put(node, Double.POSITIVE_INFINITY);
        }
        distance.put(source, 0.0);  // Khoảng cách từ node bắt đầu là 0

        // Relax tất cả các cạnh |V| - 1 lần
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (Node node : nodes) {
                for (Edge edge : node.getEdge()) {
                    // Nếu tìm được đường đi ngắn hơn, cập nhật khoảng cách
                    if (distance.get(node) + edge.getWeight() < distance.get(edge.getDestination())) {
                        distance.put(edge.getDestination(), distance.get(node) + edge.getWeight());
                        predecessor.put(edge.getDestination(), node);
                    }
                }
            }
        }

        // Kiểm tra chu trình âm (Negative Weight Cycle)
        for (Node node : nodes) {
            for (Edge edge : node.getEdge()) {
                if (distance.get(node) + edge.getWeight() < distance.get(edge.getDestination())) {
                    // Nếu có thể giảm thêm khoảng cách, chứng tỏ có chu trình âm
                    return null;  // Phát hiện chu trình âm, trả về null
                }
            }
        }

        // Xây dựng đường đi từ đích (end) đến nguồn (start) bằng cách sử dụng predecessor
        Node current = target;
        while (current != null) {
            path.push(current.getName());  // Push tên node thay vì đối tượng Node
            current = predecessor.get(current);  // Di chuyển theo predecessor
        }

        return path;
    }
}
