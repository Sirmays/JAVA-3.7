import java.lang.reflect.Method;

public class Priority {
    private Method method;
    private Integer priority;

    public Priority(Method method, Integer priority) {
        this.method = method;
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }
}