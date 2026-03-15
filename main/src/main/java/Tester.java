@FunctionalInterface
public interface Tester {
    void run(Class<?> clazz, StringBuilder report) throws Exception;
}
