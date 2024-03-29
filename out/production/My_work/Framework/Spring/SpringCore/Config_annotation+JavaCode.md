# Конфигурация Spring приложения. Аннотации + Java код

```Java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
``` 

## Аннотация @Configuration

Помечает Java класс, который хотим использовать для конфигурации Spring приложения

```Java
@Configuration
public class SpringConfig{
}
```

Пустой конфигурационный Java класс равен по фунционалу пустому конфигурационному XML файлу

## XML теги = соответствующие аннотации

| **XML** | **Аннотации** |
| --- | --- |
| `<context:component-scan base-package="директория с компонентами"/>` | @ComponentScan("директория с компонентами") |
| `<bean/>` | @Bean |
| `<context:property-placeholder location="classpath:файл.properties"/>` | @PropertySource("classpath:файл.properties")|

<br>

## Ручное внедрение зависимостей

```Java
@Configuration
public class SpringConfig {
    
    @Bean
    @Scope("singleton")
    public ClassicalMusic musicBean() { // beanId = musicBean 
        return new ClassicalMusic();
    }
    
    @Bean
    public MusicPlayer musicPlayer() { // beanId = musicPlayer 
        return new MusicPlayer(musicBean()); // Внедрение созданного бина
    }
}
```

[Вернуться назад](../../../README.md)