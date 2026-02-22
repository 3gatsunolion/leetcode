/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

class TreeNode {
    val: number;
    left: TreeNode | null;
    right: TreeNode | null;
    constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
        this.val = (val === undefined ? 0 : val);
        this.left = (left === undefined ? null : left);
        this.right = (right === undefined ? null : right);
    }
}

function distanceK(root: TreeNode | null, target: TreeNode | null, k: number): number[] {
    // Keep track of chain of parents from target to root
    const setParents = (node: TreeNode | null, parent: TreeNode | null): boolean => {
        if (!node) return false;

        if (node === target) {
            parents.set(node, parent);
            return true;
        }

        if (setParents(node.left, node)) {
            parents.set(node.left, node);
            return true;
        }
        if (setParents(node.right, node)) {
            parents.set(node.right, node);
            return true;
        }
        return false;
    }

    const parents = new Map<TreeNode, TreeNode>();
    setParents(root, null);

    const q = [target];
    const seen = new Set([target]);

    let dist = 0;
    const res: number[] = [];
    while (q.length > 0) {
        const n = q.length;

        for (let i = 0; i < n; i++) {
            const curr = q.shift();

            if (dist === k) {
                res.push(curr.val);
            } else if (dist < k) {
                [parents.get(curr) ?? null, curr.left, curr.right].forEach(adj => {
                    if (adj !== null && !seen.has(adj)) {
                        seen.add(adj);
                        q.push(adj);
                    }
                })
            }
        }

        dist++;
    }

    return res;

    // // If a node is target's child, then its distance is just depth from target
    // // If a node is not target's descendant, then it distance is distance
    // // of target to their common ancestor + its distance to common ancestor
    // // Keep track of distance from target to each of its ancestors till we get
    // // to root
    // const mp: Map<TreeNode, number> = new Map();

    // find(root, target, mp);
    // const res: number[] = [];
    // dfs(root, target, 0, k, mp, res);
    // return res;
};

function find(root: TreeNode | null, target: TreeNode | null, mp: Map<TreeNode, number>): number {
    if (!root) return -1;

    if (root === target) {
        mp.set(root, 0);
        return 0;
    }

    const left = find(root.left, target, mp);
    if (left >= 0) {
        mp.set(root, left + 1);
        return left + 1;
    }

    const right = find(root.right, target, mp);
    if (right >= 0) {
        mp.set(root, right + 1);
        return right + 1;
    }

    return -1;
};

function dfs(root: TreeNode | null, target: TreeNode | null, dist: number, k: number, mp: Map<TreeNode, number>, res: number[]): void {
    if (!root) return;

    if (mp.has(root)) dist = mp.get(root);
    if (dist === k) res.push(root.val);

    dfs(root.left, target, dist + 1, k, mp, res);
    dfs(root.right, target, dist + 1, k, mp, res);
};