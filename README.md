# Generator Bumblebee
## Version : 0.0.1 (prototype)
## Описание :
>`Сервис позволяет сгенерировать тестовые данные по заданным шаблонам и выгрузить в формате xlsx и csv`
## Используемые технологии :
* `Java 8`
* `Hibernate 5.2.16.Final`
* `Postgresql 42.2.2`
* `Spring  5.0.6`
* `Aspectjtools 1.9.1`
* `Apache POI 3.17`
* `Junit 4.12`
## Запуск : 
* `Для запуска проекта необходима БД  Postgresql :` [Generator BD](https://github.com/TimurBaldin/Bumblebee/tree/develop/src/database/migration)
* `Файл настроек для Hibernate доступен по ссылке :`  [Configuration](https://github.com/TimurBaldin/Bumblebee/blob/develop/src/main/resources/hibernate.cfg.xml)
* `Собрать проект "BootJar" ---> перейти в папку  \build\libs ---> запустить Bumblebee-0.0.1 `
* `Перейти по ссылке : http://localhost:8080/`
* `После перехода по ссылке , открывается стартовая стриница ` [Start_page](https://drive.google.com/file/d/17abrZwuefXY72D1SWx5C4B2gezVVKyy6/view?usp=sharing)
## Алгоритм создания тестовых данных  : 
* `Нажать на пункт меню "Создать тестовые данные" `
* `Ввести название колонки ---> нажать на кнопку "Добавить колонку" `
* `Задать параметры для необходимых проверок ----> нажать на кнопку "Сохранить проверку" `
* `Нажать на кнопку "Сохранить колонку". При необходимости добавить в отчет колонку ,повторить действия с шага 2 `
* `Нажать на кнопку "Сохранить модель" `
* `Заполнить параметры отчета (Report Excel/Report CSV) ----> нажать на кнопку "Загрузить" `

