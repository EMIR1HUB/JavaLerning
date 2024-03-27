# Содержание

+ [Коллекции. Интерфейс Collection](../Collection/ТипыКоллекций_ИнтерфейсCollection.md)
+ [List](#list)
    + [ArrayList](#arraylist)
    + [LinkedList](#linkedlist)
    + [Vector](#vector)
    + [Stack](#stack)
+ [Queue](#queue) | [Deque](#deque)
    + [ArrayDeque](#arrdeque)
    + [PriorityQueue](#priorityqueue)
+ [Set](#set)
    + [HashSet](#hashset)
    + [SortedSet](#sortedset)
    + [NavigableSet](#navset)
    + [LinkedHashSet](#linkedhashset)
    + [TreeSet](#treeset)
+ [Map](#map)
    + [HashMap](#hashmap)
    + [LinkedHashMap](#linkedhashmap)
    + [SortedMap](#sortedmap)
    + [NavigableMap](#navigablemap)
    + [TreeMap](#treemap)
    + [Hashtable](#hashtable)

___
<br>

<a name="list"></a>

# Интерфейс List

Для создания простых списков применяется интерфейс `List`, он сохраняет последовательность добавления элементов и
позволяет осуществлять доступ к элементу по индексу. Расширяет интерфейс `Collection`, таким образом наследует все
его [методы](/ТипыКоллекций_ИнтерфейсCollection.md#methods) и вместе с тем добавляет свои методы.

## Конструкторы

```java
ArrayList() // создает пустой список
        ArrayList(Collection<?extends E> col) // создает список, в который добавляются все элементы коллекции col.
```

## Основные методы

+ `void add(int index, E obj)`: добавляет в список по индексу index объект obj

+ `boolean addAll(int index, Collection<? extends E> col)`: добавляет в список по индексу **index** все элементы
  коллекции **col**. Если в результате добавления список был изменен, то возвращается **true**, иначе возвращается **
  false**

+ `E get(int index)`: возвращает объект из списка по индексу index

+ `int indexOf(Object obj)`: возвращает индекс первого вхождения объекта obj в список. Если объект не найден, то
  возвращается -1

+ `int lastIndexOf(Object obj)`: возвращает индекс последнего вхождения объекта obj в список. Если объект не найден, то
  возвращается -1

+ `ListIterator<E> listIterator ()`: возвращает объект ListIterator для обхода элементов списка

+ `static <E> List<E> of(элементы)`: создает из набора элементов объект List

+ `E remove(int index)`: удаляет объект из списка по индексу index, возвращая при этом удаленный объект

+ `E set(int index, E obj)`: присваивает значение объекта obj элементу, который находится по индексу index

+ `void sort(Comparator<? super E> comp)`: сортирует список с помощью компаратора comp

+ `List<E> subList(int start, int end)`: получает набор элементов, которые находятся в списке между индексами **start**
  и **end**

___
<br>

<a name="list"></a>

# ArrayList

`ArrayList` класс реализации интерфейса `List`. Поддерживает динамические массивы. Элементы могут быть абсолютно любых
типов, в том числе и null, а также могут повторяться. По умолчанию внутренний массив будет иметь размер 16.

При добавлении больше 16 элементов, происходит перераспределение памяти - создание нового массива и копирование в него
элементов из
старого массива. В случае если заранее известна максимальное количество элементов в создаваемой
коллекции, размер массива можно указать передав значение в конструктор `ArrayList`. Это позволит снизить подобные
перераспределения памяти, тем
самым повышая производительность.

## Достоинства

Быстрый доступ по индексу. Скорость такой операции - O(1).
Быстрая вставка и удаление элементов с конца. Скорость операций - O(1).

## Недостатки

Медленная вставка и удаление элементов из середины. Сложность O(n).

___
<br>

<a name="linkedlist"></a>

# LinkedList

`LinkedList<E>` - двусвязный список. Наследуется от класса `AbstractSequentialList` и реализует
интерфейсы `List`, `Dequeue`. Позволяет организовать стек, очередь, двойную очередь, а также список. Этот
класс позволяет добавлять любые значения, в том числе и **null**.

## Конструкторы

```java
LinkedList() // создает пустой список
        LinkedList(Collection<?extends E> col) // создает список, в который добавляет все элементы коллекции col
```

## Методы

`LinkedList` содержит все методы, которые определены в интерфейсах [List](#list), [Queue](#queue), [Deque](#deque).
Некоторые из них:

+ `addFirst() / offerFirst()`: добавляет элемент в начало списка

+ `addLast() / offerLast()`: добавляет элемент в конец списка

+ `removeFirst() / pollFirst()`: удаляет первый элемент из начала списка

+ `removeLast() / pollLast()`: удаляет последний элемент из конца списка

## Пример

```java
import java.util.LinkedList;

public class Program {

  public static void main(String[] args) {

    LinkedList<String> states = new LinkedList<String>();

    states.add("Germany");
    states.addLast("Great Britain"); // добавляем на последнее место
    states.addFirst("Spain"); // добавляем на первое место
    states.add(1, "Italy"); // добавляем элемент по индексу 1

    System.out.printf("List has %d elements \n", states.size());
    System.out.println(states.get(1));
    states.set(1, "Portugal");
    for (String state : states) {
      System.out.println(state);
    }
    // проверка на наличие элемента в списке
    if (states.contains("Germany")) {
      System.out.println("List contains Germany");
    }

    states.remove("Germany");
    states.removeFirst(); // удаление первого элемента

    LinkedList<Person> people = new LinkedList<Person>();
    people.add(new Person("Mike"));
    people.addFirst(new Person("Tom"));
    people.remove(1); // удаление второго элемента

    for (Person p : people) {
      System.out.println(p.getName());
    }
    Person first = people.getFirst();
    System.out.println(first.getName()); // вывод первого элемента
  }
}

class Person {
  private String name;

  public Person(String value) {
    name = value;
  }

  String getName() {
    return name;
  }
}
```

___
<br>

<a name="vector"></a>

# Vector

Класс `Vector` реализует динамический массив.

Он подобен `ArrayList`, но с двумя отличиями: `Vector` **синхронизирован** и включает много унаследованных методов, не
являющихся частью каркаса коллекций.

С появлением коллекций `Vector` был перепроектирован как расширение `AbstractList`, и в него была добавлена реализация
интерфейса `List`. В версии jdk 5 он был перепроектирован под применение обобщённого синтаксиса, и в нем появилась
реализация интерфейса `Iterable`.
___
<br>

<a name="stack"></a>

# Stack

`Stack` это подкласс `Vector`, который реализует стандартный стек **LIFO**.

`Stack` определяет только конструктор по умолчанию, создающий пустой стек.

С появлением версии Java 5 подкласс `Stack` был перепроектирован под обобщенный синтаксис, и теперь он объявлен
следующим образом:

```
class Stack<E>
```

___
<br>

<a name="queue"></a>

# Интерфейс Queue

Интерфейс `Queue<E>` расширяет `Collection` и определяет поведение класса в качестве однонаправленной очереди, которые
представляют собой список с дисциплиной "первый вошел, первый вышел" **FIFO** **(first in - first out)**. Очереди не
могут хранить значения null.

## Основные методы:

+ `E element()`: возвращает, но не удаляет, элемент из начала очереди. Если очередь пуста, генерирует исключение **
  NoSuchElementException**

+ `boolean offer(E obj)`: добавляет элемент obj в конец очереди. Если элемент удачно добавлен, возвращает **true**,
  иначе - **false**

+ `E peek()`: возвращает без удаления элемент из начала очереди. Если очередь пуста, возвращает значение **null**

+ `E poll()`: возвращает с удалением элемент из начала очереди. Если очередь пуста, возвращает значение **null**

+ `E remove()`: возвращает с удалением элемент из начала очереди. Если очередь пуста, генерирует исключение **
  NoSuchElementException**

Все классы, которые реализуют данный интерфейс, будут иметь эти методы

___
<br>

<a name="deque"></a>

# Интерфейс Deque

Интерфейс `Deque<E>` появился в Java 6. Расширяет `Queue` и описывает поведение двунаправленной очереди. Двунаправленная
очередь может функционировать как стандартная очередь **FIFO** либо как стек **LIFO**.

## Основные методы:

+ `void addFirst(E obj)`: добавляет элемент в начало очереди

+ `void addLast(E obj)`: добавляет элемент в конец очереди

+ `E getFirst()`: возвращает без удаления элемент из головы очереди. Если очередь пуста, генерирует исключение **
  NoSuchElementException**

+ `E getLast()`: возвращает без удаления последний элемент очереди. Если очередь пуста, генерирует исключение **
  NoSuchElementException**

+ `boolean offerFirst(E obj)`: добавляет элемент obj в самое начало очереди. Если элемент удачно добавлен, возвращает **
  true**, иначе - **false**

+ `boolean offerLast(E obj)`: добавляет элемент obj в конец очереди. Если элемент удачно добавлен, возвращает **true**,
  иначе - **false**

+ `E peekFirst()`: возвращает без удаления элемент из начала очереди. Если очередь пуста, возвращает значение `null`

+ `E peekLast()`: возвращает без удаления последний элемент очереди. Если очередь пуста, возвращает значение **null**

+ `E pollFirst()`: возвращает с удалением элемент из начала очереди. Если очередь пуста, возвращает значение **null**

+ `E pollLast()`: возвращает с удалением последний элемент очереди. Если очередь пуста, возвращает значение **null**

+ `E pop()`: возвращает с удалением элемент из начала очереди. Если очередь пуста, генерирует исключение **
  NoSuchElementException**

+ `void push(E element)`: добавляет элемент в самое начало очереди

+ `E removeFirst()`: возвращает с удалением элемент из начала очереди. Если очередь пуста, генерирует исключение **
  NoSuchElementException**

+ `E removeLast()`: возвращает с удалением элемент из конца очереди. Если очередь пуста, генерирует исключение **
  NoSuchElementException**

+ `boolean removeFirstOccurrence(Object obj)`: удаляет первый встреченный элемент obj из очереди. Если удаление
  произшло, то возвращает **true**, иначе возвращает **false**.

+ `boolean removeLastOccurrence(Object obj)`: удаляет последний встреченный элемент obj из очереди. Если удаление
  произшло, то возвращает **true**, иначе возвращает **false**.

> Наличие методов **pop** и **push** позволяет классам, реализующим этот элемент, действовать в качестве стека.
> Имеющийся функционал позволяет создавать двунаправленные очереди, что делает классы, применяющие данный интерфейс,
> довольно гибкими.
___
<br>


<a name="arrdeque"></a>

# ArrayDeque

Класс `ArrayDeque<E>` представляет обобщенную двунаправленную очередь, наследуя функционал от
класса `AbstractCollection` и реализуя интерфейс `Deque`.

Данный класс использует динамический массив для хранения элементов. Широко применяется при
реализации различных коллекций, таких как стеки и очереди.

## Конструкторы

```java
ArrayDeque() //создает двунаправленную пустую очередь
        ArrayDeque(Collection<?extends E> col) //создает двунаправленную очередь, наполненную элементами из коллекции col
        ArrayDeque(int numElements)  //создает пустую двунаправленную очередь с вместимостью numElements
```

___
<br>

<a name="priorityqueue"></a>

# PriorityQueue

Класс `PriorityQueue<E>` реализует интерфейс `Queue`. По умолчанию очередь с приоритетами
размещает элементы согласно естественному порядку сортировки используя `Comparable`. Элементу с наименьшим значением
присваивается наибольший приоритет. Если элементы имеют одинаковый приоритет, связь определяется произвольно.

> Отличие от интерфейса `Set`. Интерфейс `Set` не допускает добавления два одинаковых элементов. В класс `PriorityQueue`
> можно добавлять одинаковые элементы. Также можно указать специальный порядок
> размещения, используя `Comparator`, если естественный порядок сортировки не подходит.

## Конструкторы

```java
PriorityQueue() //создает очередь с приоритетами начальной емкостью 11, размещающую элементы согласно естественному порядку сортировки (Comparable).
        PriorityQueue(Collection<?extends E> c) //можно передать на вход какую-то другую коллекцию.
        PriorityQueue(int initialCapacity)  //можно указать начальную ёмкость.
        PriorityQueue(int initialCapacity,Comparator<? super E>comparator)  //можно указать начальную ёмкость и какой-то другой компаратор.
        PriorityQueue(PriorityQueue<?extends E> c);
        PriorityQueue(SortedSet<?extends E> c).
```

___
<br>

<a name="set"></a>

# Set

Интерфейс `Set` расширяет интерфейс `Collection` и представляет набор уникальных элементов.

> Каждый элемент хранится только в одном экземпляре, разные реализации `Set` используют разный порядок хранения
> элементов.

Если порядок хранения важен, применяется `TreeSet`, в котором объекты хранятся отсортированными по возрастанию или с
хранением элементов в порядке добавления `LinkedHashSet`.

<br>

<a name="hashset"></a>

# HashSet

`HashSet` представляет такую структуру данных, в которой все объекты имеют уникальный ключ или хеш-код. Данный ключ
позволяет уникально идентифицировать объект в таблице.

> Класс `HashSet` не добавляет новых методов, реализуя лишь те, что объявлены в родительских классах и применяемых
> интерфейсах:

## Создание множества и заполнение элементами

```java
HashSet<String> states=new HashSet<String>(); // Создание множества
        states.add("Germany");
        states.add("France");
        states.add("Italy");
```

## Получение множества | метод **iterator()**

`iterator()` - метод позволяющий получить все множество элементов.

```java
Iterator<String> iterator=states.iterator(); // Создание итератора
        while(iterator.hasNext()){
        System.out.println(iterator.next());
        }
```

## Основные методы

```java
int size() // размер
        boolean isEmpty()
        boolean contains(Object o)
        boolean addAll(Collection c)
        Object[]toArray()
        boolean remove(Object o)
        boolean removeAll(Collection c)
        void clear()
```

<br>

<a name="sortedset"></a>

# SortedSet

`SortedSet` предназначен для создания коллекций, который хранят элементы в отсортированном виде (сортировка по
возрастанию). **SortedSet** расширяет интерфейс **Set**, поэтому такая коллекция опять же хранит только уникальные
значения.

## Методы

+ `E first()`: возвращает первый элемент набора

+ `E last()`: возвращает последний элемент набора

+ `SortedSet<E> headSet(E end)`: возвращает объект **SortedSet**, который содержит все элементы первичного набора до
  элемента end

+ `SortedSet<E> subSet(E start, E end)`: возвращает объект **SortedSet**, который содержит все элементы первичного
  набора между элементами start и end

+ `SortedSet<E> tailSet(E start)`: возвращает объект SortedSet, который содержит все элементы первичного набора, начиная
  с элемента start

<br>

<a name="navset"></a>

# NavigableSet

`NavigableSet` расширяет интерфейс `SortedSet` и позволяет извлекать элементы на основании их значений.

[Методы](https://metanit.com/java/tutorial/5.5.php)

<br>

<a name="linkedhashset"></a>

# LinkedHashSet | Элементы в порядке добавления

Не добавляет новых методов. Класс поддерживает связный список элементов набора в том порядке, в котором они вставлялись.
Это позволяет организовать упорядоченную итерацию вставки в набор.

<br>


<a name="treeset"></a>

# TreeSet | Элементы отсортированные по возрастанию

Обобщенный класс `TreeSet<E>` представляет структуру данных в виде дерева, в котором все объекты хранятся в
отсортированном виде по возрастанию.

```java
Random random=new Random();
        SortedSet<Integer> sortedNumbers=new TreeSet<>(); //Создаие TreeSet

        for(int i=0;i< 5;i++){
        sortedNumbers.add(random.nextInt(10));
        }

// Результаты работы
        Элементы отсортированы по возрастанию,размер множества меняется,так как повторяющиеся
        элементы не добавляются,а игнорируются
        [0,4,6,9]
        [0,1,2,4,8]
        [2,3,9]
```

___
<br>

<a name="map"></a>

# Интерфейс Map | Словарь

Интерфейс `Map<K, V>` представляет словарь, где каждый элемент представляет пару "ключ-значение". Все ключи уникальные в
рамках объекта **Map**. Основные реализации: `Hashmap`, `LinkedHashMap`, `Hashtable`, `TreeMap`.

> `Map` НЕ расширяет интерфейс `Collection`.

<a name="mapmethods"></a>

## Основные методы

+ `void clear()`: очищает коллекцию

+ `boolean containsKey(Object k)`: возвращает true, если коллекция содержит ключ k

+ `boolean containsValue(Object v)`: возвращает true, если коллекция содержит значение v

+ `Set<Map.Entry<K, V>> entrySet()`: возвращает набор элементов коллекции. Все элементы представляют объект **
  Map.Entry**

+ `boolean equals(Object obj)`: возвращает true, если коллекция идентична коллекции, передаваемой через параметр obj

+ `boolean isEmpty`: возвращает true, если коллекция пуста

+ `V get(Object k)`: возвращает значение объекта, ключ которого равен k. Если такого элемента не окажется, то
  возвращается значение null

+ `V getOrDefault(Object k, V defaultValue)`: возвращает значение объекта, ключ которого равен k. Если такого элемента
  не окажется, то возвращается значение defaultValue

+ `V put(K k, V v)`: помещает в коллекцию новый объект с ключом k и значением v. Если в коллекции уже есть объект с
  подобным ключом, то он перезаписывается. После добавления возвращает предыдущее значение для ключа k, если он уже был
  в коллекции. Если же ключа еще не было в коллекции, то возвращается значение null

+ `V putIfAbsent(K k, V v)`: помещает в коллекцию новый объект с ключом k и значением v, если в коллекции еще нет
  элемента с подобным ключом.

+ `Set<K> keySet()`: возвращает набор всех ключей отображения

+ `Collection<V> values()`: возвращает набор всех значений отображения

+ `void putAll(Map<? extends K, ? extends V> map)`: добавляет в коллекцию все объекты из отображения map

+ `V remove(Object k)`: удаляет объект с ключом k

+ `int size()`: возвращает количество элементов коллекции

<br>

Обобщенный интерфейс `Map.Entry<K, V>` представляет объект с ключом типа K и значением типа V и определяет следующие
методы:

```java
boolean equals(Object obj) // возвращает true, если объект obj, представляющий интерфейс Map.Entry, идентичен текущему
        K getKey() // возвращает ключ объекта отображения
        V getValue() // возвращает значение объекта отображения
        V setValue(V v) // устанавливает для текущего объекта значение v
        int hashCode(): // возвращает хеш-код данного объекта
```

<br>

<a name="hashmap"></a>

# HashMap

> Облегчает поиск значения, если известен ключ - уникальный итендификатор.

## Создание словаря. Заполнение и получение значений

```Java
HashMap<Integer, String> hashMap=new HashMap<>();
        hashMap.put(1,"one"); // добавление элемента, если в коллекции уже есть элемент с подобным ключом, то он перезаписывается
        hashMap.put(2,"two");
        hashMap.put(3,"three");
        hashMap.get(1); // Получение значения по ключу или null, если значение отсутствует
        hashMap.get(2);
        hashMap.get(3);
```

## Перебор словаря и вывод содержимого на экран

```java
for(Map.Entry entry:hashMap.entrySet()) // entrySet() - возвращает множество элементов коллекции 
        {
        System.out.print("key: "+entry.getKey()); // getKey() - получить ключ сущности
        System.out.println("; value: "+entry.getValue()); // getKey() - получить значение сущности
        }
```

## Конвертация Map в List

```Java
// key list
List<Integer> keyList=new ArrayList<>(map.keySet());
// value list
        List<String> valueList=new ArrayList<>(map.values());
// key-value list
        List<Map.Entry<Integer, String>>entryList=new ArrayList<>(map.entrySet());
```

## Счетчик ключей Map

```Java
//map<String, Int> 
while((line=bufferedReader.readLine())!=null)
        {
        String[]words=line.split("\\W");{
        for(String word:words){
        if(word.equals("")){
        continue;
        }
        if(!map.containsKey(word)){
        map.put(word,1);
        }else{
        map.put(word,map.get(word)+1);
        }
        }
        }
        }
```

## [Основные методы](#mapmethods)

<br>

<a name="linkedhashmap"></a>

# LinkedHashMap

`LinkedHashMap` - отображение с запоминанием порядка, в котором добавлялись элементы, разрешает перебор в порядке
вставки.

```Java
// ====LinkedHashMap====
LinkedHashMap<Integer, String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put(1,"one");
        linkedHashMap.put(3,"three");
        linkedHashMap.put(4,"four");
        linkedHashMap.put(2,"two");

// ====HashMap====
        HashMap<Integer, String> hashMap=new HashMap<>();
        hashMap.put(1,"one");
        hashMap.put(3,"three");
        hashMap.put(4,"four");
        hashMap.put(2,"two");


// Результат
// ====LinkedHashMap====
        key:1;value=one
        key:3;value=three
        key:4;value=four
        key:2;value=two

// ====HashMap====
        key:1;value=one
        key:2;value=two
        key:3;value=three
        key:4;value=four
```

<br>

<a name="sortedmap"></a>

# SortedMap

Интерфейс `SortedMap` расширяет `Map` и создает отображение, в котором все элементы отсортированы в порядке возрастания
их ключей.

## Методы

```Java
K firstKey() // возвращает ключ первого элемента отображения
        K lastKey() // возвращает ключ последнего элемента отображения
        SortedMap<K, V> headMap(K end) // возвращает отображение SortedMap, которые содержит все элементы оригинального SortedMap вплоть до элемента с ключом end
        SortedMap<K, V> tailMap(K start) // возвращает отображение SortedMap, которые содержит все элементы оригинального SortedMap, начиная с элемента с ключом start
        SortedMap<K, V> subMap(K start,K end) // возвращает отображение SortedMap, которые содержит все элементы оригинального SortedMap вплоть от элемента с ключом start до элемента с ключом end
```

<br>

<a name="navigablemap"></a>

# NavigableMap

Интерфейс `NavigableMap` расширяет интерфейс `SortedMap` и обеспечивает возможность получения элементов отображения
относительно других элементов.

## Основные методы:

+ `Map.Entry<K, V> ceilingEntry(K obj)`: возвращает элемент с наименьшим ключом k, который больше или равен ключу
  obj **(k >=obj)**. Если такого ключа нет, то возвращается null.

+ `Map.Entry<K, V> floorEntry(K obj)`: возвращает элемент с наибольшим ключом k, который меньше или равен ключу obj **(
  k <=obj)**. Если такого ключа нет, то возвращается null.

+ `Map.Entry<K, V> higherEntry()`: возвращает элемент с наименьшим ключом k, который больше ключа obj **(k >obj)**. Если
  такого ключа нет, то возвращается null.

+ `Map.Entry<K, V> lowerEntry()`: возвращает элемент с наибольшим ключом k, который меньше ключа obj **(k < obj)**. Если
  такого ключа нет, то возвращается null.

+ `Map.Entry<K, V> firstEntry()` | `lastEntry()`: возвращает (первый | последний) элемент отображения

+ `Map.Entry<K, V> pollFirstEntry()` | `pollLastEntry()`: возвращает и одновременно удаляет (первый | последний) элемент
  из отображения

+ `K ceilingKey(K obj)`: возвращает наименьший ключ k, который больше или равен ключу obj **(k >=obj)**. Если такого
  ключа нет, то возвращается null.

+ `K floorKey(K obj)`: возвращает наибольший ключ k, который меньше или равен ключу obj **(k <=obj)**. Если такого ключа
  нет, то возвращается null.

+ `K lowerKey(K obj)`: возвращает наибольший ключ k, который меньше ключа obj **(k < obj)**. Если такого ключа нет, то
  возвращается null.

+ `K higherKey(K obj)`: возвращает наименьший ключ k, который больше ключа obj **(k > obj)**. Если такого ключа нет, то
  возвращается null.

+ `NavigableSet<K> descendingKeySet()`: возвращает объект NavigableSet, который содержит все ключи отображения в
  обратном порядке

+ `NavigableMap<K, V> descendingMap()`: возвращает отображение NavigableMap, которое содержит все элементы в обратном
  порядке

+ `NavigableSet<K> navigableKeySet()`: возвращает объект NavigableSet, который содержит все ключи отображения

<br>

<a name="treemap"></a>

# TreeMap

`TreeMap<K, V>` представляет отображение в виде дерева. В отличие от коллекции `HashMap` в `TreeMap` все объекты
автоматически сортируются по возрастанию их ключей.

## Сортировка ключей существующей Map

```Java
LinkedHashMap<String, Integer> linkedHashMap=new LinkedHashMap<>(); // неотсортированная мапа
        linkedHashMap.put("xxx",5);
        linkedHashMap.put("a",3);
        linkedHashMap.put("ccc",1);
        linkedHashMap.put("b",2);

        for(Map.Entry entry:linkedHashMap.entrySet()){
        System.out.println("key: "+entry.getKey()+"; value: "+entry.getValue());
        }

//Результат
        key:xxx;value:5
        key:a;value:3
        key:ccc;value:1
        key:b;value:2


        TreeMap<String, Integer> treeMap=new TreeMap<>(linkedHashMap); // Передача неотсортированной мапы для сортировки
        for(Map.Entry entry:treeMap.entrySet()){
        System.out.println("key: "+entry.getKey()+"; value: "+entry.getValue());
        }

//Результат
        key:a;value:3
        key:b;value:2
        key:ccc;value:1
        key:xxx;value:5
```

<br>

<a name="hashtable"></a>

# Hashtable

`Hashtable` подобен `HashMap`, но **синхронизирован**. `Hashtable` подобно `HashМap` сохраняет пары "ключ-значение" в
хеш-таблице. Однако ни ключи, ни значения не могут быть равны **null**.

`Hashtable` был сделан обобщенным в Java 5:

```
class Hashtable<K, V>
```

[Вернуться назад](../../README.md)