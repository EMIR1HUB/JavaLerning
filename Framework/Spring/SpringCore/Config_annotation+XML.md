# Конфигурация Spring приложения. XML + Аннотации

<a name="annotation"></a>
## Аннотации

в xml файле
```xml
<context:component-scan base-package="название пакета с классами для бинов" />
```

Аннотации - специальный тип комментариев, с помощью которых можно
+ передавать инструкции для Java-компилятора (Например, `@Override`)
+ передавать инструкции для анализаторов исхдного кода
+ передавать метаданные, которые могут быть использованы либо Java приложением (с помощью рефлексии), либо другими приложениями или фреймворками (Например, Spring)

> Преимущества аннотаций: короче и удобнее, чем XML конфигурация, код становится более читабельным.

`@Component` - пометка на создание бина из класса

+ Можно указать **id** для бина или не указывать (тогда название будет название класса с первой маленькой буквы


```Java
@Component
public class RockMusic implements Music{
    @Override
    public String getSong() { return "Rock Music"; }
}


public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Music music1 = context.getBean("rockMusic", Music.class);   // получаем бин с помощью анотации @Component
        System.out.println(music1.getSong());
        
        context.close();
    }
}
```
___

<br>

<a name="implanat"></a>
## Внедрение зависимостей с помощью аннотаций

Класс, в который нужно внедрить зависимость помечается аннотацией `@Autowired` подбирает подходящий бин по типу (класс или интерфейс).

Принцип работы:
1. Spring сканирует все классы с анотацией `@Component` и создает бины
2. Spring сканирует все созданные бины и проверяет, подходит ли хотя бы один бин в качетсве зависимоти там, где указали `@Autowired`
3. Если находится один подходящий бин, он внедряется в качетсве зависимоти. Если нет - ошибка. Если несколько - неоднозначность.

> Аннотацию `@Autowired` можно использовать на полях, сеттерах и конструкторах. Причем он внедрит зависимость в приватное поле, даже если нет конструктора или сеттера. Делает это она с помощью рефлексии (Java Reflection API)

<br>

## @Qualifier(id)

Указывает **id** бина, который хотим внедрить

```Java
@Autowired
public MusicPlayer(@Qualifier("classicalMusic") Music music1, @Qualifier("rockMusic") Music music2) {
    this.music1 = music1;
    this.music2 = music2;
}
``` 

> Аннотацию `@Qualifier` можно использовать на полях, сеттерах и конструкторах. 

<br>

## @Value

1. Создание файла`.properties` с парой ключ:значением
2. `<context:property-placeholder location="classpath:имяФайла.properties/>"`
3. ```Java
    @Value("${musicPlayer.name}")       // musicPlayer.name - ключ значения в файле .properties
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;
    ```

<br>

## @Scope

**Scope** - определяет область видимости (singleton, prototype, ...)

```Java
@Component
@Scope("singleton")
public class ClassicalMusic implements Music {
    @Override
    public String getSong() { return "Classical Music"; }
}
```

<br>

## @PostConstruct - аннотация [init-method](#indemethod)

## @PreDestroy - аннотация [destroy-method](#indemethod)

> Для использования **@PostConstruct**, **@PreDestroy** в java9 и выше подключаем зависимость `javax.annotation-api`

___

<br>

[Вернуться назад](../../../README.md)