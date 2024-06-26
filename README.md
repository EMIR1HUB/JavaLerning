## [Паттерны Проектирования](Pattern/Pattern.md)
+ ### [Принципы SOLID](Pattern/SOLID.md)

## Классы. ООП

+ [Generics (Обобщения)](Java/Class_OOP/Generics.md)
+ + [Обобщенный интерфейс](Java/Class_OOP/Generics.md#outinterface)
+ + [Обобщенные методы](Java/Class_OOP/Generics.md#outmethods)
+ + [Использование нескольких универсальных параметров](Java/Class_OOP/Generics.md#universalpar)
+ + [Обобщенные конструкторы](Java/Class_OOP/Generics.md#outconstruction)
+ [Ограничения обобщений](Java/Class_OOP/ОграниченияGenerics.md)
+ + [Интерфейсы в качестве оганичений](Java/Class_OOP/ОграниченияGenerics.md#interfgenerics)
+ + [Множественные ограничения](Java/Class_OOP/ОграниченияGenerics.md#plural)
+ [Базовый обобщенный класс](Java/Class_OOP/НаследованиеGenerics.md)
+ [Ссылочные типы и копирование объектов](Java/Class_OOP/СсылочныеТипы&КопированиеОбъектов.md)
___

## Обработка исключений
+ [Классы исключений](Java/Exception/ОператорThrows.md)
+ [Создание своих классов исключений](Java/Exception/СозданиевоихКлассовИсключений.md)

___

## Коллекции
+ [Коллекции. Интерфейс Collection](Java/Collection/ТипыКоллекций_ИнтерфейсCollection.md)
+ [List](Java/Collection/ArrayList_ИнтерфейсList.md#list)
  + [ArrayList](Java/Collection/ArrayList_ИнтерфейсList.md#arraylist)
  + [LinkedList](Java/Collection/ArrayList_ИнтерфейсList.md#linkedlist)
  + [Vector](Java/Collection/ArrayList_ИнтерфейсList.md#vector)
  + [Stack](Java/Collection/ArrayList_ИнтерфейсList.md#stack)
+ [Queue](Java/Collection/ArrayList_ИнтерфейсList.md#queue) | [Deque](Java/Collection/ArrayList_ИнтерфейсList.md#deque)
  + [ArrayDeque](Java/Collection/ArrayList_ИнтерфейсList.md#arrdeque)
  + [PriorityQueue](Java/Collection/ArrayList_ИнтерфейсList.md#priorityqueue)
+ [Set](Java/Collection/ArrayList_ИнтерфейсList.md#set)
  + [HashSet](Java/Collection/ArrayList_ИнтерфейсList.md#hashset)
  + [SortedSet](Java/Collection/ArrayList_ИнтерфейсList.md#sortedset)
  + [NavigableSet](Java/Collection/ArrayList_ИнтерфейсList.md#navset)
  + [LinkedHashSet](Java/Collection/ArrayList_ИнтерфейсList.md#linkedhashset)
+ [Map](Java/Collection/ArrayList_ИнтерфейсList.md#map)
  + [HashMap](Java/Collection/ArrayList_ИнтерфейсList.md#hashmap)
  + [LinkedHashMap](Java/Collection/ArrayList_ИнтерфейсList.md#linkedhashmap)
  + [SortedMap](Java/Collection/ArrayList_ИнтерфейсList.md#sortedmap)
  + [NavigableMap](Java/Collection/ArrayList_ИнтерфейсList.md#navigablemap)
  + [TreeMap](Java/Collection/ArrayList_ИнтерфейсList.md#treemap)
  + [Hashtable](Java/Collection/ArrayList_ИнтерфейсList.md#hashtable)

<br>

+ [Iterator](Java/Collection/Iterator.md)
+ [Comparable и Comparator](Java/Collection/ComparableComparator.md)
___


## JDBC
+ [Паттерн DAO](Java/DataBase/Patern.md)
+ JDBC API
+ + [Создание соединения - HikariCP](Java/DataBase/JDBC_API.md#1)
+ + [Statement](Java/DataBase/JDBC_API.md#2)
+ + [PreparedStatement](Java/DataBase/JDBC_API.md#4)
+ + [Выполнение SQL-выражений](Java/DataBase/JDBC_API.md#3)
+ JdbcTemplate
+ + [Подключение БД](Java/DataBase/JDBC_Template.md#1)
+ + [Mapper](Java/DataBase/JDBC_Template.md#2)
+ + [Выполнение операций](Java/DataBase/JDBC_Template.md#3)
+ + [BatchUpdate](Java/DataBase/ConfigFile.md#4)
+ [Конфигурация БД из внешнего файла](Java/DataBase/ConfigFile.md)
+ + [Использование аннотации @Value](Java/DataBase/ConfigFile.md#2)
+ + [Абстракция среды Spring](Java/DataBase/ConfigFile.md#3)
___
<br>

## Spring Core
IoC, DI, Beans, Configuration
+ [ApplicationContext](Framework/Spring/SpringCore/SpringCore.md#appcontext)
+ [IoC / DI](Framework/Spring/SpringCore/SpringCore.md#iocdi)
+ Конфигурация Spring приложения. XML
+ + [Способы внедрения зависимости](Framework/Spring/SpringCore/Configura_XML.md#dependency)
+ + [Bean Scopes](Framework/Spring/SpringCore/Configura_XML.md#beans)
+ + [Методы бинов (init, destroy, factory)](Framework/Spring/SpringCore/Configura_XML.md#indemethod)
+ Конфигурация Spring приложения. XML + Аннотации
+ + [Аннотации](Framework/Spring/SpringCore/Config_annotation+XML.md#annotation)
+ + [Внедрение зависимостей с помощью аннотаций](Framework/Spring/SpringCore/Config_annotation+XML.md#implanat)
+ Конфигурация Spring приложения. Аннотации + Java код
+ + [Java-код](Framework/Spring/SpringCore/Config_annotation+JavaCode.md)
___

## Spring MVC
+ [DispatcherServlet](Framework/Spring/MVC/MVC.md#ds)
+ [Controller](Framework/Spring/MVC/MVC.md#controller)
+ [HTTP методы](Framework/Spring/MVC/MVC.md#http)
+ [Обработка параметров GET запроса](Framework/Spring/MVC/MVC.md#get)
+ + [PATCH, DELETE, PUT запросы](Framework/Spring/MVC/MVC.md#pdp)
+ [Model](Framework/Spring/MVC/MVC.md#model)
+ [View](Framework/Spring/MVC/MVC.md#view)
+ + [HTML форма в Thymeleaf](Framework/Spring/MVC/MVC.md#thymeleaf)
+ + [Выпадающий список](Framework/Spring/MVC/MVC.md#lable)
+ + [Валидация форм, @Valid](Framework/Spring/MVC/MVC.md#valid)
+ [Spring Validator](Framework/Spring/MVC/MVC.md#springvalid)
___

## REST 
+ [RESTful](Framework/Spring/MVC/REST.md#1)
+ [Коммуникаия между клиентом и сервером](Framework/Spring/MVC/REST.md#2)
+ [Примеры запросов](Framework/Spring/MVC/REST.md#3)
___
<br>

## Модульное тестирование

+ [Junit](Framework/Test/Junit.md)

## Hibernate

+ [Аннотации](Framework/Hibernate/Hibernate.md)

## Миграция БД
+ [Flyway]()