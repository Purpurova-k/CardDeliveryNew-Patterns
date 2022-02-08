package utils;

import com.github.javafaker.Faker;
import entities.DataInfo;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class DeliveryCardForm {
        public static DataInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new DataInfo(generateCity(),
                    faker.name().lastName().replace('ё', 'е') + " "
                            + faker.name().firstName().replace('ё', 'е'),
                    faker.numerify("+79#########"));
        }


        public static String generateCity() {
            Random random = new Random();
            List<String> cities = new ArrayList<>(Arrays.asList("Майкоп", "Горно-Алтайск", "Барнаул", "Благовещенск", "Архангельск", "Астрахань",
                    "Уфа", "Белгород", "Брянск", "Улан-Удэ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Махачкала", "Биробиджан",
                    "Чита", "Иваново", "Магас", "Иркутск", "Нальчик", "Калининград", "Элиста", "Калуга", "Петропавловск-Камчатский",
                    "Черкесск", "Петрозаводск", "Кемерово", "Киров", "Сыктывкар", "Кострома", "Краснодар", "Красноярск", "Симферополь",
                    "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Йошкар-Ола", "Саранск", "Москва", "Мурманск", "Нарьян-Мар",
                    "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Пермь", "Владивосток",
                    "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Якутск", "Южно-Сахалинск", "Екатеринбург", "Севастополь",
                    "Смоленск", "Владикавказ", "Ставрополь", "Тамбов", "Казань", "Тверь", "Томск", "Тула", "Кызыл", "Тюмень", "Ижевск",
                    "Ульяновск", "Хабаровск", "Абакан", "Ханты-Мансийск", "Челябинск", "Грозный", "Чебоксары", "Анадырь", "Салехард", "Ярославль"));
            return cities.get(random.nextInt(cities.size()));
        }

        public static String generateDate() {
            return LocalDate.now()
                    .plusDays(new Faker().random().nextInt(3, 31))
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
    }

}
