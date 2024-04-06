# Миграция Flyway с помощью Spring Boot

`Flyway` – это инструмент управления версиями базы данных. Точно так же, как `Git` (система контроля версий для кодовой
базы), Flyway заботится об управлении схемой базы данных.

Представим, что запустили проект `Spring Boot`, который взаимодействует с базой данных `PostgreSQL`. Мы работали
самостоятельно, внедряли новые функции, добавляли новые таблицы и много раз меняли схему. В какой-то момент к нашей
команде присоединяется новый разработчик, поэтому мы просто экспортируем нашу локальную или удаленную базу данных и
отправляем ему скрипт, чтобы он смог настроить локальную среду.

Пока все хорошо, но что будет, если к команде присоединится больше людей или хотим создать больше тестовых сред?
Можно сохранять скрипт и обновлять его каждый раз, когда что-то меняем, но это может создать массу проблем:

+ несколько человек редактируют схему асинхронно, как убедиться, что все их сценарии были применены в определенной
  среде;
+ как можем быть уверены, что у всех разработчиков точно такая же версия на их локальном компьютере;
+ как определить правильный порядок скриптов, чтобы при их применении ничего не ломалось.

Представьте себе работу без Git при сотрудничестве с другими и обмене написанным кодом вручную. Технически выполнимо, но
не рекомендуется.

`Flyway` – не единственный инструмент, который можно использовать со Spring Boot, однако с ним легко работать.

## Настройка Flyway с помощью Spring Boot

## Импорт

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<scope>runtime</scope>
</dependency>

<dependency>
<groupId>org.flywaydb</groupId>
<artifactId>flyway-core</artifactId>
</dependency>
```

## Изменение свойств приложения

Необходимо убедиться, что правильно настроено соединение с БД. Самый простой способ
сделать это – через файл **application.properties** или **application.yaml**:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vehiclehub
spring.datasource.username=demo
spring.datasource.password=demo
spring.datasource.driver-class-name=org.postgresql.Driver
# update - Hibernate будет динамически генерировать структуру БД в процессе работы в соответствии с созданными сущностями. Используется во время разработки
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
# Configuration Flyway
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

## Добавление каталога миграции

Далее необходимо добавить в `/resources` каталог `/db/migration`. Если этого не сделаем и попытаемся запустить
приложение,
произойдет сбой со следующей ошибкой:

```
Flyway failed to initialize: none of the following migration scripts locations could be found:
classpath:db/migration
```

## Проверка

Проверим, правильно ли работает настройка (убедиться, что есть база данных с пустой схемой, запущенная и
работающая) `flyway_schema_history`.

Flyway использует эту таблицу для управления версиями и управления примененными, измененными или добавленными скриптами.
Подробнее можно узнать в [официальной документации](https://documentation.red-gate.com/fd/quickstart-how-flyway-works-184127223.html).

# Добавление миграции

Добавим первый файл миграции с именем _V1__Create_User_Table.sql_. Данное имя файла является соглашением об
именовании Flyway по умолчанию, которое состоит из:

+ префикса – **B** для базового уровня, **R** для повторения, **U** для отмены и **V** для версии миграции;
+ номера миграции – номер 1 в нашем случае; если хотим, чтобы был 1.1, то должны начать имя с V1_1;
  двойное подчеркивание – отделяет версию от описания миграции;
+ описание – символы подчеркивания переводятся в пробелы, поэтому миграция будет называться Create User Table.

С учетом сказанного добавим пример SQL-скрипта:

```postgresql
CREATE TABLE IF NOT EXISTS app_user
(
    id   SERIAL NOT NULL PRIMARY KEY,
    name TEXT   NOT NULL
);
```

После этого еще раз запустим приложение Spring Boot и проверим логи:

```
Migrating schema "public" to version "1 - Create User Table"
```

Как видим, миграция была применена успешно, и в базу данных была добавлена новая таблица.
___

# Настройка Flyway с помощью application.properties

## Включение/отключение Flyway

По умолчанию Flyway включен при добавлении в Spring Boot. Если хотим изменить это, можем установить флаг enable:

```properties
spring.flyway.enabled=false
```

## Установка пользовательского каталога миграции

Каталог по умолчанию – `/db/migration`, но его можно настроить с помощью свойства `spring.flyway.locations`:

```properties
spring.flyway.locations="classpath:my-custom-dir,classpath:my-custom-dir-2"
```

Как видим, можно предоставить несколько каталогов. При работе с` Spring Boot` **classpath** означает каталог **
resources**. В
качестве альтернативы можно обратиться к любому каталогу в системе, используя файловую систему.

Стоит отметить, что когда указываем несколько каталогов, то должен существовать хотя бы один из них (но не все). Кроме
того, можно дублировать версии миграций в каталогах.

```
/resources
    /my-custom-dir
        V2__one.sql
    /my-custom-dir-2
        V1__one.sql
```

Эти два каталога обрабатываются точно так же, как и один, и если дважды укажем версию V1, то будет выброшено исключение
_FlywayException_:

```
Found more than one migration with version 1
```

## Справочник вендоров

Кроме того, `Flyway` позволяет не только указывать жестко запрограммированные пути, но и использовать заполнитель `vendor`:

```properties
spring.flyway.locations="classpath:my-custom-dir/{vendor}"
```

С приведенной выше конфигурацией Flyway будет искать файлы миграции в каталоге `/resources/my-custom-dir/postgresql`.
Можно найти полный список поддерживаемых вендоров [здесь](https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/jdbc/DatabaseDriver.java).



