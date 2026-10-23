# Musu Bangura — PROG209 Basic Interactive GUI

## Project summary
A Java Swing desktop GUI for the PROG209 Event Driven Programming assignment. It uses a fixed black-and-yellow visual theme and an MB monogram icon.

## Included requirements
- `SwingAssignment` class extending `BaseWindow` (inheritance).
- A constructor initializes a `JFrame` variable named `frame`, with title `Java Swing Assignment`, dimensions 800 × 800, and `EXIT_ON_CLOSE` behaviour.
- A `JPanel` named `panel` and a `JLabel` named `label` with the assignment welcome message.
- A custom `ImageIcon` loaded from `src/main/resources/assets/application-icon.png`.
- Change Text: accepts a non-empty message and updates the label; cancellation and empty input are handled.
- Change Background: toggles between fixed dark-charcoal and pale-yellow backgrounds, with text contrast adjusted.
- No arbitrary colour chooser or Theme Color button is included.

## Requirements
- JDK 17 or newer.
- Maven is optional for compilation, but `pom.xml` is provided for IDE import.
- A graphical display is required to use the application.

## Run in IntelliJ IDEA or VS Code
Open the folder as a Java/Maven project, then run `sl.edu.prog209.SwingAssignment`.

## Compile and run from a terminal without Maven
```sh
mkdir -p out
javac -d out $(find src/main/java -name '*.java')
cp -R src/main/resources/* out/
java -cp out sl.edu.prog209.SwingAssignment
```

## Smoke test
```sh
mkdir -p out
javac -d out $(find src/main/java src/test/java -name '*.java')
cp -R src/main/resources/* out/
xvfb-run -a java -cp out sl.edu.prog209.GuiSmokeTest
```
On Windows or macOS, run the smoke-test class from an IDE with a normal desktop session. The test checks core component setup and the text/background behavior; it does not replace screenshot evidence required by the lecturer.
