package constants;


public class Constant {
    public static class TimeoutVariable {
        /** Установим время для неявного ожидания (секунды или минуты можно указать в классе CommonActions */
        public static final int IMPLICIT_WAIT = 1;

        /** Время в секундах для объявления явного ожидания */
        public static final int EXPLICIT_WAIT = 10;
    }

    public static class Urls {
        /** Базовая страница для выполнения тестов */
        public static final String SWAG_LABS_HOME_PAGE = "https://www.saucedemo.com/";
    }
}
