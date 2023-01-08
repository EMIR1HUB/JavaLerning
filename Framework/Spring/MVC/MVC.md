## Содержание

+ [DispatcherServlet](#ds)
+ [Controller](#controller)
+ [HTTP методы](#http)
+ [Обработка параметров GET запроса](#get)
+ + [PATCH, DELETE, PUT запросы](#pdp)
+ [Model](#model)
+ [View](#view)
+ + [HTML форма в Thymeleaf](#thymeleaf)
+ + [Валидация форм, @Valid](#valid)
+ [Spring Validator](#springvalid)
___
<br>

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
| PUT | да | да | @PutMapping | Создаёт новый ресурс или заменяет представление целевого ресурса, данными представленными в теле запроса |
| PATCH | да | да | @DeleteMapping | Удаляет указанный ресурс |
| DELETE | нет | да | @PatchMapping | Частично изменяет ресурс |

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

<a name="pdp"></a>
## PATCH, DELETE, PUT запросы

PATCH, DELETE, PUT запросы передаются с помощью POST запроса, но в скрытом поле **_method** указывается желаемый HTTP метод. `Thymeleaf` берет на себя.

На стороне Spring приложения чтение скрытого поля **_method** реализуется через фильтр. 

В **Spring MVC** фильтр подключается вручную, если использовать **Spring Boot**, то нужно добавить бин в класс аннотирующийся **@SpringBootApplication**:
```Java
@Bean
HiddenHttpMethodFilter hiddenHttpMethodFilter(){
      return new HiddenHttpMethodFilter();
}
```


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
> Второй параметр `@RequestParam`( **required =** ) необязательный он указывает на то, что значения должны быть обязательно не пустыми
<br>

<a name="modelatr"></a>
## @ModelAttribute

Атрибут `@ModelAttribute` - аннотация, которая связывает параметр метода или возвращаемое значение метода с именованным атрибутом модели, а затем предоставляет его веб-представлению.

может аннотировать:
+ метод
```Java
@ModelAttribute("headerMessage")    // любая модель из данного контроллера будет иметь значение с ключом headerMessage
public String populateHeaderMessage(){
      return "Welcome to website";
}
```
В модель каждого метода текущего контроллера добавляет **ключ-значение**, которые нужны во всех моделях этого контроллера.

+ аргумент метода
```Java
@PostMapping
public String create(@ModelAttribute("person") Person person) {
      personDAO.save(person);
      return "redirect:/people";
}
```
В данном случает аннотация берет на себя: 1) создание нового объекта, 2) добавление значений в поля с помощью сеттеров, 3) добавление созданного объекта в модель 

> Если POST запрос без полей Объекта, то в модель будет положен новый объект со значениями полей по умолчанию null, 0.

___
<br>

<a name="view"></a>
# View
+ Получает данные от контроллера и отображает их в браузере
+ Для динамического отображения данных используются шаблонизаторы (**Thymeleaf**, Freemarker, Velocity)
___
<br>

<a name="thymeleaf"></a>
## HTML форма в Thymeleaf

### Контроллер:
```Java
@GetMapping("/new")
public String newPerson(Model model) {
      model.addAttribute("person", new Person());
      return "people/new";
}
```

### Представление *new.html*
```html
<form th:method="POST" th:action="@{/people}" th:object="${person}">

    <label for="name">Enter name: </label>
    <input type="text" th:field="*{name}" id="name"/>
    <br>

    <label for="email">Enter email: </label>
    <input type="text" th:field="*{email}" id="email">
    <br>

    <input type="submit" value="Create"/>
</form>
```
<br>

<a name="valid"></a>
## Валидация форм, @Valid


`Hibernate Validator` задает правила валидации с помощью аннотаций над полями класса. Определяет такие правила, как "поле не может быть null", "диапозон числа" и тд. 

Подключаем зависимость
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Пример

```Java
@NotEmpty(message = "Имя не должно быть пустым")
@Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
private String name;

@Min(value = 0, message = "Возраст должен быть больше 0")
private int age;

@NotEmpty(message = "Email не должн быть пустым")
@Email(message = "Email должен быть с @")
private String email;
```
[Популярные аннотации](https://alexkosarev.name/2018/07/30/bean-validation-api/)

### Controller

Для того чтобы значения с формы валидировались необходимо поставить аннотацию `@Valid` на моделе. Если условия валидации нарушаются, то ошибка помещается в объект `BindingResult` (должен идти после валидированной модели)

```Java
@PostMapping()
public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
      
      if (bindingResult.hasErrors()) return "people/new";   // проверка на ошибку валидации

      personDAO.save(person);
      return "redirect:/people";
}
```
<a name="viewthymeleaf"></a>
### View
```html
<form th:method="POST" th:action="@{/people}" th:object="${person}">
    <label for="name">Enter name: </label>
    <input type="text" th:field="*{name}" id="name"/>
    <!--  Скрытая запись, при ошибки -->
    <div style="color:red" th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Name Error</div>
    <br>

    <!-- .... -->
</form>
```
___
<br>

<a name="springvalid"></a>
# Spring Validator

Spring предлагает дизайн проверки (и привязки данных) с помощью интерфейса `Validator` для более сложных валидаций, который одновременно является базовым и в высшей степени пригодным для использования на каждом уровне приложения. При проверке объектов валидаторы могут сообщать `Errors` объекту об ошибки проверки.

### Пример

Обсепечим поведение проверки повторных **email** для `Person` класса, реализуя следующие два метода `org.springframework.validation.Validator` интерфейса:

+ `supports(Class)` - Может ли это `Validator` проверять экземпляры поставляемых Class?
+ `validate(Object, org.springframework.validation.Errors)`- проверяет данный объект и в случае ошибок проверки регистрирует те, которые связаны с данным `Errors` объектом

### Validator

```Java
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) { this.personDAO = personDAO; }

    @Override
    public boolean supports(Class<?> clazz) 
        return Person.class.equals(clazz);

    @Override
    public void validate(Object obj, Errors errors) {
        Person person = (Person) obj;

        // see if there is a person with the same email
        if(personDAO.show(person.getEmail()).isPresent())   // check for not null
            errors.rejectValue("email", "", "This email is already taken");
    }
}
```

### DAO
```Java
@Component
public class PersonDAO {
//...
      public Optional<Person> show(String email) {
            String SQL = "SELECT * FROM person WHERE email = ?";
            return jdbcTemplate.query(SQL, new Object[]{email}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
      }
}
```

### Controller

```Java
@PostMapping()
public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
      // в bindingResult складываются ошибки от @Valid и от объекта Errors в методе validate
      personValidator.validate(person, bindingResult);

      if (bindingResult.hasErrors()) return "people/new";
      personDAO.save(person);
      return "redirect:/people";
}
```
> В bindingResult храняться все ошибки от простых и более сложных валидаций

### View код [Thymeleaf](#viewthymeleaf)

[Источник](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/validation.html)
___
<br>

## Validator Pattern

Еще одна возможность валидировать с использованием `jakarta.validation.constraints.Pattern`.

Аннотация позволяет проверить, что страка соответствует определенному паттерну - задает его с помощью [регулярных выражений](https://rexegg.com/)

```Java
@Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Структура формы должны быть: Country, City, Postal Code (6 digits)")
    private String address;
```
___
<br>


[Вернуться назад](../../../README.md)