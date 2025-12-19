# Quiz System (Web + Console)

A small quiz application implemented in two forms:

- Web version: interactive HTML/CSS/JavaScript (uses a Stack and Map internally to track questions and answers).
- Console version: Java program (`QuizApp.java`) that runs in the terminal and demonstrates the same logic for stack/map and scoring.

## Features
- Clickable options (web) or numbered choices (console).
- 10 points per correct answer.
- Stack (LIFO) recording of questions asked.
- Map storing question -> chosen answer.
- Restart support and graceful handling of invalid input.

## Files
- `index.html` — web UI entry point
- `script.js` — web quiz logic (stack, map, scoring, restart)
- `style.css` — styles for the web UI
- `QuizApp.java` — console/terminal quiz implementation

## Run (Web)
Open the web version in your default browser:

```powershell
cd "D:\quiz system with stack and hashmaps"
Start-Process ".\index.html"
```

- The page will load the quiz and attach `script.js` automatically.
- Open browser DevTools (F12) → Console to see the Stack and Map outputs logged when the quiz finishes.

## Run (Java / Console)
Compile and run the Java console app:

```powershell
cd "D:\quiz system with stack and hashmaps"
javac QuizApp.java
java QuizApp
```

- Follow on-screen prompts: enter option numbers (1–n), press Enter.
- Commands while running: type `restart` to restart the quiz, or `exit`/`quit` to quit.

## Troubleshooting
- "File not found" when using `Start-Process`: confirm the path and filename; run `Get-ChildItem` in PowerShell to list files.
- If `javac` or `java` are not recognized: install a JDK and ensure `java`/`javac` are on your PATH.
- Web confetti comes from a CDN; if it fails to load the quiz still works (confetti is optional).

