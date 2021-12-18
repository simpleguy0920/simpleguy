package sql;

public class TestAssert {
    public static void main(String[] args) throws InterruptedException {
        TestAssert a = new TestAssert();
        System.out.println("Created " + a);
        for (int i = 0; i < 1_000_000_000; i++) {
            if (i % 1_000_00 == 0)
                System.gc();
        }
        System.out.println("done.");
    }

    @Override
    protected void finalize() {
        System.out.println(this + " was finalized!");
    }

}
