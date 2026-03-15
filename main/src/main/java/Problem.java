import java.util.List;

public class Problem {
    // class fields
    private final int id;
    private final String title;
    private final String description;
    private final String methodSignature;
    private final String starterCode;
    private final List<String> hints;
    private final Tester tester;

    // constructor
    public Problem(
            int id,
            String title,
            String description,
            String methodSignature,
            String starterCode,
            List<String> hints,
            Tester tester
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.methodSignature = methodSignature;
        this.starterCode = starterCode;
        this.hints = hints;
        this.tester = tester;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public String getStarterCode() {
        return starterCode;
    }

    public List<String> getHints() {
        return hints;
    }

    public Tester getTester() {
        return tester;
    }
}
