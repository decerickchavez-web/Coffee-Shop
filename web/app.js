/* ============================================================
   Team Dream Cafe — browser demo logic
   Same menu, prices and rules as the Java Swing build.
   Orders and stock are saved in localStorage, so a reload keeps them.
   ============================================================ */

const MENU = [
  ["MCH-01", "Matcha Latte", "MATCHA_SERIES", 48, 58],
  ["MCH-02", "Milktea Matcha", "MATCHA_SERIES", 48, 58],
  ["MCH-03", "Dirty Matcha", "MATCHA_SERIES", 58, 68],
  ["MCH-04", "Team Dream Matcha", "MATCHA_SERIES", 58, 68],
  ["MCH-05", "White Chocolate Matcha", "MATCHA_SERIES", 58, 68],
  ["MCH-06", "Strawberry Matcha", "MATCHA_SERIES", 68, 78],
  ["MCH-07", "Chocolate Matcha", "MATCHA_SERIES", 68, 78],

  ["COF-01", "Americano", "COFFEE_BASED", 48, 58],
  ["COF-02", "Latte", "COFFEE_BASED", 48, 58],
  ["COF-03", "Hazelnut Latte", "COFFEE_BASED", 48, 58],
  ["COF-04", "Vanilla Latte", "COFFEE_BASED", 48, 58],
  ["COF-05", "Brown Sugar Latte", "COFFEE_BASED", 48, 58],
  ["COF-06", "Team Dream Signature", "COFFEE_BASED", 48, 58],

  ["SPC-01", "White Mocha", "SPECIAL_COFFEE", 58, 68],
  ["SPC-02", "Dark Mocha", "SPECIAL_COFFEE", 58, 68],
  ["SPC-03", "Caramel Latte", "SPECIAL_COFFEE", 58, 68],
  ["SPC-04", "Caramel Macchiato", "SPECIAL_COFFEE", 58, 68],
  ["SPC-05", "White Caramel Mocha", "SPECIAL_COFFEE", 58, 68],
  ["SPC-06", "Salted Caramel", "SPECIAL_COFFEE", 58, 68],
  ["SPC-07", "Spanish Latte", "SPECIAL_COFFEE", 58, 68],
  ["SPC-08", "Tres Leches Latte", "SPECIAL_COFFEE", 58, 68],
  ["SPC-09", "Einspanner Latte", "SPECIAL_COFFEE", 58, 68],
  ["SPC-10", "Choco Hazelnut", "SPECIAL_COFFEE", 58, 68],
  ["SPC-11", "Caramel Coffee Jelly", "SPECIAL_COFFEE", 58, 68],
  ["SPC-12", "Blueberry Greek Latte", "SPECIAL_COFFEE", 58, 68],

  ["MTB-01", "Caramel", "MILK_TEA", 38, 48],
  ["MTB-02", "Okinawa", "MILK_TEA", 38, 48],
  ["MTB-03", "Wintermelon", "MILK_TEA", 38, 48],
  ["MTB-04", "Brown Sugar", "MILK_TEA", 38, 48],
  ["MTB-05", "Taro", "MILK_TEA", 48, 58],
  ["MTB-06", "Mango", "MILK_TEA", 48, 58],
  ["MTB-07", "Strawberry", "MILK_TEA", 48, 58],
  ["MTB-08", "Choco Hazelnut", "MILK_TEA", 48, 58],
  ["MTB-09", "Dark Chocolate", "MILK_TEA", 48, 58],

  ["MLK-01", "Taro", "MILK_BASED", 48, 58],
  ["MLK-02", "Mango", "MILK_BASED", 48, 58],
  ["MLK-03", "Chocolate", "MILK_BASED", 48, 58],
  ["MLK-04", "Strawberry", "MILK_BASED", 48, 58],

  ["SML-01", "Choco Berry", "SPECIAL_MILK_BASED", 58, 68],
  ["SML-02", "Mango Berry", "SPECIAL_MILK_BASED", 58, 68],
  ["SML-03", "Choco Mango", "SPECIAL_MILK_BASED", 58, 68],
  ["SML-04", "Brown Sugar Boba Milk", "SPECIAL_MILK_BASED", 58, 68],
  ["SML-05", "Milo Dinosaur", "SPECIAL_MILK_BASED", 58, 68],

  ["FT-01", "Lemon", "FRUIT_TEA", 38, 48],
  ["FT-02", "Mango", "FRUIT_TEA", 38, 48],
  ["FT-03", "Lychee", "FRUIT_TEA", 38, 48],
  ["FT-04", "Blueberry", "FRUIT_TEA", 38, 48],
  ["FT-05", "Green Apple", "FRUIT_TEA", 38, 48],
  ["FT-06", "Strawberry", "FRUIT_TEA", 38, 48],
  ["FT-07", "Honey Peach", "FRUIT_TEA", 38, 48],
  ["FT-08", "Passion Fruit", "FRUIT_TEA", 38, 48],

  ["FRC-01", "Dark Mocha", "FRAPPE_COFFEE", 68, 78],
  ["FRC-02", "White Mocha", "FRAPPE_COFFEE", 68, 78],
  ["FRC-03", "Choco Hazelnut", "FRAPPE_COFFEE", 68, 78],
  ["FRC-04", "Team Dream Signature", "FRAPPE_COFFEE", 68, 78],
  ["FRC-05", "Caramel Macchiato", "FRAPPE_COFFEE", 68, 78],
  ["FRC-06", "Java Chip", "FRAPPE_COFFEE", 78, 88],
  ["FRC-07", "Caramel Coffee Jelly", "FRAPPE_COFFEE", 78, 88],

  ["FRM-01", "Matcha", "FRAPPE_MILK", 68, 78],
  ["FRM-02", "Cookies and Cream", "FRAPPE_MILK", 68, 78],
  ["FRM-03", "Chocolate", "FRAPPE_MILK", 78, 88],
  ["FRM-04", "Taro", "FRAPPE_MILK", 78, 88],
  ["FRM-05", "Mango", "FRAPPE_MILK", 78, 88],
  ["FRM-06", "Strawberry", "FRAPPE_MILK", 78, 88],
  ["FRM-07", "Milo Dinosaur", "FRAPPE_MILK", 78, 88]
].map(function (row) {
  return { id: row[0], name: row[1], cat: row[2], m: row[3], l: row[4] };
});

