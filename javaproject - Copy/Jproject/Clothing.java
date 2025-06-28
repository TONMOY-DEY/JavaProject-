package Jproject;

public class Clothing extends product {
    private String size;
    private String material ;
    private double discountPercentage; 
    

    public Clothing(String productid,String name,double price,double discount,String brand,Seller seller,String material,String size){
       super(productid,name,price,seller,discount,brand);
        this.size=size;
        this.material=material;
        this.discountPercentage=discountPercentage;
        
    }
   
    
    public void setsize(String size){
        this.size=size;
    }
    public String getsize(){
        return size;
    }
    public void setmaterial(String material){
        this.material=material;

    }
    public String getmaterial(){
        return material;
    }
    public double getDiscountPercentage(){
        return discountPercentage;
    }
    public  void  setDiscountPercentage(double discountPercentage){
        this.discountPercentage=discountPercentage;
        
    }


    @Override
    public double getDiscountedPrice() {
        return getprice() * (1 - discountPercentage / 100);
    }
    public void display(){
        super.display();
        System.out.println("Size is:"+size);
        System.out.println("Material is:"+material);
        //System.out.println("Brand:"+brand);
    }
     
        public String getProductInfo() {
    return "ID:" + getproductid() + ", Name:" + getName() + ", Price:" + getprice() + ", Discounted:" + getDiscountedPrice() + ", Size:" + size + ", Material:" + material + ", Seller Rating:" + getseller().getrating();
}

    }


    

