package Jproject;

public class Admin {
    private String name;
    private String email;
    private String password;

    public Admin(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password; 
        }
        public void setname(String  name){
            this.name=name;
        }
        public void setemail(String email){
            this.email=email;
        }
        public void setpassword(String password){
            this.password=password;
        }
        public String getname(){
            return name;
        }
        public String getemail(){
            return email;
        }
        public String getpassword(){
            return password;
        }

        public boolean authenticate(String email,String password) {
        return this.email.equals(email)&&this.password.equals(password);
    }
        
    }
    

