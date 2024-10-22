
package ObserverPattern;


public class HexaObserver extends Observer {
      public boolean status = false;
   public HexaObserver(Subject subject){
      this.subject = subject;
      if(isActive())
      {
         // System.out.println(isActive());
          this.subject.attach(this);}
      else 
      {this.subject.deattach(this);}
   }
   public void setStatus(){
       this.status = true;
   }
   public boolean isActive(){
       return this.status;
   }
   @Override
   public void update() {
      System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ).toUpperCase() ); 
   }
}
