class Robot {
    private int width;
    private int height;
    private int x;
    private int y;
    private int d;
    private int steps;

    private final static int[][] DIRS = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        x = 0;
        y = 0;
        d = 0; // east
        steps = 0;
    }
    
    private void lazyStep(int num) {
        int perimeter = 2 * (width + height) - 4;
        num %= perimeter;

        // Special case: full cycle
        if (num == 0) {
            num = perimeter;
        }

        while (num > 0) {
            if (d == 0) { // East
                if (x + num > width - 1) {
                    num = num - (width - x - 1);
                    d = (d + 1) % 4;
                    x = width - 1;
                }
                else{
                    x += num;
                    num = 0;
                }
            } else if (d == 1) { // North
                if (y + num > height - 1) {
                    num = num - (height - y - 1);
                    d = (d + 1) % 4;
                    y = height - 1;
                } else {
                    y += num;
                    num = 0;
                }
            } else if (d == 2) { // West
                if (x - num < 0) {
                    num = num - x;
                    d = (d + 1) % 4;
                    x = 0;
                } else {
                    x -= num;
                    num = 0;
                }
            } else { // South
                if (y - num < 0) {
                    num = num - y;
                    d = (d + 1) % 4;
                    y = 0;
                } else {
                    y -= num;
                    num = 0;
                }
            }
        }

        steps = 0;
    }

    public void step(int num) {
        steps += num;
    }
    
    public int[] getPos() {
        if (steps > 0) {
            lazyStep(steps);
        }
        return new int[] {x, y};
    }
    
    public String getDir() {
        if (steps > 0) {
            lazyStep(steps);
        }
        if (d == 0) {
            return "East";
        } else if (d == 1) {
            return "North";
        } else if (d == 2) {
            return "West";
        } else {
            return "South";
        }
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */