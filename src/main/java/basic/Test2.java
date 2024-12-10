package basic;

public class Test2 {

    public static void main(String[] args) {

        int x = 1, y = 2;

        x = x ^ y;
        y = x ^ y;
        x = x ^ y;

        System.out.println("x=" + x);
        System.out.println("y=" + y);

    }
}
