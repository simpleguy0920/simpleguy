package sql;

public class TestC1 {
    public static void main(String[] args) {
        int a = 2;
        int b = a++ << ++a + ++a;
        System.out.println(b);
    }
}
