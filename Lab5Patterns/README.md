# TASK 1

Observer pattern is used when there is one-to-many relationship between objects such as if one object is modified, its depenedent objects are to be notified automatically. Observer pattern falls under behavioral pattern category.

### Implementation
Observer pattern uses three actor classes. Subject, Observer and Client. Subject is an object having methods to attach and detach observers to a client object. We have created an abstract class Observer and a concrete class Subject that is extending class Observer.

ObserverPatternDemo, our demo class, will use Subject and concrete class object to show observer pattern in action.

## The deattach() method:

package ObserverPattern;
import java.util.ArrayList;
import java.util.List;


public class Subject {
	
   private List<Observer> observers = new ArrayList<Observer>();
   private int state;

   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
      notifyAllObservers();
   }

   public void attach(Observer observer){
      observers.add(observer);		
   }
   public void deattach(Observer o){
       observers.remove(o);
   }
   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   } 	
}

#### Changes IN Observers 
package ObserverPattern;

public class OctalObserver extends Observer{
   public boolean status= true;
   public OctalObserver(Subject subject){
      this.subject = subject;
      if(isActive())
      {
         //  System.out.println(isActive());
          this.subject.attach(this);}
      else 
      {this.subject.deattach(this);}
   }
   public void setStatus(Boolean status){
       this.status = true;
   }
   public boolean isActive(){
       return this.status;
   }
   @Override
   public void update() {
     System.out.println( "Octal String: " + Integer.toOctalString( subject.getState() ) ); 
   }  
}
# DESCRIPTION OF CODE
The code demonstrates the implementation of the Observer pattern, a behavioral design pattern where an object (known as the "subject") maintains a list of its dependent objects ("observers"), and notifies them of any state changes automatically. In this example, different observer classes display the state of a subject in various formats (Binary, Hexadecimal, and Octal) when the subject's state changes.

### Key Components in the Code:

1. **`Subject` Class:**
   - Represents the "subject" that holds the state. It maintains a list of attached observers and provides methods to manage these observers.
   - **State Management:**
     - The state of the `Subject` can be retrieved using the `getState()` method, and set using the `setState()` method.
     - When the state changes via `setState()`, the `notifyAllObservers()` method is called to update all attached observers.
   - **Observer Management:**
     - Observers can be attached using the `attach()` method, which adds them to the list of observers.
     - Observers can be removed using the `deattach()` method, which removes them from the list.
     - `notifyAllObservers()` iterates over the list of observers, calling the `update()` method on each.

2. **`Observer` Abstract Class:**
   - This abstract class serves as the base for all concrete observer classes.
   - It contains a reference to the `Subject` and declares two abstract methods: `update()` and `isActive()`.
   - Each concrete observer class extends this base class to implement specific behavior for updating when the subject's state changes.

3. **Concrete Observer Classes:**
   These classes extend the `Observer` class and provide specific implementations for the `update()` method:
   
   - **`BinaryObserver` Class:**
     - Displays the subject's state in binary format.
     - Has a `status` flag that determines if the observer is active or not. If active, it attaches itself to the subject; otherwise, it detaches.
     - The `isActive()` method returns the current status, and the `update()` method prints the state as a binary string.
   
   - **`HexaObserver` Class:**
     - Displays the subject's state in hexadecimal format.
     - The observer initially has an inactive status (`status = false`). If the status is active, it attaches to the subject; otherwise, it detaches.
     - The `update()` method prints the state as a hexadecimal string in uppercase.
   
   - **`OctalObserver` Class:**
     - Displays the subject's state in octal format.
     - It has a `status` flag set to true initially, which determines if it is active. The observer attaches or detaches from the subject based on this flag.
     - The `update()` method prints the state as an octal string.

4. **`ObserverPatternDemo` Class (Main Program):**
   - Demonstrates the Observer pattern by creating a `Subject` and three concrete observers (`HexaObserver`, `OctalObserver`, and `BinaryObserver`), each observing the same subject.
   - The program changes the state of the subject three times (`15`, `10`, and `5`). Each time the state changes, all active observers are notified and update their respective outputs.

### Observer Pattern Explanation:
The Observer pattern allows an object (subject) to maintain a list of dependents (observers) that are automatically updated whenever the subject's state changes. This pattern promotes loose coupling between the subject and its observers. Here’s how the pattern is implemented in this code:

- The `Subject` notifies all attached observers when its state changes.
- Observers can dynamically attach or detach themselves from the subject.
- Each observer reacts independently to state changes based on its implementation of the `update()` method.

### Summary:
The provided code illustrates the core concepts of the Observer pattern:
- **Decoupling:** Observers are not tightly bound to the subject; they only react to changes through the `update()` method.
- **Dynamic Behavior:** Observers can attach or detach from the subject dynamically based on certain conditions (`isActive()` status).
- **Multiple Representations:** The same subject state can be represented in different formats (binary, hexadecimal, and octal), demonstrating how different observers can perform distinct actions based on the same notification.

