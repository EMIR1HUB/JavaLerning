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
    + [Bridge (Мост)](#bridge)
    + [Composite (Компоновщик)](#composite)
    + [Decorator (Декоратор)](#decorator)
    + [Facade (Фасад)](#facade)
    + [Flyweight (Легковес)](#flyweight)
    + [Proxy (Заместитель)](#proxy)
+ [Поведенческие паттерны](#chain) используются для того, чтобы упростить взаимодействие между сущностями
    + [Chain of responsibility (Цепочка обязанностей)](#chain)
    + [Command (Команда)](#command)
    + [Interpreter (Интерпретатор)](#interpreter)
    + [Iterator (Итератор)](#iterator)
    + [Mediator (Посредник)](#mediator)
    + [Memento (Хранитель)](#memento)
    + [Observer (Наблюдатель)](#observer)
    + [State (Состояние)](#state)
    + [Strategy (Стратегия)](#strategy)
    + [Template Method (Шаблонный метод)](#template)
    + [Visitor (Посетитель)](#visitor)

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


<a name="adapter"></a>

## Структурные паттерны

## Adapter (Адаптер)

Преобразует интерфейс одного класса в интерфейс другого, который ожидают клиенты. Адаптер дает возможность классам с
несовместимыми интерфейсами работать вместе.

```java
// Интерфейс зарядного устройства с разъемом USB
interface USBCharger {
  void chargeWithUSB();
}

class USBChargerImpl implements USBCharger {
  @Override
  public void chargeWithUSB() {
    System.out.println("Заряжаем устройство через USB");
  }
}

// Интерфейс зарядного устройства с разъемом Lightning
interface LightningCharger {
  void chargeWithLightning();
}

class LightningChargerImpl implements LightningCharger {
  @Override
  public void chargeWithLightning() {
    System.out.println("Заряжаем устройство через разъем Lightning");
  }
}

// Адаптер, который преобразует интерфейс USBCharger в LightningCharger
class USBToLightningAdapter implements LightningCharger {
  private USBCharger usbCharger;

  public USBToLightningAdapter(USBCharger usbCharger) {
    this.usbCharger = usbCharger;
  }

  @Override
  public void chargeWithLightning() { // Используем зарядное устройство с разъемом USB через адаптер
    usbCharger.chargeWithUSB();
  }
}

// Клиентский код
public class Main {
  public static void main(String[] args) {
    // Создаем зарядное устройство с разъемом USB
    USBCharger usbCharger = new USBChargerImpl();

    // Создаем адаптер, преобразующий USB в Lightning
    LightningCharger lightningAdapter = new USBToLightningAdapter(usbCharger);

    // Заряжаем устройство через разъем Lightning
    lightningAdapter.chargeWithLightning();
  }
}
```

В примере адаптер `USBToLightningAdapter` принимает объект типа `USBCharger` в качестве параметра конструктора и
реализует интерфейс `LightningCharger`. При вызове метода `chargeWithLightning()` адаптер использует
метод `chargeWithUSB()`
объекта `USBCharger` для зарядки устройства через разъем `Lightning`. Таким образом, мы можем использовать зарядное
устройство с разъемом **USB** для зарядки устройства через разъем **Lightning** с помощью адаптера.

<br>

<a name="bridge"></a>

## Bridge (Мост)

Используется для отделения абстракции от ее реализации, позволяя им изменяться независимо друг от друга.

```java
// Интерфейс для управления устройством
interface Device {
  void turnOn();

  void turnOff();
}

// Реализация устройства для конкретной операционной системы Windows
class WindowsDevice implements Device {
  @Override
  public void turnOn() {
    System.out.println("Устройство включено на Windows");
  }

  @Override
  public void turnOff() {
    System.out.println("Устройство выключено на Windows");
  }
}

// Реализация устройства для конкретной операционной системы MacOS
class MacOSDevice implements Device {
  @Override
  public void turnOn() {
    System.out.println("Устройство включено на MacOS");
  }

  @Override
  public void turnOff() {
    System.out.println("Устройство выключено на MacOS");
  }
}

// Абстракция для управления устройством
abstract class RemoteControl {
  protected Device device;

  public RemoteControl(Device device) {
    this.device = device;
  }

  public abstract void turnOn();

  public abstract void turnOff();
}

// Реализация пульта управления для конкретной операционной системы Windows
class WindowsRemoteControl extends RemoteControl {
  public WindowsRemoteControl(Device device) {
    super(device);
  }

  @Override
  public void turnOn() {
    System.out.println("Пульт включен на Windows");
    device.turnOn();
  }

  @Override
  public void turnOff() {
    System.out.println("Пульт выключен на Windows");
    device.turnOff();
  }
}

// Реализация пульта управления для конкретной операционной системы MacOS
class MacOSRemoteControl extends RemoteControl {
  public MacOSRemoteControl(Device device) {
    super(device);
  }

  @Override
  public void turnOn() {
    System.out.println("Пульт включен на MacOS");
    device.turnOn();
  }

  @Override
  public void turnOff() {
    System.out.println("Пульт выключен на MacOS");
    device.turnOff();
  }
}

// Клиентский код
public class Main {
  public static void main(String[] args) {
    // Создаем устройства
    Device windowsDevice = new WindowsDevice();
    Device macOsDevice = new MacOSDevice();

    // Создаем пульты управления для разных операционных систем
    RemoteControl windowsRemoteControl = new WindowsRemoteControl(windowsDevice);
    RemoteControl macOsRemoteControl = new MacOSRemoteControl(macOsDevice);

    // Включаем и выключаем устройства с помощью различных пультов
    windowsRemoteControl.turnOn();
    windowsRemoteControl.turnOff();

    macOsRemoteControl.turnOn();
    macOsRemoteControl.turnOff();
  }
}
```

В этом примере абстракция представлена классом `RemoteControl`, который может быть реализован для разных операционных
систем. Реализации класса `WindowsRemoteControl` и `MacOSRemoteControl` включают в себя объекты устройств
`WindowsDevice` и `MacOSDevice` и делегируют им управление. Таким образом, абстракция и реализация разделены, что
позволяет
изменять их независимо друг от друга.

<br>

<a name="composite"></a>

## Composite (Компоновщик)

Группирует несколько объектов в древовидную структуру используя один класс. Позволяет работать с несколькими классами
через один объект.

```java
// Интерфейс для элемента структуры (компонента)
interface FileSystemComponent {
  void showDetails();
}

// Реализация компонента для файла
class File implements FileSystemComponent {
  private String name;

  public File(String name) {
    this.name = name;
  }

  @Override
  public void showDetails() {
    System.out.println("File: " + name);
  }
}

// Реализация компонента для папки
class Folder implements FileSystemComponent {
  private String name;
  private List<FileSystemComponent> children;

  public Folder(String name) {
    this.name = name;
    this.children = new ArrayList<>();
  }

  public void addComponent(FileSystemComponent component) {
    children.add(component);
  }

  @Override
  public void showDetails() {
    System.out.println("Folder: " + name);
    for (FileSystemComponent component : children) {
      component.showDetails();
    }
  }
}

// Клиентский код
public class Main {
  public static void main(String[] args) {
    // Создаем файлы
    File file1 = new File("file1.txt");
    File file2 = new File("file2.txt");
    File file3 = new File("file3.txt");

    // Создаем папки и добавляем в них файлы
    Folder rootFolder = new Folder("Root");
    Folder folder1 = new Folder("Folder1");
    Folder folder2 = new Folder("Folder2");

    folder1.addComponent(file1);
    folder1.addComponent(file2);
    folder2.addComponent(file3);

    rootFolder.addComponent(folder1);
    rootFolder.addComponent(folder2);

    // Показываем детали всех элементов структуры
    rootFolder.showDetails();
  }
}
```

В этом примере `File` и `Folder` реализуют общий интерфейс `FileSystemComponent`. Папка может содержать в себе как
файлы, так
и другие папки. Метод `showDetails()` выводит информацию о текущем элементе (название файла или папки) и рекурсивно
вызывает `showDetails()` для всех дочерних элементов, что позволяет обрабатывать структуру компонентов независимо от их
типа.

<br>

<a name="decorator"></a>

## Decorator (Декоратор)

Добавляет новое поведение или функциональность существующего объекта динамически без привязки его структуры.

Когда необходимо добавить новую функциональность, создаем новую имплементацию, которая
инкапсулирует в себе логику и проксирует вызовы далее по необходимости.

Простым примером декоратора может служить кофейный автомат. Имеется базовый класс кофе и различные добавки к нему,
такие как сахар, молоко и взбитые сливки. Каждая добавка представляет собой декоратор, который добавляет дополнительные
функции к базовому классу кофе.

```java
// Интерфейс для кофе
interface Coffee {
  String getDescription();

  double getCost();
}

// Базовый класс кофе
class SimpleCoffee implements Coffee {
  @Override
  public String getDescription() {
    return "Simple coffee";
  }

  @Override
  public double getCost() {
    return 1.0;
  }
}

// Декоратор добавки сахара
class SugarDecorator implements Coffee {
  private Coffee coffee;

  public SugarDecorator(Coffee coffee) {
    this.coffee = coffee;
  }

  @Override
  public String getDescription() {
    return coffee.getDescription() + ", with sugar";
  }

  @Override
  public double getCost() {
    return coffee.getCost() + 0.5;
  }
}

// Декоратор добавки молока
class MilkDecorator implements Coffee {
  private Coffee coffee;

  public MilkDecorator(Coffee coffee) {
    this.coffee = coffee;
  }

  @Override
  public String getDescription() {
    return coffee.getDescription() + ", with milk";
  }

  @Override
  public double getCost() {
    return coffee.getCost() + 0.7;
  }
}

// Клиентский код
public class Main {
  public static void main(String[] args) {
    // Создаем простой кофе
    Coffee coffee = new SimpleCoffee();
    System.out.println(coffee.getDescription() + ": $" + coffee.getCost());

    // Добавляем сахар к кофе
    coffee = new SugarDecorator(coffee);
    System.out.println(coffee.getDescription() + ": $" + coffee.getCost());

    // Добавляем молоко к кофе
    coffee = new MilkDecorator(coffee);
    System.out.println(coffee.getDescription() + ": $" + coffee.getCost());
  }
}
```

<br>

<a name="facade"></a>

## Facade (Фасад)

Предоставляет унифицированный интерфейс для взаимодействия с комплексной подсистемой, делая её более простой в
использовании.

Скрывает сложную систему классов приводя все вызовы к одному объекту. Помещает вызов нескольких сложных объектов в один
объект.

```java
interface Car {
  void start();

  void stop();
}

class Key implements Car {
  public void start() {
    System.out.println("Вставить ключи");
  }

  public void stop() {
    System.out.println("Вытянуть ключи");
  }
}

class Engine implements Car {
  public void start() {
    System.out.println("Запустить двигатель");
  }

  public void stop() {
    System.out.println("Остановить двигатель");
  }
}

// Фасад для взаимодействия с ключом и двигателем
class Facade {
  private Key key;
  private Engine engine;

  public Facade() {
    key = new Key();
    engine = new Engine();
  }

  public void startCar() {
    key.start();
    engine.start();
  }

  public void stoptCar() {
    key.stop();
    engine.stop();
  }
}

public class FacadeTest {//тест

  public static void main(String[] args) {
    Facade facade = new Facade();
    facade.startCar();
    facade.stoptCar();
  }
}
```

В примере `Engine` и `Key` представляет комплексную подсистему, а `Facade` - фасад, который предоставляет простой
интерфейс
для взаимодействия с ключами и двигателем. Клиентский код использует фасад `Facade` для запуска и остановки автомобиля,
скрывая детали внутренней реализации.

<br>

<a name="flyweight"></a>

## Flyweight (Легковес)

Используется для эффективной поддержки множества мелких объектов, разделяя их состояние между собой и позволяя им
совместно использовать общие данные.
Вместо создания большого количества похожих объектов, объекты используются повторно. Экономит память.

```java
// Интерфейс легковеса для представления цвета
interface Color {
  void fill();
}

// Реализация для представления конкретного цвета
class ConcreteColor implements Color {
  private String name;

  public ConcreteColor(String name) {
    this.name = name;
  }

  @Override
  public void fill() {
    System.out.println("Filling with color: " + name);
  }
}

// Фабрика легковесов цветов
class ColorFactory {
  private static final Map<String, Color> colorMap = new HashMap<>();

  public static Color getColor(String name) {
    Color color = colorMap.get(name);
    if (color == null) {
      color = new ConcreteColor(name);
      colorMap.put(name, color);
    }
    return color;
  }
}

// Клиентский код
public class Main {
  public static void main(String[] args) {
    // Получаем легковесы для различных цветов
    Color red = ColorFactory.getColor("red");
    Color blue = ColorFactory.getColor("blue");
    Color green = ColorFactory.getColor("green");

    // Используем легковесы для заполнения цветом
    red.fill();
    blue.fill();
    green.fill();
  }
}
```

<br>

<a name="proxy"></a>

## Proxy (Заместитель)

Представляет объекты, которые могут контролировать другие объекты перехватывая их вызовы. Используется для контроля
доступа к объекту или его функциональности.

```java
// Общий интерфейс для изображения
interface Image {
  void display();
}

// Реальный объект, представляющий загрузку изображения из интернета
class RealImage implements Image {
  private String filename;

  public RealImage(String filename) {
    this.filename = filename;
    loadFromDisk();
  }

  private void loadFromDisk() {    // Здесь происходит загрузка изображения
    System.out.println("Loading image " + filename + " from disk");
  }

  @Override
  public void display() { // Здесь происходит отображение изображения
    System.out.println("Displaying image " + filename);
  }
}

// Заместитель, контролирующий доступ к реальному объекту
class ProxyImage implements Image {
  private RealImage realImage;
  private String filename;

  public ProxyImage(String filename) {
    this.filename = filename;
  }

  @Override
  public void display() {
    if (realImage == null) {
      realImage = new RealImage(filename);
    }
    realImage.display();
  }
}

// Клиентский код
public class Main {
  public static void main(String[] args) {
    // Создаем объекты заместителя и реального объекта
    Image image1 = new ProxyImage("image1.jpg");
    Image image2 = new ProxyImage("image2.jpg");

    // Загружаем и отображаем изображения (при первом вызове будет произведена загрузка)
    image1.display();
    image2.display();
  }
}
```

В примере `RealImage` представляет реальное изображение, которое загружается из интернета при создании объекта.
`ProxyImage` - это заместитель, который контролирует доступ к реальному изображению. Если изображение еще не загружено,
то
заместитель создает объект `RealImage` и делегирует ему вызов метода `display()`. Таким образом, клиентский код может
работать с объектом `ProxyImage`, не зная, что реальное изображение загружается только по мере необходимости.
___

<br>

<a name="chain"></a>

## Поведенческие паттерны

## Chain of responsibility (Цепочка обязанностей)

Позволяет избежать привязки отправителя запроса к его получателю, давая шанс обработать запрос нескольким объектам.
Позволяет передавать запросы по цепочке от одного обработчика к другому. Каждый обработчик решает, может ли он
обработать запрос, а также стоит ли передавать запрос дальше по цепочке.

```java
// Интерфейс для обработчика запроса
interface AuthenticationHandler {
  void setNextHandler(AuthenticationHandler nextHandler);

  boolean authenticate(String username, String password);
}

// Конкретный обработчик для проверки логина
class UsernameAuthenticator implements AuthenticationHandler {
  private AuthenticationHandler nextHandler;

  @Override
  public void setNextHandler(AuthenticationHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean authenticate(String username, String password) {
    if (username != null && !username.isEmpty()) {
      System.out.println("Username is valid");
      if (nextHandler != null) {
        return nextHandler.authenticate(username, password);
      }
      return true;
    } else {
      System.out.println("Username is invalid");
      return false;
    }
  }
}

// Конкретный обработчик для проверки пароля
class PasswordAuthenticator implements AuthenticationHandler {
  private AuthenticationHandler nextHandler;

  @Override
  public void setNextHandler(AuthenticationHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean authenticate(String username, String password) {
    if (password != null && password.length() >= 6) {
      System.out.println("Password is valid");
      if (nextHandler != null) {
        return nextHandler.authenticate(username, password);
      }
      return true;
    } else {
      System.out.println("Password is invalid");
      return false;
    }
  }
}

public class Main { // Клиентский код
  public static void main(String[] args) { // Создаем обработчики
    AuthenticationHandler usernameAuthenticator = new UsernameAuthenticator();
    AuthenticationHandler passwordAuthenticator = new PasswordAuthenticator();

    // Устанавливаем цепочку обработчиков
    usernameAuthenticator.setNextHandler(passwordAuthenticator);

    // Пытаемся авторизоваться
    boolean isAuthenticated = usernameAuthenticator.authenticate("user123", "password123");
    System.out.println("Is authenticated: " + isAuthenticated);
  }
}
```

<br>

<a name="command"></a>

## Command (Команда)

Позволяет инкапсулировать различные операции в отдельные объекты. Позволяя таким образом параметризовать клиентов с
обработчиками, организовать очередь запросов или записывать их в логи, а также поддерживать отмену операций.

Простой пример - реализация редактора текста с помощью команд. Имеется редактор текста, который
поддерживает операции вроде вставки, удаления текста и отмены последнего действия.

```java
// Интерфейс команды
interface Command {
  void execute();
}

// Конкретная команда для вставки текста
class InsertCommand implements Command {
  private final TextEditor textEditor;
  private final String text;

  public InsertCommand(TextEditor textEditor, String text) {
    this.textEditor = textEditor;
    this.text = text;
  }

  @Override
  public void execute() {
    textEditor.insert(text);
  }
}

// Конкретная команда для удаления текста
class DeleteCommand implements Command {
  private final TextEditor textEditor;
  private final int position;

  public DeleteCommand(TextEditor textEditor, int position) {
    this.textEditor = textEditor;
    this.position = position;
  }

  @Override
  public void execute() {
    textEditor.delete(position);
  }
}

// Класс редактора текста
class TextEditor {
  private StringBuilder text = new StringBuilder();

  public void insert(String textToInsert) {
    text.append(textToInsert);
  }

  public void delete(int position) {
    if (position >= 0 && position < text.length()) {
      text.deleteCharAt(position);
    }
  }

  public void print() {
    System.out.println(text);
  }
}

public class Main { // Класс, представляющий клиентский код
  public static void main(String[] args) {
    TextEditor textEditor = new TextEditor();

    // команды для редактора текста
    Command insertCommand = new InsertCommand(textEditor, "Hello, ");
    Command deleteCommand = new DeleteCommand(textEditor, 5);
    insertCommand.execute();
    deleteCommand.execute();

    textEditor.print(); // Результат: "Hello"
  }
}
```

В примере `Command` представляет интерфейс для всех команд, а `InsertCommand` и `DeleteCommand` - конкретные реализации
команд для вставки и удаления текста соответственно. `TextEditor` - это объект, с которым взаимодействуют команды.

<br>

<a name="interpreter"></a>

## Interpreter (Интерпретатор)

Используется для определения представления грамматики определенного языка и интерпретации предложений на этом языке.

Простым примером может быть интерпретатор простого языка запросов к базе данных. Допустим, есть язык запросов,
который позволяет делать запросы к базе данных для извлечения информации.

```java
// Интерфейс выражения
interface Expression {
  boolean interpret(String context);
}

// Конкретное выражение для проверки наличия слова в строке
class WordExpression implements Expression {
  private final String word;

  public WordExpression(String word) {
    this.word = word;
  }

  @Override
  public boolean interpret(String context) {
    return context.contains(word);
  }
}

// выражение для объединения двух выражений через логическое "И"
class AndExpression implements Expression {
  private final Expression expr1;
  private final Expression expr2;

  public AndExpression(Expression expr1, Expression expr2) {
    this.expr1 = expr1;
    this.expr2 = expr2;
  }

  @Override
  public boolean interpret(String context) {
    return expr1.interpret(context) && expr2.interpret(context);
  }
}

// выражение для объединения двух выражений через логическое "ИЛИ"
class OrExpression implements Expression {
  private final Expression expr1;
  private final Expression expr2;

  public OrExpression(Expression expr1, Expression expr2) {
    this.expr1 = expr1;
    this.expr2 = expr2;
  }

  @Override
  public boolean interpret(String context) {
    return expr1.interpret(context) || expr2.interpret(context);
  }
}

public class Main { // Класс, представляющий клиентский код
  public static void main(String[] args) {
    // Создаем выражения
    Expression personExpr = new WordExpression("person");
    Expression ageExpr = new WordExpression("age");

    // Создаем составные выражения
    Expression query1 = new AndExpression(personExpr, ageExpr);
    Expression query2 = new OrExpression(personExpr, ageExpr);

    // Проверяем, соответствует ли строка запросу
    String context = "The person's age is 30";
    System.out.println("Query 1 result: " + query1.interpret(context));
    System.out.println("Query 2 result: " + query2.interpret(context));
  }
}
```

<br>

<a name="iterator"></a>

## Iterator (Итератор)

Последовательно осуществляет доступ к элементам объекта коллекции, не зная его основного представления.

```java
interface Iterator {
  boolean hasNext();

  Object next();
}

class Numbers {
  public int num[] = {1, 2, 3};

  public Iterator getIterator() {
    return new NumbersIterator();
  }

  private class NumbersIterator implements Iterator {
    int ind;

    public boolean hasNext() {
      if (ind < num.length) return true;
      return false;
    }

    public Object next() {
      if (this.hasNext()) return num[ind++];
      return null;
    }
  }
}

public class IteratorTest { //тест
  public static void main(String[] args) {
    Numbers numbers = new Numbers();
    Iterator iterator = numbers.getIterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
```

<br>

<a name="mediator"></a>

## Mediator (Посредник)

Используется для уменьшения связанности между объектами путем введения посредника,
который обрабатывает взаимодействие между объектами.

Простым примером может быть чат-приложение, где участники чата обмениваются сообщениями через посредника, который
управляет передачей сообщений и уведомлением участников о новых сообщениях.

```java
// Интерфейс посредника
interface ChatMediator {
  void sendMessage(User user, String message);

  void addUser(User user);
}

// Конкретный класс посредника
class ChatMediatorImpl implements ChatMediator {
  private final List<User> users;

  public ChatMediatorImpl() {
    this.users = new ArrayList<>();
  }

  @Override
  public void addUser(User user) {
    this.users.add(user);
  }

  @Override
  public void sendMessage(User user, String message) {
    for (User u : users) {
      // Отправка сообщения всем пользователям, кроме отправителя
      if (u != user) {
        u.receiveMessage(message);
      }
    }
  }
}

// Класс участника чата
class User {
  private final String name;
  private final ChatMediator mediator;

  public User(String name, ChatMediator mediator) {
    this.name = name;
    this.mediator = mediator;
  }

  public String getName() {
    return name;
  }

  public void sendMessage(String message) {
    System.out.println(name + " sends: " + message);
    mediator.sendMessage(this, message);
  }

  public void receiveMessage(String message) {
    System.out.println(name + " receives: " + message);
  }
}

public class Main {
  public static void main(String[] args) {
    // Создаем посредника
    ChatMediator mediator = new ChatMediatorImpl();

    // Создаем пользователей и регистрируем их в посреднике
    User user1 = new User("User 1", mediator);
    User user2 = new User("User 2", mediator);
    User user3 = new User("User 3", mediator);
    mediator.addUser(user1);
    mediator.addUser(user2);
    mediator.addUser(user3);

    // Пользователи обмениваются сообщениями через посредника
    user1.sendMessage("Hello, everyone!");
    user2.sendMessage("Hi, User 1!");
  }
}
```

<br>

<a name="memento"></a>

## Memento (Хранитель)

Позволяет сохранить текущее состояние объекта так, чтобы в дальнейшем можно было восстановить его без нарушения
инкапсуляции.

Простым примером может быть текстовый редактор, который позволяет пользователю сохранять историю изменений и
откатываться к предыдущим версиям текста.

```java
// Класс, представляющий состояние текста
class TextState {
  private final String text;

  public TextState(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}

// Класс, представляющий текстовый редактор
class TextEditor {
  private String text;
  private final List<TextState> history = new ArrayList<>();

  public void setText(String text) {
    this.text = text;
    save();
  }

  public void undo() {
    if (!history.isEmpty()) {
      TextState prevState = history.remove(history.size() - 1);
      text = prevState.getText();
    }
  }

  public void printText() {
    System.out.println("Current text: " + text);
  }

  private void save() {
    history.add(new TextState(text));
  }
}

public class Main { //тест
  public static void main(String[] args) {
    TextEditor editor = new TextEditor();

    editor.setText("Hello, world!");
    editor.printText();

    editor.setText("Goodbye, world!");
    editor.printText();

    editor.undo();
    editor.printText();
  }
}
```

<br>

<a name="observer"></a>

## Observer (Наблюдатель)

Позволяет оповещать зависимые объекты об изменениях в состоянии другого объекта.

Простым примером может быть рассылка уведомлений пользователям. Когда администратор отправляет сообщение, все подписчики
получают уведомление.

```java
// Интерфейс для наблюдателя
interface Observer {
  void update(String message);
}

// Конкретный наблюдатель (подписчик)
class User implements Observer {
  private final String name;

  public User(String name) {
    this.name = name;
  }

  @Override
  public void update(String message) {
    System.out.println(name + " received message: " + message);
  }
}

// Интерфейс для наблюдаемого объекта (издателя)
interface Observable {
  void addObserver(Observer observer);

  void removeObserver(Observer observer);

  void notifyObservers(String message);
}

// Конкретный наблюдаемый объект (издатель)
class NotificationSystem implements Observable {
  private final List<Observer> observers = new ArrayList<>();

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String message) {
    for (Observer observer : observers) {
      observer.update(message);
    }
  }

  public void sendNotification(String message) {
    System.out.println("Sending notification: " + message);
    notifyObservers(message);
  }
}

public class Main {
  public static void main(String[] args) {
    NotificationSystem notificationSystem = new NotificationSystem();

    User user1 = new User("User 1");
    User user2 = new User("User 2");
    notificationSystem.addObserver(user1);
    notificationSystem.addObserver(user2);

    notificationSystem.sendNotification("Hello, world!");
  }
}
```

<br>

<a name="state"></a>

## State (Состояние)

Используется для реализации объекта, который может изменять свое поведение в зависимости от своего состояния. В этом
паттерне состояние объекта выносится в отдельные классы, что позволяет объекту динамически изменять свое поведение во
время выполнения.

Простым примером может быть моделирование работы светофора. У светофора есть три состояния: красный, желтый и зеленый.
Он переходит из одного состояния в другое по определенным правилам.

```java
// Интерфейс для состояний
interface State {
  void switchState(TrafficLight trafficLight);
}

// Конкретные состояния
class RedState implements State {
  @Override
  public void switchState(TrafficLight trafficLight) {
    System.out.println("Switching to GREEN state");
    trafficLight.setState(new GreenState());
  }
}

class GreenState implements State {
  @Override
  public void switchState(TrafficLight trafficLight) {
    System.out.println("Switching to YELLOW state");
    trafficLight.setState(new YellowState());
  }
}

class YellowState implements State {
  @Override
  public void switchState(TrafficLight trafficLight) {
    System.out.println("Switching to RED state");
    trafficLight.setState(new RedState());
  }
}

// Класс, представляющий светофор
class TrafficLight {
  private State state;

  public TrafficLight() {
    this.state = new RedState(); // Изначальное состояние - красный
  }

  public void setState(State state) {
    this.state = state;
  }

  public void switchState() {
    state.switchState(this);
  }
}

public class Main {
  public static void main(String[] args) {
    TrafficLight trafficLight = new TrafficLight();

    // Пример переключения состояний
    trafficLight.switchState(); // Красный -> Зеленый
    trafficLight.switchState(); // Зеленый -> Желтый
    trafficLight.switchState(); // Желтый -> Красный
  }
}
```

<br>

<a name="strategy"></a>

## Strategy (Стратегия)

Позволяет определить набор алгоритмов, инкапсулировать каждый из них и обеспечивает их взаимозаменяемость. Алгоритм
стратегии позволяет выбирать алгоритм в зависимости от условий и может быть изменен во время выполнения программы.

Простой пример может быть в контексте сортировки массива. Допустим, имеется
несколько стратегий сортировки: сортировка пузырьком, вставками и слиянием. Можно создать
интерфейс `SortingStrategy`, который будет иметь метод `sort()`, и реализовать этот интерфейс для каждой стратегии
сортировки

```java
// Интерфейс стратегии сортировки
interface SortingStrategy {
  void sort(int[] array);
}

// Конкретные стратегии сортировки
class BubbleSort implements SortingStrategy {
  @Override
  public void sort(int[] array) {
    // Реализация сортировки пузырьком
    System.out.println("Bubble sort");
  }
}

class InsertionSort implements SortingStrategy {
  @Override
  public void sort(int[] array) {
    // Реализация сортировки вставками
    System.out.println("Insertion sort");
  }
}

class MergeSort implements SortingStrategy {
  @Override
  public void sort(int[] array) {
    // Реализация сортировки слиянием
    System.out.println("Merge sort");
  }
}

// Класс контекста, который использует стратегию сортировки
class Sorter {
  private SortingStrategy strategy;

  public void setStrategy(SortingStrategy strategy) {
    this.strategy = strategy;
  }

  public void performSort(int[] array) {
    strategy.sort(array);
  }
}


public class Main {
  public static void main(String[] args) {
    int[] array = {3, 1, 4, 1, 5, 9, 2, 6};

    Sorter sorter = new Sorter();

    sorter.setStrategy(new BubbleSort());
    sorter.performSort(array);

    sorter.setStrategy(new InsertionSort());
    sorter.performSort(array);

    sorter.setStrategy(new MergeSort());
    sorter.performSort(array);
  }
}
```

Благодаря использованию шаблона "Стратегия" мы соблюдаем принцип `OCP` - O из `SOLID`. А также избавляемся от
необходимости
менять клиентский код при добавлении новых стратегий.

<br>

<a name="template"></a>

## Template Method (Шаблонный метод)

Определяет основу алгоритма в родительском классе, позволяя подклассам переопределить определенные шаги алгоритма без
изменения его структуры в целом.

Простой пример использования шаблонного метода может быть в контексте приготовления напитков, например, кофе и чая.

```java
// Абстрактный класс, представляющий напиток
abstract class Beverage {
  // Шаблонный метод, определяющий алгоритм приготовления напитка
  public final void prepareBeverage() {
    boilWater();
    brew();
    pourInCup();
    addCondiments();
  }

  // Абстрактные методы, которые должны быть реализованы в подклассах
  protected abstract void brew();

  protected abstract void addCondiments();

  // Методы, общие для всех напитков
  private void boilWater() {
    System.out.println("Boiling water");
  }

  private void pourInCup() {
    System.out.println("Pouring into cup");
  }
}

// Конкретные классы для различных напитков
class Coffee extends Beverage {
  @Override
  protected void brew() {
    System.out.println("Brewing coffee");
  }

  @Override
  protected void addCondiments() {
    System.out.println("Adding sugar and milk");
  }
}

class Tea extends Beverage {
  @Override
  protected void brew() {
    System.out.println("Steeping the tea");
  }

  @Override
  protected void addCondiments() {
    System.out.println("Adding lemon");
  }
}

public class Main {
  public static void main(String[] args) {
    Beverage coffee = new Coffee();    // Приготовление кофе
    System.out.println("Making coffee:");
    coffee.prepareBeverage();
    System.out.println();

    Beverage tea = new Tea();     // Приготовление чая
    System.out.println("Making tea:");
    tea.prepareBeverage();
  }
}
```

<br>

<a name="visitor"></a>

## Visitor (Посетитель)

Позволяет добавлять новые операции к объектам с сохранением инкапсуляции и не изменяя их классы.

Простым примером может быть рассмотрение различных геометрических фигур и применение операций к ним, таких как
вычисление площади или периметра.

```java
// Интерфейс элемента, который может быть посещен
interface Shape {
  void accept(Visitor visitor);
}

// Конкретные классы элементов (геометрические фигуры)
class Circle implements Shape {
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}

class Square implements Shape {
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}

// Интерфейс посетителя
interface Visitor {
  void visit(Circle circle);

  void visit(Square square);
}

// Конкретный посетитель, выполняющий операции над геометрическими фигурами
class AreaVisitor implements Visitor {
  double totalArea = 0;

  @Override
  public void visit(Circle circle) {
    // Расчет площади круга и добавление к общей площади
    totalArea += Math.PI * Math.pow(10, 2); // Примерный расчет для радиуса 10
  }

  @Override
  public void visit(Square square) {
    // Расчет площади квадрата и добавление к общей площади
    totalArea += Math.pow(10, 2); // Примерный расчет для стороны 10
  }

  double getTotalArea() {
    return totalArea;
  }
}

public class Main {
  public static void main(String[] args) {
    Shape[] shapes = {
            new Circle(),
            new Square()
    };

    // Создание посетителя
    AreaVisitor areaVisitor = new AreaVisitor();
    for (Shape shape : shapes) {
      shape.accept(areaVisitor);
    }


    // Вывод общей площади
    System.out.println("Total area: " + areaVisitor.getTotalArea());
  }
}
```
___




