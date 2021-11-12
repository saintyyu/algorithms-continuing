package bitree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/12 11:16 下午
 */
public class SerializeBiTree {

    public static Integer[] serializeBiTree(BiTree biTree) {
        if (biTree == null) {
            return new Integer[0];
        }
        List<Integer> list = new ArrayList<>();
        LinkedList<BiTree> linkedList = new LinkedList<>();
        linkedList.addLast(biTree);
        while (!linkedList.isEmpty()) {
            BiTree tmp = linkedList.pollFirst();
            if (tmp == null) {
                list.add(null);
            } else {
                list.add(tmp.val);
                linkedList.addLast(tmp.left);
                linkedList.addLast(tmp.right);
            }
        }
        return list.toArray(new Integer[0]);
    }

    public static BiTree deserializeBiTree(Integer[] integers) {
        if (integers == null || integers[0] == null) {
            return null;
        }
        LinkedList<BiTree> linkedList = new LinkedList<>();
        BiTree root = new BiTree(integers[0]);
        linkedList.addLast(root);
        int index = 0;
        while (!linkedList.isEmpty()) {
            BiTree[] arrayList = linkedList.toArray(new BiTree[0]);
            linkedList.clear();
            for (BiTree biTree : arrayList) {
                if (biTree != null) {
                    index++;
                    if (integers[index] != null) {
                        biTree.left = new BiTree(integers[index]);
                        linkedList.addLast(biTree.left);
                    } else {
                        linkedList.addLast(null);
                    }
                    index++;
                    if (integers[index] != null) {
                        biTree.right = new BiTree(integers[index]);
                        linkedList.addLast(biTree.right);
                    } else {
                        linkedList.addLast(null);
                    }
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BiTree biTree = new BiTree(1);
        biTree.left = new BiTree(2);
        biTree.right = new BiTree(3);
        biTree.right.left = new BiTree(4);
        biTree.right.right = new BiTree(5);
        biTree.right.left.right = new BiTree(6);
        biTree.right.right.left = new BiTree(7);
        System.out.println(JSON.toJSONString(serializeBiTree(biTree)));
        Integer[] integers = serializeBiTree(biTree);
        BiTree root = deserializeBiTree(integers);
        System.out.println(JSON.toJSONString(serializeBiTree(root)));
    }
}
