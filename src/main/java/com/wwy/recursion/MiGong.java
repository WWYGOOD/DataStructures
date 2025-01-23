package com.wwy.recursion;

public class MiGong {
    public static void main(String[] args) {
        /**
         * 迷宫问题
         */
//        创建一个地图
        int[][] map = new int[8][7];
//   设置上下地图的墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
//        设置地图的左右墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
        map[3][4] = 1;
        map[3][5] = 1;
        for (int[] m : map) {
            for (int d : m) {
                System.out.format("%3d", d);
            }
            System.out.println(" ");
        }
        setWay(map, 1, 1);
        System.out.println("-------------------------------------------------");
        for (int[] m : map) {
            for (int d : m) {
                System.out.format("%3d", d);
            }
            System.out.println(" ");
        }
    }

    /**
     * @param map 地图
     * @param i   出生点
     * @param j   出生点
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }

    }
}
