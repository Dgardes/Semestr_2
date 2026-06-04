package org.example.productsshop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import store.objects.Product;
import store.services.FileManager;
import store.services.LogBuilder;
import store.services.SalesManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MainController {

    private FileManager fileManager = new FileManager("store_data");
    private SalesManager salesManager = new SalesManager(fileManager, null);
    private final double TAX_RATE = 0.20;

    @FXML
    private Pane ContentPanel;

    @FXML
    private ListView<String> cartListView;

    @FXML
    private TextField inputAge;

    @FXML
    private TextField inputProductCode;

    @FXML
    private TextField inputQuantity;

    @FXML
    private TextField inputSubField1;

    @FXML
    private TextField inputSubField2;

    @FXML
    private TextField inputSubField3;

    @FXML
    private TextField inputSubField4;

    @FXML
    private TextField inputSupplyCode;

    @FXML
    private TextField inputSupplyDate;

    @FXML
    private TextField inputSupplyDiscount;

    @FXML
    private TextField inputSupplyExpiryDays;

    @FXML
    private TextField inputSupplyName;

    @FXML
    private TextField inputSupplyPrice;

    @FXML
    private TextField inputSupplyTempMax;

    @FXML
    private TextField inputSupplyTempMin;

    @FXML
    private TextField inputReportDate;

    @FXML
    private Label lblInfoName;

    @FXML
    private Label lblInfoPrice;

    @FXML
    private Label lblInfoRestrictions;

    @FXML
    private Label lblInfoType;

    @FXML
    private Label lblSubField1;

    @FXML
    private Label lblSubField2;

    @FXML
    private Label lblSubField3;

    @FXML
    private Label lblSubField4;

    @FXML
    private Label lblTotalSum;

    @FXML
    private RadioButton radioCustom;

    @FXML
    private RadioButton radioPiece;

    @FXML
    private RadioButton radioRestricted;

    @FXML
    private RadioButton radioWeight;

    @FXML
    private TextArea txtAdminLogArea;

    @FXML
    private TextArea txtSupplyLogArea;

    @FXML
    private TextField supplyQuantity;

    @FXML
    private Label lblWarning;

    @FXML
    private TextField inputReceiptNumber;

    @FXML
    private TextField inputPreparedProductCode;

    @FXML
    private TextField inputPortionsCount;

    @FXML
    private TextField inputShiefsCount;

    @FXML
    private Label lblPreparedProductName;

    @FXML
    private TextField inputActionProductCode;

    @FXML
    private Label lblActionProductName;

    @FXML
    private TextField inputActionDiscount;

    @FXML
    private TextField inputActionWaste;

    @FXML
    private TextField inputVolume;

    //
    // Методи
    //


    @FXML
    public void initialize() {

        // завантаження каталогу товарів
        salesManager.loadStoreData();
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        //підставка дат для табів 2 і 3
        if (inputReportDate != null) {
            inputReportDate.setText(currentDate);
        }
        if (inputSupplyDate != null) {
            inputSupplyDate.setText(currentDate);
        }


        inputProductCode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                //дозвіл лише цифр
                inputProductCode.setText(newValue.replaceAll("[^\\d]", ""));
                return;
            }

            if (!inputProductCode.getText().isEmpty()) {
                try {
                    int code = Integer.parseInt(inputProductCode.getText());
                    if (code >= 0) {
                        updateProductInfoLabels(code);
                    }
                } catch (NumberFormatException e) {
                    clearInfoLabels();
                }
            } else {
                clearInfoLabels();
            }
        });

        //дозвіл на дробові та цілі (кількість / вага)
        inputQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            //тільки одна крапка для дробових
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                inputQuantity.setText(oldValue);
            }
        });

        // дозвіл на цілі (вік покупця)
        inputAge.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputAge.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        inputVolume.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                inputVolume.setText(oldValue);
            }
        });


        javafx.scene.control.ToggleGroup productTypeGroup = new javafx.scene.control.ToggleGroup();
        radioPiece.setToggleGroup(productTypeGroup);
        radioWeight.setToggleGroup(productTypeGroup);
        radioRestricted.setToggleGroup(productTypeGroup);
        radioCustom.setToggleGroup(productTypeGroup);

        radioPiece.setSelected(true);
        updateSubFieldsLabels("Штучний");

        productTypeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadio = (RadioButton) newValue;
                updateSubFieldsLabels(selectedRadio.getText());
            }
        });

        //
        // лісенери для табів 2 та 3 для предпросмотру продукту
        //

        inputPreparedProductCode.textProperty().addListener((observable, oldValue, newValue) -> {
            String codeStr = newValue.trim();

            if (codeStr.isEmpty()) {
                lblPreparedProductName.setText("---");
                return;
            }

            try {
                int code = Integer.parseInt(codeStr);
                Map<Product, Double> catalog = salesManager.getCatalog();

                if (catalog != null) {
                    store.objects.PreparedProduct foundProduct = null;
                    for (Product p : catalog.keySet()) {
                        if (p.getCode() == code && p instanceof store.objects.PreparedProduct) {
                            foundProduct = (store.objects.PreparedProduct) p;
                            break;
                        }
                    }

                    if (foundProduct != null) {
                        lblPreparedProductName.setText(foundProduct.getName());
                    } else {
                        lblPreparedProductName.setText("---");
                    }
                }
            } catch (NumberFormatException e) {
                lblPreparedProductName.setText("---");
            }
        });

        inputActionProductCode.textProperty().addListener((observable, oldValue, newValue) -> {
            String codeStr = newValue.trim();
            if (codeStr.isEmpty()) {
                lblActionProductName.setText("---");
                return;
            }
            try {
                int code = Integer.parseInt(codeStr);
                Map<Product, Double> catalog = salesManager.getCatalog();
                if (catalog != null) {
                    Product foundProduct = null;
                    for (Product p : catalog.keySet()) {
                        if (p.getCode() == code) {
                            foundProduct = p;
                            break;
                        }
                    }
                    if (foundProduct != null) {
                        lblActionProductName.setText(foundProduct.getName());
                    } else {
                        lblActionProductName.setText("---");
                    }
                }
            } catch (NumberFormatException e) {
                lblActionProductName.setText("---");
            }
        });


    }

    //
    //
    // Таб 1
    //
    //

    private void clearInfoLabels() {
        lblInfoName.setText("---");
        lblWarning.setText("");
        clearInfoLabelsExceptName();
    }

    private void clearInfoLabelsExceptName() {
        lblInfoPrice.setText("---");
        lblInfoType.setText("---");
        lblInfoRestrictions.setText("---");
    }

    private void updateProductInfoLabels(int code) {
        lblWarning.setText("");
        Map<Product, Double> catalog = salesManager.getCatalog();

        if (catalog == null || catalog.isEmpty()) {
            lblInfoName.setText("---");
            lblWarning.setText("Помилка: Каталог товарів порожній");
            clearInfoLabelsExceptName();
            return;
        }

        Product foundProduct = null;
        for (Map.Entry<Product, Double> entry : catalog.entrySet()) {
            Product p = entry.getKey();
            if (p != null && p.getCode() == code) {
                foundProduct = p;
                break;
            }
        }

        if (foundProduct != null) {
            lblInfoName.setText(foundProduct.getName());
            lblInfoPrice.setText(String.format("%.2f", foundProduct.getPrice()));

            // Визначаємо тип продукту
            if (foundProduct instanceof store.objects.WeightProduct) {
                lblInfoType.setText("Ваговий (кг)");
            } else if (foundProduct instanceof store.objects.RestrictedProduct) {
                lblInfoType.setText("З обмеженнями");
            } else if (foundProduct instanceof store.objects.PreparedProduct) {
                lblInfoType.setText("Власна кухня");
            } else {
                lblInfoType.setText("Штучний");
            }

            if (foundProduct instanceof store.objects.RestrictedProduct) {
                lblInfoRestrictions.setText("Алкоголь / Тютюн (18+)");
            } else {
                lblInfoRestrictions.setText("Немає");
            }
        } else {
            lblInfoName.setText("---");
            lblWarning.setText("Попередження: Товар з кодом " + code + " не знайдено!");
            clearInfoLabelsExceptName();
        }
    }

    @FXML
    private void onAddProductClick() {
        lblWarning.setText("");

        String codeStr = inputProductCode.getText().trim();
        String qtyStr = inputQuantity.getText().trim();
        String ageStr = inputAge.getText().trim();
        String volStr = inputVolume.getText().trim();

        if (codeStr.isEmpty() || qtyStr.isEmpty()) {
            lblWarning.setText("Попередження: Заповніть Код та Кількість");
            return;
        }

        int code = Integer.parseInt(codeStr);
        double quantity = Double.parseDouble(qtyStr.replace(",", "."));
        int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
        double volume = volStr.isEmpty() ? 0.0 : Double.parseDouble(volStr.replace(",", "."));

        Product stockProduct = null;
        Map<Product, Double> catalog = salesManager.getCatalog();
        if (catalog != null) {
            for (Product p : catalog.keySet()) {
                if (p != null && p.getCode() == code) {
                    stockProduct = p;
                    break;
                }
            }
        }

        if (stockProduct == null) {
            lblWarning.setText("Попередження: Спроба додати неіснуючий товар");
            clearInfoLabels();
            return;
        }

        //контроль продукту з обмеженнями
        if (stockProduct instanceof store.objects.RestrictedProduct) {
            store.objects.RestrictedProduct rp = (store.objects.RestrictedProduct) stockProduct;

            if (volume <= 0.0) {
                lblWarning.setText("Помилка: Введіть об'єм однієї одиниці (пляшки/пачки)");
                return;
            }

            //перевірка
            int maxPackages = rp.calculateMaxAllowedPackages(volume);

            if (quantity > maxPackages) {
                lblWarning.setText(String.format("Перевищено ліміт. Дозволено не більше %d шт. (по %.2f л/кг)", maxPackages, volume));
                return;
            }
        }

        // додаємо в кошик з перевіркою віку
        boolean success = salesManager.scanAndAddProduct(code, quantity, age);

        if (success) {
            double positionTotal = 0.0;

            if (stockProduct instanceof store.objects.WeightProduct) {
                store.objects.WeightProduct wp = (store.objects.WeightProduct) stockProduct;
                positionTotal = wp.calculateFinalPrice(TAX_RATE, quantity);
            } else {
                double finalUnitPrice = stockProduct.calculateFinalPrice(TAX_RATE);
                positionTotal = finalUnitPrice * quantity;
            }

            String productName = stockProduct.getName();

            String listRow = String.format("Код: %d | %s x%.2f = %.2f грн", code, productName, quantity, positionTotal);
            cartListView.getItems().add(listRow);

            updateCartTotalSum(TAX_RATE);

            inputProductCode.clear();
            inputQuantity.clear();
            inputVolume.clear();
            clearInfoLabels();
        } else {
            if (stockProduct instanceof store.objects.RestrictedProduct) {
                lblWarning.setText("Продаж заборонено: Покупець неповнолітній!");
            } else {
                lblWarning.setText("Помилка додавання: Недостатньо товару на складі.");
            }
        }
    }

    @FXML
    private void onRemoveProductClick() {
        lblWarning.setText("");
        String codeStr = inputProductCode.getText().trim();

        if (codeStr.isEmpty()) {
            lblWarning.setText("Попередження: Введіть код товару для видалення");
            return;
        }

        int code = Integer.parseInt(codeStr);
        String qtyStr = inputQuantity.getText().trim();
        double quantityToRemove = qtyStr.isEmpty() ? 1.0 : Double.parseDouble(qtyStr.replace(",", "."));

        Product targetProduct = null;
        store.services.Cart currentCart = salesManager.getCurrentCart();

        if (currentCart != null && currentCart.getItems() != null) {
            for (Product p : currentCart.getItems().keySet()) {
                if (p != null && p.getCode() == code) {
                    targetProduct = p;
                    break;
                }
            }
        }

        if (targetProduct == null) {
            return;
        }
        //оновлення кошику
        salesManager.removeProductFromCart(targetProduct, quantityToRemove);

        String targetPrefix = "Код: " + codeStr + " ";
        String itemToRemove = null;

        int targetIndex = -1;
        for (int i = 0; i < cartListView.getItems().size(); i++) {
            if (cartListView.getItems().get(i).startsWith(targetPrefix)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex != -1) {
            Double remainingQty = currentCart.getItems().get(targetProduct);

            if (remainingQty == null || remainingQty <= 0) {
                //якщо товару більше немає в кошику, повністю прибираємо рядок з екрана
                cartListView.getItems().remove(targetIndex);
            } else {
                // Якщо товар залишився, перераховуємо його нову фінальну вартість
                double newPositionTotal = 0.0;
                if (targetProduct instanceof store.objects.WeightProduct) {
                    store.objects.WeightProduct wp = (store.objects.WeightProduct) targetProduct;
                    newPositionTotal = wp.calculateFinalPrice(TAX_RATE, remainingQty);
                } else {
                    newPositionTotal = targetProduct.calculateFinalPrice(TAX_RATE) * remainingQty;
                }

                String updatedRow = String.format("Код: %d | %s x%.2f = %.2f грн",
                        code, targetProduct.getName(), remainingQty, newPositionTotal);
                cartListView.getItems().set(targetIndex, updatedRow);
            }
        }

        //оновлюємо загальну суму кошика на екрані
        updateCartTotalSum(TAX_RATE);

        inputProductCode.clear();
        inputQuantity.clear();
        clearInfoLabels();
    }

    private void updateCartTotalSum(double taxRate) {
        store.services.Cart currentCart = salesManager.getCurrentCart();
        if (currentCart != null) {
            double total = currentCart.calculateTotalCartCost(taxRate);
            lblTotalSum.setText(String.format("Всього: %.2f грн", total));
        } else {
            lblTotalSum.setText("Всього: 0.00 грн");
        }
    }

    @FXML
    private void onCheckoutClick() {
        //якщо кошик порожній
        if (cartListView.getItems().isEmpty()) {
            return;
        }
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        salesManager.checkout(TAX_RATE, dateStr);

        cartListView.getItems().clear();
        lblTotalSum.setText("Всього: 0.00 грн");
        inputProductCode.clear();
        inputQuantity.clear();
        clearInfoLabels();
    }

    //
    //
    // Таб 2
    //
    //

    private String getAdminInputDate() {
        String date = inputReportDate.getText().trim();
        if (date.isEmpty()) {
            return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        return date;
    }

    @FXML
    private void onPerformWasteInspectionClick() {
        String date = getAdminInputDate();
        String oldReport = "";

        try {
            oldReport = salesManager.readWasteReportFile(date);
            if (oldReport.startsWith("Файл не знайдено:")) {
                oldReport = "";
            }
        } catch (Exception e) {
            oldReport = "";
        }

        try {
            //рахуємо скільки прострочених позицій є на складі до виклику інспекції
            long expiredCount = 0;
            Map<Product, Double> catalog = salesManager.getCatalog();
            if (catalog != null) {
                expiredCount = catalog.entrySet().stream()
                .filter(entry -> entry.getKey() != null && entry.getKey().isExpired() && entry.getValue() > 0)
                .count();
            }
            salesManager.performWasteInspection(date);
            String newReport = salesManager.readWasteReportFile(date);

            //склеюємо тільки якщо реально було що списувати
            if (!oldReport.isEmpty() && expiredCount > 0 && !newReport.contains("Прострочених товарів під час перевірки не виявлено")) {

                String cleanNewReport = newReport.replace(
            "================================================================================\n" +
                  "                             Звіт списання товарів                              \n" +
                  "================================================================================\n" +
                  "Дата формування: " + date + "\n" +
                  "--------------------------------------------------------------------------------\n", "");

                String combinedText = oldReport + "\n[Додаткова інспекція за сьогодні]\n" + cleanNewReport;

                String dirPath = "store_data/Звіти/Списання";
                java.io.File dir = new java.io.File(dirPath);
                if (!dir.exists()) dir.mkdirs();

                try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(dirPath + "/Звіт_списання_за_" + date + ".txt"))) {
                    writer.write(combinedText);
                }

                txtAdminLogArea.setText(combinedText);
            } else {
                txtAdminLogArea.setText(oldReport.isEmpty() ? newReport : oldReport);
            }

        } catch (RuntimeException e) {
            if (e.getCause() instanceof java.io.FileNotFoundException) {
                txtAdminLogArea.setText(
                "Проведення авто списання простроченого товару\n" +
                "Дата: " + date + "\n" +
                "----------------------------------------------------\n" +
                "Результат: Прострочених товарів на складі не виявлено.\n" +
                "Файл звіту не створювався.\n" +
                "----------------------------------------------------"
                );
            } else {
                txtAdminLogArea.setText("Помилка списання: " + e.getMessage());
            }
        } catch (Exception e) {
            txtAdminLogArea.setText("Критична помилка: " + e.getMessage());
        }
    }

    @FXML
    private void onCloseDailyShiftClick() {
        String date = getAdminInputDate();
        try {
            salesManager.closeDailyShift(date);
            txtAdminLogArea.setText(salesManager.readDailySummaryReport(date));
        } catch (Exception e) {
            txtAdminLogArea.setText("Помилка закриття зміни: " + e.getMessage());
        }
    }

    @FXML
    private void onShowCatalogClick() {
        Map<Product, Double> catalog = salesManager.getCatalog();
        if (catalog == null || catalog.isEmpty()) {
            txtAdminLogArea.setText("Склад порожній або дані не завантажено.");
            return;
        }

        //генеруємо чистий звіт ревізії складу
        String date = getAdminInputDate();
        String report = store.services.LogBuilder.generateRevisionReport(catalog, date);
        txtAdminLogArea.setText(report);
    }

    // методи перегляду за вказану дату

    @FXML
    private void onLoadSalesReportClick() {
        txtAdminLogArea.setText(salesManager.readDailySummaryReport(getAdminInputDate()));
    }

    @FXML
    private void onLoadWasteReportClick() {
        txtAdminLogArea.setText(salesManager.readWasteReportFile(getAdminInputDate()));
    }

    @FXML
    private void onLoadReceiptClick() {
        String receiptNumStr = inputReceiptNumber.getText().trim();
        if (receiptNumStr.isEmpty()) {
            txtAdminLogArea.setText("Помилка: Введіть номер чеку!");
            return;
        }
        try {
            int num = Integer.parseInt(receiptNumStr);
            txtAdminLogArea.setText(salesManager.readReceiptFile(getAdminInputDate(), num));
        } catch (NumberFormatException e) {
            txtAdminLogArea.setText("Помилка: Номер чеку має бути цілим числом!");
        }
    }

    @FXML
    private void onClearAdminLogClick() {
        txtAdminLogArea.clear();
    }

    // метод аналітики навантаження

    @FXML
    private void onCalculateWorkloadClick() {
        try {
            int code = Integer.parseInt(inputPreparedProductCode.getText().trim());
            int portions = Integer.parseInt(inputPortionsCount.getText().trim());
            int chefs = Integer.parseInt(inputShiefsCount.getText().trim());

            // Валідація
            if (portions <= 0 || chefs <= 0) {
                txtAdminLogArea.setText("Помилка: Кількість порцій та кухарів має бути більшою за нуль!");
                return;
            }

            Map<Product, Double> catalog = salesManager.getCatalog();
            if (catalog == null) return;

            //пошук страви в каталозі
            store.objects.PreparedProduct targetProduct = null;
            for (Product p : catalog.keySet()) {
                if (p.getCode() == code && p instanceof store.objects.PreparedProduct) {
                    targetProduct = (store.objects.PreparedProduct) p;
                    break;
                }
            }

            if (targetProduct == null) {
                lblPreparedProductName.setText("---");
                txtAdminLogArea.setText("Помилка: Страву власного виробництва з кодом " + code + " не знайдено!");
                return;
            }

            // Оновлюємо UI та виконуємо розрахунки методів
            lblPreparedProductName.setText(targetProduct.getName());

            int timeRequired = targetProduct.calculateCookingTimeForOrder(portions, chefs);
            int portionsPerHour = targetProduct.calculatePortionsPerHour(chefs);

            String result =
                    "========================================\n" +
                            "         Аналітика навантаження         \n" +
                            "========================================\n" +
                            "Страва: " + targetProduct.getName() + " (Цех: " + targetProduct.getPreparationDepartment() + ")\n" +
                            "Замовлення: " + portions + " порцій\n" +
                            "Працює кухарів: " + chefs + "\n" +
                            "----------------------------------------\n" +
                            "Час на приготування замовлення : " + timeRequired + " хв.\n" +
                            "Продуктивність зміни (за годину): " + portionsPerHour + " порцій\n" +
                            "========================================\n";

            txtAdminLogArea.setText(result);

        } catch (NumberFormatException e) {
            txtAdminLogArea.setText("Помилка: Для розрахунку введіть коректні числові значення!");
        }
    }

    //
    //
    // Таб 3
    //
    //

    private void updateSubFieldsLabels(String type) {

        switch (type) {
            case "Штучний": // клас PieceProduct
                lblSubField1.setText("Термін після відкриття (днів):");
                lblSubField2.setText("Вага упаковки (грам):");
                lblSubField3.setText("Матеріал упаковки:");
                lblSubField4.setText("Крихкий товар? (T/F):");

                //значення за замовчуванням
                inputSubField1.setText("0");
                inputSubField2.setText("0.0");
                inputSubField3.setText("Пластик");
                inputSubField4.setText("F");
                break;

            case "Ваговий": //клас WeightProduct
                lblSubField1.setText("Вага нетто (кг):");
                lblSubField2.setText("Сорт продукту:");
                lblSubField3.setText("Органічний? (T/F):");
                lblSubField4.setText("М'який продукт? (T/F):");

                inputSubField1.setText("1.0");
                inputSubField2.setText("Вищий");
                inputSubField3.setText("F");
                inputSubField4.setText("F");
                break;

            case "З обмеженнями": // клас RestrictedProduct
                lblSubField1.setText("Мінімальний вік (років):");
                lblSubField2.setText("Потрібна акцизна марка? (T/F):");
                lblSubField3.setText("Макс. об'єм продажу (л/кг):");
                lblSubField4.setText("Висока цінність? (T/F):");

                inputSubField1.setText("18");
                inputSubField2.setText("T");
                inputSubField3.setText("2.0");
                inputSubField4.setText("F");
                break;

            case "Власна продукція": // клас PreparedProduct
                lblSubField1.setText("ПІБ Кухаря:");
                lblSubField2.setText("Цех приготування:");
                lblSubField3.setText("Гаряча страва? (T/F):");
                lblSubField4.setText("Час приготування (хв):");

                inputSubField2.setText("Холодний цех");
                inputSubField3.setText("F");
                inputSubField4.setText("15");
                break;
        }
    }

    @FXML
    private void onAcceptSupplyClick() {
        try {
            // зчитування базових полів форми
            String codeStr = inputSupplyCode.getText().trim();
            String name = inputSupplyName.getText().trim();
            String priceStr = inputSupplyPrice.getText().trim();
            String discountStr = inputSupplyDiscount.getText().trim();
            String productionDate = inputSupplyDate.getText().trim();
            String expiryDaysStr = inputSupplyExpiryDays.getText().trim();
            String tempMinStr = inputSupplyTempMin.getText().trim();
            String tempMaxStr = inputSupplyTempMax.getText().trim();
            String supplyQtyStr = supplyQuantity.getText().trim();

            //перевірка
            if (name.isEmpty() || priceStr.isEmpty() || discountStr.isEmpty() ||
                    productionDate.isEmpty() || expiryDaysStr.isEmpty() ||
                    tempMinStr.isEmpty() || tempMaxStr.isEmpty() || supplyQtyStr.isEmpty()) {
                txtSupplyLogArea.setText("Помилка:\nВсі базові поля продукту мають бути заповнені!\n" +
                        "(Включаючи Знижку, Дату, Термін, Температуру та Кількість)");
                return;
            }

            //парсинг базових полів
            int code = codeStr.isEmpty() ? 0 : Integer.parseInt(codeStr);
            double price = Double.parseDouble(priceStr.replace(",", "."));
            double discount = Double.parseDouble(discountStr.replace(",", "."));
            int shelfLifeDays = Integer.parseInt(expiryDaysStr);
            double minTemp = Double.parseDouble(tempMinStr.replace(",", "."));
            double maxTemp = Double.parseDouble(tempMaxStr.replace(",", "."));
            double supplyQty = Double.parseDouble(supplyQtyStr.replace(",", "."));

            //валідація
            if (supplyQty <= 0) {
                txtSupplyLogArea.setText("Помилка: Кількість поставки має бути більшою за 0");
                return;
            }
            if (minTemp > maxTemp) {
                txtSupplyLogArea.setText("Помилка: Мінімальна температура не може бути більшою за максимальну");
                return;
            }

            Product newProduct = null;

            if (radioPiece.isSelected()) {
                //зчитуємо специфічні поля для PieceProduct
                int openingDays = inputSubField1.getText().isEmpty() ? 3 : Integer.parseInt(inputSubField1.getText().trim());
                double pkgWeight = inputSubField2.getText().isEmpty() ? 15.0 : Double.parseDouble(inputSubField2.getText().trim().replace(",", "."));
                String pkgMaterial = inputSubField3.getText().isEmpty() ? "Поліетилен" : inputSubField3.getText().trim();
                boolean fragile = inputSubField4.getText().isEmpty() ? false : parseBooleanStrict(inputSubField4.getText());

                newProduct = new store.objects.PieceProduct(code, price, name, discount, productionDate,
                        shelfLifeDays, minTemp, maxTemp, pkgWeight, pkgMaterial, fragile, openingDays);
            } else if (radioWeight.isSelected()) {
                double weightKg = inputSubField1.getText().isEmpty() ? 1.0 : Double.parseDouble(inputSubField1.getText().trim().replace(",", "."));
                String grade = inputSubField2.getText().isEmpty() ? "Вищий" : inputSubField2.getText().trim();
                boolean organic = inputSubField3.getText().isEmpty() ? false : parseBooleanStrict(inputSubField3.getText());
                boolean soft = inputSubField4.getText().isEmpty() ? false : parseBooleanStrict(inputSubField4.getText());

                newProduct = new store.objects.WeightProduct(code, price, name, discount, productionDate,
                        shelfLifeDays, minTemp, maxTemp, weightKg, grade, organic, soft);
            } else if (radioRestricted.isSelected()) {
                int minAge = inputSubField1.getText().isEmpty() ? 18 : Integer.parseInt(inputSubField1.getText().trim());
                boolean requiresExcise = inputSubField2.getText().isEmpty() ? true : parseBooleanStrict(inputSubField2.getText());
                double maxVolume = inputSubField3.getText().isEmpty() ? 2.0 : Double.parseDouble(inputSubField3.getText().trim().replace(",", "."));
                boolean highValue = inputSubField4.getText().isEmpty() ? false : parseBooleanStrict(inputSubField4.getText());

                newProduct = new store.objects.RestrictedProduct(code, price, name, discount, productionDate,
                        shelfLifeDays, minTemp, maxTemp, minAge, requiresExcise, maxVolume, highValue);
            } else if (radioCustom.isSelected()) {
                String chefName = inputSubField1.getText().isEmpty() ? "Кухар" : inputSubField1.getText().trim();
                String department = inputSubField2.getText().isEmpty() ? "Кухня" : inputSubField2.getText().trim();
                boolean isHot = inputSubField3.getText().isEmpty() ? false : parseBooleanStrict(inputSubField3.getText());
                int cookTime = inputSubField4.getText().isEmpty() ? 10 : Integer.parseInt(inputSubField4.getText().trim());

                newProduct = new store.objects.PreparedProduct(code, price, name, discount, productionDate,
                        shelfLifeDays, minTemp, maxTemp, chefName, department, isHot, cookTime);
            }

            if (newProduct != null) {
                if (code > 0) {
                    newProduct.setCode(code);
                }

                //реєстрація на складі
                boolean success = salesManager.replenishProductStock(newProduct, supplyQty);

                if (success) {
                    txtSupplyLogArea.setText("Успішно прийнято на склад:\n" +
                            "----------------------------------------\n" +
                            "Тип: " + newProduct.getClass().getSimpleName() + "\n" +
                            "Назва товару: " + newProduct.getName() + "\n" +
                            "Код у системі: " + newProduct.getCode() + "\n" +
                            "Завезено (шт/кг): " + supplyQty + "\n" +
                            "Термін придатності: " + shelfLifeDays + " днів\n" +
                            "Температура: від " + minTemp + " до " + maxTemp + "\n" +
                            "----------------------------------------");

                    salesManager.loadStoreData();
                    onClearSupplyFieldsClick();
                } else {
                    txtSupplyLogArea.setText("Помилка: Об'єкт відхилено методом replenishProductStock.");
                }
            }

        } catch (NumberFormatException e) {
            txtSupplyLogArea.setText("Помилка: Перевірте числові поля!\n" +
                    "Ціна, Знижка, Термін, Кількість та Температури мають бути числовими значеннями.");
        } catch (IllegalArgumentException e) {
            txtSupplyLogArea.setText("Помилка валідації додаткових полів:\n" + e.getMessage());
        } catch (Exception e) {
            txtSupplyLogArea.setText("Критична помилка: " + e.getMessage());
        }
    }

    @FXML
    private void onClearSupplyFieldsClick() {
        inputSupplyCode.clear();
        inputSupplyName.clear();
        inputSupplyPrice.clear();
        inputSupplyDiscount.clear();
        inputSupplyDate.clear();
        inputSupplyExpiryDays.clear();
        inputSupplyTempMin.clear();
        inputSupplyTempMax.clear();
        supplyQuantity.clear();

        inputSubField1.clear();
        inputSubField2.clear();
        inputSubField3.clear();
        inputSubField4.clear();

        radioPiece.setSelected(true);

        txtSupplyLogArea.clear();
        updateSubFieldsLabels("Штучний");
    }

    @FXML
    private void onApplyDiscountClick() {
        try {
            int code = Integer.parseInt(inputActionProductCode.getText().trim());
            double discountInput = Double.parseDouble(inputActionDiscount.getText().trim().replace(",", "."));

            if (discountInput < 0.0 || discountInput >= 100.0) {
                txtSupplyLogArea.setText("Помилка: Знижка має бути в межах від 0.0 до 0.99");
                return;
            }

            Map<Product, Double> catalog = salesManager.getCatalog();
            if (catalog == null || catalog.isEmpty()) return;

            Product targetProduct = null;
            for (Product p : catalog.keySet()) {
                if (p.getCode() == code) {
                    targetProduct = p;
                    break;
                }
            }

            if (targetProduct != null) {
                double oldDiscountPercent = targetProduct.getDiscount() * 100;

                if (discountInput >= 1.0) {
                    int extraDiscountPercent = (int) discountInput;
                    targetProduct.applyExtraDiscount(extraDiscountPercent);
                } else {
                    double discountInMoney = discountInput * targetProduct.getPrice();
                    targetProduct.applyExtraDiscount(discountInMoney);
                }

                salesManager.saveStoreData();

                double newDiscountPercent = targetProduct.getDiscount() * 100;
                txtSupplyLogArea.setText(
                "До товару '" + targetProduct.getName() + "' додано додаткову знижку.\n" +
                "----------------------------------------\n" +
                "Попередня знижка : " + String.format("%.0f", oldDiscountPercent) + "%\n" +
                "Введена надбавка  : " + (discountInput >= 1.0 ? discountInput + "%" : String.format("%.0f", discountInput * 100) + "%") + "\n" +
                "Нова сумарна знижка: " + String.format("%.0f", newDiscountPercent) + "%\n" +
                "----------------------------------------");

                inputActionDiscount.clear();
            } else {
                txtSupplyLogArea.setText("Помилка: Товар з кодом " + code + " не знайдено");
            }
        } catch (NumberFormatException e) {
            txtSupplyLogArea.setText("Помилка: Перевірте правильність введеного коду або знижки.");
        }
    }

    @FXML
    private void onManualWasteClick() {
        try {
            int code = Integer.parseInt(inputActionProductCode.getText().trim());
            double wasteQty = Double.parseDouble(inputActionWaste.getText().trim().replace(",", "."));

            if (wasteQty <= 0) {
                txtSupplyLogArea.setText("Помилка: Кількість для списання має бути більше 0");
                return;
            }

            Map<Product, Double> catalog = salesManager.getCatalog();
            if (catalog == null || catalog.isEmpty()) return;

            Product targetProduct = null;
            double currentStock = 0;

            for (Map.Entry<Product, Double> entry : catalog.entrySet()) {
                if (entry.getKey().getCode() == code) {
                    targetProduct = entry.getKey();
                    currentStock = entry.getValue();
                    break;
                }
            }

            if (targetProduct != null) {
                double newStock = currentStock - wasteQty;


                if (newStock <= 0) {
                    newStock = 0.0;
                }

                catalog.put(targetProduct, newStock);
                salesManager.saveStoreData();

                txtSupplyLogArea.setText("Товар успішно списано" + "\nНовий залишок на складі: " + newStock);
                inputActionWaste.clear();
            } else {
                txtSupplyLogArea.setText("Помилка: Товар з кодом " + code + " не знайдено!");
            }
        } catch (NumberFormatException e) {
            txtSupplyLogArea.setText("Помилка: Перевірте правильність введеного коду або кількості.");
        }
    }

    @FXML
    private void onDeleteFromCatalogClick() {
        try {
            int code = Integer.parseInt(inputActionProductCode.getText().trim());

            String productName = lblActionProductName.getText();
            if (productName.equals("---")) {
                txtSupplyLogArea.setText("Помилка: Товар з кодом " + code + " не існує в базі!");
                return;
            }

            salesManager.removeProductFromCatalog(code);

            txtSupplyLogArea.setText("Товар '" + productName + "' (Код: " + code + ") видалено з каталогу");

            inputActionProductCode.clear();
            inputActionDiscount.clear();
            inputActionWaste.clear();
            lblActionProductName.setText("---");

        } catch (NumberFormatException e) {
            txtSupplyLogArea.setText("Помилка: Введіть коректний числовий код товару.");
        }
    }

    //
    //
    // допоміжні методи
    //
    //

    private boolean parseBooleanStrict(String text)
    {
        if (text == null) {
            throw new IllegalArgumentException("Значення не може бути порожнім");
        }
        String clean = text.trim().toUpperCase();
        if (clean.equals("T")) {
            return true;
        } else if (clean.equals("F")) {
            return false;
        } else {
            throw new IllegalArgumentException("Очікувалось 'T' або 'F' (знайдено: " + text + ")");
        }
    }

    private double parseDoubleOptional(String text, double defaultValue) {
        if (text == null || text.trim().isEmpty()) {
            return defaultValue;
        }
        return Double.parseDouble(text.trim().replace(",", "."));
    }

    private int parseIntOptional(String text, int defaultValue) {
        if (text == null || text.trim().isEmpty()) {
            return defaultValue;
        }
        return Integer.parseInt(text.trim());
    }



}
