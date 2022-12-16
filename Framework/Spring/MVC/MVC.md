# Spring MVC

Предполагает разработку web-приложений с использованием архитектуры **Model - View - Controller**.

## MVC | (Model - View - Controller)

+ **Model** - логика работы с данными
+ **View** - логика представления, интерфейс
+ **Controller** - логика навигации, обработка запросов (HTTP)

<br>


## Дополнительные зависимости (pop.xml)

```xml
<dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>...</version>
</dependency>

spring-webmvc
thymeleaf-spring5
```
___
<br>

## DispatcherServlet

Входная точка Spring MVC приложения. Находиться между `HTTP Reques` и `Controller`.

HTTP запрос от пользователя:

1. Приходит на сервер. Сервер обрабатывает запрос и передает его на Spring MVC приложение.
2. Запрос попадает в `DispatcherServlet`. 
3. `DispatcherServlet` отправляет запрос на правильный контроллер.
___
<br>

## Controller

`@Controller`
+ Обрабатывает запросы от пользователя
+ Обменивается данными с моделью 
+ Показывает пользователю правильное представление 
+ Переадресовывается пользователя на другие страницы

___
<br>

## Model
+ Хранит в себе данные
+ Взаимодействует с БД для получения данных
+ Отдает данные контроллеру

___
<br>

## View
+ Получает данные от контроллера и отображает их в браузере
+ Для динамического отображения данных используются шаблонизаторы (**Thymeleaf**, Freemarker, Velocity)
___
<br>