const CATEGORIES = [
  ["MATCHA_SERIES", "Matcha Series"],
  ["COFFEE_BASED", "Coffee Based"],
  ["SPECIAL_COFFEE", "Special Coffee Based"],
  ["MILK_TEA", "Milk Tea Baby"],
  ["MILK_BASED", "Milk Based"],
  ["SPECIAL_MILK_BASED", "Special Milk Based"],
  ["FRUIT_TEA", "Fruit Tea Series"],
  ["FRAPPE_COFFEE", "Frappe Coffee Based"],
  ["FRAPPE_MILK", "Frappe Milk Based"]
];

const CAT_LABEL = {};
CATEGORIES.forEach(function (pair) { CAT_LABEL[pair[0]] = pair[1]; });

const ADDONS = [
  { id: "milk", name: "Milk", price: 8 },
  { id: "nata", name: "Nata", price: 8 },
  { id: "pearls", name: "Pearls", price: 8 },
  { id: "jelly", name: "Coffee Jelly", price: 8 },
  { id: "syrup", name: "Flavoured Syrup", price: 8 },
  { id: "coffee", name: "Coffee Shot", price: 18 },
  { id: "whip", name: "Whipped Cream", price: 18 }
];

const DISCOUNTS = [
  { id: "none", label: "No discount", rate: 0 },
  { id: "senior", label: "Senior / PWD (20%)", rate: 0.2 },
  { id: "promo", label: "Promo DREAM10 (10%)", rate: 0.1 }
];

const STOCK_DEFS = [
  { id: "cupM", name: "16 oz cups", unit: "pcs", low: 60, start: 400 },
  { id: "cupL", name: "22 oz cups", unit: "pcs", low: 60, start: 400 },
  { id: "beans", name: "Coffee beans", unit: "g", low: 600, start: 6000 },
  { id: "matcha", name: "Matcha powder", unit: "g", low: 300, start: 3000 },
  { id: "tea", name: "Tea base", unit: "ml", low: 1200, start: 9000 },
  { id: "milk", name: "Fresh milk", unit: "ml", low: 2500, start: 18000 },
  { id: "pearls", name: "Tapioca pearls", unit: "g", low: 300, start: 2500 },
  { id: "nata", name: "Nata de coco", unit: "g", low: 300, start: 2000 },
  { id: "jelly", name: "Coffee jelly", unit: "g", low: 300, start: 2000 },
  { id: "syrup", name: "Flavoured syrup", unit: "ml", low: 500, start: 4000 },
  { id: "cream", name: "Whipped cream", unit: "servings", low: 20, start: 150 }
];

