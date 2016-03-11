变量（属性）的覆盖

原文
作者：臧圩人（zangweiren） 
网址：http://zangweiren.javaeye.com/ 

>>>转载请注明出处！<<< 
我们来看看这么一道题： 
Java代码 
class ParentClass {   
    public int i = 10;   
}   
  
public class SubClass extends ParentClass {   
    public int i = 30;   
  
    public static void main(String[] args) {   
        ParentClass parentClass = new SubClass();   
        SubClass subClass = new SubClass();   
        System.out.println(parentClass.i + subClass.i);   
    }   
}  

控制台的输出结果是多少呢？20？40？还是60？ 

变量，或者叫做类的属性，在继承的情况下，如果父类和子类存在同名的变量会出现什么情况呢？这就是这道题要考查的知识点——变量（属性）的覆盖。 

这个问题虽然简单，但是情况却比较复杂。因为我们不仅要考虑变量、静态变量和常量三种情况，还要考虑private、friendly（即不加访问修饰符）、protected和public四种访问权限下对属性的不同影响。 

我们先从普通变量说起。依照我们的惯例，先来看一段代码： 
Java代码 
class ParentClass {   
    private String privateField = "父类变量--private";   
  
    /* friendly */String friendlyField = "父类变量--friendly";   
  
    protected String protectedField = "父类变量--protected";   
  
    public String publicField = "父类变量--public";   
  
    // private的变量无法直接访问，因此我们给他增加了一个访问方法   
    public String getPrivateFieldValue() {   
        return privateField;   
    }   
}   
  
public class SubClass extends ParentClass {   
    private String privateField = "子类变量--private";   
  
    /* friendly */String friendlyField = "子类变量--friendly";   
  
    protected String protectedField = "子类变量--protected";   
  
    public String publicField = "子类变量--public";   
  
    // private的变量无法直接访问，因此我们给他增加了一个访问方法   
    public String getPrivateFieldValue() {   
        return privateField;   
    }   
  
    public static void main(String[] args) {   
        // 为了便于查阅，我们统一按照private、friendly、protected、public的顺序   
        // 输出下列三种情况中变量的值   
  
        // ParentClass类型，ParentClass对象   
        ParentClass parentClass = new ParentClass();   
        System.out.println("ParentClass parentClass = new ParentClass();");   
        System.out.println(parentClass.getPrivateFieldValue());   
        System.out.println(parentClass.friendlyField);   
        System.out.println(parentClass.protectedField);   
        System.out.println(parentClass.publicField);   
  
        System.out.println();   
  
        // ParentClass类型，SubClass对象   
        ParentClass subClass = new SubClass();   
        System.out.println("ParentClass subClass = new SubClass();");   
        System.out.println(subClass.getPrivateFieldValue());   
        System.out.println(subClass.friendlyField);   
        System.out.println(subClass.protectedField);   
        System.out.println(subClass.publicField);   
  
        System.out.println();   
  
        // SubClass类型，SubClass对象   
        SubClass subClazz = new SubClass();   
        System.out.println("SubClass subClazz = new SubClass();");   
        System.out.println(subClazz.getPrivateFieldValue());   
        System.out.println(subClazz.friendlyField);   
        System.out.println(subClazz.protectedField);   
        System.out.println(subClazz.publicField);   
    }   
}  

这段代码的运行结果如下： 

1、ParentClass parentClass = new ParentClass(); 
2、父类变量--private 
3、父类变量--friendly 
4、父类变量--protected 
5、父类变量--public 
6、
7、ParentClass subClass = new SubClass(); 
8、子类变量--private 
9、父类变量--friendly 
10、父类变量--protected 
11、父类变量--public 
12、
13、SubClass subClazz = new SubClass(); 
14、子类变量--private 
15、子类变量--friendly 
16、子类变量--protected 
17、子类变量--public 

从上面的结果中可以看出，private的变量与其它三种访问权限变量的不同，这是由于方法的重写（override）而引起的。关于重写知识的回顾留给以后的章节，这里我们来看一下其它三种访问权限下变量的覆盖情况。 

分析上面的输出结果就会发现，变量的值取决于我们定义的变量的类型，而不是创建的对象的类型。 

在上面的例子中，同名的变量访问权限也是相同的，那么对于名称相同但是访问权限不同的变量，情况又会怎样呢？事实胜于雄辩，我们继续来做测试。由于private变量的特殊性，在接下来的实验中我们都把它排除在外，不予考虑。 

由于上面的例子已经说明了，当变量类型是父类（ParentClass）时，不管我们创建的对象是父类（ParentClass）的还是子类（SubClass）的，都不存在属性覆盖的问题，因此接下来我们也只考虑变量类型和创建对象都是子类（SubClass）的情况。 

Java代码 
class ParentClass {   
    /* friendly */String field = "父类变量";   
}   
  
public class SubClass extends ParentClass {   
    protected String field = "子类变量";   
  
    public static void main(String[] args) {   
        SubClass subClass = new SubClass();   
        System.out.println(subClass.field);   
    }   
}  

运行结果： 

1、子类变量 

Java代码 
class ParentClass {   
    public String field = "父类变量";   
}   
  
public class SubClass extends ParentClass {   
    protected String field = "子类变量";   
  
    public static void main(String[] args) {   
        SubClass subClass = new SubClass();   
        System.out.println(subClass.field);   
    }   
}  

运行结果： 

1、子类变量 

上面两段不同的代码，输出结果确是相同的。事实上，我们可以将父类和子类属性前的访问修饰符在friendly、protected和public之间任意切换，得到的结果都是相同的。也就是说访问修饰符并不影响属性的覆盖，关于这一点大家可以自行编写测试代码验证。 

对于静态变量和常量又会怎样呢？我们继续来看： 
Java代码 
class ParentClass {   
    public static String staticField = "父类静态变量";   
  
    public final String finalField = "父类常量";   
  
    public static final String staticFinalField = "父类静态常量";   
}   
  
public class SubClass extends ParentClass {   
    public static String staticField = "子类静态变量";   
  
    public final String finalField = "子类常量";   
  
    public static final String staticFinalField = "子类静态常量";   
  
    public static void main(String[] args) {   
        SubClass subClass = new SubClass();   
        System.out.println(SubClass.staticField);   
        System.out.println(subClass.finalField);   
        System.out.println(SubClass.staticFinalField);   
    }   
}  

运行结果如下： 

1、子类静态变量 
2、子类常量 
3、子类静态常量 

虽然上面的结果中包含“子类静态变量”和“子类静态常量”，但这并不表示父类的“静态变量”和“静态常量”可以被子类覆盖，因为它们都是属于类，而不属于对象。 

上面的例子中，我们一直用对象来对变量（属性）的覆盖做测试，如果是基本类型的变量，结果是否会相同呢？答案是肯定的，这里我们就不再一一举例说明了。 

最后，我们来做个总结。通过以上测试，可以得出一下结论： 

1、由于private变量受访问权限的限制，它不能被覆盖。 
2、属性的值取父类还是子类并不取决于我们创建对象的类型，而是取决于我们定义的变量的类型。 
3、friendly、protected和public修饰符并不影响属性的覆盖。 
4、静态变量和静态常量属于类，不属于对象，因此它们不能被覆盖。 
5、常量可以被覆盖。 
6、对于基本类型和对象，它们适用同样的覆盖规律。 

我们再回到篇首的那道题，我想大家都已经知道答案了，输出结果应该是40。