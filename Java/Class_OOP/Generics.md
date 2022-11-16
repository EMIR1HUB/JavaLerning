# Generics

`Generics` (обобщенные типы и методы) позволяют нам уйти от жесткого определения используемых типов.

```Java
////// Java5 //////
List animals = new ArrayList();
animals.add("cat");
animals.add("dog");

//используется Downcasting
System.out.println((String) animals.get(1));     //dog

////// С появлением дженериков //////
List<String> animals2 = new ArrayList<>();
animals2.add("cat");
animals2.add("dog");

System.out.println(animals2.get(1));    //dog

```

### Пример
Мы определяем класс для представления банковского счета

```Java
class Account<T>{
     
    private T id;
    private int sum;
     
    Account(T id, int sum){
        this.id = id;
        this.sum = sum;
    }
     
    public T getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}
```

С помощью буквы **T** в определении класса `class Account<T>` мы указываем, что данный тип **T** будет использоваться этим классом. Параметр **T** в угловых скобках называется универсальным параметром, так как вместо него можно подставить любой тип: String, int или какой-то другой. Причем буква **T** выбрана условно, это может и любая другая буква или набор символов.

После объявления класса мы можем применить универсальный параметр **T**

Метод `getId()` возвращает значение переменной id, но так как данная переменная представляет тип **T**, то данный метод также возвращает объект типа **T**:` public T getId()`.

Используем данный класс:

```Java
public class Program{
      
    public static void main(String[] args) {
          
        Account<String> acc1 = new Account<String>("2345", 5000);
        String acc1Id = acc1.getId();
        System.out.println(acc1Id);
         
        Account<Integer> acc2 = new Account<Integer>(2345, 5000);
        Integer acc2Id = acc2.getId();
        System.out.println(acc2Id);
    }
}
```

> Типы в угловых скобках должны быть только объектами и не работают с примитивными типами. То есть мы можем написать Account<Integer>, но не можем использовать тип int или double. Вместо примитивных типов надо использовать классы-обертки: `Integer` вместо int, `Double` вместо double и т.д.
____
<br>

<a name="outinterface"></a>

## Обобщенные интерфейсы

Интерфейсы, как и классы, также могут быть обобщенными.

```Java
public class Program{

    public static void main(String[] args) {

        Accountable<String> acc1 = new Account("1235rwr", 5000);
        Account acc2 = new Account("2373", 4300);
        System.out.println(acc1.getId());
        System.out.println(acc2.getId());
    }
}

interface Accountable<T>{
    T getId();
    int getSum();
    void setSum(int sum);
}

class Account implements Accountable<String>{
     
    private String id;
    private int sum;
     
    Account(String id, int sum){
        this.id = id;
        this.sum = sum;
    }
     
    public String getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}
```

Есть две стратегии реализации подобного интерфейса. В данном случае реализована первая стратегия, когда при реализации для универсального параметра интерфейса задается конкретный тип, в данном случае это тип `String`. Тогда класс, реализующий интерфейс, жестко привязан к этому типу.

Вторая стратегия представляет определение обобщенного класса, который также использует тот же универсальный параметр:

```Java
public class Program{
      
    public static void main(String[] args) {
          
        Account<String> acc1 = new Account<String>("1235rwr", 5000);
        Account<String> acc2 = new Account<String>("2373", 4300);
        System.out.println(acc1.getId());
        System.out.println(acc2.getId());
    }
}
interface Accountable<T>{
    T getId();
    int getSum();
    void setSum(int sum);
}
class Account<T> implements Accountable<T>{
     
    private T id;
    private int sum;
     
    Account(T id, int sum){
        this.id = id;
        this.sum = sum;
    }
     
    public T getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}
```
___
<br>

<a name="outmethods"></a>

## Обобщенные методы

Кроме обобщенных типов можно также создавать обобщенные методы, которые точно также будут использовать универсальные параметры:

```Java
public class Program{
      
    public static void main(String[] args) {
          
        Printer printer = new Printer();
        String[] people = {"Tom", "Alice", "Sam", "Kate", "Bob", "Helen"};
        Integer[] numbers = {23, 4, 5, 2, 13, 456, 4};
        printer.<String>print(people);
        printer.<Integer>print(numbers);
    }
}
 
class Printer{
     
    public <T> void print(T[] items){
        for(T item: items){
            System.out.println(item);
        }
    }
}
```

Особенностью обобщенного метода является использование универсального параметра в объявлении метода после всех модификаторов и перед типом возвращаемого значения.

```public <T> void print(T[] items)```
___

<br>

<a name="universalpar"></a>

## Использование нескольких универсальных параметров

Можем также задать сразу несколько универсальных параметров:

```Java
public class Program{
      
    public static void main(String[] args) {
          
        Account<String, Double> acc1 = new Account<String, Double>("354", 5000.87);
        String id = acc1.getId();
        Double sum = acc1.getSum();
        System.out.printf("Id: %s  Sum: %f \n", id, sum);
    }
}
class Account<T, S>{
     
    private T id;
    private S sum;
     
    Account(T id, S sum){
        this.id = id;
        this.sum = sum;
    }
     
    public T getId() { return id; }
    public S getSum() { return sum; }
    public void setSum(S sum) { this.sum = sum; }
}
```

В данном случае тип `String` будет передаваться на место параметра **T**, а тип `Double` - на место параметра **S**.
___

<br>

<a name="outconstruction"></a>

## Обобщенные конструкторы

Конструкторы также могут быть обобщенными. В этом случае перед конструктором также указываются в угловых скобках универсальные параметры:

```Java
public class Program{
      
    public static void main(String[] args) {
          
        Account acc1 = new Account("cid2373", 5000);
        Account acc2 = new Account(53757, 4000);
        System.out.println(acc1.getId());
        System.out.println(acc2.getId());
    }
}
 
class Account{
     
    private String id;
    private int sum;
     
    <T>Account(T id, int sum){
        this.id = id.toString();
        this.sum = sum;
    }
     
    public String getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}
```

В данном случае конструктор принимает параметр id, который представляет тип **T**. В конструкторе его значение превращается в строку и сохраняется в локальную переменную.

[Вернуться назад](../../README.md)