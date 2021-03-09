public class Case {

    @BeforeSuite
    public void methodBefore() {
        System.out.println("Case.methodBefore (Начальный Метод)");
    }

    @AfterSuite
    public void methodAfter() {
        System.out.println("Case.methodAfter (Конечный Метод)");
    }

    @Test(priority = 8)
    public void method1() {
        System.out.println("Case.method1 (Приоритет = 8)");
    }

    @Test(priority = 5)
    public void method2() {
        System.out.println("Case.method2 (Приоритет = 5)");
    }

    @Test(priority = 3)
    public void method3() {
        System.out.println("TestCase.method3 (Приоритет = 3)");
    }

    @Test(priority = 1)
    public void method4() {
        System.out.println("TestCase.method3 (Приоритет = 1)");
    }

}