/* How each drink and add-on consumes stock. */
const RECIPE = {
  category: {
    MATCHA_SERIES: ["matcha", 12],
    COFFEE_BASED: ["beans", 18],
    SPECIAL_COFFEE: ["beans", 18],
    MILK_TEA: ["tea", 12],
    MILK_BASED: ["milk", 160],
    SPECIAL_MILK_BASED: ["milk", 160],
    FRUIT_TEA: ["tea", 14],
    FRAPPE_COFFEE: ["beans", 18],
    FRAPPE_MILK: ["milk", 160]
  },
  addon: {
    milk: ["milk", 30],
    nata: ["nata", 30],
    pearls: ["pearls", 30],
    jelly: ["jelly", 30],
    syrup: ["syrup", 20],
    coffee: ["beans", 9],
    whip: ["cream", 1]
  }
};

const STORAGE_KEY = "teamdream-cafe-v1";

/* ---------------- state ---------------- */

let cart = [];
let selectedId = null;
let filterCat = "";
let searchTerm = "";
let db = loadDb();

function defaultDb() {
  const stock = {};
  STOCK_DEFS.forEach(function (def) { stock[def.id] = def.start; });
  return { orders: [], stock: stock, nextOrder: 1001 };
}

function loadDb() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) { return defaultDb(); }
    const parsed = JSON.parse(raw);
    if (!parsed || !Array.isArray(parsed.orders) || !parsed.stock) { return defaultDb(); }
    STOCK_DEFS.forEach(function (def) {
      if (typeof parsed.stock[def.id] !== "number") { parsed.stock[def.id] = def.start; }
    });
    if (typeof parsed.nextOrder !== "number") { parsed.nextOrder = 1001 + parsed.orders.length; }
    return parsed;
  } catch (error) {
    return defaultDb();
  }
}

function saveDb() {
  try { localStorage.setItem(STORAGE_KEY, JSON.stringify(db)); } catch (error) { /* storage may be blocked */ }
}

/* ---------------- helpers ---------------- */

const $ = function (selector) { return document.querySelector(selector); };
const round2 = function (value) { return Math.round(value * 100) / 100; };
const peso = function (value) {
  return "\u20B1" + round2(value).toLocaleString("en-PH", { minimumFractionDigits: 2, maximumFractionDigits: 2 });
};
const compact = function (value) {
  const rounded = round2(value);
  return "\u20B1" + (rounded % 1 === 0 ? rounded.toLocaleString("en-PH") : rounded.toFixed(2));
};

function drinkById(id) { return MENU.find(function (drink) { return drink.id === id; }); }
function addonById(id) { return ADDONS.find(function (addon) { return addon.id === id; }); }
function orderRef() { return "TDC-" + db.nextOrder; }

function lineUnitPrice(line) {
  const drink = drinkById(line.drinkId);
  const base = line.size === "L" ? drink.l : drink.m;
  const extras = line.addons.reduce(function (sum, id) { return sum + addonById(id).price; }, 0);
  return round2(base + extras);
}

function lineTotal(line) { return round2(lineUnitPrice(line) * line.qty); }
function cartSubtotal() { return round2(cart.reduce(function (sum, line) { return sum + lineTotal(line); }, 0)); }
function cartCount() { return cart.reduce(function (sum, line) { return sum + line.qty; }, 0); }

function toast(message, isError) {
  const el = $("#toast");
  el.textContent = message;
  el.className = "toast show" + (isError ? " error" : "");
  clearTimeout(toast.timer);
  toast.timer = setTimeout(function () { el.className = "toast"; }, 2600);
}

/* ---------------- menu ---------------- */

function renderCategoryOptions() {
  const select = $("#category-filter");
  select.innerHTML = '<option value="">All categories</option>' +
    CATEGORIES.map(function (pair) { return '<option value="' + pair[0] + '">' + pair[1] + "</option>"; }).join("");

  $("#category-chips").innerHTML = '<button class="chip active" data-cat="">All drinks</button>' +
    CATEGORIES.map(function (pair) {
      return '<button class="chip" data-cat="' + pair[0] + '">' + pair[1] + "</button>";
    }).join("");
}