The Observer pattern is widely used in scenarios where a one-to-many dependency is needed, such as in event handling systems, data-binding in UI frameworks, and real-time data updates in software applications.



# TASK 2 REFACTORING

## Description Of Code 
 A simple implementation of the Observer design pattern in Java, which allows an object, known as the "subject," to notify a list of "observers" about changes to its state. In this example, the pattern is applied to an event management system where various event listeners can subscribe to or unsubscribe from specific events, and they get notified when those events occur. Here’s a breakdown of the components:

## 1. EventManager Class
This class manages event subscriptions and notifications:

#### Data Structure: A Map<String, List<EventListener>> is used to store the event types (such as "open," "save," or "send") and their corresponding list of subscribed listeners.
###### Constructor: 
Takes a variable number of event types and initializes the listeners map for each type with an empty list.
Methods:
subscribe(String eventType, EventListener listener): 
Adds a listener to the list of subscribers for a specified event type.
unsubscribe(String eventType, EventListener listener): Removes a listener from the subscribers' list for a specified event type.
notify(String eventType, File file): Notifies all listeners subscribed to a given event type by calling their update method.
#### 2. EventListener Interface
Defines the contract for any class that wants to listen for events. Implementing classes must provide the update(String eventType, File file) method, which will be called when an event is triggered.

### 3. Editor Class
Represents the "subject" that generates events:

### Fields:
events: An instance of EventManager, which is responsible for managing different types of events ("open," "save," "send").
file: A File object representing the currently opened file.
Constructor: Initializes the EventManager with event types "open," "save," and "send."
## Methods:
openFile(String filePath): Sets the file field to the specified file and notifies all listeners that an "open" event has occurred.
saveFile(): Checks if a file is currently opened; if so, notifies listeners of a "save" event; otherwise, throws an exception indicating that no file is opened.
sendMsg(): Notifies listeners of a "send" event, presumably indicating that a file-related message has been sent.

The system allows multiple EventListener implementations to be notified of different events occurring within the Editor. For example, one listener might log the event, while another might send notifications. The EventManager helps decouple the event generation (Editor) from the event handling (EventListener), promoting flexibility and maintainability in the code.
The code extends the Observer pattern implementation by introducing several specific event listeners that react to different events generated by the `Editor` class. It includes the following components:

### 1. `EmailNotificationListener` Class
- **Implements `EventListener` Interface:** This class defines how to handle events by sending email notifications.
- **Field:** It has a `String email` field to specify the email address where notifications will be sent.
- **Constructor:** Takes an email address as a parameter to initialize the listener.
- **`update()` Method:** Implements the `update()` method of the `EventListener` interface to display a message indicating an email notification has been sent. It outputs the type of operation and the name of the file involved.

### 2. `LogOpenListener` Class
- **Implements `EventListener` Interface:** This class handles events by logging them.
- **Field:** Uses a `File` object to represent the log file where events will be recorded.
- **Constructor:** Initializes the listener with the log file's name.
- **`update()` Method:** Displays a message indicating that the event has been logged, including the event type and file name.

### 3. `SMSSupportListener` Class
- **Implements `EventListener` Interface:** This class is designed to handle SMS notifications.
- **Field:** It has a `String SMS` field to represent the message content.
- **Constructor:** Initializes the SMS message.
- **`update()` Method:** Implements the `update()` method to handle the SMS notification logic:
  - Checks if the SMS message length exceeds 160 characters.
  - If the message is too long, it outputs a warning message indicating the issue.
  - Otherwise, it simulates sending the SMS by printing a confirmation message.

### 4. `Demo` Class (Main Program)
- **Sets up the event management system by creating an `Editor` instance.**
- **Subscribes various listeners to different event types:**
  - `LogOpenListener` is subscribed to the "open" event, which logs when a file is opened.
  - `EmailNotificationListener` is subscribed to the "save" event, which sends an email notification when a file is saved.
  - `SMSSupportListener` is subscribed to the "send" event, which handles SMS notifications.
- **Triggers events:**
  - Calls `editor.openFile("test.txt")`, which notifies all listeners subscribed to the "open" event.
  - Calls `editor.saveFile()`, which notifies listeners subscribed to the "save" event.
  - Calls `editor.sendMsg()`, which triggers the "send" event and the corresponding SMS notification.
- **Exception Handling:** Catches any exceptions during the file operations and prints the stack trace.


The program demonstrates the Observer pattern by allowing different actions (email, logging, and SMS) to be performed in response to file-related events (`open`, `save`, `send`). Each listener reacts independently based on the event type. This approach provides a flexible way to extend functionality without modifying the existing code structure, adhering to the open/closed principle in object-oriented design.




