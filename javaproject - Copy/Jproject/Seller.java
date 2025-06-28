package Jproject;

public class Seller {
    private String sellername;
    private double rating;

    public Seller(String sellername,double rating){
        this.sellername=sellername;
        this.rating=rating;

    }
    public void setsellername(String sellername){
        this.sellername=sellername;
    }
    public String getsellername(){
        return sellername;
    }
    public void setrating(double rating){
        this.rating=rating;
    }
    public double getrating(){
        return rating;
    }
    public void display(){
        System.out.println("sellername is:"+sellername);
        System.out.println("rating is:"+rating);
    }
    public String getSellerInfo(){
        return sellername +"(Rating:"+rating+")";
    }


    
}
