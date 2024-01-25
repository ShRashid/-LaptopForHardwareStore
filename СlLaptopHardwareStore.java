import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int ssd;
    int diagonal;
    String os;
    String color;

    public Laptop(String brand, int ram, int ssd, int diagonal, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.ssd = ssd;
        this.diagonal = diagonal;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", ssd=" + ssd +
                ", diagonal=" + diagonal +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class СlLaptopHardwareStore {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("MSI", 16, 1024,14, "Windows", "Silver"));
        laptops.add(new Laptop("Dell", 16, 512,17, "Windows", "Black"));
        laptops.add(new Laptop("Sony", 32, 1024,14, "Windows", "Gray"));
        laptops.add(new Laptop("Apple", 8, 256,19, "MacOS", "Silver"));
        laptops.add(new Laptop("Acer", 8, 256,14, "NotOS", "White"));
        laptops.add(new Laptop("Lenovo", 32, 1024,17, "Windows", "Gray"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);        

        int choice;
        while (true) {
            PrintMenu();
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Наименование Бренда ? (Пример: MSI/Dell/Apple/Acer/Lenovo/Sony)");
                    filters.put("brand", scanner.next());
                    break;
                case 2:
                    System.out.println("Минимальный объем ОЗУ? (Пример: 8/16/32/64/128)");
                    filters.put("ram", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Минимальный объем SSD? (Пример: 120/256/512/1024)");
                    filters.put("hdd", scanner.nextInt());
                    break;
                case 4:
                    System.out.println("Минимальная диогональ экрана? (Пример: 14/17/19)");
                    filters.put("diagonal", scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Операционная система? (Пример: Windows/MacOS/NotOs)");
                    filters.put("os", scanner.next());
                    break;
                case 6:
                    System.out.println("Цвет? (Пример: Gray/Black/Red/Green/Blue/Silver/White)");
                    filters.put("color", scanner.next());
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("brand", "").equals("") || laptop.brand.equalsIgnoreCase((String) filters.getOrDefault("brand", "")))
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("ssd", 0) instanceof Integer && laptop.ssd >= (int) filters.getOrDefault("ssd", 0))
                .filter(laptop -> filters.getOrDefault("diagonal", 0) instanceof Integer && laptop.diagonal >= (int) filters.getOrDefault("diagonal", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Ноутбуки соответствующие критерию отбора:");
        for (Laptop laptop : filteredLaptops) {            
            System.out.println(laptop);            
        }
    }

    public static void PrintMenu() {
        System.out.println("Выберите необходимые критерии для фильтрации:");
        System.out.println("1 - Бренд ");
        System.out.println("2 - ОЗУ ");
        System.out.println("3 - Объем диска ");
        System.out.println("4 - Диоганаль экрана ");
        System.out.println("5 - Операционная система ");
        System.out.println("6 - Цвет ");
        System.out.println("0 - Завершить выбор");        
    }
}