
package ObserverPattern;

public class BinaryObserver extends Observer{
    public boolean status = true;
   public BinaryObserver(Subject subject){
        this.subject = subject;
      if(isActive())          
      {
          // System.out.println(isActive());
          this.subject.attach(this);
      }
      else 
      {
          this.subject.deattach(this);
      }
   }
   public void setStatus(){
       this.status = false;
   }
   public boolean isActive(){
       return this.status;
   }

   @Override
   public void update() {
      System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
   }
}
