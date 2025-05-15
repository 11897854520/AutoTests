# Проект "AutoTests"
______
## Описание проекта
Данный проект содержит автотесты для сайта "https://demo.reportportal.io".
_____
## Cтек проекта
1. JDK 11.0.22;
2. Rest assured 5.5.0;
3. Selenium 3.141.59;
4. Allure junit5 2.6.0;
____
## Необходимые инструменты
1. Maven;
2. Intellij idea;
3. Git
   Директории бинарных файлов Maven и ChromeDriver должны быть добавлены в переменную системного окружения PATH.
___   
## Запуск тестов через Maven
Все команды выполнять перейдя в директорию проекта через командную строку.

Проверка наличия maven mvn -v.

Сборка проекта mvn clean install.

Запуск тестов mvn clean test.

Запуск отдельного класса/пакета:

### Запуск UI-теста
mvn clean test -Dtest=WidgetCreationTest

### Запуск позитивного API-теста
mvn clean test -Dtest=PositiveDashboardCreationTest

### Запуск негативного API-теста
mvn clean test -Dtest=NegativeDashboardCreationTest

## Вывод результатов теста
1. Перейти в директорию "target" проекта;
2. В командной строке ввести команду __allure serve allure-results__
