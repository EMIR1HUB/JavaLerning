# Spring MVC

+ [DispatcherServlet](#ds)
+ [Controller](#controller)
+ [HTTP методы](#http)
+ [Обработка параметров GET запроса](#get)
+ [Model](#model)
+ [View](#view)

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

<a name="ds"></a>
# DispatcherServlet

Входная точка Spring MVC приложения. Находиться между `HTTP Reques` и `Controller`.

HTTP запрос от пользователя:

1. Приходит на сервер. Сервер обрабатывает запрос и передает его на Spring MVC приложение.
2. Запрос попадает в `DispatcherServlet`. 
3. `DispatcherServlet` отправляет запрос на правильный контроллер.
___
<br>

<a name="controller"></a>
# Controller

`@Controller`
+ Обрабатывает запросы от пользователя
+ Обменивается данными с моделью 
+ Показывает пользователю правильное представление 
+ Переадресовывается пользователя на другие страницы

`@Controller` - тот же `@Component`, но с дополнительными возможностями. В контроллере может быть несколько методов, которые возвращают строки ( название представления, которое будет показано пользователю ). Каждый метод соответствует одному URL'у

<br>

## Маппинги

Связывают метод контроллера с адресом, по которум можно к этому методу обратиться (например, из браузера). Всего 5 разных видов.

```Java
@Controller
public class HelloController {
      @GetMapping("/hello-world")   // URL-адрес
      public String sayHello() { return "hello_world"; }

      // иногда пишут устаревший вариант
      @RequestMapping(method = RequestMethod.GET)
}
```

### **@RequestMapping**
Используется для мапинга с URL для всего класса или для конкретного метода обработчика.

```Java
@Controller
@RequestMapping("/people")
public class PersonController {
      @GetMapping("/new") // обрабатывает запросы /people/new
      public String new() { return "new"; }

      @GetMapping("/old") // /people/old
      public String old() { return "old"; }
}
```
<br>

<a name="http"></a>
## HTTP 

**Hyper Text Transfer Protocol** - запросы (методы)
| **HTTP методы** | **POST-параметры** | **Идемпотентность** | **Аннотация** | **Описание**                                                                                                                                                                                                                                                                                                                                                                                                                   |
| --------------- | ------------------ | ------------------- | ------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| GET             | нет                | да                  | @GetMapping   | Самый используемый запрос. Получение данных с сервера (переход по адресу, ссылке, поисковой запрос и т.д.) <br><br> Параметры нужны для передачи информации от клиента серверу во время запроса. Передаются в самом URL после знака `?` в формате **ключ=значение**. Несколько параметров разделяются знаком `&`. <br><br> https://vk.com/audios202613076?q=joji&section=playlist <br> Параметры (?q=lilpeep&section=playlist) |
| POST            | да                 | нет                 | @PostMapping  | Добавление (изменение) данных на сервер (отправка данных с веб-форм, создать учетную запись, твитнуть, загрузить фото, добавить запись в сообщество). Параметры закодированы в теле запроса. <br> Content-Type может быть разный (JSON, XML, и т.д.)|
| PUT | да | да |
| PATCH | да | да |
| DELETE | нет | да |

> Идемпотентность - ничего не меняет на сервере

<br>

### HTTP - статус ответа (response)

200 - 299 - ОК

300 - 399 - Редирект

400 - 499 - Клиентские ошибки 
+ 401 не авторизован (юзер не представился)
+ 403 запрещено (юзер не уполномочен)
+ 404 ресурс не найден

500 - 599 - Серверные ошибки
+ 502 Bad Gateway. сервер не смог обработать полученный запрос по техническим причинам

`curl -I domain.ru` - получение командой заголовки ответа сервера 
___
<br>

<a name="get"></a>
## Обработка параметров GET запроса 

Пример: localhost:8080`?name=Elon&surname=Musk`

Параметры GET запроса можно получить двумя сопособами (практически эквиваленты):

1. С помощью объекта `HttpServletRequest`, если нужно получить полный доступ к HTTP запросу

```Java
@GetMapping("/hello")
public String helloPage(HttpServletRequest request) {
      String name = request.getParameter("name");
      // Работаем с пришедшим от пользователя параметром

      return "home/hello";
}
```

2. С помощью аннотации `@RequestParam`, если нужны только  параметры запроса
```Java
@GetMapping("/hello")
public String helloPage(@RequestParam("name") String name) {
      // Работаем с пришедшим от пользователя параметром

      return "home/hello";
}
```
`@RequestParam(value = "name", required = false)` - в таком случае можно будет передавать пустые параметры с **null**
___

<br>

<a name="model"></a>
# Model
+ Хранит в себе данные
+ Взаимодействует с БД для получения данных
+ Отдает данные контроллеру

<br>

## Получение доступа к модели в контроллере

Для получения доступа к **модели** в параметрах метода контроллера пишем `Model` и Spring автоматически внедряет данный объект. 

С помощью метода `addAttribute` можем положить в `Model` пару **ключ:значение**, после чего модель будет отправленна на `View` (представление) и с помощью шаблонизатора `Thymeleaf` можем получить значение по ключу.

```Java
@GetMapping("/hello")
public String helloPage(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "surname", required = false) String surname,
                        Model model) {

      model.addAttribute("message", "Hello " + name + " " + surname);
      return "home/hello";
}
```

```html
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">

<body>
      <a href="/page/hello?name=Elon&surname=Mask">Request with Parameter</a>
      <p th:text="${message}"/>
</body>

</html>
```


___
<br>

<a name="view"></a>
# View
+ Получает данные от контроллера и отображает их в браузере
+ Для динамического отображения данных используются шаблонизаторы (**Thymeleaf**, Freemarker, Velocity)
___
<br>

