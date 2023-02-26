# Utilitium
Useful universal direction library
#### Readme written with OpenAI ChatGPT (GPT-3)

---
## Package org.ReStudios.utilitium.vectors (Vectors)
Vectors are divided into 2 categories: a number and a letter at the end of the class name. digit - means how many numbers the class stores (2 - x and y, 3 - x, y and z). letter - number type (no letter - integers, d - double, f - floating). There is also a class Vector, which has `Vector.of()` methods that help create a vector with the desired type and number of numbers


##### Classes `Vector(number)(char)`
The class has two constructors, one with no parameters which initializes the vector with zero values, and another one which takes two integer parameters to initialize the x and y [and z] values.

The class has getter and setter methods for the x and y [and z] values, as well as methods to add, subtract, multiply, and divide the vector with other vectors, direct values, or a single value that is applied to both x and y [and z] values.

The `vector.add()` method adds the x and y [and z] values of the vector with the x and y [and z] values of the given vector or direct values.

The `vector.sub()` method subtracts the x and y [and z] values of the vector with the x and y [and z] values of the given vector or direct values.

The `vector.mul()` method multiplies the x and y [and z] values of the vector with the x and y [and z] values of the given vector or direct values.

The `vector.div()` method divides the x and y [and z] values of the vector with the x and y [and z] values of the given vector or direct values, but only if the divisor is not zero.

All the methods return an instance of vector class to allow method chaining.

---
## ArrayUtils
The `ArrayUtils` class provides a set of utility methods for working with arrays, collections, and maps. Here is a brief description of each method:

### createMap(Object... values)
This method allows for the easy creation of maps by accepting arguments in the form of key-value pairs. It takes any number of arguments, where every even argument is a `String`  key and every odd argument is the corresponding value of any `Object` type. It then creates a `HashMap` with those key-value pairs and returns it. Here is an example of how to use this method:
```java
HashMap<String, Object> map = ArrayUtils.createMap("key1", "value1", "key2", 2, "key3", new CustomClass());
```

### toArrayList(T... values)
This method allows for the easy creation of `ArrayList`s by accepting any number of objects and converting them into an `ArrayList`. Here is an example of how to use this method:
```java
ArrayList<Integer> list = ArrayUtils.toArrayList(1, 2, 3, 4, 5);
```

### removeSame(List\<T\> list, ComparingMode way)
This method removes duplicate elements from a given list based on the specified `ComparingMode`. It accepts a `List` of any type and a `ComparingMode` enum value. The `ComparingMode` enum has three possible values: `EQUALITY`, `CLASS`,  and `HASH_CODE`. If `EQUALITY` is chosen, the method will remove any element that is equal to another element in the list using the `equals()` method. If `CLASS` is chosen, the method will remove any element that is of the same class as another element in the list. If `HASH_CODE` is chosen, the method will remove any element that has the same hash code as another element in the list. Here is an example of how to use this method:
```java 
List<String> list = Arrays.asList("a", "b", "c", "d", "a", "b");
List<String> uniqueList = ArrayUtils.removeSame(list, ArrayUtils.ComparingMode.EQUALITY);
```

### reverse(T\[\] arr)
This method reverses the order of elements in an array. It accepts an array of any type and modifies it in place. Here is an example of how to use this method:
```java 
Integer[] array = {1, 2, 3, 4, 5};
ArrayUtils.reverse(array);
```

### getSum(Collection<Float> floatCollection)
This method calculates the sum of all `Float` values in a collection. It accepts a collection of `Float` values and returns their sum as a `float`. Here is an example of how to use this method:

### getLastItem(T\[\] list)
This method returns the last element of an array. It accepts an array of any type and returns the last element of that array. Here is an example of how to use this method:
```java
String[] array = {"a", "b", "c", "d", "e"};
String lastItem = ArrayUtils.getLastItem(array);
```

