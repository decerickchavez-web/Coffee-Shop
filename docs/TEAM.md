# Team Dream Cafe — Team and Contributions

| Member | Role / module | Evidence for evaluation |
| --- | --- | --- |
| **John Evaris** (group creator) | Project lead, documentation and presentation | README and `docs/`, test report, slide deck, presentation walkthrough |
| **Decerick Chavez** | Menu model, pricing rules and automated tests | `model/` package (`MenuItem`, `Drink`, `Order`, `OrderItem`, discount policies), unit tests |
| **Kenneth Clier Yusores** | Order-taking screen and interface theme | `ui/PosPanel.java`, `ui/Theme.java`, `ui/FlatButton.java`, validation and feedback |
| **Khyle Etolle** | Checkout, receipt, inventory and sales reports | Checkout dialog and receipt, `Inventory` screen, sales report screen, `web/` demo |

> These are the suggested assignments based on the modules that exist. If the real
> split was different, edit this table and the About screen — nothing in the code
> depends on it.

## How to demonstrate individual contributions

1. Each member opens the file(s) listed in their row and explains one method
   they wrote (what it takes in, what it returns, which edge case it handles).
2. Each member describes one bug they found and how it was fixed.
3. Show a Git history (or a short log in the documentation) with each member's
   commits, if you used version control as a group.

## Splitting the remaining work

- Checkout and receipt printing: the person assigned to checkout.
- Inventory rules (stock per drink and add-on): the person assigned to inventory.
- Sales report and CSV persistence: the person assigned to reports.
- Test table, screenshots and final documentation: the project lead.
