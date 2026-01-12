/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // BFS
        // if (node == null) {
        //     return null;
        // }

        // Map<Node, Node> cloned = new HashMap<Node, Node>();
        // Node rootClone = new Node(node.val);
        // cloned.put(node, rootClone);

        // Queue<Node> q = new ArrayDeque<>();
        // q.offer(node);

        // while (!q.isEmpty()) {
        //     Node curr = q.poll();
        //     Node currClone = cloned.get(curr);

        //     for (Node adj : curr.neighbors) {
        //         if (!cloned.containsKey(adj)) {
        //             Node adjClone = new Node(adj.val);
        //             cloned.put(adj, adjClone);
        //             q.offer(adj);
        //         }
        //         currClone.neighbors.add(cloned.get(adj));
        //     }
        // }

        // return rootClone;
        if (node == null) {
            return null;
        }
        Map<Node, Node> clones = new HashMap<Node, Node>();

        return clone(node, clones);
    }

    private Node clone(Node node, Map<Node, Node> clones) {
        if (clones.containsKey(node)) {
            return clones.get(node);
        }
        Node clone = new Node(node.val);
        clones.put(node, clone);

        for (Node adj : node.neighbors) {
            clone.neighbors.add(clone(adj, clones));
        }

        return clone;
    }
}