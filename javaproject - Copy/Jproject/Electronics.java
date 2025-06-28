package Jproject;

public class Electronics extends product{
    private int warranty;
   // private String brand;

    public Electronics(String productid,String name,double price,double discount,String brand,Seller seller,int warranty){
        super(productid,name,price,seller,discount,brand);
        this.warranty=warranty;
        //this.brand=brand;
    }
    
    
    
    public void setwarranty(int warranty){
        this.warranty=warranty;
    }
    public int getwarranty(){
        return warranty;
    }
    @Override
    public double getDiscountedPrice() {
        return getprice()*0.90; //10%
    }
     @Override
    public void display(){
        super.display();
        System.out.println(",Warranty:"+warranty+"months");
    }
    
    public String getWarrantyInfo() {
        return "Warranty:"+ warranty+"years";
    }
}





