import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Furniture implements ActionListener{
    public String title; //to store furniture title
    public int stock; //to store furniture stock
    public int price; //to store furniture price
    static ArrayList<Furniture> furnitures= new ArrayList<Furniture>(); //array containing the 4 products
    public static int noChairsBuying=0;//these 4 variables store amount of each product that will be bought
    public static int noTablesBuying=0;
    public static int noCouchesBuying=0;
    public static int noBedsBuying=0;
    Furniture(String title, int stock, int price) //constructor to set attributes to the ones given
    {
        this.title=title;
        this.stock=stock;
        this.price=price;
    }



    public static void readProductsCSV  () throws IOException {//function to read product details from csv file
        String path = "ProductsFile.csv";

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        String line = "";

        while ((line = br.readLine()) != null)//while loop to go through all the lines of the file
        {
            String [] values = line.split(",");//splitting the words in each line by commas to store in array
            if (values[1].equals("stock"))//ignore the first line containing headings
                continue;
            else//create a new furniture object for every line in the file
            {
                if (furnitures.size()<4)
                {
                    Furniture product = new Furniture(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                    furnitures.add(product); //add product to furnitures array
                }
            }
        }
        fr.close();
    }
    public static void displayProducts()//function for handling display and buying of products
    {
        noChairsBuying=0;
        noTablesBuying=0;
        noCouchesBuying=0;
        noBedsBuying=0;
        JFrame frame= new JFrame("Buy Furniture");//frame called buy furniture
        frame.setSize(1250, 700);//chosen size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menu= new JPanel();//top part of frame to have black background and display store name
        menu.setBackground(Color.BLACK);
        menu.setPreferredSize(new Dimension(1200, 50));
        JLabel iFurnexLabel= new JLabel("iFurnex");
        iFurnexLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        iFurnexLabel.setBounds(350, 40, 100, 100);
        menu.add(iFurnexLabel);
        iFurnexLabel.setForeground(Color.WHITE);
        frame.setLayout(new FlowLayout());
        frame.add(menu);

        JPanel main= new JPanel();//main part of page that has products
        main.setLayout(new GridLayout(2, 2, 40, 20));//have a layout of 2 rows and 4 cols in main
        frame.add(main);

        JPanel chairContainer= new JPanel();//container for chair
        chairContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));//border
        chairContainer.setLayout(new FlowLayout());
        JLabel chairLabel= new JLabel("Chair");//chair label
        chairLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        chairContainer.add(chairLabel);
        JLabel chairStock= new JLabel("Available stock: " + String.valueOf(furnitures.get(0).stock));//chair stock
        chairContainer.add(chairStock);
        JButton buyChair= new JButton("Add to cart");//add to cart button for chair
        ImageIcon chairImage= new ImageIcon("chair.jpeg");//chair image
        JLabel displayChair= new JLabel();
        Image newImg= chairImage.getImage();
        newImg= newImg.getScaledInstance(211, 200, Image.SCALE_SMOOTH);//fix image size
        chairImage= new ImageIcon(newImg);
        displayChair.setIcon(chairImage);//display image
        chairContainer.add(displayChair);
        JLabel chairPrice= new JLabel("Price: " + String.valueOf(furnitures.get(0).price) + "EGP");//display chair price
        chairContainer.add(chairPrice);
        chairContainer.add(buyChair);
        main.add(chairContainer);
        buyChair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//if clicked on add to cart
                if (furnitures.get(0).stock>0)//if there is stock
                {
                    furnitures.get(0).stock--;//decrement stock in furniture array
                    chairStock.setText("");
                    chairStock.setText("Available stock: " +String.valueOf(furnitures.get(0).stock));//change displayed stock
                    noChairsBuying++;//increment variable storing number of chairs being bought
                }
                else//if no more stock display that
                {
                    chairStock.setText("");
                    chairStock.setText("No more stock");
                }
            }
        });

        JPanel tableContainer= new JPanel();//same comments for each of the products
        tableContainer.setLayout(new FlowLayout());
        tableContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JLabel tableLabel= new JLabel("Table");
        tableLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tableContainer.add(tableLabel);
        JLabel tableStock= new JLabel("Available stock: " + String.valueOf(furnitures.get(1).stock));
        tableContainer.add(tableStock);
        JButton buyTable= new JButton("Add to cart");
        ImageIcon tableImage= new ImageIcon("table.jpeg");
        JLabel displayTable= new JLabel();
        Image newImgT= tableImage.getImage();
        newImgT= newImgT.getScaledInstance(211, 200, Image.SCALE_SMOOTH);
        tableImage= new ImageIcon(newImgT);
        displayTable.setIcon(tableImage);
        tableContainer.add(displayTable);
        JLabel tablePrice= new JLabel("Price: " + String.valueOf(furnitures.get(1).price) + "EGP");
        tableContainer.add(tablePrice);
        tableContainer.add(buyTable);
        main.add(tableContainer);
        buyTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (furnitures.get(1).stock>0)
                {
                    furnitures.get(1).stock--;
                    tableStock.setText("");
                    tableStock.setText("Available stock: " +String.valueOf(furnitures.get(1).stock));
                    noTablesBuying++;
                }
                else
                {
                    tableStock.setText("");
                    tableStock.setText("No more stock");
                }
            }
        });

        JPanel couchContainer= new JPanel();
        couchContainer.setLayout(new FlowLayout());
        couchContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JLabel couchLabel= new JLabel("Couch");
        couchLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        couchContainer.add(couchLabel);
        JLabel couchStock= new JLabel("Available stock: " + String.valueOf(furnitures.get(2).stock));
        couchContainer.add(couchStock);
        JButton buyCouch= new JButton("Add to cart");
        ImageIcon couchImage= new ImageIcon("couch.jpeg");
        JLabel displayCouch= new JLabel();
        Image newImgC= couchImage.getImage();
        newImgC= newImgC.getScaledInstance(211, 200, Image.SCALE_SMOOTH);
        couchImage= new ImageIcon(newImgC);
        displayCouch.setIcon(couchImage);
        couchContainer.add(displayCouch);
        JLabel couchPrice= new JLabel("Price: " + String.valueOf(furnitures.get(2).price) + "EGP");
        couchContainer.add(couchPrice);
        couchContainer.add(buyCouch);
        main.add(couchContainer);
        buyCouch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (furnitures.get(2).stock>0)
                {
                    furnitures.get(2).stock--;
                    couchStock.setText("");
                    couchStock.setText("Available stock: " +String.valueOf(furnitures.get(2).stock));
                    noCouchesBuying++;
                }
                else
                {
                    couchStock.setText("");
                    couchStock.setText("No more stock");
                }
            }
        });

        JPanel bedContainer= new JPanel();
        bedContainer.setLayout(new FlowLayout());
        bedContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JLabel bedLabel= new JLabel("Bed");
        bedLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        bedContainer.add(bedLabel);
        JLabel bedStock= new JLabel("Available stock: " + String.valueOf(furnitures.get(3).stock));
        bedContainer.add(bedStock);
        JButton buyBed= new JButton("Add to cart");
        ImageIcon bedImage= new ImageIcon("bed.jpeg");
        JLabel displayBed= new JLabel();
        Image newImgB= bedImage.getImage();
        newImgB= newImgB.getScaledInstance(211, 200, Image.SCALE_SMOOTH);
        bedImage= new ImageIcon(newImgB);
        displayBed.setIcon(bedImage);
        bedContainer.add(displayBed);
        JLabel bedPrice= new JLabel("Price: " + String.valueOf(furnitures.get(3).price) + "EGP");
        bedContainer.add(bedPrice);
        bedContainer.add(buyBed);
        main.add(bedContainer);
        buyBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (furnitures.get(3).stock>0)
                {
                    furnitures.get(3).stock--;
                    bedStock.setText("");
                    bedStock.setText("Available stock: " +String.valueOf(furnitures.get(3).stock));
                    noBedsBuying++;
                }
                else
                {
                    bedStock.setText("");
                    bedStock.setText("No more stock");
                }
            }
        });

        JButton goToCart= new JButton("Go to cart");//button to go to cart
        frame.add(goToCart);
        goToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//if clicked, set frame to not visible and call cart
                frame.setVisible(false);
                Cart cart= new Cart(noChairsBuying, noTablesBuying, noCouchesBuying, noBedsBuying, furnitures);
                cart.displayCart(noChairsBuying, noTablesBuying, noCouchesBuying, noBedsBuying, furnitures);
            }
        });

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) throws IOException {
        readProductsCSV();
        displayProducts();
    }
}
