package GuiJproject;
import Jproject.Catalog;
import Jproject.Clothing;
import Jproject.Customer;
import Jproject.Electronics;
import Jproject.Seller;
import Jproject.SimpleProduct;
import Jproject.product;
import GuiJproject.MainPage;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import GuiJproject.MainPage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Jproject.Customerinfo.Filemanager; 



public class Loginpage extends JFrame implements ActionListener {
    JPanel panel;
    JLabel namelabel;
    JLabel emaiLabel,idJLabel,titLabel;
    JTextField nameTF,emailTF,idTF;
    JButton loginButton,exitButton;
    Image bgImage;

    public Loginpage(){
        super(" Online Shopping Managment System");
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //getContentPane().setBackground(Color.BLACK);  


        ImageIcon bgImage = new ImageIcon(getClass().getResource("logo6.png"));
        JLabel background = new JLabel(bgImage);
        background.setBounds(0,0,1923,1500);
        background.setLayout(null);
        add(background);
       

        
       
        panel =new JPanel();
        panel.setLayout(null);
        panel.setBounds(70,50,450,280);
        panel.setBackground(new Color(225,225,255,180));
        panel.setOpaque(false);
        background.add(panel);



        titLabel=new JLabel("Customer Login && Admin Login");
        titLabel.setBounds(20,30,400,30);
        titLabel.setFont(new Font("Arial",Font.ITALIC,26));
        titLabel.setForeground(new Color(0,0,128) );
        panel.add(titLabel);


        namelabel = new JLabel("Name:");
        namelabel.setBounds(100,80,200,25);
        namelabel.setFont(new Font("Arial",Font.BOLD,15));
        namelabel.setForeground(new Color(0,0,0));
        panel.add(namelabel);


        nameTF=new JTextField();
        nameTF.setBounds(200,80,200,25);
        nameTF.setBackground(new Color(255,255,204));
        nameTF.setForeground(Color.BLACK);
        panel.add(nameTF);

        emaiLabel = new JLabel("Email:");
        emaiLabel.setBounds(100, 120, 100, 25);
        emaiLabel.setFont(new Font("Arial", Font.BOLD, 15));
        emaiLabel.setForeground(new Color(0,0,0)); 
        panel.add(emaiLabel);;

        emailTF = new JTextField();
        emailTF.setBounds(200, 120, 200, 25);
        emailTF.setBackground(new Color(255,255,204));
        panel.add(emailTF);



        idJLabel=new JLabel("Password:");
        idJLabel.setBounds(100,160,100,25);
        idJLabel.setFont(new Font("Arial",Font.BOLD,15));
        idJLabel.setForeground(new Color(0,0,0));
        panel.add(idJLabel);

        idTF=new JTextField();
        idTF.setBounds(200,160,200,25);
        idTF.setBackground(new Color(255,255,204));
        idTF.setForeground(Color.BLACK);
        panel.add(idTF);

        loginButton=new JButton("Login");
        loginButton.setBounds(150,220,100,30);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        panel.add(loginButton);


        exitButton=new JButton("Exit");
        exitButton.setBounds(270,220,100,30);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(this);
        panel.add(exitButton);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==loginButton){
        String name=nameTF.getText().trim();
        String email=emailTF.getText().trim();
        String id=idTF.getText().trim();
        if(!name.isEmpty()&&!email.isEmpty()&&!id.isEmpty()){
            boolean isAdmin=email.equals("tonmoydey2025@gmail.com")&&id.equals("admin123");
            Customer customer=new Customer(name,email,id,isAdmin);
            customer.display();

            Filemanager.saveCustomer(name,email,id);

            Seller seller=new Seller("BestSeller",5);
            Seller seller0=new Seller("TechStore",4.5);
            Seller seller1=new Seller("OtherStore",4.0);
            Seller seller2=new Seller("ClothingStore",4.2);
            Seller seller3=new Seller("Infinity Mega Mall",5.0);

            Catalog catalog=new Catalog(10);
            catalog.addproduct(new Clothing("P004","Jeans",1500,0.06,"Easy",seller3,"Coton","M"));
            catalog.addproduct(new Clothing("P006","Hoodie",1200,0.05,"Richman",seller3,"Coton","M"));
            catalog.addproduct(new Electronics("P005","AC",70000.0,0.005,"Gree",seller3,2));
            catalog.addproduct(new Electronics("P007","Asus VivoBook X515",75000,0.005,"Asus",seller0,2));
            catalog.addproduct(new SimpleProduct("P001","Laptop",60000,0.10,"Asus",seller0));
            catalog.addproduct(new SimpleProduct("p002","Smartphone",25000,0.15,"Samsung",seller1));
            catalog.addproduct(new SimpleProduct("P003","T-Shirt",800.0,0.05,"Arong",seller2));

            new MainPage(customer, catalog);
            this.setVisible(false);

            JOptionPane.showMessageDialog(this,"Loginn Successfull! Thank you for logging in,"+customer.getname());

        }
        else{
            JOptionPane.showMessageDialog(this,"please fill all fields");
        }
    }
    else if(ae.getSource()==exitButton){
        System.exit(0);
    }
   }
}
