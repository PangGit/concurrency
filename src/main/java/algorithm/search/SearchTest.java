package algorithm.search;

/**
 * 查找
 */
public class SearchTest {

    /**
     * 无序队列
     */
    public static final int[] disorderlyQueue = {2, 1, 4, 0, 5, 3, 6, 9, 8, 7};

    /**
     * 有序队列
     */
    public static final int[] orderlyQueue = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};


    public static void main(String[] args) {

        System.out.println("---linear search---position："
                + SequenceSearch.search(disorderlyQueue, 5, disorderlyQueue.length));

        System.out.println("---binary search---position："
                + BinarySearch.search(orderlyQueue, 5, 0, 9));
    }
}
