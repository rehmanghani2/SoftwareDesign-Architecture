package ProtectedVariability;
// Class Payment
class Payment{
  private double amount;
  private String gateway;
  public Payment(double amount, String gateway){
      this.amount = amount;
      this.gateway = gateway;
  }
  public double getAmount(){
      return amount;
  }
  public String getGateway(){
      return gateway;
  }
}
// Class PaymentProcessor
abstract class PaymentProcessor{
    public abstract void processPayment(Payment payment);
}
// Class  PayPalProcessor 
class PayPalProcessor extends PaymentProcessor{
     public void processPayment(Payment payment){
         System.out.println("Processing PayPal Payment of $"+ payment.getAmount());
     }
}
// Class StripeProcessor
class stripeProcessor extends PaymentProcessor{
     public void processPayment(Payment payment){
         System.out.println("Processing stripe Payment of $"+ payment.getAmount());
     }
}
// Class CreditCardProcessor
class CreditCardProcessor extends PaymentProcessor{
     public void processPayment(Payment payment){
         System.out.println("Processing credit Payment of $"+ payment.getAmount());
     }
}
// Class bankTransferProcessor
class BankTransferProcessor extends PaymentProcessor{
     public void processPayment(Payment payment){
         System.out.println("Processing Bnak Payment of $"+ payment.getAmount());
     }
}
// Class PaymentGateway
class PaymentGateway{
    private PaymentProcessor paymentProcessor;
    public PaymentGateway(PaymentProcessor paymentProcessor){
        this.paymentProcessor = paymentProcessor;
    }
    public void processPayment(Payment payment) {
        paymentProcessor.processPayment(payment);
    }
}
// MAIN CLASS
public class PaymentProcessingSystem {
    public static void main(String[] args) {
        Payment payment1 = new Payment(100.0,"PayPal");
        Payment payment2 = new Payment(200.0,"Stripe");
        Payment payment3 = new Payment(300.0,"Bank Transfer");
        Payment payment4 = new Payment(400.0,"CreditCard");
        
        PaymentProcessor paypalProcessor = new PayPalProcessor();
        PaymentProcessor stripeProcessor = new stripeProcessor();
        PaymentProcessor bankTransferProcessor = new BankTransferProcessor();
        CreditCardProcessor creditCardProcessor = new CreditCardProcessor();
        
        PaymentGateway paypalGateway = new PaymentGateway(paypalProcessor);
        PaymentGateway stripeGateway = new PaymentGateway(stripeProcessor);
        PaymentGateway bankTransferGateway = new PaymentGateway(bankTransferProcessor);
        PaymentGateway creditCardGateway = new PaymentGateway(creditCardProcessor);
        
        paypalGateway.processPayment(payment1);
        stripeGateway.processPayment(payment2);
        bankTransferGateway.processPayment(payment3);
        creditCardGateway.processPayment(payment4);
    }
}
