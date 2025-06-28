package GuiJproject;

import Jproject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.UUID;

public class MainPage extends JFrame {

    private Customer customer;
    private Catalog catalog;

    private JPanel categoryPanel;
    private JPanel brandPanel;
    private JPanel productPanel;
    private JPanel detailPanel;
    private JPanel orderPanel;
    private JTextField productIdField;

    private JLabel welcomeLabel;
    private JLabel productNameLabel, productPriceLabel, productDiscountLabel, productSellerLabel;
    private JLabel productMaterialLabel, productSizeLabel, productWarrantyLabel;

    private JButton orderButton;
    private JButton addProductButton, updateProductButton, deleteProductButton;

    private ArrayList<JButton> brandButtons = new ArrayList<>();
    private ArrayList<JButton> categoryButtons = new ArrayList<>();

    private product selectedProduct = null;
    private String selectedCategory = null;
    private String selectedBrand = null;

    public MainPage(Customer customer, Catalog catalog) {
        this.customer = customer;
        this.catalog = catalog;

        setTitle("Online Shopping Management System - Main Page");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        welcomeLabel = new JLabel("Welcome, " + customer.getname() + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(0, 100, 255));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(5, 5));
        leftPanel.setPreferredSize(new Dimension(250, 0));
        add(leftPanel, BorderLayout.WEST);

        categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(0, 1, 5, 5));
        categoryPanel.setBorder(BorderFactory.createTitledBorder("Categories"));
        leftPanel.add(categoryPanel, BorderLayout.NORTH);

        brandPanel = new JPanel();
        brandPanel.setLayout(new GridLayout(0, 1, 5, 5));
        brandPanel.setBorder(BorderFactory.createTitledBorder("Brands"));
        leftPanel.add(brandPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        add(centerPanel, BorderLayout.CENTER);

        productPanel = new JPanel();
        productPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        productPanel.setBorder(BorderFactory.createTitledBorder("Products"));
        centerPanel.add(new JScrollPane(productPanel));

        detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(7, 1, 5, 5));
        detailPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        centerPanel.add(detailPanel);

        productNameLabel = new JLabel("Name: ");
        productPriceLabel = new JLabel("Price: ");
        productDiscountLabel = new JLabel("Discount: ");
        productSellerLabel = new JLabel("Seller: ");
        productMaterialLabel = new JLabel("Material: ");
        productSizeLabel = new JLabel("Size: ");
        productWarrantyLabel = new JLabel("Warranty: ");

        detailPanel.add(productNameLabel);
        detailPanel.add(productPriceLabel);
        detailPanel.add(productDiscountLabel);
        detailPanel.add(productSellerLabel);
        detailPanel.add(productMaterialLabel);
        detailPanel.add(productSizeLabel);
        detailPanel.add(productWarrantyLabel);

        orderPanel = new JPanel();
        orderPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(orderPanel, BorderLayout.SOUTH);

        orderButton = new JButton("Order This Product");
        orderButton.setEnabled(false);
        orderButton.addActionListener(e -> placeOrder());
        orderPanel.add(orderButton);

        if (customer.isAdmin()) {
            addProductButton = new JButton("Add Product");
            updateProductButton = new JButton("Update Product");
            deleteProductButton = new JButton("Delete Product");

            addProductButton.addActionListener(e -> addProduct());
            updateProductButton.addActionListener(e -> updateProduct());
            deleteProductButton.addActionListener(e -> deleteProduct());

            orderPanel.add(addProductButton);
            orderPanel.add(updateProductButton);
            orderPanel.add(deleteProductButton);
        }

        initializeCategories();

        setVisible(true);
    }

    private void initializeCategories() {
        categoryPanel.removeAll();
        categoryButtons.clear();

        String[] categories = { "Clothing", "Electronics", "SimpleProduct" };

        for (String category : categories) {
            JButton catBtn = new JButton(category);
            catBtn.setFocusPainted(false);
            catBtn.setBackground(new Color(255, 255, 153));
            catBtn.setForeground(Color.BLACK);

            catBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    catBtn.setBackground(new Color(204, 204, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    catBtn.setBackground(new Color(255, 255, 153));
                }
            });

            catBtn.addActionListener(e -> {
                selectedCategory = category;
                showBrandsForCategory(category);
            });

            categoryPanel.add(catBtn);
            categoryButtons.add(catBtn);
        }

        categoryPanel.revalidate();
        categoryPanel.repaint();

        if (categories.length > 0) {
            selectedCategory = categories[0];
            showBrandsForCategory(selectedCategory);
        }
    }

    private void showBrandsForCategory(String category) {
        brandPanel.removeAll();
        brandButtons.clear();

        ArrayList<String> brands = new ArrayList<>();

        for (product p : catalog.getProducts()) {
            if (category.equalsIgnoreCase("Clothing") && p instanceof Clothing) {
                Clothing c = (Clothing) p;
                if (!brands.contains(c.getbrand()))
                    brands.add(c.getbrand());
            } else if (category.equalsIgnoreCase("Electronics") && p instanceof Electronics) {
                Electronics e = (Electronics) p;
                if (!brands.contains(e.getbrand()))
                    brands.add(e.getbrand());
            } else if (category.equalsIgnoreCase("SimpleProduct") && p instanceof SimpleProduct) {
                SimpleProduct sp = (SimpleProduct) p;
                if (!brands.contains(sp.getbrand()))
                    brands.add(sp.getbrand());
            }
        }

        if (brands.isEmpty()) {
            brandPanel.add(new JLabel("No brands found"));
        } else {
            for (String brand : brands) {
                JButton brandBtn = new JButton(brand);
                brandBtn.setFocusPainted(false);
                brandBtn.setBackground(new Color(204, 204, 255));
                brandBtn.setForeground(Color.BLACK);

                brandBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        brandBtn.setBackground(new Color(255, 255, 204));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        brandBtn.setBackground(new Color(204, 204, 255));
                    }
                });

                brandBtn.addActionListener(e -> {
                    selectedBrand = brand;
                    showProductsForBrand(category, brand);
                });

                brandPanel.add(brandBtn);
                brandButtons.add(brandBtn);
            }
        }

        brandPanel.revalidate();
        brandPanel.repaint();

        productPanel.removeAll();
        productPanel.revalidate();
        productPanel.repaint();

        clearProductDetails();
        orderButton.setEnabled(false);
    }

    private void showProductsForBrand(String category, String brand) {
        productPanel.removeAll();

        ArrayList<product> filteredProducts = new ArrayList<>();

        for (product p : catalog.getProducts()) {
            boolean matchCategory = false;

            if (category.equalsIgnoreCase("Clothing") && p instanceof Clothing) {
                Clothing c = (Clothing) p;
                if (c.getbrand().equalsIgnoreCase(brand))
                    matchCategory = true;
            } else if (category.equalsIgnoreCase("Electronics") && p instanceof Electronics) {
                Electronics e = (Electronics) p;
                if (e.getbrand().equalsIgnoreCase(brand))
                    matchCategory = true;
            } else if (category.equalsIgnoreCase("SimpleProduct") && p instanceof SimpleProduct) {
                SimpleProduct sp = (SimpleProduct) p;
                if (sp.getbrand().equalsIgnoreCase(brand))
                    matchCategory = true;
            }

            if (matchCategory) {
                filteredProducts.add(p);
            }
        }

        if (filteredProducts.isEmpty()) {
            productPanel.add(new JLabel("No products found for brand: " + brand));
        } else {
            for (product p : filteredProducts) {
                JButton prodBtn = new JButton(p.getName());
                prodBtn.setPreferredSize(new Dimension(150, 40));
                prodBtn.setFocusPainted(false);
                prodBtn.setBackground(new Color(51, 204, 204));
                prodBtn.setForeground(Color.BLACK);

                prodBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        prodBtn.setBackground(new Color(0, 204, 255));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        prodBtn.setBackground(new Color(204, 153, 255));
                    }
                });

                prodBtn.addActionListener(e -> {
                    selectedProduct = p;
                    displayProductDetails(p);
                    orderButton.setEnabled(true);
                });

                productPanel.add(prodBtn);
            }
        }

        productPanel.revalidate();
        productPanel.repaint();

        clearProductDetails();
        orderButton.setEnabled(false);
    }

    private void displayProductDetails(product p) {
        productNameLabel.setText("Name: " + p.getName());
        productPriceLabel.setText("Price: " + p.getprice() + "TAKA");
        productDiscountLabel.setText("Discount: " + p.getDiscountedPrice() + "%");
        productSellerLabel
                .setText("Seller: " + p.getseller().getsellername() + " (Rating: " + p.getseller().getrating() + ")");

        if (p instanceof Clothing) {
            Clothing c = (Clothing) p;
            productMaterialLabel.setText("Material: " + c.getmaterial());
            productSizeLabel.setText("Size: " + c.getsize());
            productWarrantyLabel.setText("Warranty: N/A");
        } else if (p instanceof Electronics) {
            Electronics e = (Electronics) p;
            productMaterialLabel.setText("Material: N/A");
            productSizeLabel.setText("Size: N/A");
            productWarrantyLabel.setText("Warranty: " + e.getwarranty() + " months");
        } else {
            productMaterialLabel.setText("Material: N/A");
            productSizeLabel.setText("Size: N/A");
            productWarrantyLabel.setText("Warranty: N/A");
        }
    }

    private void clearProductDetails() {
        productNameLabel.setText("Name: ");
        productPriceLabel.setText("Price: ");
        productDiscountLabel.setText("Discount: ");
        productSellerLabel.setText("Seller: ");
        productMaterialLabel.setText("Material: ");
        productSizeLabel.setText("Size: ");
        productWarrantyLabel.setText("Warranty: ");
        selectedProduct = null;
    }

    private void placeOrder() {
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this, "Please select a product to order.", "No Product Selected",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to order: " + selectedProduct.getName() + " for " + selectedProduct.getprice()
                        + "TAKA",
                "Confirm Order", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            String orderId = UUID.randomUUID().toString();

            String orderId1 = "ORD" + System.currentTimeMillis();
            Order newOrder = new Order(orderId, customer);
            customer.addOrder(newOrder);

            JOptionPane.showMessageDialog(this, "Order placed successfully!");
        }
    }

    // Admin Feature

    private void addProduct() {
        ProductFormDialog dialog = new ProductFormDialog(this, "Add New Product", null, catalog);
        dialog.setVisible(true);

        if (dialog.isSaved()) {
            product newProd = dialog.getProduct();
            if (newProd != null) {
                catalog.addproduct(newProd);
                JOptionPane.showMessageDialog(this, "Product added successfully!");
                refreshUIAfterAdminChange();
            } else {
                JOptionPane.showConfirmDialog(this, "Filed to create product. Please fill all required fields.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateProduct() {
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this, "Select a product first to update.", "No Product Selected",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        ProductFormDialog dialog = new ProductFormDialog(this, "Update Product", selectedProduct, catalog);
        dialog.setVisible(true);

        if (dialog.isSaved()) {
            product updatedprod = dialog.getProduct();
            if (updatedprod != null) {
                catalog.updateProduct(selectedProduct.getproductid(), updatedprod);
                JOptionPane.showMessageDialog(this, "Product updated successfully!");
                refreshUIAfterAdminChange();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteProduct() {
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this, "Select a product first to delete.", "No Product Selected",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete product: " + selectedProduct.getName() + "?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            catalog.removeproduct(selectedProduct);
            JOptionPane.showMessageDialog(this, "Product deleted successfully!");
            refreshUIAfterAdminChange();
        }
    }

    private void refreshUIAfterAdminChange() {
        if (selectedCategory != null) {
            showBrandsForCategory(selectedCategory);

            if (selectedBrand != null) {
                showProductsForBrand(selectedCategory, selectedBrand);
            }
        }

        clearProductDetails();
        orderButton.setEnabled(false);
    }

    class ProductFormDialog extends JDialog {
        private JTextField nameField, priceField, discountField, brandField, sellerNameField, ratingField;
        private JTextField materialField, sizeField, warrantyField;
        private JComboBox<String> categoryCombo;

        private JButton saveButton, cancelButton;

        private product editingProduct = null;
        private boolean saved = true;

        private Catalog catalog;

        public ProductFormDialog(JFrame parent, String title, product prodToEdit, Catalog catalog) {
            super(parent, title, true);
            this.catalog = catalog;
            this.editingProduct = prodToEdit;
            
            productIdField = new JTextField();

            setLayout(new GridBagLayout());
            setSize(400, 450);
            setLocationRelativeTo(parent);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            int y = 0;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Category:"), gbc);

            gbc.gridx = 1;
            String[] categories = { "Clothing", "Electronics", "SimpleProduct" };
            categoryCombo = new JComboBox<>(categories);
            add(categoryCombo, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Name:"), gbc);

            gbc.gridx = 1;
            nameField = new JTextField(15);
            add(nameField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Price:"), gbc);

            gbc.gridx = 1;
            priceField = new JTextField(15);
            add(priceField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Discount(%):"), gbc);

            gbc.gridx = 1;
            discountField = new JTextField(15);
            add(discountField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Brand:"), gbc);

            gbc.gridx = 1;
            brandField = new JTextField(15);
            add(brandField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Seller Name:"), gbc);

            gbc.gridx = 1;
            sellerNameField = new JTextField(15);
            add(sellerNameField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Seller Rating (0-5):"), gbc);

            gbc.gridx = 1;
            ratingField = new JTextField(15);
            add(ratingField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Material (Clothing):"), gbc);

            gbc.gridx = 1;
            materialField = new JTextField(15);
            add(materialField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Size (Clothing):"), gbc);

            gbc.gridx = 1;
            sizeField = new JTextField(15);
            add(sizeField, gbc);

            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            add(new JLabel("Warranty (Electronics, months):"), gbc);

            gbc.gridx = 1;
            warrantyField = new JTextField(15);
            add(warrantyField, gbc);

            y++;

            JPanel buttonPanel = new JPanel();
            saveButton = new JButton("Save");
            cancelButton = new JButton("Cancel");

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            gbc.gridx = 0;
            gbc.gridy = y;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(buttonPanel, gbc);

            saveButton.addActionListener(e -> onSave());
            cancelButton.addActionListener(e -> onCancel());
            categoryCombo.addActionListener(e -> updateFieldStates());

            if (editingProduct != null) {
                loadProductData(editingProduct);
            } else {
                updateFieldStates();
            }
        }

        private void updateFieldStates() {
            String cat = (String) categoryCombo.getSelectedItem();
            if ("Clothing".equalsIgnoreCase(cat)) {
                materialField.setEnabled(true);
                sizeField.setEnabled(true);
                warrantyField.setEnabled(false);
                warrantyField.setText("");
            } else if ("Electronics".equalsIgnoreCase(cat)) {
                materialField.setEnabled(false);
                sizeField.setEnabled(false);
                materialField.setText("");
                sizeField.setText("");
                warrantyField.setEnabled(true);
            } else {
                materialField.setEnabled(false);
                sizeField.setEnabled(false);
                warrantyField.setEnabled(false);
                materialField.setText("");
                sizeField.setText("");
                warrantyField.setText("");
            }
        }

        private void loadProductData(product p) {
            if (p instanceof Clothing) {
                categoryCombo.setSelectedItem("Clothing");
            } else if (p instanceof Electronics) {
                categoryCombo.setSelectedItem("Electronics");
            } else {
                categoryCombo.setSelectedItem("SimpleProduct");
            }

            nameField.setText(p.getName());
            priceField.setText(String.valueOf(p.getprice()));
            discountField.setText(String.valueOf(p.getDiscountedPrice()));

            String brand = "";
            if (p instanceof Clothing)
                brand = ((Clothing) p).getbrand();
            else if (p instanceof Electronics)
                brand = ((Electronics) p).getbrand();
            else if (p instanceof SimpleProduct)
                brand = ((SimpleProduct) p).getbrand();
            brandField.setText(brand);

            sellerNameField.setText(p.getseller().getsellername());
            ratingField.setText(String.valueOf(p.getseller().getrating()));

            if (p instanceof Clothing) {
                Clothing c = (Clothing) p;
                materialField.setText(c.getmaterial());
                sizeField.setText(c.getsize());
            } else if (p instanceof Electronics) {
                Electronics e = (Electronics) p;
                warrantyField.setText(String.valueOf(e.getwarranty()));
            }

            updateFieldStates();
        }

        private void onSave() {
            String cat = (String) categoryCombo.getSelectedItem();
            //This line is to check the potentiality of code.
            String productId = productIdField.getText().trim();
            String name = nameField.getText().trim();
            String priceStr = priceField.getText().trim();
            String discountStr = discountField.getText().trim();
            String brand = brandField.getText().trim();
            String sellerName = sellerNameField.getText().trim();
            String ratingStr = ratingField.getText().trim();
            String material = materialField.getText().trim();
            String size = sizeField.getText().trim();
            String warrantyStr = warrantyField.getText().trim();
            String[] fields = { name, priceStr, discountStr, brand, sellerName, ratingStr };
            for (String field : fields) {
                if (field.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            double price;
            double discount;
            double rating;
            int warranty = 0;

            try {
                price = Double.parseDouble(priceStr);
                discount = Double.parseDouble(discountStr);
                rating = Double.parseDouble(ratingStr);
                if ("Electronics".equalsIgnoreCase(cat)) {
                    warranty = Integer.parseInt(warrantyStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Price, Discount, Rating, and Warranty (if applicable) must be numeric.", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            if (editingProduct == null) {

                Seller seller = new Seller(sellerName, rating);
                product newProd = null;

                switch (cat.toLowerCase()) {
                    case "clothing":
                        if (material.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Material is required for Clothing products.",
                                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (size.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Size is required for Clothing products.",
                                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        newProd = new Clothing(productId, name, price, discount, brand, seller, material, size);

                        break;
                    case "electronics":
                        newProd = new Electronics(productId, name, price, discount, brand, seller, warranty);
                        break;
                    case "simpleproduct":
                        newProd = new SimpleProduct(productId, name, price, discount, brand, seller);
                        break;
                }

                if (newProd != null) {
                    catalog.addproduct(newProd);
                    saved = true;
                    dispose();
                }
            } else {
                editingProduct.setName(name);
                editingProduct.setprice(price);
                editingProduct.setdiscount(discount);
                editingProduct.setSeller(new Seller(sellerName, rating));
                if (editingProduct instanceof Clothing) {
                    if (material.isEmpty() || size.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Material and Size are required for Clothing products.",
                                "Validation Error", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    Clothing c = (Clothing) editingProduct;
                    c.setmaterial(material);
                    c.setsize(size);
                } else if (editingProduct instanceof Electronics) {
                    Electronics e = (Electronics) editingProduct;
                    e.setwarranty(warranty);
                }
                saved = true;
                dispose();
            }
        }

        private void onCancel() {
            saved = false;
            dispose();
        }

        public boolean isSaved() {
            return saved;
        }

        public product getProduct() {
            if (editingProduct != null)
                return editingProduct;
            return null;
        }
    }

}
