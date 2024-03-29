# Интерфейсы Comparable и Comparator. Сортировка

При добавлении новых элементов объект `TreeSet` автоматически проводит сортировку, помещая новый объект на правильное для него место. Однако, если бы мы использовали не строки, а свои классы, например, следующий класс **Person**:

```Java
class Person{
    private String name;
    Person(String name){
          
        this.name=name;
    }
    //...
}
```

Объект `TreeSet` мы не сможем типизировать данным классом, поскольку в случае добавления объектов **TreeSet** не будет знать, как их сравнивать. При попытки это совершить мы столкнемся с ошибкой.
<br>

## Comparable

Для того, чтобы объекты `Person` можно было сравнить и сортировать, они должны применять интерфейс `Comparable<E>`. При применении интерфейса он типизируется текущим классом.

```Java
class Person implements Comparable<Person>{
     
    private String name;
    Person(String name){
        this.name = name;
    }
    String getName(){return name;}
     
    public int compareTo(Person p){
        return name.compareTo(p.getName());
    }

    // своя логика, сравнивать по длине имени
    public int compareTo(Person p){
        return name.length()-p.getName().length();
    }

}
```
Интерфейс `Comparable` содержит один единственный метод `int compareTo(E item)`, который сравнивает текущий объект с объектом, переданным в качестве параметра. 
+ Если метод возвращает отрицательное число, то текущий объект будет располагаться перед тем, который передается через параметр.
+ Если метод вернет положительное число, то, наоборот, после второго объекта.
+ Если метод возвратит ноль, значит, оба объекта равны.

Типизирование `TreeSet` типом `Person`:

```Java
TreeSet<Person> people = new TreeSet<Person>();
people.add(new Person("Tom"));
```
___
<br>

## Comparator

Eсли разработчик не реализовал в своем классе, интерфейс `Comparable`, либо реализовал, но нас не устраивает его функциональность, и мы хотим ее переопределить? В таком случай есть более гибкий способ, применение интерфейса `Comparator<E>`.

Интерфейс `Comparator` содержит ряд методов, ключевым из которых является метод `compare`():

```Java
public interface Comparator<E> {
    int compare(T a, T b);
    // остальные методы
}
```

Метод `compare` также возвращает числовое значение - если оно отрицательное, то объект a предшествует объекту b, иначе - наоборот. А если метод возвращает 0, то объекты равны.

```Java
class PersonComparator implements Comparator<Person>{

    public int compare(Person a, Person b){
        return a.getName().compareTo(b.getName());
    }
}
```

Проводим сравнение по строкам. Теперь используем класс компаратора для создания объекта `TreeSet`:

```Java
PersonComparator pcp = new PersonComparator();
TreeSet<Person> people = new TreeSet<Person>(pcp);
people.add(new Person("Tom"));
people.add(new Person("Nick"));
people.add(new Person("Alice"));

for(Person  p : people){     
    System.out.println(p.getName());
}
```
Для создания `TreeSet` здесь используется одна из версий конструктора, которая в качестве параметра принимает компаратор.
___

<br>

## Сортировка по нескольким критериям

с JDK 8 мы можем применять сразу несколько компараторов по принципу приоритета. 

```Java
class Person{
      
    private String name;
    private int age;       // Добавлены поле для хранения возраста пользователя
    public Person(String n, int a){
        name=n;
        age=a;
    }
    
    String getName(){return name;}
    int getAge(){return age;}
}
``` 

Допустим, нам надо отсортировать пользователей по имени и по возрасту. Для этого определим два компаратора:

```Java
class PersonNameComparator implements Comparator<Person>{
  
    public int compare(Person a, Person b){
        return a.getName().compareTo(b.getName());
    }
}

class PersonAgeComparator implements Comparator<Person>{
  
    public int compare(Person a, Person b){
        if(a.getAge()> b.getAge())
            return 1;
        else if(a.getAge()< b.getAge())
            return -1;
        else
            return 0;
    }
}
```

Интерфейс компаратора определяет специальный метод по умолчанию `thenComparing`, который позволяет использовать цепочки компараторов для сортировки набора:

```Java
Comparator<Person> pcomp = new PersonNameComparator().thenComparing(new PersonAgeComparator());
TreeSet<Person> people = new TreeSet(pcomp);
people.add(new Person("Tom", 23));
people.add(new Person("Nick",34));
people.add(new Person("Tom",10));
 
for(Person  p : people){
    System.out.println(p.getName() + " " + p.getAge());
}
```

[Вернуться назад](../../README.md)





