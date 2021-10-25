![](https://i.ibb.co/PNq2bYt/788135203-w640-h640-pchelovodbiz.png)
# Bumblebee GeneratorService 
## Version : 1.0.0
## Назначение :
`Платформа генерации тестовых данных для ручного или автоматизированного тестирования.`
## Описание генераторов:
* Классы отвечающие за генерацию тестовых данных находятся в пакете **.generators** и реализуют интерфейс **DataGenerator** для работы с генераторами используются две аннотации:
---
```java
@GeneratorDescription(
        generatorName = "Название генератора. Используется при создание объекта и валидации параметров",
        generatorClass = generatorClass.class,
        description = "Описание генератора. Используется при запросе информации о имеющихся генераторах"
)
```
**Пример:**
```java
@GeneratorDescription(
        generatorName = "SymbolGenerator",
        generatorClass = SymbolDataGenerator.class,
        description = "Generator for create random values"
)
```
---
```java
@GeneratorParameter(
        name = "Имя параметра. Используется для установки значения",
        description = "Описание параметра.Используется при запросе информации о параметрах генератора",
        InClass = "InClass.class, класс параметра. Используется для установки значени. !Не использовать примитивные типы!"
)
```
**Пример:**
```java
@GeneratorParameter(
        name = "count",
        description = "Number of text values in the list",
        InClass = Integer.class
)
```
---
* Примером реализации класса-генератора является SymbolDataGenerator в пакете **.generators**

## Описание url:
1. *Swagger UI доступен по адресу: http://localhost:8081/generator-service/swagger-ui.html#*
2. *Запрос на добавление контейнера(Контейнер - объект ) http://localhost:8081/generator-service/containers*
3. *Процесс инициализация и запуск генераторов доступен по адресу http://localhost:8081/generator-service/generators*
---
**Формат запроса:**
```json
{
  "cuid": "Уникальный идентификатор контейнера",
  "generatorInfo": [
    {
      "generatorName": "Имя генератора из аннотации @GeneratorParameter.generatorName",
      "values": {
        "Название параметра из аннотации @GeneratorParameter.name": "Значение параметра"
      }
    }
  ]
}
```
**Пример:**
```json
{
  "cuid": "112311134664332",
  "generatorInfo": [
    {
      "generatorName": "SymbolGenerator",
      "values": {
        "len": "25",
        "count": "22",
        "mode": "STRING",
        "isCascade":"true",
        "isNull":"false"
      }
    }
  ]
}
```
---
3.1 *Для запрос информации по всем имеющимся генераторам и их параметров воспользуйтесь http://localhost:8081/generator-service/generators/information*

---
**Пример:**
```json
[
  {
    "generatorName": "SymbolGenerator",
    "generatorDescription": "Generator for create random values",
    "parameters": [
      {
        "parameter": "len",
        "parameterDescription": "The length of the text value, applied if isCascade = false"
      },
      {
        "parameter": "count",
        "parameterDescription": "Number of text values in the list"
      },
      {
        "parameter": "mode",
        "parameterDescription": "Maybe value STRING or NUMBER"
      },
      {
        "parameter": "isNull",
        "parameterDescription": "The presence of a NULL value"
      },
      {
        "parameter": "isCascade",
        "parameterDescription": "Cascading increment of values in a text expression"
      }
    ]
  }
]
```
---






