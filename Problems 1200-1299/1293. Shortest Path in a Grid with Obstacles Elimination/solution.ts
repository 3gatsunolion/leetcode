function shortestPath(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;

    if (k >= m + n - 2) return m + n - 2;

    const visited = Array(m).fill(null).map((el) => Array(n).fill(-1));
    let steps = 0;

    const DIRS = [0, -1, 0, 1, 0];

    const q: number[][] = [[0, 0, k]];
    while (q.length > 0) {
        const qLen = q.length;

        for (let j = 0; j < qLen; j++) {
            const [x, y, eliminate]: any = q.shift();

            if (x === (m - 1) && y === (n - 1)) return steps;

            for (let i = 0; i < 4; i++) {
                const dx = x + DIRS[i];
                const dy = y + DIRS[i+1];

                if (dx < 0 || dy < 0 || dx === m || dy === n || (grid[dx][dy] === 1 && visited[dx][dy] >= (eliminate - 1)) || (grid[dx][dy] === 0 && visited[dx][dy] >= eliminate)) continue;
                const newEliminate = grid[dx][dy] === 1 ? eliminate - 1 : eliminate;
                visited[dx][dy] = newEliminate;
                q.push([dx, dy, newEliminate]);
            }
        }
        steps++;
    }

    return -1;
};