---
## BetterArguments
The `BetterArguments` class is a utility class that provides a simple way to parse command-line arguments and manage default arguments. It accepts an array of command-line arguments and parses them into a HashMap with keys and values. It also provides methods to manage default arguments and retrieve values by key.

argument example: `-intValue 2 -youCanUseArgumentWithoutValue -booleanValue true -string you can use values with spaces`

- `putDefault(String key, String value)` - adds a default argument to the default arguments HashMap.
- `removeDefault(String key)` - removes a default argument from the default arguments HashMap.
- `getDefault(String key)` - gets the value of a default argument.
- `containsDefault(String key)` - checks if a default argument exists.
- `contains(String key)` - checks if a command-line argument exists.
- `getBoolean(String key)` - gets a boolean value by key.
- `getString(String key)` - gets a string value by key.
- `getOrShort(String full, String shortKey)` - gets a string value by key, checking both the full and short key.
- `getInteger(String key)` - gets an integer value by key.
- `getIntegerOr(String key, int i)` - gets an integer value by key or a default value if the key is not found.

---
## Command
The Command class is an abstract class that represents a command that can be executed with a set of arguments. It provides a simple framework for defining and executing commands and subcommands, as well as enforcing a minimum number of arguments required for a command to execute.

The class provides methods for adding subcommands, setting the minimum number of arguments required, and executing the command. It also allows for easy access to the root command and the arguments of the parent and root commands.

Example usage:
```java 
public class MyCommand extends Command {

    public MyCommand() {
        setMinArguments(1);
        addSubCommand(new MySubCommand());
    }

    @Override
    public boolean executeCommand(Object sender, String[] args) {
        // Implement command logic here
        return true;
    }
}

public class MySubCommand extends Command {

    public MySubCommand() {
        setMinArguments(0);
    }

    @Override
    public boolean executeCommand(Object sender, String[] args) {
        // Implement subcommand logic here
        return true;
    }
}
```
In this example, a command "MyCommand" is defined that requires at least one argument to execute. It also has a subcommand "MySubCommand" that requires no arguments. When the command is executed, the "executeCommand" method is called with the arguments provided. The subcommands are checked first, and if the argument matches a subcommand, the subcommand's "executeCommand" method is called with the remaining arguments. If no subcommand is matched, the command's "executeCommand" method is called with all the arguments provided.

---
## DataChannel
Convenient work with two streams at the same time. The most important method is `transfer`, which reads the input stream and writes data to the output stream. also convenient method `close` it closes both streams IN THIS SEQUENCE: first the input stream, and then the output stream.

---
## FileHandle
FileHandle is a Java class that extends the built-in File class to provide additional file handling functionality. This class includes methods to read and write file content, open a data channel to a file, get a file's extension, and unzip a file from a jar file.

One of the main advantages of using FileHandle is that it simplifies file handling by providing a more intuitive API than the standard Java File class. For example, opening a data channel to a file is as simple as calling the `open()` method, and reading a file's content can be accomplished with the `read()` method.

Another advantage of FileHandle is that it provides methods for common file handling tasks, such as getting a file's extension and unzipping a file from a jar file. This can save time and reduce code complexity, as developers do not need to write these methods themselves.

In summary, FileHandle is a useful extension of the Java File class that provides additional file handling functionality and simplifies common file handling tasks.

---
## InstanceManager
The `InstanceManager` class provides a way to register and retrieve instances of objects based on their superclass. This can be useful in situations where you have multiple instances of a class and need a way to access them from other parts of your code.

#### Usage
To use `InstanceManager`, first, you need to register instances of your objects using the `register` method:
```java
// constructor 
public MyClass(){
  InstanceManager.register(this);
}
```
or:
```java 
MyClass myObject = new MyClass();
InstanceManager.register(myObject);
```

You can only register 1 instance per class (class, not instance).

To retrieve an instance, you can use the `getInstance` method, passing in the superclass of the instance you want to retrieve:
```java 
MyClass myObject = InstanceManager.getInstance(MyClass.class);
```

