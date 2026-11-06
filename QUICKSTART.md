# Quickstart — get Team Dream Cafe onto your computer

## Step 1 — Download the files

Pick whichever is easiest for you.

**Option A (recommended): the whole project as one zip**

Download `team-dream-cafe.zip` from the project root (Files panel on the right),
then extract it. You get:

```
team-dream-cafe/
├── web/                 <- the browser version
│   ├── index.html
│   ├── styles.css
│   ├── app.js
│   └── team-dream-cafe-standalone.html
├── src/main/java/...    <- the Java version (the graded one)
├── docs/
├── build.sh, run.sh, run.bat
├── README.md
└── QUICKSTART.md
```

**Option B: the three web files**

Download `web/index.html`, `web/styles.css` and `web/app.js` and keep them
**in the same folder**. They must stay together — the HTML loads the other two
by name.

**Option C: one single file**

Download `web/team-dream-cafe-standalone.html`. CSS and JavaScript are already
inside it, so it works on its own with a double-click. Use this one if moving
three files around is a hassle.

## Step 2 — Open it in VS Code

1. Extract the zip somewhere you can find it, e.g. `Documents\team-dream-cafe`.
2. Open VS Code → **File → Open Folder…** → choose `team-dream-cafe`.
   (Open the **folder**, not the single HTML file — that keeps the file tree and
   the relative paths intact.)
3. In the Explorer you should see `web`, `src`, `docs`, `README.md`.
4. Open `web/index.html` in the editor.

From a terminal instead:

```bash
cd Documents/team-dream-cafe
code .
```

## Step 3 — Run the website

**Easiest:** in VS Code, right-click `web/index.html` → **Reveal in File Explorer**
→ double-click the file. It opens in your browser.

**Better (saved data works properly):**

- Install the **Live Server** extension in VS Code, then right-click
  `web/index.html` → **Open with Live Server**.
- Or, in a terminal:

```bash
cd web
python3 -m http.server 3000
```

then open <http://localhost:3000>.

**Watch out:** opened as a `file://` page, some browsers block `localStorage`,
so the demo still works but saved orders may disappear on reload. Running it
through a local server (or Live Server) fixes that.

## Step 4 — Run the Java version (the graded deliverable)

You need **JDK 17 or newer**. Check with `java -version`.

- Windows: double-click `run.bat` (or `run.bat` in a terminal).
- macOS / Linux:

```bash
chmod +x build.sh run.sh
./run.sh
```

- Or open the `team-dream-cafe` folder in VS Code and use the Java extension's
  Run button on `src/main/java/com/teamdreamcafe/App.java`.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| Page loads but looks unstyled | `styles.css` is not next to `index.html`, or the folder was renamed. Keep the three files together. |
| Buttons do nothing, menu empty | `app.js` is missing or in a different folder from `index.html`. Open the browser console (F12) — a 404 for `app.js` means the path is wrong. |
| Orders disappear after refresh | You opened it as `file://`; use Live Server or `python3 -m http.server`. |
| Peso sign shows as a box | Font limitation on that PC; everything else still works. |
| `javac` / `java` not recognized | A JDK is not installed or not on PATH. Install JDK 17+, then restart the terminal/VS Code. |
| Java window is blank on a school PC | Run from a terminal to see the error, and tell me the message. |
