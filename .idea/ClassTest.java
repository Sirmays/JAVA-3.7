public class ClassTest {

    @BeforeSuite
    public void doTask1() {
        System.out.println("@BeforeSuite");
    }

    @Test(priority = 5)
    public void doTask2() {
        System.out.println("@Test");


    }
    @Test(priority = 10)
    public void doTask3() {
        System.out.println("@Test");
    }

    @Test(priority = 7)
    public void doTask4() {
        System.out.println("@Test");
    }

    @AfterSuite
    public void doTask5() {
        System.out.println("@AfterSuite");
    }

}