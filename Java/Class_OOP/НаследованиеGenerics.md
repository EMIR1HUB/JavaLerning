# Базовый обобщенный класс

Обобщенные классы могут участвовать в иерархии наследования: могут наследоваться от других, либо выполнять роль базовых классов.

При наследовании от обобщенного класса класс-наследник должен передавать данные о типе в конструкции базового класса, при этом класс-наследник может добавлять и использовать какие-то свои параметры типов:

```Java
class Account<T>
{
    private T id;
    T getId(){return id;}
    Account(T id)
    {
        this.id = id;
    }
}
 
class DepositAccount<T, S> extends Account<T>{
 
    private S name;
    S getName(){return name;}
    DepositAccount(T id, S name){
        super(id);
        this.name=name;
    }
}
```

Варианты использования:

```Java
DepositAccount<Integer, String> dAccount1 = new DepositAccount(20, "Tom");
System.out.println(dAccount1.getId() + " : " + dAccount1.getName());
         
DepositAccount<String, Integer> dAccount2 = new DepositAccount("12345", 23456);
System.out.println(dAccount2.getId() + " : " + dAccount2.getName());
```
<br>
Еще одна ситуация - класс-наследник вообще может не быть обобщенным:

```Java
class Account<T>
{
    private T id;
    T getId(){return id;}
    Account(T id)
    {
        this.id = id;
    }
}
 
class DepositAccount extends Account<Integer>{
    DepositAccount()
    {
        super(5);
    }
}
```
Здесь при наследовании явным образом указывается тип, который будет использоваться конструкциями базового класса, то есть тип Integer. Затем в конструктор базового класса передается значение именно этого типа - в данном случае число 5.

```Java
DepositAccount dAccount = new DepositAccount();
System.out.println(dAccount.getId());
```
___

<br>

## Обобщенный класс-наследник

Cитуация, когда базовый класс является обычным необобщенным классом. Например:

```Java
class Account
{
    private String name;
    String getName(){return name;}
    Account(String name)
    {
        this.name=name;
    }
}
 
class DepositAccount<T> extends Account{
 
    private T this.id;
    T getId(){return this.id;}
    DepositAccount(String name, T id){
        super(name);
        this.id = id;
    }
}
```
___

<br>

## Преобразование обобщенных типов

Объект одного обобщенного типа можно привести к другому типу, если они используют один и тот же тип. Рассмотрим преобразование типов на примере следующих двух обобщенных классов:

```Java
class Account<T>
{
    private T id;
    T getId(){return id;}
    Account(T id)
    {
        this.id = id;
    }
}
 
class DepositAccount<T> extends Account<T>{
 
    DepositAccount(T id){
        super(id);
    }
}
```

Можем привести объект `DepositAccount<Integer>` к `Account<Integer>` или `DepositAccount<String>` к `Account<String>`:

```Java
DepositAccount<Integer> depAccount = new DepositAccount(10);
Account<Integer> account = (Account<Integer>)depAccount;
System.out.println(account.getId());
```

> Но сделать то же самое с разнотипными объектами мы не можем.

[Вернуться назад](../../README.md)

