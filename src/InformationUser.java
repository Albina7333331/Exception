import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InformationUser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата рождения:dd.mm.yyyy номер телефона:xxxxxxxxxx пол:f или m");
        String input = scanner.nextLine();

        scanner.close();

        try {
            String[] value = input.split(" ");
            if (value.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Должно быть 6 значений.");
            }

            String lastName = value[0];
            String firstName = value[1];
            String middleName = value[2];
            String dob = value[3];
            long phoneNumber = Long.parseLong(value[4]);
            char gender = value[5].charAt(0);

            if (!isValidDate(dob)) {
                throw new IllegalArgumentException("Неверный формат даты рождения");
            }

            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Неверный формат пола. Используйте 'f' или 'm'");
            }


            String fileName = lastName + ".txt";
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(lastName + " " + firstName + " " + middleName + " " + dob + " " + phoneNumber + " " + gender + "\n");
            writer.close();

            System.out.println("Данные успешно записаны в файл: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат номера телефона. Введите целое число без разделителей.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static boolean isValidDate(String dob) {
        String[] parts = dob.split("\\.");
        if (parts.length != 3) {
            return false;
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 2024) {
            return false;
        }

        return true;
    }
}
