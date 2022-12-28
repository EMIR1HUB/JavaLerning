# JDBC API

<br>

## Содержание
+ [Создание соединения - HikariCP](#1)
+ [Statement](#2)
+ [PreparedStatement](#4)
+ [Выполнение SQL-выражений](#3)
___
<br>

<a name="1"></a>
## Создание соединения на примере HikariCP

```Java
HikariConfig config = new HikariConfig(); // Создание и заполнение конфига соединения
config.setJdbcUrl(DB_URL);
config.setUsername(DB_USERNAME);
config.setPassword(DB_PASSWORD);

HikariDataSource hikariDataSource = new HikariDataSource(config); // Создание пула соединений
if (hikariDataSource.getConnection() == null) {
    throw new SQLException("Database connection failed");
}
return hikariDataSource.getConnection(); // Возврат соединения типа Connection
```
____
<br>

<a name="2"></a>
## Statement - создание SQL-выражений

Для выполнения команд используется объект класса `Statement`.

+ `Statement` - базовый, предназначен для выполнения простых запросов и извлечения результатов.
+ `PreparedStatement`- позволяет добавлять в запросы входные параметры; добавлять методы управления входными папаметрами.
+ `CallableStatement` - используется для вызовов хранимых процедур; добавляет методы для манипуляции выходными параметрами.

### Пример

```Java
try {
    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    Statement statement = connection.createStatement();
    // ...
} catch (SQLException e) {
    throw new RuntimeException(e);
}
```
____
<br>

<a name="3"></a>
## Выполнение SQL-выражений

+ `executeQuery` - используется в запросах, результатом которых является один единственный набор значений, таких как запросов типа **SELECT**.

+ `executeUpdate` - следует использовать для **INSERT**, **UPDATE**, **DELETE** - результат - количество измененных строк таблицы. **CREATE TABLE**, **DROP TABLE** - возвращаемое значениие 0.

+ `execute` - редко используется, в случаях, когда операторы SQL возвращают более одного набора данных, более одного счетчика обновлений или и то, и другое.

## Пример

```Java
// ...
ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

if (resultSet.next()) {
    Person person = new Person();

    person.setId(resultSet.getInt("id"));
    person.setName(resultSet.getString("name"));
    person.setAge(resultSet.getInt("age"));
    person.setEmail(resultSet.getString("email"));
}
// ...
```
____
<br>

<a name="4"></a>
## Использование PreparedStatement

**PreparedStatement** предварительно компилирует запросы, которые могут содержать входные параметры обозначенные символом `?`, вместо которых определяются значения.
> Нумерация ? начинается с 1.

## Пример

```Java
// Запись в таблицу БД 
PreparedStatement preparedStatement = connection.prepareStatement(
    "INSERT INTO person(name, age, email) VALUES (?, ?, ?);");

    // Определяем значения параметров
    preparedStatement.setString(1, person.getName());
    preparedStatement.setInt(2, person.getAge());
    preparedStatement.setString(3, person.getEmail());

    // Выполнение запроса
    preparedStatement.executeUpdate();
```
___
[Вернуться назад](../../README.md)























