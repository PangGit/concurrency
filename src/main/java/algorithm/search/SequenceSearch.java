package algorithm.search;

/**
 * 线性查找
 *
 * @author pang
 * @since 2023/4/15 17:38
 */
public class SequenceSearch {

    /**
     * 顺序查找（linear search ）
     *
     * @param a     数组
     * @param value 查询目标值
     * @param n     数组长度
     * @return 目标值位置
     */
    public static int search(int[] a, int value, int n) {
        int i;
        for (i = 0; i < n; i++) {
            if (a[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
