import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        start(ClassTest.class);
    }

    private static void start(Class<ClassTest> tClass) {
        ClassTest test;
        try {
            test = tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Method before = null;
        Method after = null;
        Method[] methods = ClassTest.class.getMethods();
        List<Priority> testingMethods = new ArrayList<>();

        for (Method method : methods)
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (before != null) {
                    throw new RuntimeException("Анатация @BeforeSuite используется больше 1го раза");
                }
                before = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (after != null) {
                    throw new RuntimeException("Анатация @AfterSuite используется больше 1го раза");
                }
                after = method;
            } else if (method.getAnnotation(Test.class) != null) {
                Test annotation = method.getAnnotation(Test.class);
                testingMethods.add(new Priority(method, annotation.priority()));
            }

        testingMethods.sort(Comparator.comparing(Priority::getPriority));

        try {
            if (before != null) {
                before.invoke(test);
            }
            for (Priority methodsPriority : testingMethods) {
                methodsPriority.getMethod().invoke(test);
            }
            if (after != null) {
                after.invoke(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}