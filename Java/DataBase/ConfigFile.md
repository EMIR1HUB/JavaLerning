# Получение доступа к значениям из файла свойств

Файл свойств по умолчанию, создаваемый `Spring Boot`, называется `application.properties` и находится в каталоге `src/main/resources/` проекта. Свойства следуют одному и тому же синтаксису ключ=значение. При инициализации проекта файл по умолчанию пуст.

Пример файла `application.properties`:

```properties
db.driver=org.postgresql.Driver
db.url=jdbc:postgresql://localhost:5432/data_base
db.username=postgres
db.password=8888
```
> Обычно файл `.properties` не публикуют (скрытый, к примеру в .gitignore). 
> 
> Можно создать публичный файл c такимже префиксом, названием и окончанием `.origin`, но без значений (ключ=). Он будет задавать структуру файла, которая нужна для запуска приложения. 
____

<a name="2"></a>
# Использование аннотации @Value

Аннотация `@Value` -это предварительно определенная аннотация, используемая для чтения значений из любых файлов свойств в пути к классам проекта.

Чтобы получить доступ к значению одного свойства из файла свойств с помощью этой аннотации, указываем имя свойства в качестве аргумента:

```Java
@Value("${db.driver}")
private String DRIVER_DB;   //org.postgresql.Driver
```
___

<a name="3"></a>
# Абстракция среды Spring

Другим способом доступа к значениям, определенным в `Spring Boot`, является автоматическое подключение объекта `Environment` и вызов метода `getProperty()` для доступа к значению файла свойств.

Метод `getProperty()` принимает один обязательный параметр, представляющий собой строку, содержащую имя свойства, и возвращает значение этого свойства, если оно существует. Если свойство не существует, метод вернет значение **null**.

```Java
@SpringBootApplication
public class MvcCrudApplication implements EnvironmentAware {
    private String DRIVER_DB;

    @Override
    public void setEnvironment(Environment environment) {
        this.DRIVER_DB = environment.getProperty("db.driver");
        //...
    }
}
```

Другой вариант — просто внедрить `Environment` в наш контроллер/компонент.

```Java
@SpringBootApplication
public class MvcCrudApplication {

    @Autowired
    private Environment environment;

    public void setEnvironment() {
        String DRIVER_DB = environment.getProperty("db.driver");
        //...
    }
}
```
____
<br>

<a name="4"></a>
# BatchUpdate

`Batch Update` (Пакетное обновление) - К примеру, когда необходимо за раз поместить множество записей в БД. Мы их упаковывеам в **один** пакет и одним запросом отправляем к БД и получаем один ответ от БД. 

Преимущества в том, что снижает нагрузку на сеть.

## Пример

### Controller
```Java
@Controller
@RequestMapping("/test-batch-update")
public class BatchController {
    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) { this.personDAO = personDAO; }

    @GetMapping()
    public String index() { return "batch/index"; }

    @GetMapping("/without")
    public String withoutBatch() {      // без использования BatchUpdate
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String withBatch() {         // с использованием BatchUpdate
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }
}
```

### методы DAO

Создает 1000 людей и добавляет их через INSERT

```Java
// запрос без использования BatchUpdate
public void testMultipleUpdate() {
    List<Person> people = createOneThousandPeople();
    long before = System.currentTimeMillis();

    String SQL = "INSERT INTO person(name, age, email) VALUES (?, ?, ?);";
    for (Person person : people)
        jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail());

    long after = System.currentTimeMillis();
    System.out.println("Time: " + (after - before));    // примерно 24898 мил. сек.
}

// запрос с использованием BatchUpdate
public void testBatchUpdate() {
    List<Person> people = createOneThousandPeople();
    long before = System.currentTimeMillis();

    jdbcTemplate.batchUpdate("INSERT INTO person(name, age, email) VALUES (?, ?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setString(1, people.get(i).getName());
                    preparedStatement.setInt(2, people.get(i).getAge());
                    preparedStatement.setString(3, people.get(i).getEmail());
                }

                @Override
                public int getBatchSize() {
                    return people.size();
                }
            });
    long after = System.currentTimeMillis();
    System.out.println("Time: " + (after - before));    // примерно 48 мил. сек.
}

// создание 1000 людей
private List<Person> createOneThousandPeople() {
    List<Person> people = new ArrayList<>();
    for (int i = 0; i < 1000; i++)
        people.add(new Person("Name " + i, 25, "test " + i + "@mail.ru"));
    return people;
}
```
___
<br>

[Вернуться назад](../../README.md)