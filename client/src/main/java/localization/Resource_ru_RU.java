package localization;

import java.util.ListResourceBundle;

public class Resource_ru_RU extends ListResourceBundle {
    public static final Object[][] contents = {
            {"insert", "вставить"},
            {"show", "показать"},
            {"help", "помощь"},
            {"info", "информация"},
            {"clear", "очистить"},
            {"execute script", "выполнить скрипт"},
            {"count greater semester", "подсчитать с большим семестром"},
            {"filter less students count", "отобрать с меньшим количеством студентов"},
            {"remove greater key", "удалить с большим ключом"},
            {"log out", "выйти"},
            {"remove key", "удалить ключ"},
            {"remove lower", "удалить меньшие"},
            {"replace if greater", "удалить, если больше"},
            {"update", "обновить"},
            {"id", "ид"},
            {"name", "имя"},
            {"coordinates", "координаты"},
            {"creation date", "дата создания"},
            {"students count", "количество студентов"},
            {"expelled students", "отчисленные"},
            {"should be expelled", "будут отчислены"},
            {"semester", "семестр"},
            {"admin", "админ"},
            {"height", "рост"},
            {"hair color", "цвет волос"},
            {"nationality", "национальность"},
            {"location", "локация"},
            {"Novikov & Vinogradov lab8", "Новиков и Виноградов лаба8"},
            {"login", "логин"},
            {"password", "пароль"},
            {"sign up", "регистрация"},
            {"log in", "вход"},
            {"this field is required", "это обязательное поле"},
            {"incorrect login/password", "неверный логин/пароль"},
            {"Server error, try again!", "Ошибка сервера, попробуйте снова!"},
            {"User with this login already exists", "Пользователь с таким логином уже существует"},


    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