function renderAddons() {
  $("#addons").innerHTML = ADDONS.map(function (addon) {
    return '<label class="addon"><input type="checkbox" value="' + addon.id + '">' +
      addon.name + ' <span class="price">+' + compact(addon.price) + "</span></label>";
  }).join("");
}

function visibleMenu() {
  const needle = searchTerm.trim().toLowerCase();
  return MENU.filter(function (drink) {
    if (filterCat && drink.cat !== filterCat) { return false; }
    if (needle && drink.name.toLowerCase().indexOf(needle) === -1) { return false; }
    return true;
  });
}

function renderMenu() {
  const drinks = visibleMenu();
  const list = $("#drink-list");

  if (!selectedId || !drinks.some(function (d) { return d.id === selectedId; })) {
    selectedId = drinks.length ? drinks[0].id : null;
  }

  if (!drinks.length) {
    list.innerHTML = '<p class="empty-state">No drinks match your search.</p>';
    return;
  }

  list.innerHTML = drinks.map(function (drink) {
    const selected = drink.id === selectedId ? " selected" : "";
    return '<div class="drink-row' + selected + '" data-id="' + drink.id + '">' +
      '<div><span class="drink-name">' + drink.name + '</span>' +
      '<span class="drink-section">' + CAT_LABEL[drink.cat] + "</span></div>" +
      '<span class="drink-price">M ' + compact(drink.m) + " &nbsp;|&nbsp; L " + compact(drink.l) + "</span></div>";
  }).join("");
}

function setFeedback(message, isError) {
  const el = $("#menu-feedback");
  el.textContent = message;
  el.className = "feedback " + (isError ? "error" : "ok");
}

/* ---------------- cart ---------------- */

function addSelectedDrink() {
  if (!selectedId) {
    setFeedback("Pick a drink from the list first.", true);
    return;
  }

  const qtyInput = $("#qty");
  const quantity = Number(qtyInput.value);
  if (!quantity || quantity < 1 || quantity > 99 || quantity % 1 !== 0) {
    setFeedback("Quantity must be a whole number from 1 to 99.", true);
    return;
  }

  const size = document.querySelector('input[name="size"]:checked').value;
  const addons = Array.prototype.slice.call(document.querySelectorAll(".addon input:checked"))
    .map(function (input) { return input.value; })
    .sort();

  const key = selectedId + "|" + size + "|" + addons.join(",");
  const existing = cart.find(function (line) { return line.key === key; });

  if (existing) {
    existing.qty += quantity;
  } else {
    cart.push({ key: key, drinkId: selectedId, size: size, addons: addons, qty: quantity });
  }

  const drink = drinkById(selectedId);
  setFeedback("Added " + quantity + " \u00D7 " + drink.name + " (" + (size === "L" ? "Large" : "Medium") + ").", false);
  qtyInput.value = 1;
  renderCart();
}

function renderCart() {
  const body = $("#order-body");
  const empty = $("#order-empty");

  body.innerHTML = cart.map(function (line, index) {
    const drink = drinkById(line.drinkId);
    const extras = line.addons.length
      ? '<small class="item-extras">+ ' + line.addons.map(function (id) { return addonById(id).name; }).join(", ") + "</small>"
      : "";
    return "<tr>" +
      "<td>" + drink.name + extras + "</td>" +
      "<td>" + line.size + "</td>" +
      '<td class="num">' + line.qty + "</td>" +
      '<td class="num">' + peso(lineTotal(line)) + "</td>" +
      '<td><button class="row-remove" data-index="' + index + '" title="Remove line">\u00D7</button></td>' +
      "</tr>";
  }).join("");

  empty.style.display = cart.length ? "none" : "block";

  const subtotal = cartSubtotal();
  $("#subtotal").textContent = peso(subtotal);
  $("#summary-discount").textContent = "\u2212" + peso(0);
  $("#total").textContent = peso(subtotal);
  $("#order-meta").textContent = cart.length
    ? orderRef() + "  \u00B7  " + cartCount() + " item(s)"
    : orderRef();
  $("#checkout-btn").disabled = cart.length === 0;
  $("#clear-btn").disabled = cart.length === 0;
}

/* ---------------- checkout ---------------- */

