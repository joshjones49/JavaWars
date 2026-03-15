# JavaWars

JavaWars is an early-stage Java desktop app inspired by Codewars.

## Current Project State

The project currently contains a Swing-based UI scaffold for a coding-practice style app. The main window is implemented in `main/src/main/java/App.java` and includes:

- a top bar with title text
- action buttons (`Random Problem`, `Reset Starter`, `Hint`, `Check Solution`)
- a font dropdown placeholder
- a split layout with scrollable text areas for problem content and results/output

At this stage, the app focuses on layout and structure. Problem loading, hint logic, solution checking, and persistence are not implemented yet.

## Tech Stack

- Java (configured for source/target `24` in `main/pom.xml`)
- Swing (`JFrame`, `JPanel`, `JTextArea`, `JSplitPane`, etc.)
- Maven (module located in `main/`)

## Project Layout

- `main/pom.xml` - Maven configuration
- `main/src/main/java/App.java` - application entry point and UI construction
- `main/src/main/resources/` - resources directory (currently unused)
- `main/src/test/java/` - test directory (currently unused)

## Running the App

From the repository root, build the Maven module in `main/`:

```powershell
mvn -f .\main\pom.xml clean package
```

Then run the `App` class from your IDE, or using the compiled classes/classpath setup you prefer.

## Next Milestones

- add a problem model and random problem loading
- implement starter-code reset behavior
- implement hints and solution checks
- connect basic test cases/execution feedback to the result panel
