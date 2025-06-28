package Jproject;

public class SimpleProduct extends product {
   // private String brand;

    public SimpleProduct(String productid, String name,double price,double discount,String brand,Seller seller) {
        super(productid,name,price,seller,discount,brand); 
        
    }

   
    
    @Override
    public double getDiscountedPrice() {
        return getprice() * 0.90;
    }

    @Override 
    public void display(){
        super.display();
        //System.out.println("Brand: " + brand);
    }

    public String getProductInfo() {
        return "ID:" + getproductid() + 
               ", Name:" + getName() +
               ", Price:" + getprice() +
               ", Discounted:" + getDiscountedPrice() +
               ", Seller Rating:" + getseller().getrating();
    }
}
