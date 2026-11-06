# Team Dream Cafe — Coffee Shop Management System

A Java desktop application (Swing GUI) for a milk-tea and coffee shop: take orders,
price them, apply discounts, track stock and report sales.

**Current build: step 1 — working order-taking screen.** Checkout, inventory,
reports and saved data come in the next steps.

---

## 1. Why Java + Swing

Your assignment requires **Java**, so the real decision is the GUI toolkit.

| Option | Verdict |
| --- | --- |
| **Java Swing** | ✅ Ships inside every JDK. Runs with `javac` + `java` only — no internet, no extra SDK, works in any computer lab. |
| JavaFX | ❌ Nicer animations, but needs a separate SDK/module path. One misconfigured laptop and the demo fails. |
| Console program | ❌ The rubric explicitly grades GUI design. |

Swing gives full credit for "GUI development, intuitive navigation, consistent
layouts, input validation and clear feedback", and it needs nothing installed
beyond the JDK you already have for your Java class.

## 2. Requirements

- **JDK 17 or newer** (`java -version` should print 17 or higher).
- Any IDE is optional: IntelliJ IDEA, VS Code with the Java extension, Eclipse, or NetBeans all open this folder directly.

Check your JDK:

```bash
java -version
javac -version
```

## 3. How to run

**Windows**

```bat
run.bat
```

**macOS / Linux**

```bash
chmod +x build.sh run.sh
./run.sh
```

Or manually:

```bash
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
java -cp out com.teamdreamcafe.App
```

## 4. Project structure

```
team-dream-cafe/
├── build.sh, run.sh, run.bat      # build + launch scripts (JDK only)
├── README.md                      # this guide
├── docs/                          # documentation and test report (later steps)
└── src/main/java/com/teamdreamcafe/
    ├── App.java                   # entry point, starts the window on the EDT
    ├── model/                     # data + business rules (no Swing imports)
    │   ├── MenuItem.java          # abstract base class, abstract priceFor(Size)
    │   ├── Drink.java             # extends MenuItem, medium/large prices
    │   ├── AddOn.java             # paid extras (pearls, nata, ...)
    │   ├── Category.java          # menu sections (enum)
    │   ├── Size.java              # Medium / Large (enum)
    │   ├── OrderItem.java         # one line: drink + size + add-ons + qty
    │   ├── Order.java             # lines, discount policy, totals
    │   ├── DiscountPolicy.java    # interface (strategy pattern)
    │   ├── NoDiscount.java
    │   └── PercentDiscount.java   # Senior / PWD 20%
    ├── service/
    │   ├── MenuCatalog.java       # the 65 drinks + 7 add-ons, read-only
    │   └── OrderService.java      # current order + order numbering
    ├── util/
    │   └── Money.java             # peso rounding and formatting
    └── ui/
        ├── MainFrame.java         # window + espresso navigation rail
        ├── Theme.java             # colours, fonts, card styling
        ├── FlatButton.java        # custom-painted rounded button
        ├── PosPanel.java          # order-taking screen
        ├── PlaceholderPanel.java  # screens planned for later steps
        └── AboutPanel.java        # system overview for the presentation
```

**Layering rule:** `ui` may use `service`, `service` may use `model`,
`model` uses nothing but `util`. The model has zero Swing imports, which is why
the pricing rules can be unit-tested without opening a window.

## 5. What already works (step 1)

- Browse the full menu — 65 drinks in 9 categories, from the shop's price boards.
- Search by name and filter by category.
- Choose size (M/L), quantity (1–99), and any of 7 paid add-ons.
- Add to the order; identical drink + size + add-on lines merge into one row.
- Remove a line or clear the order (with a confirmation dialog).
- Live subtotal, discount and total, with peso formatting.
- Input validation: no drink selected → red error message, quantity bounded by the spinner, prices validated in the model.
- Order numbering (`TDC-1001`, `TDC-1002`, …).

### Menu data source

The drinks, sections and prices (₱) come from the two menu boards provided.
The house specials are labelled **Team Dream Signature** and **Team Dream Matcha**
so the menu matches the shop name. Add-ons are ₱8 / ₱18 per the board.

## 6. Roadmap

| Step | Scope |
| --- | --- |
| 1 ✅ | Menu catalogue, order model, pricing, order-taking screen |
| 2 | Checkout: discount buttons, cash tendered, change, receipt dialog + print |
| 3 | Transaction history and order lookup |
| 4 | Inventory: stock levels, auto-deduct on sale, low-stock warnings |
| 5 | Sales reports: daily totals, best sellers, per-category breakdown |
| 6 | Persistence: save orders, stock and menu to CSV so data survives restarts |
| 7 | JUnit tests, test report, documentation, screenshots for submission |

## 7. Mapping to the grading rubric

| Criterion | Where it is demonstrated |
| --- | --- |
| OOP | `MenuItem` abstraction, `Drink` inheritance, `DiscountPolicy` interface + implementations, encapsulation in `Order`/`OrderItem` |
| GUI | `MainFrame` navigation rail + `CardLayout`, consistent `Theme`, validation and success/error feedback |
| Functionality | Pricing, merging, removing, discounts, totals, edge cases |
| Testing | Unit tests for pricing/discount rules (step 7) + manual test table |
| Team evaluation | Assign one package/module per member (see below) |

### Suggested team split

- **Member A** — `model` package (pricing, discounts) + unit tests.
- **Member B** — `ui` order-taking screen and `Theme`.
- **Member C** — checkout, payment, receipt printing.
- **Member D** — inventory module.
- **Member E** — reports, persistence, documentation and screenshots.

## 8. Browser demo (`web/`)

Because the graded deliverable is a Java desktop app, `web/` holds a
browser version of the same system that anyone can open without installing a
JDK — handy for checking the design or showing it on a phone during the
presentation.

- **Built with:** HTML, CSS and vanilla JavaScript (no frameworks, no build step).
- **Same data:** the 65 drinks, 7 add-ons and prices from the Java catalogue.
- **Same rules:** line totals, 20% Senior/PWD and 10% promo discounts, change.
- **Extra screens:** checkout with cash tendered and change, printable receipt,
  order history, inventory with automatic stock deduction, daily sales report.
- **Data:** saved in `localStorage`, so orders and stock survive a page reload.

Run it by opening `web/index.html`, or serve the folder:

```bash
cd web && python3 -m http.server 3000
```

This demo does **not** replace the Java submission — the rubric grades the Java
source in `src/main/java`.

## 9. Team

| Member | Module |
| --- | --- |
| John Evaris (group creator) | Project lead, documentation and presentation |
| Decerick Chavez | Menu model, pricing rules and automated tests |
| Kenneth Clier Yusores | Order-taking screen and interface theme |
| Khyle Etolle | Checkout, receipt, inventory and sales reports |

See `docs/TEAM.md` for the evidence to present for each member.

## 10. Notes

- The peso sign `₱` is defined once in `Money.SYMBOL`. If a lab PC shows a box
  instead of the symbol, change that one constant to `"PHP "`.
- No external libraries are used, so the project compiles offline.
