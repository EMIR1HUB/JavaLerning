# JdbcTemplate

## Содержание

+ [Подключение БД](#1)
+ [Mapper](#2)
+ [Выполнение операций](#3)
___
<br>

`JdbcTemplate`- Обертка вокруг JBDC API. 

Решает такие проблемы, как количество лишнего кода, дублирование, неинформативный SQLException, который надо везде обрабатывать

Подключение зависимость
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>6.0.3</version>
</dependency>
```
<a name="1"></a>
## Подключение БД

Создаем бин JdbcTemplate

```Java
@Bean
public DataSource dataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://localhost:5432/db_for_spring");
    dataSource.setUsername("postgres");
    dataSource.setPassword("3696");
    return dataSource;
}

@Bean
public JdbcTemplate jdbcTemplate(){
    return new JdbcTemplate(dataSource());
}
```

### в классе DAO
```Java
private final JdbcTemplate jdbcTemplate;

@Autowired
public PersonDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}
```

<br>

<a name="2"></a>
## Mapper

Создаем Маппер для избежания дублирования, реализуется от интерфейса `RowMapper<>`

### Пример

```Java
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }
}
```
<a name="3"></a>
## Выполнение операций

```Java
public List<Person> index() {
    return jdbcTemplate.query("SELECT * FROM person;", new PersonMapper());
}

public Person show(int id) {
    String SQL = "SELECT * FROM person WHERE id = ?";
    return jdbcTemplate.query(SQL, new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
}

public void save(Person person) {
    String SQL = "INSERT INTO person(name, age, email) VALUES (?, ?, ?);";
    jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail());
}
```
> `jdbcTemplate` использует `PreparedStatement` поэтому можем указывать значение после запятой для каждого `?`

В Spring JDBC есть уже готовый Mapper, тем самым можем не использовать собственный, а использовать готовый `new BeanPropertyRowMapper<>(Person.class)`
___

[Вернуться назад](../../README.md)