function openCheckout() {
  if (!cart.length) {
    toast("Add at least one drink first.", true);
    return;
  }

  const subtotal = cartSubtotal();
  const lines = cart.map(function (line) {
    const drink = drinkById(line.drinkId);
    const extras = line.addons.length
      ? " + " + line.addons.map(function (id) { return addonById(id).name; }).join(", ")
      : "";
    return '<div class="summary-line"><span>' + line.qty + " \u00D7 " + drink.name +
      " (" + line.size + ")" + extras + "</span><span>" + peso(lineTotal(line)) + "</span></div>";
  }).join("");

  openModal(
    "<h2>Checkout</h2>" +
    '<p class="muted">' + orderRef() + " \u00B7 " + cartCount() + " item(s)</p>" +
    lines +
    '<div class="field" style="margin-top:16px"><label for="discount">Discount</label>' +
    '<select id="discount">' + DISCOUNTS.map(function (discount) {
      return '<option value="' + discount.id + '">' + discount.label + "</option>";
    }).join("") + "</select></div>" +
    '<div class="field"><label for="cash">Cash tendered</label>' +
    '<input id="cash" type="number" min="0" step="0.01" placeholder="0.00" />' +
    '<div class="quick-cash">' +
    [100, 200, 500, 1000].map(function (value) {
      return '<button class="btn ghost small" data-cash="' + value + '">' + compact(value) + "</button>";
    }).join("") +
    '<button class="btn ghost small" data-cash="exact">Exact</button>' +
    "</div></div>" +
    '<div class="summary-line"><span>Subtotal</span><span>' + peso(subtotal) + "</span></div>" +
    '<div class="summary-line"><span>Discount</span><span id="co-discount">\u2212' + peso(0) + "</span></div>" +
    '<div class="summary-total"><span>TOTAL</span><span id="co-total">' + peso(subtotal) + "</span></div>" +
    '<div class="change-line"><span>Change</span><span id="co-change">' + peso(0) + "</span></div>" +
    '<div class="modal-actions"><button class="btn ghost" id="co-cancel">Cancel</button>' +
    '<button class="btn primary" id="co-complete" disabled>Complete sale</button></div>',
    function () { bindCheckout(subtotal); }
  );
}

function bindCheckout(subtotal) {
  const discountSelect = $("#discount");
  const cashInput = $("#cash");
  const completeBtn = $("#co-complete");
  const state = { subtotal: subtotal, discount: 0, total: subtotal, rate: 0 };

  function recalc() {
    state.rate = DISCOUNTS.find(function (discount) { return discount.id === discountSelect.value; }).rate;
    state.discount = round2(state.subtotal * state.rate);
    state.total = round2(state.subtotal - state.discount);

    $("#co-discount").textContent = "\u2212" + peso(state.discount);
    $("#co-total").textContent = peso(state.total);

    const changeEl = $("#co-change");
    const cash = Number(cashInput.value);

    if (!cashInput.value.trim()) {
      changeEl.textContent = peso(0);
      completeBtn.disabled = true;
      return;
    }
    if (cash + 0.0001 < state.total) {
      changeEl.innerHTML = '<span class="change-due">Short by ' + peso(state.total - cash) + "</span>";
      completeBtn.disabled = true;
      return;
    }
    changeEl.textContent = peso(cash - state.total);
    completeBtn.disabled = false;
  }

  discountSelect.addEventListener("change", recalc);
  cashInput.addEventListener("input", recalc);

  Array.prototype.forEach.call(document.querySelectorAll("[data-cash]"), function (button) {
    button.addEventListener("click", function () {
      cashInput.value = button.dataset.cash === "exact" ? state.total : Number(button.dataset.cash);
      recalc();
    });
  });

  $("#co-cancel").addEventListener("click", closeModal);
  completeBtn.addEventListener("click", function () { completeSale(state); });

  recalc();
  cashInput.focus();
}

function completeSale(state) {
  const cash = Number($("#cash").value);
  if (!cashInputValid(cash, state.total)) {
    toast("Cash tendered must cover the total.", true);
    return;
  }

  const order = {
    ref: orderRef(),
    at: new Date().toISOString(),
    lines: cart.map(function (line) {
      return { drinkId: line.drinkId, size: line.size, addons: line.addons.slice(), qty: line.qty };
    }),
    subtotal: state.subtotal,
    discountLabel: DISCOUNTS.find(function (d) { return d.id === $("#discount").value; }).label,
    discount: state.discount,
    total: state.total,
    cash: round2(cash),
    change: round2(cash - state.total)
  };

  deductStock(order.lines);
  db.orders.push(order);
  db.nextOrder += 1;
  saveDb();

  cart = [];
  renderCart();
  renderHistory();
  renderInventory();
  renderReports();
  closeModal();
  showReceipt(order);
}

