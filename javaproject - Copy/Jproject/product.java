package Jproject;

public abstract class product {
    private String productid;
    private String name;
    private double price;
    private Seller seller;
    private double discount;
    private String brand;

    public product(String productid, String name, double price, Seller seller,double discount,String brand) {
        this.productid = productid;
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.discount=discount;
        this.brand=brand;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
    public String getbrand(){
        return brand;
    }
    public void setdiscount(double discount){
        this.discount=discount;
    }
    public double getdiscount(){
        return discount;
    }

    

    public void setproductid(String productid) {
        this.productid = productid;
    }

    public String getproductid() {
        return productid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public double getprice() {
        return price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Seller getseller() {
        return seller;
    }

    
    public abstract double getDiscountedPrice();

    public void display() {
        System.out.println("ProductID:"+productid);
        System.out.println("Name:"+name);
        System.out.println("Price:"+price);
        System.out.println("Discounted Price:"+getDiscountedPrice());
        if (seller != null) {
            seller.display();
        }
    }

    @Override
    public String toString() {
        return "ProductID: "+ productid +",Name: "+ name+",Price:"+ price +",Discounted Price:"+ getDiscountedPrice();

    }
}
