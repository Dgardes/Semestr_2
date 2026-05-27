package store.services;

import store.objects.Product;
import store.objects.WeightProduct;

import java.util.Map;

public class LogBuilder
{
    private LogBuilder() {}

    public static String generateCustomerReceipt(Map<Product, Double> cartItems, double taxRate, double totalCost, int receiptNumber, String date) {
        String receipt =
        "========================================\n" +
        "           ПРОДУКТОВИЙ МАГАЗИН          \n" +
        "========================================\n" +
        "Чек №: " + receiptNumber + "           Дата: " + date + "\n" +
        "----------------------------------------\n";

        if (cartItems != null && !cartItems.isEmpty())
        {
            for (Map.Entry<Product, Double> entry : cartItems.entrySet())
            {
                Product product = entry.getKey();
                Double quantity = entry.getValue();
                double itemCost;
                double itemPricePerUnit;

                //розрахунок вартості залежно від типу продукту
                if (product instanceof WeightProduct) {
                    itemCost = ((WeightProduct) product).calculateFinalPrice(taxRate, quantity);
                    itemPricePerUnit = itemCost / quantity;
                } else {
                    itemPricePerUnit = product.calculateFinalPrice(taxRate);
                    itemCost = itemPricePerUnit * quantity;
                }

                //додаємо базовий рядок товару
                receipt += product.getName() + "\n" + "  " + String.format("%.2f", quantity) + " х "
                        + String.format("%.2f", itemPricePerUnit) + " грн = " + String.format("%.2f", itemCost) + " грн\n";

                //інтеграція методів PieceProduct
                if (product instanceof store.objects.PieceProduct) {
                    store.objects.PieceProduct pp = (store.objects.PieceProduct) product;

                    //getNewShelfLifeAfterOpening()
                    String expiryDateStr = pp.getNewShelfLifeAfterOpening();
                    if (expiryDateStr != null) {
                        receipt += "   * Вжити після відкриття до: " + expiryDateStr + "\n";
                    }

                    //spoilsQuicklyAfterOpening(int criticalDays)
                    if (pp.spoilsQuicklyAfterOpening(3)) {
                        receipt += "     [Увага: Швидкопсувний продукт!]\n";
                    }

                    //requiresEcoFriendlyDisposal()
                    if (pp.requiresEcoFriendlyDisposal()) {
                        receipt += "     (Еко-маркування: Утилізуйте упаковку окремо!)\n";
                    }
                }

            }
        } else {
            receipt += "Кошик порожній.\n";
        }

        receipt +=
        "----------------------------------------\n" +
        "Загальна сума (з ПДВ): " + String.format("%.2f", totalCost) + " грн\n" +
        "========================================\n" +
        "          ДЯКУЄМО ЗА ПОКУПКУ!           \n" +
        "========================================\n";

        return receipt;
    }

