<a name="top"></a>

Пять основных принципов дизайна классов (S.O.L.I.D.) в Java

Классы — это блоки, из которых строится приложение Java. И если материал, из которого построено здание — некачественный,
рано или поздно для такого здания настанут трудные времена. Так и в Java — некачественно написанные классы однажды могут
привести к трудной ситуации в процессе работы приложения.

С другой стороны, хорошо разработанные и качественно написанные классы могут ускорить процесс кодирования и уменьшить
количество ошибок.

`S.O.L.I.D.` - пять основных принципов ООП и проектирования, являются достаточно важными элементами при написании кода.

Вот что входит в принципы `SOLID`:

+ [`Single Responsibility Principle`](#srp) (Принцип единственной ответственности).
+ [`Open Closed Principle`](#ocp) (Принцип открытости/закрытости).
+ [`Liskov’s Substitution Principle`](#lsp) (Принцип подстановки Барбары Лисков).
+ [`Interface Segregation Principle`](#isp) (Принцип разделения интерфейса).
+ [`Dependency Inversion Principle`](#dip) (Принцип инверсии зависимостей).

<a name="srp"></a>

## Принцип единственной ответственности (SRP)

Суть принципа ясна из одной-единственной фразы: **На каждый объект должна быть возложена одна единственная обязанность**
.
Другими словами, необходимо писать и поддерживать класс только для одной цели. Если это модель класса, то она
должна строго представлять собой одну функцию или действие. Это даст возможность вносить изменения в будущем, не
боясь влияния изменений на другие объекты.

Каждый объект должен иметь одну обязанность и эта обязанность должна быть полностью инкапсулирована в класс. Все его
сервисы должны быть направлены исключительно на обеспечение этой обязанности.

Например, есть модуль, который составляет и печатает отчёт. Такой модуль может измениться по двум причинам.

1. может измениться само содержимое отчёта.
2. может измениться формат отчёта.

Оба фактора изменяют модуль по разным причинам: в одном случае изменение содержательное, а во втором — косметическое.
Принцип единственной
обязанности говорит, что оба аспекта этой проблемы на самом деле являются двумя разными обязанностями, и в таком случае
должны находиться в разных классах или модулях.
> Объединение двух сущностей, изменяющихся по разным причинам и в разное
> время, считается плохим проектным решением.

<a name="ocp"></a>

## Принцип открытости/закрытости (OCP)

Данный принцип достаточно емко характеризует следующее положение: **Программные сущности (классы, модули, функции и
т.п.)
должны быть открыты для расширения, но закрыты для изменения**.

Это означает, что должна быть возможность изменять внешнее поведение класса, не внося физические изменения в сам класс.
Следуя этому принципу, классы разрабатываются так, чтобы для подстройки класса к конкретным условиям применения было
достаточно расширить его и переопределить некоторые функции.

Поэтому система должна быть гибкой, с возможностью работы в изменяющихся условиях без изменения исходного кода.

<a name="lsp"></a>

## Принцип подстановки Барбары Лисков (LSP)

Данный принцип является вариацией принципа `открытости/закрытости`. В нем говорится: **Объекты в
программе могут быть заменены их наследниками без изменения свойств программы**.

Это означает, что класс, разработанный на основании базового класса путем расширения, должен переопределять его методы
без нарушения функциональности с точки зрения клиента. То есть, если разработчик расширяет ваш класс и
использует его в приложении, он не должен изменять ожидаемое поведение переопределенных методов.

Подклассы должны переопределять методы базового класса так, чтобы не нарушалась функциональность с точки зрения клиента.

<a name="isp"></a>

## Принцип разделения интерфейса (ISP)

Характеризуется следующим утверждением: **клиенты не должны быть вынуждены реализовывать методы, которые они не будут
использовать**.

Принцип разделения интерфейсов говорит о том, что слишком «толстые» интерфейсы необходимо разделять на более мелкие и
специфические, чтобы клиенты мелких интерфейсов знали только о методах, необходимых в работе. В итоге при изменении
метода интерфейса не должны меняться клиенты, которые этот метод не используют.

<a name="dip"></a>

## Принцип инверсии зависимостей (DIP)

Данный принцип в Java описывают так: **зависимости внутри системы строятся на основе абстракций**. Модули верхнего
уровня не зависят от модулей нижнего уровня. Абстракции не должны зависеть от деталей. Детали должны зависеть от
абстракций.

Программное обеспечение нужно разрабатывать так, чтобы различные модули были автономными и соединялись друг с другом с
помощью абстракции.

Классическое применение этого принципа —` Spring framework`. В рамках Spring framework все модули выполнены в виде
отдельных компонентов, которые могут работать вместе. Они настолько автономны, что могут быть с такой же легкостью
задействованы в других программных модулях помимо Spring framework.

Это достигнуто за счет зависимости закрытых и открытых принципов. Все модули предоставляют доступ только к абстракции,
которая может использоваться в другом модуле.

[Вверх](#top)
[Вернуться назад](../README.md)