function cashInputValid(cash, total) { return cash >= total && cash > 0; }

function deductStock(lines) {
  lines.forEach(function (line) {
    const drink = drinkById(line.drinkId);
    addStock(line.size === "L" ? "cupL" : "cupM", -line.qty);

    const base = RECIPE.category[drink.cat];
    if (base) { addStock(base[0], -base[1] * line.qty); }

    line.addons.forEach(function (id) {
      const extra = RECIPE.addon[id];
      if (extra) { addStock(extra[0], -extra[1] * line.qty); }
    });
  });
}

function addStock(id, delta) {
  db.stock[id] = Math.max(0, round2(db.stock[id] + delta));
}

/* ---------------- receipt ---------------- */

function showReceipt(order) {
  const rows = order.lines.map(function (line) {
    const drink = drinkById(line.drinkId);
    const extras = line.addons.length
      ? '<br><small>+ ' + line.addons.map(function (id) { return addonById(id).name; }).join(", ") + "</small>"
      : "";
    return "<tr><td>" + line.qty + " \u00D7 " + drink.name + " (" + line.size + ")" + extras +
      '</td><td class="num">' + peso(lineTotal(line)) + "</td></tr>";
  }).join("");

  const when = new Date(order.at).toLocaleString("en-PH", {
    month: "short", day: "numeric", year: "numeric", hour: "numeric", minute: "2-digit"
  });

  openModal(
    '<div class="receipt-sheet">' +
      '<div class="r-head"><div class="r-brand">TEAM DREAM CAFE</div>' +
      '<div class="r-sub">COFFEE SHOP</div></div>' +
      '<div class="r-rule"></div>' +
      "<div>" + order.ref + "<br><small>" + when + "</small></div>" +
      '<div class="r-rule"></div>' +
      "<table>" + rows + "</table>" +
      '<div class="r-rule"></div>' +
      '<table><tr><td>Subtotal</td><td class="num">' + peso(order.subtotal) + "</td></tr>" +
      "<tr><td>" + order.discountLabel + "</td><td class=\"num\">\u2212" + peso(order.discount) + "</td></tr>" +
      '<tr class="r-total"><td>TOTAL</td><td class="num">' + peso(order.total) + "</td></tr>" +
      '<tr><td>Cash</td><td class="num">' + peso(order.cash) + "</td></tr>" +
      '<tr><td>Change</td><td class="num">' + peso(order.change) + "</td></tr></table>" +
      '<div class="r-rule"></div>' +
      '<div class="r-foot">Thank you for visiting Team Dream Cafe!<br>This serves as your official receipt.</div>' +
    "</div>" +
    '<div class="modal-actions"><button class="btn ghost" id="r-print">Print receipt</button>' +
    '<button class="btn primary" id="r-close">Done</button></div>',
    function () {
      $("#r-close").addEventListener("click", closeModal);
      $("#r-print").addEventListener("click", function () { window.print(); });
    }
  );
}

/* ---------------- history ---------------- */

function renderHistory() {
  const body = $("#history-body");
  const empty = $("#history-empty");
  const orders = db.orders.slice().reverse();

  body.innerHTML = orders.map(function (order) {
    const when = new Date(order.at).toLocaleString("en-PH", {
      month: "short", day: "numeric", hour: "numeric", minute: "2-digit"
    });
    const items = order.lines.reduce(function (sum, line) { return sum + line.qty; }, 0);
    return "<tr>" +
      "<td><strong>" + order.ref + "</strong></td>" +
      '<td class="muted">' + when + "</td>" +
      '<td class="num">' + items + "</td>" +
      '<td class="num muted">' + (order.discount > 0 ? order.discountLabel : "-") + "</td>" +
      '<td class="num">' + peso(order.total) + "</td>" +
      '<td><button class="btn ghost small" data-order="' + order.ref + '">View</button></td>' +
      "</tr>";
  }).join("");

  empty.style.display = orders.length ? "none" : "block";

  Array.prototype.forEach.call(document.querySelectorAll("[data-order]"), function (button) {
    button.addEventListener("click", function () {
      const order = db.orders.find(function (entry) { return entry.ref === button.dataset.order; });
      if (order) { showReceipt(order); }
    });
  });
}

/* ---------------- inventory ---------------- */