    public static String generateDailySalesSummaryReport(Map<Product, double[]> soldProducts, String date) {
        String report =
        "================================================================================\n" +
        "                      ЗВІТ З ПРОДАЖІВ ЗА ДЕНЬ (ФІНАНСОВИЙ)                      \n" +
        "================================================================================\n" +
        "Дата звіту: " + date + "\n" +
        "--------------------------------------------------------------------------------\n";

        if (soldProducts != null && !soldProducts.isEmpty())
        {
            double totalIncome = 0;
            double totalGovTaxes = 0; //змінна для накопичення загальних податків держави

            double weightQty = 0.0;
            int pieceQty = 0;
            int restrictedQty = 0;
            int preparedQty = 0;

            for (Map.Entry<Product, double[]> entry : soldProducts.entrySet())
            {
                Product product = entry.getKey();
                double[] financialData = entry.getValue();

                if (product == null || financialData == null) continue;

                double quantitySold = financialData[0];
                double productIncome = financialData[1];

                totalIncome += productIncome;

                //базовий рядок інформації про товар
                String row =
                "[Код: " + product.getCode() + "] " + product.getName() +
                " | Продано: " + String.format("%.2f", quantitySold) +
                " | Виручка: " + String.format("%.2f", productIncome) + " грн\n";

                if (product instanceof store.objects.WeightProduct)
                {
                    weightQty += quantitySold;
                    report += row;
                }
                else if (product instanceof store.objects.PreparedProduct)
                {
                    preparedQty += (int) quantitySold;
                    report += row;
                }
                else if (product instanceof store.objects.RestrictedProduct)
                {
                    restrictedQty += (int) quantitySold;

                    //calculatePureStoreRevenue()
                    store.objects.RestrictedProduct rp = (store.objects.RestrictedProduct) product;

                    double storeRevenue = rp.calculatePureStoreRevenue(0.20, quantitySold);
                    double govTaxes = productIncome - storeRevenue;
                    totalGovTaxes += govTaxes;

                    report += row;
                    report +=
                    "–> [Аналітика: Чистий дохід магазину: " + String.format("%.2f", storeRevenue) +
                    "грн | Податки та акциз державі: " + String.format("%.2f", govTaxes) + " грн]\n";
                }
                else
                {
                    pieceQty += (int) quantitySold;
                    report += row;
                }
            }

            report +=
            "--------------------------------------------------------------------------------\n" +
            "Загальна сума виручки за день : " + String.format("%.2f", totalIncome) + " грн\n";

            report +=
            "--------------------------------------------------------------------------------\n" +
            "Показники за категоріями:\n" +
            "  Вагові продукти              : " + String.format("%.3f", weightQty) + " кг.\n" +
            "  Поштучні продукти            : " + pieceQty + " шт.\n" +
            "  Алкоголь/тютюн               : " + restrictedQty + " шт.\n" +
            "  Власна кухня                 : " + preparedQty + " шт.\n";
        }
        else
        {
            report += "За вказану дату продажів не зафіксовано.\n";
        }
        report += "================================================================================\n";
        return report;
    }

    public static String generateRevisionReport(Map<Product, Double> catalog, String date) {
        String report =
        "================================================================================\n" +
        "                          Звіт поточного стану складу                           \n" +
        "================================================================================\n" +
        "Дата формування: " + date + "\n" +
        "--------------------------------------------------------------------------------\n";

        if (catalog != null && !catalog.isEmpty())
        {
            for (Map.Entry<Product, Double> entry : catalog.entrySet())
            {
                Product product = entry.getKey();
                Double quantity = entry.getValue();
                report += "[К-сть: " + String.format("%.2f", quantity) + "] " + product.generateInvoiceRow("грн") + "\n";
            }
        }
        else
        {
            report += "Склад порожній.\n";
        }

        report +=
        "--------------------------------------------------------------------------------\n" +
        "Кінець звіту обліку залишків.\n" +
        "================================================================================\n";

        return report;
    }

    public static String generateWasteReport(Map<Product, Double> expiredProducts, String date) {
        String report =
        "================================================================================\n" +
        "                             Звіт списання товарів                              \n" +
        "================================================================================\n" +
        "Дата формування: " + date + "\n" +
        "--------------------------------------------------------------------------------\n";

        if (expiredProducts != null && !expiredProducts.isEmpty())
        {
            double totalWaste = 0;
            for (Map.Entry<Product, Double> entry : expiredProducts.entrySet())
            {
                Product product = entry.getKey();
                Double quantity = entry.getValue();
                double wasteCost = product.getPrice() * quantity;
                totalWaste += wasteCost;

                report +=
                "[Списано: " + String.format("%6.2f", quantity) + "] [Код: " + product.getCode() + "] " +
                product.getName() + " – Собівартість втрат: " + String.format("%.2f", wasteCost) + " грн\n";
            }
            report +=
            "--------------------------------------------------------------------------------\n" +
            "Загальна сума збитків списання: " + String.format("%.2f", totalWaste) + " грн\n";
        } else
        {
            report += "Прострочених товарів під час перевірки не виявлено.\n";
        }
        report += "================================================================================\n";

        return report;
    }
}