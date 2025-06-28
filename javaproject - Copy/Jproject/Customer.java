package Jproject;

import java.util.ArrayList;

public class Customer {
    private String Customerid;
    private String name;
    private String email;
    private boolean isAdmin; 

    public  Customer(String name,String email,String Customerid){
        this.name=name;
        this.email=email;
        this.Customerid=Customerid;
        this.isAdmin=false;
    }
    public Customer(String name,String email,String Customerid, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.Customerid = Customerid;
        this.isAdmin = isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin=isAdmin;
    }
    public boolean isAdmin(){
        return isAdmin;
    }
    public void setCustomerid(String Customerid){
        this.Customerid=Customerid;
    }
    public String getCustomerid(){
        return Customerid;
    }

    public void setname(String name){
        this.name=name;
    }
    public String getname(){
        return name;
    }
    public void setemail(String email){
        this.email=email;
    }
    public String getemail(){
        return email;
    }
    private ArrayList<Order> orders = new ArrayList<>();

public void addOrder(Order o) {
    if(o != null) {
        orders.add(o);
    }
}

    public void display(){
        System.out.println("Customerid is :"+Customerid);
        System.out.println("Customer name:"+name);
        System.out.println("Customer email:"+email);
        System.out.println("Is Admin? " + isAdmin);
    }
    public String getInfoForGUI(){
        return name+"("+email+")"+(isAdmin?"[Admin]":"");
    }

    
}
