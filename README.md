  # Дипломный проект по провессии "Тестировщик ПО"
  ### Описание 
Для выполнения диплломного проекта требовалость автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
### Бизнес-часть
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

Обычная оплата по дебетовой карте.

Уникальная технология: выдача кредита по данным банковской карты.

## Ссылки на документацию
1. [Дипломное задание](https://github.com/netology-code/qa-diploma).
1. [План тестирования](https://github.com/ForesterinForest/DiplomQA/blob/main/docs/Plan.md);
1. [Отчёт по итогам тестирования](https://github.com/ForesterinForest/DiplomQA/blob/main/docs/Report.md);
1. [Отчет по итогам автоматизации](https://github.com/ForesterinForest/DiplomQA/blob/main/docs/Summary.md)


## Начало работы
1. Установить и запустить IntelliJ IDEA;
1. Установать и запустить Docker Desktop;
1. Скопировать репозиторий с Github командой git clone [ссылка](https://github.com/ForesterinForest/DiplomQA).
1. Открыть проект в IntelliJ IDEA.

## Запуск тестовой стреды
1. Запускаем необходимые контейнеры одной командой в терминале (Local)

* MySQL, PostgreSQL, NodeJS

  ```
  docker-compose up
  ```
2. Запускаем тестируемое приложение командой в терминале (Local 2)

***Работа двух приложений одновременно невозможна***

* Для MySQL
```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```
* Для PostgreSQL
```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/db" -jar artifacts/aqa-shop.jar
```
* Остановка прилежения осуществляется сочитанием `Ctrl + c`

## Запуск тестов
Запускаем тесты в терминале для MySQL в терминале (Local 3) и для PostgreSQL в терминале (Local 4)
* Для MySQL:
   ```
  ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
   ```
*  Для PostgreSQL:
   ```
   ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/db"
   ```
## Формирование отчетности в системе allure

```
./gradlew allureServe
```
   