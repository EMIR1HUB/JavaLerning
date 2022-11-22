# Ограничения обобщений

Необходим для того чтобы применять ограничения, которые позволяют указать базовый класс, которому должен соответствовать параметр.

Для установки ограничения после универсального параметра ставится слово `extends`, после которого указывается базовый класс ограничения:

```Java
class Account{ }
class Transaction<T extends Account>{ }
```

К примеру, в данном случае для параметра **T** в **Transaction** ограничением является класс **Account**. То есть на место параметра **T** мы можем передать либо класс **Account**, либо один из его классов-наследников.

### Пример

```Java
public class Program{
      
    public static void main(String[] args) {
          
        Account acc1 = new Account("1876", 4500);
        Account acc2 = new Account("3476", 1500);
              
        Transaction<Account> tran1 = new Transaction<Account>(acc1,acc2, 4000);
        tran1.execute();
        tran1 = new Transaction<Account>(acc1,acc2, 4000);
        tran1.execute();
    }
}
class Transaction<T extends Account>{
     
    private T from;     // с какого счета перевод
    private T to;       // на какой счет перевод
    private int sum;    // сумма перевода
     
    Transaction(T from, T to, int sum){
        this.from = from;
        this.to = to;
        this.sum = sum;
    }
    public void execute(){
         
        if (from.getSum() > sum)
        {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.printf("Account %s: %d \nAccount %s: %d \n", 
                from.getId(), from.getSum(),to.getId(), to.getSum());
        }
        else{
            System.out.printf("Operation is invalid");
        }
    }
}
class Account{
     
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

В данном случае класс `Transaction`, который представляет операцию перевода средств между двумя счетами, типизирован параметром **T**, у которого в качестве ограничения установлен класс `Account`. При создании объекта `Transaction` в его конструктор передаются два объекта `Account` - два счета, между которыми надо осуществить перевод, и сумма перевода.
___
<br>

<a name="interfgenerics"></a>

## Интерфейсы в качестве оганичений

В качестве ограничений могут выступать также интерфейсы. В этом случае передаваемый на место универсального параметра тип должен реализовать данный интерфейс:

```Java
public class Program{
      
    public static void main(String[] args) {
          
        Account acc1 = new Account("1235rwr", 5000);
        Account acc2 = new Account("2373", 4300);
        Transaction<Account> tran1 = new Transaction<Account>(acc1, acc2, 1560);
        tran1.execute();
    }
}
interface Accountable{
    String getId();
    int getSum();
    void setSum(int sum);
}
class Account implements Accountable{
     
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
class Transaction<T extends Accountable>{
     
    private T from;     // с какого счета перевод
    private T to;       // на какой счет перевод
    private int sum;    // сумма перевода
     
    Transaction(T from, T to, int sum){
        this.from = from;
        this.to = to;
        this.sum = sum;
    }
    public void execute(){
         
        if (from.getSum() > sum)
        {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.printf("Account %s: %d \nAccount %s: %d \n", 
                from.getId(), from.getSum(),to.getId(), to.getSum());
        }
        else{
            System.out.printf("Operation is invalid");
        }
    }
}
```
____
<br>

<a name="plural"></a>

## Множественные ограничения

```Java
class Person{}
interface Accountable{}
 
class Transaction<T extends Person & Accountable>{}
```

[Вернуться назад](../../README.md)