This will return the first instance of `MyClass` that was registered.
```java 
public class MyClass {
    public MyClass(){
        InstanceManager.register(this);
    }

    public void doSomething() {
        System.out.println("Doing something");
    }
}

public class MyApp {
    public static void main(String[] args) {
        new MyClass();

        MyClass retrievedObject = InstanceManager.getInstance(MyClass.class);
        retrievedObject.doSomething(); // Output: Doing something
    }
}
```

---
## Logger
The `Logger` class is a subclass of `PrintStream` and `Thread.UncaughtExceptionHandler`. It provides a simple way to log information to the console with additional metadata such as the name of the current thread and the class and line number where the log statement was called. It also supports logging of uncaught exceptions.

The `Logger` constructor takes a `PrintStream` object as its parameter and assigns it to an instance variable `originalPrintStream`. This allows the `Logger` object to delegate printing to the original stream while also adding metadata to the log statements.

The print methods of `Logger` override the corresponding methods in `PrintStream`. They call a utility method `Utilitium.getCaller` to get the `StackTraceElement` of the method that called the print method. This element contains information about the class and line number of the calling method, which is used to construct the metadata portion of the log statement.

The `setupPrints` method is a static method that sets up the logging functionality for `System.out` and `System.err`. It creates a new `Logger` object for each stream and sets the `isErrorStream` flag to true for `System.err`. It also sets the uncaught exception handler of the current thread to the `Logger` object for `System.err`.

Example usage:
```java
Logger.setupPrints();

System.out.print("This is an info message.");
System.err.print("This is an error message.");

int result = 10 / 0; // throws an ArithmeticException

// Output:
// [main INFO com.example.MyClass:14] This is an info message.
// [main ERROR com.example.MyClass:15] This is an error message.
// java.lang.ArithmeticException: / by zero
// 	at com.example.MyClass.main(MyClass.java:17)
```

---
## MathUtils
This class provides various utility methods for mathematical calculations.

#### Methods
- `clamp()` - Clamps a given value between a minimum and maximum value.
- `map()` - Maps a value from one range to another range.
- `getPositionFromDistanceRotation()` - Calculates the position of a point in a given distance and direction from a starting point.
- `getAverageValue()` - Calculates the average value of a list of float values.

---
## StringUtils
- `parseInteger(String s)` - Safely parses an integer from a string. Returns 0 if the string is not a valid integer.
- `parseDouble(String s)` - Safely parses a double from a string. Returns 0.0 if the string is not a valid double.
- `parseFloat(String s)` - Safely parses a float from a string. Returns 0 if the string is not a valid float.
- `parseInteger(String s, int defaultValue)` - Safely parses an integer from a string, with a default value if the string is not a valid integer.
- `parseDouble(String s, double defaultValue)` - Safely parses a double from a string, with a default value if the string is not a valid double.
- `parseFloat(String s, float defaultValue)` - Safely parses a float from a string, with a default value if the string is not a valid float.
- `reverse(String s)` - Reverses a string.
- `characterUp(int index, String s)` - Sets the character at a specified index to upper case.
- `charsUp(String s, int... ints)` - Sets all characters at specified indexes to upper case.
- `md5(String str)` - Encodes a string using the MD5 algorithm.
- `firstCharUp(String s)` - Sets the first character of a string to upper case.

---
## ThreadBuilder
Example:
```java
public static void main(String[] args) {
    Runnable task = new Runnable() {
        @Override
        public void run() {
            System.out.println("This is a task that will be executed in a new thread.");
        }
    };

    Thread thread = new ThreadBuilder(task)
                    .setName("MyThread")
                    .setPriority(Thread.MAX_PRIORITY)
                    .setHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.out.println("Exception caught in thread: " + t.getName() + " Message: " + e.getMessage());
                        }
                    })
                    .buildAndRun();

    System.out.println("Main thread continues to execute while the new thread is running.");
}
```
In this example, we created a new thread that will execute the task. We used the `ThreadBuilder` class to set the thread's name, priority, and exception handler. Then we started the thread and continued the execution of the main thread.

