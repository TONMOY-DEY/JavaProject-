package Jproject;

import java.util.ArrayList;

public class Catalog {
    private product products[];

    public Catalog(int size) {
        this.products = new product[size];

    }

    public product searchById(String id) {
        for (product p : products) {
            if (p != null && p.getproductid().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void addproduct(product a) {
        for (int i = 0; i < this.products.length; i++) {
            if (products[i] == null) {
                products[i] = a;
                break;
            }
        }
    }

    public void removeproduct(product a) {
        for (int i = 0; i < this.products.length; i++) {
            if (products[i] == a) {
                products[i] = null;
                break;
            }
        }
    }
    public  void updateProduct(String id,product updatedproduct){
        for(int i=0;i<products.length;i++){
            if(products[i]!=null && products[i].getproductid().equals(id)){
                products[i]=updatedproduct;
                break;
            }
        }
    }

    public void display() {
        for (product px : products) {
            if (px != null) {
                px.display();
                System.out.println(".............................");
            }
        }
    }

    public product[] getAllProducts() {
        return this.products;
    }

    public ArrayList<product> getProducts() {
        ArrayList<product> list = new ArrayList<>();
        for (product p : products) {
            if (p != null) {
                list.add(p);
            }
        }
        return list;
    }

    public ArrayList<product> getProductsByCategory(Class<?> cls) {
        ArrayList<product> filtered = new ArrayList<>();
        for (product p : products) {
            if (p != null && cls.isInstance(p)) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    public ArrayList<product> getProductsByBrand(String brandName) {
        ArrayList<product> filtered = new ArrayList<>();
        for (product p : products) {
            if (p != null && p.getbrand().equalsIgnoreCase(brandName)) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    public ArrayList<product> getProductsByCategoryAndBrand(Class<?> cls, String brandName) {
        ArrayList<product> filtered = new ArrayList<>();
        for (product p : products) {
            if (p != null && cls.isInstance(p) && p.getbrand().equalsIgnoreCase(brandName)) {
                filtered.add(p);
            }
        }
        return filtered;
    }
}