function renderInventory() {
  const lowCount = STOCK_DEFS.filter(function (def) { return db.stock[def.id] <= def.low; }).length;

  $("#inventory-summary").innerHTML =
    statCard("Tracked items", String(STOCK_DEFS.length)) +
    statCard("Low on stock", String(lowCount)) +
    statCard("Cups remaining", (db.stock.cupM + db.stock.cupL).toLocaleString("en-PH"));

  $("#inventory-body").innerHTML = STOCK_DEFS.map(function (def) {
    const value = db.stock[def.id];
    const percent = Math.max(2, Math.min(100, (value / def.start) * 100));
    const isLow = value <= def.low;
    return "<tr>" +
      "<td>" + def.name + ' <span class="muted">(' + def.unit + ")</span></td>" +
      '<td class="num">' + value.toLocaleString("en-PH") + "</td>" +
      '<td><div class="bar"><i style="width:' + percent + '%"></i></div></td>' +
      '<td><span class="status-pill ' + (isLow ? "status-low" : "status-ok") + '">' +
        (isLow ? "LOW STOCK" : "OK") + "</span></td>" +
      '<td><button class="btn ghost small" data-restock="' + def.id + '">Restock +25%</button></td>' +
      "</tr>";
  }).join("");

  Array.prototype.forEach.call(document.querySelectorAll("[data-restock]"), function (button) {
    button.addEventListener("click", function () {
      const def = STOCK_DEFS.find(function (entry) { return entry.id === button.dataset.restock; });
      db.stock[def.id] = round2(db.stock[def.id] + def.start * 0.25);
      saveDb();
      renderInventory();
      toast("Restocked " + def.name + ".");
    });
  });
}

/* ---------------- reports ---------------- */

function todaysOrders() {
  const today = new Date().toDateString();
  return db.orders.filter(function (order) { return new Date(order.at).toDateString() === today; });
}

function renderReports() {
  const orders = todaysOrders();
  const sales = round2(orders.reduce(function (sum, order) { return sum + order.total; }, 0));
  const cups = orders.reduce(function (sum, order) {
    return sum + order.lines.reduce(function (inner, line) { return inner + line.qty; }, 0);
  }, 0);
  const average = orders.length ? round2(sales / orders.length) : 0;

  $("#report-stats").innerHTML =
    statCard("Sales today", peso(sales)) +
    statCard("Orders today", String(orders.length)) +
    statCard("Average order", peso(average)) +
    statCard("Cups sold today", String(cups));

  const sellers = {};
  orders.forEach(function (order) {
    order.lines.forEach(function (line) {
      const drink = drinkById(line.drinkId);
      const key = drink.name + " (" + line.size + ")";
      sellers[key] = (sellers[key] || 0) + line.qty;
    });
  });

  const ranked = Object.keys(sellers)
    .map(function (key) { return { name: key, qty: sellers[key] }; })
    .sort(function (a, b) { return b.qty - a.qty; })
    .slice(0, 6);

  $("#best-sellers").innerHTML = ranked.length
    ? ranked.map(function (entry) {
        const width = Math.max(4, (entry.qty / ranked[0].qty) * 100);
        return '<div class="bar-row"><div class="bar-label"><span>' + entry.name +
          "</span><strong>" + entry.qty + " pc</strong></div>" +
          '<div class="bar"><i style="width:' + width + '%"></i></div></div>';
      }).join("")
    : '<p class="empty-state">No sales recorded today yet.</p>';

  const categories = {};
  orders.forEach(function (order) {
    order.lines.forEach(function (line) {
      const label = CAT_LABEL[drinkById(line.drinkId).cat];
      categories[label] = round2((categories[label] || 0) + lineTotal(line));
    });
  });

  const catEntries = Object.keys(categories)
    .map(function (key) { return { name: key, amount: categories[key] }; })
    .sort(function (a, b) { return b.amount - a.amount; });

  $("#category-breakdown").innerHTML = catEntries.length
    ? catEntries.map(function (entry) {
        const width = Math.max(4, (entry.amount / catEntries[0].amount) * 100);
        return '<div class="bar-row"><div class="bar-label"><span>' + entry.name +
          "</span><strong>" + peso(entry.amount) + "</strong></div>" +
          '<div class="bar"><i style="width:' + width + '%"></i></div></div>';
      }).join("")
    : '<p class="empty-state">No sales recorded today yet.</p>';

  const allSales = round2(db.orders.reduce(function (sum, order) { return sum + order.total; }, 0));
  $("#report-totals").innerHTML = db.orders.length
    ? '<div class="summary-line"><span>Total recorded sales</span><strong>' + peso(allSales) + "</strong></div>" +
      '<div class="summary-line"><span>Completed orders</span><strong>' + db.orders.length + "</strong></div>" +
      '<div class="summary-line"><span>Best seller overall</span><strong>' + bestSellerOverall() + "</strong></div>"
    : '<p class="empty-state">Complete a checkout and this report fills in automatically.</p>';
}

