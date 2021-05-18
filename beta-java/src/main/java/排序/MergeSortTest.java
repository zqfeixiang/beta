package 排序;

import java.util.Arrays;

/**
 * 基本思想
 * 归并排序是建立在归并操作上的一种有效的排序算法,该算法是采用分治法的一个非常典型的应用。
 * 即先使每个子序列有序，再将已有序的子序列合并，得到完全有序的序列。这里给出一种递归形式的归并排序实现。
 */
public class MergeSortTest {

    public static void main(String[] args) {
        int[] list = {5,2,8,1,9,4,3,10,7,15,6};
        mergetSort(list);
        Arrays.stream(list).forEach(System.out::println);
    }

    /**
     * 归并排序算法
     * @param list
     */
    private static void mergetSort(int[] list) {
        mergeSort(list, 0, list.length-1);
    }

    private static void mergeSort(int[] list, int left, int right) {
        // 终止条件
        if (list == null || left == right) return;

        // 确定分割的边界
        int middle = left + (right - left) / 2;

        // 对左半部分调用递归方法，使其有序
        mergeSort(list, left, middle);

        // 对右半部分调用递归方法，使其有序
        mergeSort(list, middle+1, right);

        // 合并左右两部分，使整个数组有序
        merge(list, left, middle, right);
    }

    /**
     * @param list 要合并的数组
     * @param left 左边界
     * @param middle 中间的分界
     * @param right 右边界
     */
    private static void merge(int[] list, int left, int middle, int right) {
        // 首先定义一个辅助数组
        int[] helpArr = new int[right-left+1];
        int lPoint = left;
        int rPoint = middle+1;
        // 辅助指针
        int i  = 0;

        // 比较并填充辅助数组
        while (lPoint <= middle && rPoint <= right){
            if (list[lPoint] <= list[rPoint]){
                helpArr[i++] = list[lPoint++];
            }else {
                helpArr[i++] = list[rPoint++];
            }
        }

        // 将剩余元素填充至辅助数组
        while (lPoint <= middle){
            helpArr[i++] = list[lPoint++];
        }
        while (rPoint <= right){
            helpArr[i++] = list[rPoint++];
        }

        // 将辅助数组中的元素回填至原数组
        for (int j = 0; j < helpArr.length; j++){
            list[left + j] = helpArr[j];
        }
    }
}
