Паттернами проектирования (Design Patterns) называют решения часто встречающихся проблем в области разработки
программного обеспечения. Предполагается, что есть некоторый набор общих формализованных проблем,
которые довольно часто встречаются, и паттерны предоставляют ряд принципов для решения этих проблем.

## Типы паттернов:

+ [Порождающие паттерны](#singleton) предоставляют механизмы инициализации, позволяя создавать объекты удобным
  способом
    + [Singleton (Одиночка)](#singleton)
    + [Prototype (Прототип)](#prototype)
    + [Builder (Строитель)](#builder)
    + [Factory (Фабрика)](#factory)
    + [Abstract Factory (Абстрактная фабрика)](#abstractfactory)
+ [Структурные паттерны](#adapter) определяют отношения между классами и объектами, позволяя им работать совместно
    + [Adapter (Адаптер)](#adapter)
    +
+ [Поведенческие паттерны](Behavioral.md) используются для того, чтобы упростить взаимодействие между сущностями

___
<br>

<a name="singleton"></a>

## Порождающие паттерны

## Singleton (Одиночка)

Ограничивает создание одного экземпляра класса, обеспечивает доступ к его единственному объекту. Конструктор класса
**приватный**. Метод getInstance() создает только один экземпляр класса.

```java
class Singleton {
  private static Singleton instance = null;

  private Singleton() {
  }

  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }

  public void setUp() {
    System.out.println("setUp");
  }
}

public class SingletonTest { //тест
  public static void main(String[] args) {
    Singleton singelton = Singleton.getInstance();
    singelton.setUp();
  }
}
```

<br>

<a name="singleton"></a>

## Prototype (Прототип)

Позволяет создавать новые объекты путем клонирования уже существующих. Клонирует существующий объект.

```java
// Интерфейс прототипа пользователя
interface UserPrototype {
  UserPrototype clone();
}

// Класс, представляющий карточку пользователя
class UserCard implements UserPrototype {
  private String username;
  private String email;

  public UserCard(String username, String email) {
    this.username = username;
    this.email = email;
  }

  @Override
  public UserCard clone() {
    return new UserCard(this.username, this.email);
  }
  // Геттеры и сеттеры для получения и установки данных пользователя
}

public class Main {
  public static void main(String[] args) { // тест
    // Оригинальная карточка пользователя
    UserCard orUserCard = new UserCard("John", "john@example.com");

    // Клонирование карточки пользователя
    UserCard clonedUserCard = orUserCard.clone();
    clonedUserCard.setUsername("Bob");
    clonedUserCard.setEmail("bob@example.com");
  }
}
```

Так создаем карточку пользователя, а затем создаем ее клон с помощью метода `clone()`. Изменяются данные
клона, не затрагивая оригинальный объект. Таким образом, можно использовать шаблон **Прототип** для
создания копий объектов без необходимости знать их внутреннюю структуру или логику создания.

<br>

<a name="builder"></a>

## Builder (Строитель)

Используется для создания объектов с составными параметрами. Позволяет создавать объекты с различными конфигурациями
без необходимости создания множества конструкторов с разным набором параметров.

> Основная идея заключается в том, чтобы выделить процесс создания объекта из его основного класса и разделить его на
> отдельные шаги. У класса-строителя есть методы для установки различных параметров объекта, а затем метод для создания
> самого объекта. Это позволяет клиентскому коду последовательно устанавливать параметры объекта, обеспечивая тем самым
> гибкость и читаемость кода.

```java
class HouseBuilder {  // Строитель
  private int bedrooms;
  private int bathrooms;
  private boolean hasGarage;

  // Методы для установки параметров дома
  public HouseBuilder setBedrooms(int bedrooms) {
    this.bedrooms = bedrooms;
    return this;
  }

  public HouseBuilder setBathrooms(int bathrooms) {
    this.bathrooms = bathrooms;
    return this;
  }

  public HouseBuilder setHasGarage(boolean hasGarage) {
    this.hasGarage = hasGarage;
    return this;
  }

  // Метод для построения дома
  public House build() {
    return new House(bedrooms, bathrooms, hasGarage);
  }
}

class House {   // Класс, представляющий дом
  private int bedrooms;
  private int bathrooms;
  private boolean hasGarage;

  public House(int bedrooms, int bathrooms, boolean hasGarage) {
    this.bedrooms = bedrooms;
    this.bathrooms = bathrooms;
    this.hasGarage = hasGarage;
  }

  // Геттеры для получения параметров дома
}

public class Main {
  public static void main(String[] args) {
    // Создание строителя
    HouseBuilder builder = new HouseBuilder();

    // Установка параметров пошагово
    House house = builder.setBedrooms(3)
            .setBathrooms(2)
            .setHasGarage(true)
            .build();
  }
}
```

В примере используется шаблона `Builder` для построения дома с разными параметрами.

<br>

<a name="factory"></a>

## Factory (Фабрика)

Определяет интерфейс для создания объекта, но оставляет подклассам решение о том, какой класс инстанцировать. Вместо
того, чтобы создавать объекты напрямую, мы делегируем эту задачу фабрике, которая выбирает подходящий класс для создания
объекта.

```java
interface Shape { // Интерфейс фигуры
  void draw();
}

// Конкретные реализации фигур
class Circle implements Shape {
  @Override
  public void draw() {
    System.out.println("Рисуем круг");
  }
}

class Square implements Shape {
  @Override
  public void draw() {
    System.out.println("Рисуем квадрат");
  }
}

class Triangle implements Shape {
  @Override
  public void draw() {
    System.out.println("Рисуем треугольник");
  }
}

// Фабрика для создания объектов фигур
class Factory {
  // Метод для создания объекта фигуры на основе переданного типа
  public Shape createShape(String type) {
    if (type.equalsIgnoreCase("Circle")) {
      return new Circle();
    } else if (type.equalsIgnoreCase("Square")) {
      return new Square();
    } else if (type.equalsIgnoreCase("Triangle")) {
      return new Triangle();
    } else {
      throw new IllegalArgumentException("Неизвестный тип фигуры");
    }
  }
}

public class Main {
  public static void main(String[] args) {
    Factory factory = new Factory();

    // Создание объектов фигур с помощью фабрики
    Shape circle = factory.createShape("Circle");
    Shape square = factory.createShape("Square");
    circle.draw();
    square.draw();
  }
}
```

<br>

<a name="abstractfactory"></a>

## Abstract Factory (Абстрактная фабрика)

Позволяет создавать семейства взаимосвязанных или взаимозависимых объектов без указания их конкретных классов. Простой
пример абстрактной фабрики можно рассмотреть на примере создания мебели для разных стилей интерьера: современного и
классического.

```java
// Абстрактные классы мебели
interface Chair {
  void sitOn();
}

interface Table {
  void putOn();
}

// Реализации мебели для современного и классического стиля
class ModernChair implements Chair {
  @Override
  public void sitOn() {
    System.out.println("Сидим на современном стуле");
  }
}

class ModernTable implements Table {
  @Override
  public void putOn() {
    System.out.println("Кладем на современный стол");
  }
}

class ClassicChair implements Chair {
  @Override
  public void sitOn() {
    System.out.println("Сидим на классическом стуле");
  }
}

class ClassicTable implements Table {
  @Override
  public void putOn() {
    System.out.println("Кладем на классический стол");
  }
}

// Абстрактная фабрика мебели
interface FurnitureFactory {
  Chair createChair();

  Table createTable();
}

// Конкретные реализации абстрактной фабрики для современного и классического стиля
class ModernFurnitureFactory implements FurnitureFactory {
  @Override
  public Chair createChair() {
    return new ModernChair();
  }

  @Override
  public Table createTable() {
    return new ModernTable();
  }
}

class ClassicFurnitureFactory implements FurnitureFactory {
  @Override
  public Chair createChair() {
    return new ClassicChair();
  }

  @Override
  public Table createTable() {
    return new ClassicTable();
  }
}


public class Main {
  public static void main(String[] args) {
    // Создаем фабрику для современного стиля
    FurnitureFactory modernFactory = new ModernFurnitureFactory();

    // Создаем стул и стол для современного стиля
    Chair chair = modernFactory.createChair();
    Table table = modernFactory.createTable();
    сhair.sitOn();  // Используем созданную мебель
    table.putOn();
  }
}
```

____
<br>


<a name="singleton"></a>

## Структурные паттерны

## Adapter (Адаптер)

Преобразует интерфейс одного класса в интерфейс другого, который ожидают клиенты. Адаптер дает возможность классам с
несовместимыми интерфейсами работать вместе.

```java
// Интерфейс описывает способность говорить на английском языке
interface EnglishSpeaker {
  void speakEnglish();
}

class EnglishPerson implements EnglishSpeaker {
  @Override
  public void speakEnglish() {
    System.out.println("Hello! I can speak English.");
  }
}

// Интерфейс описывает способность говорить на русском языке
interface RussianSpeaker {
  void speakRussian();
}

class RussianPerson implements RussianSpeaker {
  @Override
  public void speakRussian() {
    System.out.println("Привет! Я умею говорить по-русски.");
  }
}

// Адаптер для перевода с английского на русский
class EnglishToRussianAdapter implements RussianSpeaker {
  private EnglishSpeaker englishSpeaker;

  public EnglishToRussianAdapter(EnglishSpeaker englishSpeaker) {
    this.englishSpeaker = englishSpeaker;
  }

  @Override
  public void speakRussian() {
    System.out.println("Переведено с английского: ");
    englishSpeaker.speakEnglish();
  }
}

// Клиентский код
public class Main {
  public static void main(String[] args) {
    EnglishSpeaker englishPerson = new EnglishPerson();
    RussianSpeaker russianPerson = new RussianPerson();

    // Переводим английский текст на русский с помощью адаптера
    RussianSpeaker adapter = new EnglishToRussianAdapter(englishPerson);
    adapter.speakRussian();
  }
}
```













