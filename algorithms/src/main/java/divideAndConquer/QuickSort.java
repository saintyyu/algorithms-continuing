package divideAndConquer;

import com.alibaba.fastjson.JSON;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/6 4:25 下午
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {5,2,5,234,5,65,235};
        quickSort(array, 0, array.length-1);
        System.out.println(JSON.toJSONString(array));
    }

    public static void quickSort(int[] array, int s, int t) {
        if (s < t) {
            int i = quickPartition(array, s, t);
            quickSort(array, s, i-1);
            quickSort(array, i+1, t);
        }
    }

    public static int quickPartition(int[] array, int i, int j) {
        int tmp = array[i];
        while(i < j) {
            while (i < j && array[j] >= tmp) {
                j--;
            }
            if (i < j) {
                array[i] = array[j];
                i++;
            }
            while (i < j && array[i] <= tmp) {
                i++;
            }
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = tmp;
        return i;
    }

}
