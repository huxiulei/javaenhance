package mytest;

import java.lang.reflect.Array;

/**
 * 最小堆
 *
 * @author Ayou
 *
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> {

    private int maxSize;
    private int curSize;
    private Node[] heapArray;

    public MinHeap(int size) {
        maxSize = size;
        curSize = 0;
        heapArray = (Node[]) Array.newInstance(Node.class, maxSize);
    }

    public void push(T key) {
        if (curSize < maxSize) {
            heapArray[curSize] = new Node(key);
            filterUp(curSize);
            curSize++;
        } else {
            if (heapArray[0].key.compareTo(key) < 0) {
                heapArray[0].key = key;
                filterDown(0);
            }
        }
    }

    private void filterUp(int start) {
        Node cur = heapArray[start];
        int parentIdx = (start - 1) / 2;
        while (start > 0) {
            if (heapArray[parentIdx].key.compareTo(heapArray[start].key) < 0) {
                // 父节点的值较小，不进行调整
                break;
            } else {
                heapArray[start] = heapArray[parentIdx];
                start = parentIdx;
                parentIdx = (start - 1) / 2;
                heapArray[start] = cur;
            }
        }
    }

    private void filterDown(int start) {
        Node cur = heapArray[start];
        int childIdx = start * 2 + 1;
        while (childIdx < curSize) {
            if (childIdx < curSize - 1
                    && heapArray[childIdx].key
                    .compareTo(heapArray[childIdx + 1].key) > 0) {
                // 指向值较小的孩子
                childIdx++;
            }
            if (heapArray[childIdx].key.compareTo(heapArray[start].key) > 0) {
                // 孩子节点的值较大，不进行调整
                break;
            } else {
                heapArray[start] = heapArray[childIdx];
                start = childIdx;
                childIdx = start * 2 + 1;
                heapArray[start] = cur;
            }
        }
    }

    public T pop() {
        Node cur = heapArray[0];
        heapArray[0] = heapArray[curSize - 1];
        heapArray[curSize - 1] = null;
        curSize--;
        filterDown(0);
        return cur == null ? null : cur.key;
    }

    public void printer() {
        for (Node n : heapArray) {
            if (n == null)
                break;
            System.out.print(n.key + " ");
        }
        System.out.print("\r\n");
    }

    class Node {
        private T key;

        Node(T e) {
            this.key = e;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MinHeap<Integer> hp = new MinHeap<Integer>(5);

        hp.push(53);
        hp.push(17);
        hp.push(78);
        hp.push(9);
        hp.push(45);
        hp.push(65);
        hp.push(87);
        hp.push(23);
        hp.printer();

        hp.pop();
        hp.printer();

    }

}  