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

