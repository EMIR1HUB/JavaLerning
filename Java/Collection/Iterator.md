# Итераторы

метод `Iterator<E> iterator()` - одним из ключевых методов интерфейса `Collection`. Он возвращает итератор - то есть объект, реализующий интерфейс `Iterator`.

```Java
public interface Iterator <E>{
    E next();
    boolean hasNext();
    void remove();
}
```

Реализация интерфейса: с помощью вызова метода `next()` можно получить следующий элемент. С помощью метода `hasNext()` можно узнать, есть ли следующий элемент, и не достигнут ли конец коллекции. И если элементы еще имеются, то `hasNext()` вернет значение **true**. 
> Метод `hasNext()` следует вызывать перед методом `next()`, так как при достижении конца коллекции метод `next()` выбрасывает исключение **NoSuchElementException**. И метод `remove()` удаляет текущий элемент, который был получен последним вызовом `next()`.

## Итератор для перебора ArrayList:

```Java
public static void main(String[] args) {
        
    ArrayList<String> states = new ArrayList<String>();
    states.add("Germany");
    states.add("France");
    states.add("Italy");
        
    Iterator<String> iter = states.iterator();

    while(iter.hasNext()){
        System.out.println(iter.next());
    }
}
```
<br>

## ListIterator

`ListIterator` предоставляет гораздо больший набор методов. Данный итератор используется классами, реализующими интерфейс `List`.

## Основные методы
```Java
void add(E obj)     // вставляет объект obj перед элементом, который должен быть возвращен следующим вызовом next()
boolean hasNext()   // возвращает true, если в коллекции имеется следующий элемент, иначе возвращает false
boolean hasPrevious()   // возвращает true, если в коллекции имеется предыдущий элемент, иначе возвращает false
E next()        // возвращает текущий элемент и переходит к следующему, если такого нет, то генерируется исключение NoSuchElementException
E previous()    // возвращает текущий элемент и переходит к предыдущему, если такого нет, то генерируется исключение NoSuchElementException
int nextIndex()     // возвращает индекс следующего элемента. Если такого нет, то возвращается размер списка
int previousIndex()     // возвращает индекс предыдущего элемента. Если такого нет, то возвращается число -1
void remove()       // удаляет текущий элемент из списка. Таким образом, этот метод должен быть вызван после методов next() или previous(), иначе будет сгенерировано исключение IlligalStateException
void set(E obj)     // присваивает текущему элементу, выбранному вызовом методов next() или previous(), ссылку на объект obj
```

## Использование

```Java
ArrayList<String> states = new ArrayList<String>();
states.add("Germany");
states.add("France");
states.add("Italy");
    
ListIterator<String> listIter = states.listIterator();
    
while(listIter.hasNext()){
    System.out.println(listIter.next());
}
// сейчас текущий элемент - Италия
// изменим значение этого элемента
listIter.set("Португалия");
// пройдемся по элементам в обратном порядке
while(listIter.hasPrevious()){
    System.out.println(listIter.previous());
}
``` 

[Вернуться назад](../../README.md)
