The code of the `ThreadBuilder` class allows us to easily create and configure threads without resorting to additional lines of code for each thread. We can use the `build()` method to create a thread with the specified parameters or the `buildAndRun()` method to create and start the thread in one method. Additionally, we can use the `setName()`, `setPriority()`, and `setHandler()` methods to configure the thread's name, priority, and exception handler, respectively.

---
## Timer
The `Timer` class is a simple Java class that provides basic timing functionality. The class contains a `startTime` field, which stores the time at which the timer is started, and a boolean field named `running`, which indicates whether the timer is currently running or not. The Timer class provides four methods: `run()`, `getTime()`, `running()`, and `stop()`.

The `run()` method starts the timer by setting the startTime field to the current system time. The `getTime()` method returns the time elapsed since the timer was started by subtracting the start time from the current system time. The `stop()` method stops the timer and returns the total elapsed time in milliseconds.

The goal of this class is to provide a simple way to measure the time taken by a program or a specific operation within a program. This can be useful for profiling and optimizing code, as well as for measuring the performance of different algorithms.

Example usage:
```java
Timer timer = new Timer();
timer.run();
// Run some code here
long elapsedTime = timer.stop();
System.out.println("Elapsed time: " + elapsedTime + " ms");
```
This will create a new `Timer` object, start the timer using the `run()` method, run some code, stop the timer using the `stop()` method, and print the elapsed time in milliseconds.

---
## TrayUtils
The `TrayUtils` class is a utility class for working with the system tray in Java. It provides methods for creating a tray icon, displaying notifications, changing the icon and tooltip text at runtime, setting a custom right-click menu, and adding a double-click listener.

To use this class, simply create an instance of `TrayUtils` and pass in the desired title and icon (if any). Then, use the various setter methods to configure the tray icon as desired. Finally, call the `notification()` method to display a notification in the system tray.

Example usage:
```java
TrayUtils trayUtils = new TrayUtils("My Application", myAppIcon);
trayUtils.setPopupMenu(myPopupMenu);
trayUtils.addDoubleClickListener(myDoubleClickListener);
trayUtils.notification("Hello", "World", TrayIcon.MessageType.INFO);
```
Note that the system tray may not be supported on all platforms, so you should check that it is supported before using this class by calling the `isSupported()` method.

---
## Class 'Utilitium.java'
### castOrNull(Class\<T\> clazz, Object object)
This method attempts to cast an object to a given class type. It takes two arguments: `clazz`, the target class type, and `object`, the object to be cast. If the cast is successful, it returns the cast object; otherwise, it returns null.

Example usage:
```java 
String str = "hello";
Object obj = str;

// Cast object to String using castOrNull method
String castedStr = Utilitium.castOrNull(String.class, obj);

System.out.println(castedStr); // "hello"
```

### getCaller(int depth)
This method returns the `StackTraceElement` of the method caller at a given `depth` in the call stack. It takes a single argument depth, the number of stack frames to look back to find the caller.

Example usage:
```java 
// Get the stack trace element of the immediate caller
StackTraceElement caller = Utilitium.getCaller(1);

System.out.println(caller.getClassName()); // prints the class name of the caller
```

### safeEquals(Object a, Object b)
This method performs a safe comparison of two objects using the `equals` method. It takes two arguments, `a` and `b`, the objects to compare. If both objects are `null`, it returns `true`. If only one of the objects is `null`, it returns `false`. Otherwise, it returns the result of the `equals` method called on `a`.

---
## WarnFunctions
Functions whose functionality works, but the desired result MAYBE not be the desired one

### getTTSAudioFile(String text, String language)
Get Text-to-speech audio data using Google Translator.
Language in format `ISO 639-1` ('ru', 'en', 'de', etc.).