function bestSellerOverall() {
  const counts = {};
  db.orders.forEach(function (order) {
    order.lines.forEach(function (line) {
      const name = drinkById(line.drinkId).name;
      counts[name] = (counts[name] || 0) + line.qty;
    });
  });
  const names = Object.keys(counts);
  if (!names.length) { return "-"; }
  names.sort(function (a, b) { return counts[b] - counts[a]; });
  return names[0] + " (" + counts[names[0]] + " pc)";
}

function statCard(label, value) {
  return '<div class="stat"><span>' + label + "</span><strong>" + value + "</strong></div>";
}

/* ---------------- modal ---------------- */

function openModal(html, onReady) {
  $("#modal-root").innerHTML = '<div class="modal-backdrop"><div class="modal">' + html + "</div></div>";
  const backdrop = $(".modal-backdrop");
  backdrop.addEventListener("click", function (event) {
    if (event.target === backdrop) { closeModal(); }
  });
  document.addEventListener("keydown", onEscape);
  if (onReady) { onReady(); }
}

function onEscape(event) {
  if (event.key === "Escape") { closeModal(); }
}

function closeModal() {
  $("#modal-root").innerHTML = "";
  document.removeEventListener("keydown", onEscape);
}

/* ---------------- navigation + wiring ---------------- */

function showScreen(name) {
  Array.prototype.forEach.call(document.querySelectorAll(".screen"), function (screen) {
    screen.classList.toggle("active", screen.id === "screen-" + name);
  });
  Array.prototype.forEach.call(document.querySelectorAll(".nav-item"), function (button) {
    button.classList.toggle("active", button.dataset.screen === name);
  });
}

function wireEvents() {
  Array.prototype.forEach.call(document.querySelectorAll(".nav-item"), function (button) {
    button.addEventListener("click", function () { showScreen(button.dataset.screen); });
  });

  $("#search").addEventListener("input", function (event) {
    searchTerm = event.target.value;
    renderMenu();
  });

  $("#category-filter").addEventListener("change", function (event) {
    filterCat = event.target.value;
    syncChips();
    renderMenu();
  });

  $("#category-chips").addEventListener("click", function (event) {
    const chip = event.target.closest(".chip");
    if (!chip) { return; }
    filterCat = chip.dataset.cat;
    $("#category-filter").value = filterCat;
    syncChips();
    renderMenu();
  });

  $("#drink-list").addEventListener("click", function (event) {
    const row = event.target.closest(".drink-row");
    if (!row) { return; }
    selectedId = row.dataset.id;
    renderMenu();
  });

  $("#drink-list").addEventListener("dblclick", function (event) {
    const row = event.target.closest(".drink-row");
    if (!row) { return; }
    selectedId = row.dataset.id;
    renderMenu();
    addSelectedDrink();
  });

  $("#add-btn").addEventListener("click", addSelectedDrink);

  $("#order-body").addEventListener("click", function (event) {
    const button = event.target.closest(".row-remove");
    if (!button) { return; }
    cart.splice(Number(button.dataset.index), 1);
    renderCart();
    setFeedback("Line removed from the order.", false);
  });

  $("#clear-btn").addEventListener("click", function () {
    if (!cart.length) { return; }
    if (!window.confirm("Clear every line in the current order?")) { return; }
    cart = [];
    renderCart();
    setFeedback("Order cleared. Ready for the next customer.", false);
  });

  $("#checkout-btn").addEventListener("click", openCheckout);
}

function syncChips() {
  Array.prototype.forEach.call(document.querySelectorAll(".chip"), function (chip) {
    chip.classList.toggle("active", chip.dataset.cat === filterCat);
  });
}

/* ---------------- start ---------------- */

renderCategoryOptions();
renderAddons();
renderMenu();
renderCart();
renderHistory();
renderInventory();
renderReports();
wireEvents();
