package com.test.java;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestFlood {
    public static void main(String[] args) {
        TestFlood testFlood = new TestFlood();
        testFlood.hasValidPath(new int[][]{{1, 1, 2}});
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originColor = image[sr][sc];
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodFill(image, originColor, newColor, sr, sc, visited);
        return image;
    }


    public void floodFill(int[][] image, int originColor, int newColor, int x, int y, boolean[][] visited) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
            return;
        }
        if (visited[x][y]) {
            return;
        }
        if (image[x][y] != originColor) {
            return;
        }
        image[x][y] = newColor;
        visited[x][y] = true;
        floodFill(image, originColor, newColor, x - 1, y, visited);
        floodFill(image, originColor, newColor, x + 1, y, visited);
        floodFill(image, originColor, newColor, x, y - 1, visited);
        floodFill(image, originColor, newColor, x, y + 1, visited);
    }

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[r0][c0] = true;
        colorBorder(grid, grid[r0][c0], color, r0 - 1, c0, visited, r0, c0);
        colorBorder(grid, grid[r0][c0], color, r0 + 1, c0, visited, r0, c0);
        colorBorder(grid, grid[r0][c0], color, r0, c0 - 1, visited, r0, c0);
        colorBorder(grid, grid[r0][c0], color, r0, c0 - 1, visited, r0, c0);
        return grid;
    }

    public void colorBorder(int[][] grid, int origin, int color, int x, int y, boolean[][] visited, int lastx, int lasty) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            grid[lastx][lasty] = color;
            return;
        }
        if (visited[x][y]) {
            return;
        }
        if (grid[x][y] != origin) {
            grid[lastx][lasty] = color;
            return;
        } else {
            visited[x][y] = true;
            colorBorder(grid, origin, color, x - 1, y, visited, x, y);
            colorBorder(grid, origin, color, x + 1, y, visited, x, y);
            colorBorder(grid, origin, color, x, y - 1, visited, x, y);
            colorBorder(grid, origin, color, x, y + 1, visited, x, y);
        }
    }

    public int numEnclaves(int[][] grid) {
        int total = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {
                    if (grid[i][j] == 1) {
                        fill(grid, visited, i, j);
                    }
                }
            }
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    total++;
                }
            }
        }
        return total;
    }

    public void fill(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }
        if (visited[x][y]) {
            return;
        }
        if (grid[x][y] == 1) {
            visited[x][y] = true;
            fill(grid, visited, x - 1, y);
            fill(grid, visited, x + 1, y);
            fill(grid, visited, x, y - 1);
            fill(grid, visited, x, y + 1);
        }
    }

    public boolean hasValidPath(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        return hasValidPath(grid, visited, 0, 0, 0);
    }


    public boolean hasValidPath(int[][] grid, boolean[][] visited, int x, int y, int last) {

        //检查边界
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return false;
        }
        //是否访问过
        if (visited[x][y]) {
            return false;
        }
        int current = grid[x][y];
        //验证当前是否匹配上一个
        //上一个在左
        if (last == 1) {
            if (current != 1 && current != 3 & current != 5) {
                return false;
            }
            //上一个在右
        } else if (last == 2) {
            if (current != 1 && current != 4 & current != 6) {
                return false;
            }
            //上一个在上
        } else if (last == 3) {
            if (current != 2 && current != 5 & current != 6) {
                return false;
            }
            //上一个在下
        } else if (last == 4) {
            if (current != 2 && current != 3 & current != 4) {
                return false;
            }
        }
        //检查是否到达
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return true;
        }
        visited[x][y] = true;
        System.out.println("x=" + x + " y=" + y + " result=" + current + " last=" + last);

        //深度遍历
        if (current == 1) {
            return hasValidPath(grid, visited, x, y - 1, 2) || hasValidPath(grid, visited, x, y + 1, 1);
        } else if (current == 2) {
            return hasValidPath(grid, visited, x - 1, y, 4) || hasValidPath(grid, visited, x + 1, y, 3);
        } else if (current == 3) {
            return hasValidPath(grid, visited, x, y - 1, 2) || hasValidPath(grid, visited, x + 1, y, 3);
        } else if (current == 4) {
            return hasValidPath(grid, visited, x, y + 1, 1) || hasValidPath(grid, visited, x + 1, y, 3);
        } else if (current == 5) {
            return hasValidPath(grid, visited, x, y - 1, 2) || hasValidPath(grid, visited, x - 1, y, 4);
        } else {
            return hasValidPath(grid, visited, x, y + 1, 1) || hasValidPath(grid, visited, x - 1, y, 4);
        }
    }
}
