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
