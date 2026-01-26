/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // // BFS
    // public String serialize(TreeNode root) {
    //     Queue<TreeNode> q = new LinkedList<>();
    //     q.offer(root);

    //     StringBuilder sb = new StringBuilder();
    //     while (!q.isEmpty()) {
    //         TreeNode node = q.poll();

    //         if (node == null) {
    //             sb.append("# ");
    //         } else {
    //             sb.append(node.val + " ");
    //             q.offer(node.left);
    //             q.offer(node.right);
    //         }
    //     }

    //     return sb.toString().trim();
    // }

    // public TreeNode deserialize(String data) {
    //     String[] nodes = data.split(" ");
    //     if (nodes[0].equals("#")) {
    //         return null;
    //     }
    //     TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
    //     Queue<TreeNode> q = new LinkedList<>();
    //     q.offer(root);

    //     for (int i = 1; i < nodes.length; i++) {
    //         TreeNode node = q.poll();
    //         if (!nodes[i].equals("#")) {
    //             TreeNode left = new TreeNode(Integer.valueOf(nodes[i]));
    //             node.left = left;
    //             q.offer(left);
    //         }
    //         if (!nodes[++i].equals("#")) {
    //             TreeNode right = new TreeNode(Integer.valueOf(nodes[i]));
    //             node.right = right;
    //             q.offer(right);
    //         }
    //     }
    //     return root;
    // }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // List<String> data = new ArrayList<>();
        // dfsSerialize(root, data);
        // return String.join(" ", data);
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        return sb.toString().trim();
    }

    private void dfsSerialize(TreeNode node, StringBuilder data) {
        if (node == null) {
            data.append("# ");
        } else {
            data.append(node.val + " ");
            dfsSerialize(node.left, data);
            dfsSerialize(node.right, data);
        }
    }

    private void dfsSerialize(TreeNode node, List<String> data) {
        if (node == null) {
            data.add("#");
        } else {
            data.add(node.val + "");
            dfsSerialize(node.left, data);
            dfsSerialize(node.right, data);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(" ");

        return dfsDeserialize(Arrays.asList(parts).iterator());
        // return dfsDeserialize(new LinkedList<>(Arrays.asList(parts)));
    }

    private TreeNode dfsDeserialize(Queue<String> tree) {
        String val = tree.poll();
        if (val.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = dfsDeserialize(tree);
        node.right = dfsDeserialize(tree);
        return node;
    }

    private TreeNode dfsDeserialize(Iterator<String> tree) {
        String val = tree.next();
        if (val.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = dfsDeserialize(tree);
        node.right = dfsDeserialize(tree);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));