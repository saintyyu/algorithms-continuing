package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/9/15 11:09 下午
 *
 * https://leetcode-cn.com/problems/max-points-on-a-line/
 * https://leetcode-cn.com/problems/max-points-on-a-line/solution/zhi-xian-shang-zui-duo-de-dian-shu-by-le-tq8f/
 *
 */
public class FindMaxPointNumInTheSameLine {
    /**
     * 问题：在一个由多个二维平面上的点构成的数组中，找出位于同一条直线上点数最多的这条直线上的点的数量
     * 分析：任意两个点都在一条直线上，所以我们可以依次穷举所有的点，看其他点和当前点之间的斜率是否相同，如果相同则这些点位于同一条直线。
     * 如何记录斜率？delta(Y)/delta(X)，肯定不行，因为一方面delta(X)可能为0，另外，浮点数直接比较是否相等可能有问题。
     * 所以我们可以记录两个值：delta(Y)和delta(X)，这里还有两个问题：第一，delta(Y)和delta(X)
     * 之间如果存在大于1的公约数，比如1/2=2/4，所以需要计算两者的最简约形式；第二，-1/2=1/-2的场景，可以通过保证delta(Y)必须大于0来保证。
     * 实现：可以用一个map来存储斜率相同的点数量
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     *
     */
    public static int findMaxPointNumInTheSameLine(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        int result = 0;
        for (int i=0;i<points.length-1;i++) {
            if (result >= points.length - i || result > points.length / 2) {
                break;
            }
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int deltaY = points[j][1] - points[i][1];
                int deltaX = points[j][0] - points[i][0];
                if (deltaX == 0) {
                    deltaY = 1;
                } else if (deltaY == 0) {
                    deltaX = 1;
                } else {
                    if (deltaY < 0) {
                        deltaY = -deltaY;
                        deltaX = -deltaX;
                    }
                    int gcd = gcd(deltaY, deltaX);
                    deltaY = deltaY / gcd;
                    deltaX = deltaX / gcd;
                }
                map.put(deltaY + "_" + deltaX, map.getOrDefault(deltaY + "_" + deltaX, 1) + 1);
            }
            for (String key : map.keySet()) {
                result = Math.max(result, map.get(key));
            }
        }
        return result;
    }


    /**
     * 最大公约数：greatest common divider
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return Math.abs(b != 0 ? gcd(b, a % b) : a);
    }


    /**
     * 问题：在一个由多个二维平面上的点构成的数组中，找出位于同一条直线上点数最多的这条直线上的这些点
     * 分析：任意两个点都在一条直线上，所以我们可以依次穷举所有的点，看其他点和当前点之间的斜率是否相同，如果相同则这些点位于同一条直线。
     * 如何记录斜率？delta(Y)/delta(X)，肯定不行，因为一方面delta(X)可能为0，另外，浮点数直接比较是否相等可能有问题。
     * 所以我们可以记录两个值：delta(Y)和delta(X)，这里还有两个问题：第一，delta(Y)和delta(X)
     * 之间如果存在大于1的公约数，比如1/2=2/4，所以需要计算两者的最简约形式；第二，-1/2=1/-2的场景，可以通过保证delta(Y)必须大于0来保证。
     * 实现：
     * 时间复杂度：
     * 空间复杂度：
     *
     */
    public static Point[] findPointsInTheSameLineWithMaxPointNum(Point[] points) {
        if (points == null) {
        }
        if (points == null || points.length <= 2) {
            return points;
        }
        List<Point> result = new ArrayList<>();
        for (int i=0;i<points.length-1;i++) {
            if (result.size() >= points.length - i || result.size() > points.length / 2) {
                break;
            }
            Map<String, List<Point>> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int deltaY = points[j].y - points[i].y;
                int deltaX = points[j].x - points[i].x;
                if (deltaX == 0) {
                    deltaY = 1;
                } else if (deltaY == 0) {
                    deltaX = 1;
                } else {
                    if (deltaY < 0) {
                        deltaY = -deltaY;
                        deltaX = -deltaX;
                    }
                    int gcd = gcd(deltaY, deltaX);
                    deltaY = deltaY / gcd;
                    deltaX = deltaX / gcd;
                }
                if (map.containsKey(deltaY + "_" + deltaX)) {
                    map.get(deltaY + "_" + deltaX).add(points[j]);
                } else {
                    List<Point> listPoint = new ArrayList<>();
                    listPoint.add(points[i]);
                    listPoint.add(points[j]);
                    map.put(deltaY + "_" + deltaX, listPoint);
                }
            }
            for (String key : map.keySet()) {
                result = result.size() > map.get(key).size() ? result : map.get(key);
            }
        }
        return result.toArray(new Point[0]);
    }


    public static void main(String[] args) {
        Point[] points = new Point[3];
        points[0] = new Point(1, 1);
        points[1] = new Point(2, 2);
        points[2] = new Point(3, 3);
        findPointsInTheSameLineWithMaxPointNum(points);
    }
}
