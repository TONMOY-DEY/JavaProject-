package Jproject;
import java.util.ArrayList;

public class Order {
    private String orderid;
    private  Customer customer ;
    private ArrayList<product>orderedProducts;

    public Order(String orderid,Customer customer){
        this.orderid=orderid;
        this.customer=customer;
         this.orderedProducts = new ArrayList<>();
    }

    public void addproduct(product p){
        if(p!=null){
            orderedProducts.add(p);

        }
            }
        
    
    public void removeproduct(product p) {
        orderedProducts.remove(p);
        
        }
    

    public void display(){
        System.out.println("order id:"+orderid);
        customer.display();
        System.out.println("Ordered Products:");
            for(product p:orderedProducts){
                p.display();
                System.out.println("........................");
            }
            System.out.println("Total:"+calculateTotal()+"Taka");
        }
    public double calculateTotal(){
        double total=0;
        for(product p:orderedProducts){
            total=total+p.getDiscountedPrice();
        }
        return total;
    }
    public String getOrderId(){
        return orderid;
    }
    public Customer getCustomer(){
        return customer;
    }
     public ArrayList<product>getOrderedProducts() {
        return orderedProducts;
    }
}