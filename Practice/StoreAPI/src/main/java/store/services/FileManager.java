package store.services;

import store.objects.Product;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager
{
    private String baseDir;

    public FileManager(String baseDir) {
        this.baseDir = baseDir;
    }

    private void serialize(File file, Map<Product, Double> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Product, Double> deserialize(File file) {
        if (!file.exists()) return new HashMap<Product, Double>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<Product, Double> map = (Map<Product, Double>) ois.readObject();
            if (map == null) return new HashMap<Product, Double>();
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void serializeSold(File file, Map<Product, double[]> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Product, double[]> deserializeSold(File file) {
        if (!file.exists()) return new HashMap<Product, double[]>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<Product, double[]> map = (Map<Product, double[]>) ois.readObject();
            if (map == null) return new HashMap<Product, double[]>();
            return map;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //
    // Каталог
    //
    public void saveCatalog(Map<Product, Double> catalog) {
        File file = new File(baseDir + File.separator + "Каталог.dat");
        serialize(file, catalog);
    }

    public Map<Product, Double> loadCatalog() {
        File file = new File(baseDir + File.separator + "Каталог.dat");
        return deserialize(file);
    }

    //
    // Продані продукти
    //
    public void saveSoldProducts(String date, Map<Product, double[]> soldMap) {
        String dirPath = baseDir + File.separator + "Дані";
        ensureDirectoryExists(dirPath);
        File file = new File(dirPath + File.separator + "Продажі_" + date + ".dat");
        serializeSold(file, soldMap);
    }

    public Map<Product, double[]> loadSoldProducts(String date) {
        String filePath = baseDir + File.separator + "Дані" + File.separator + "Продажі_" + date + ".dat";
        File file = new File(filePath);
        return deserializeSold(file);
    }

    //
    // Публічні методи для запису
    //
    public void writeReceiptFile(String date, String receiptText) {
        String receiptsDirStr = baseDir + File.separator + "Продажі" + File.separator + date + File.separator + "Чеки";
        File receiptsDir = new File(receiptsDirStr);

        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs();
        }
        int receiptNumber = this.getRecieptsCount(date) + 1;

        String filePath = receiptsDirStr + File.separator + "Чек_" + receiptNumber + ".txt";
        writeText(receiptText, filePath);
    }

    public void writeDailySummaryReport(String date, String reportText) {
        String dirPath = baseDir + File.separator + "Звіти" + File.separator + "Продажі";
        ensureDirectoryExists(dirPath);

        String filePath = dirPath + File.separator + "Звіт_з_продажів_за_" + date + ".txt";
        writeText(reportText, filePath);
    }

    public void writeWasteReportFile(String date, String reportText) {
        String dirPath = baseDir + File.separator + "Звіти" + File.separator + "Списання";
        ensureDirectoryExists(dirPath);

        String filePath = dirPath + File.separator + "Звіт_списання_за_" + date + ".txt";
        writeText(reportText, filePath);
    }

    public void writeRevisionReportFile(String date, String reportText) {
        String dirPath = baseDir + File.separator + "Звіти" + File.separator + "Ревізії";
        ensureDirectoryExists(dirPath);

        String filePath = dirPath + File.separator + "Ревізія_за_" + date + ".txt";
        writeText(reportText, filePath);
    }

    //
    // Публічні методи для читання
    //
    public String readReceiptFile(String date, int receiptNumber) {
        String filePath = baseDir + File.separator + "Продажі" + File.separator + date +
                File.separator + "Чеки" + File.separator + "Чек_" + receiptNumber + ".txt";
        return readText(filePath);
    }

    public String readDailySummaryReport(String date) {
        String filePath = baseDir + File.separator + "Звіти" + File.separator + "Продажі" +
                File.separator + "Звіт_з_продажів_за_" + date + ".txt";
        return readText(filePath);
    }

    public String readWasteReportFile(String date) {
        String filePath = baseDir + File.separator + "Звіти" + File.separator + "Списання" +
                File.separator + "Звіт_списання_за_" + date + ".txt";
        return readText(filePath);
    }

    public String readRevisionReportFile(String date) {
        String filePath = baseDir + File.separator + "Звіти" + File.separator + "Ревізії" +
                File.separator + "Ревізія_за_" + date + ".txt";
        return readText(filePath);
    }

    //
    // Методи читання та запису тексту
    //
    private void writeText(String text, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readText(String path) {
        File file = new File(path);
        if (!file.exists()) return "Файл не знайдено: " + path;

        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                content += line + "\n";
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRecieptsCount(String date) {
        String receiptsDirStr = baseDir + File.separator + "Продажі" + File.separator + date + File.separator + "Чеки";
        File receiptsDir = new File(receiptsDirStr);
        File[] files = receiptsDir.listFiles();
        return (files != null) ? files.length : 0;
    }

    private void ensureDirectoryExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}