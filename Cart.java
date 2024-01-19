import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Cart {
    public static int cartSum=0;//to store total of cart
    static boolean confirmed=false;//to record whether user confirmed or deleted items or not

    Cart(int noChairsBuying, int noTablesBuying, int noCouchesBuying, int noBedsBuying, ArrayList<Furniture> furnitures)
    {
        cartSum=0;
        if (noChairsBuying>0)//calculate total price of products
        {
            cartSum=cartSum+noChairsBuying*furnitures.get(0).price;
        }
        if (noTablesBuying>0)
        {
            cartSum=cartSum+noTablesBuying*furnitures.get(1).price;
        }
        if (noCouchesBuying>0)
        {
            cartSum=cartSum+noCouchesBuying*furnitures.get(2).price;
        }
        if (noBedsBuying>0)
        {
            cartSum=cartSum+noBedsBuying*furnitures.get(3).price;
        }
    }

    public static void updateProductsCSV(ArrayList<Furniture> furnitures) throws IOException {

       File prdsFile= new File("ProductsFile.csv");
       if (prdsFile!=null)//delete contents of products file
       {
           try {
               BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(prdsFile, false));
               PrintWriter fileWriter = new PrintWriter(bufferedWriter);

               fileWriter.println("");
               fileWriter.close();
           }catch (Exception e)
           {
                e.printStackTrace();
           }
       }
        try {//add products and updated stocks to file from array
            BufferedWriter writer= new BufferedWriter(new FileWriter("ProductsFile.csv"));
            for (int i=0; i<furnitures.size(); i++)
            {
                writer.write(furnitures.get(i).title);
                writer.write(",");
                writer.write(Integer.toString(furnitures.get(i).stock));
                writer.write(",");
                writer.write(Integer.toString(furnitures.get(i).price));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void displayCart(int noChairsBuying, int noTablesBuying, int noCouchesBuying, int noBedsBuying, ArrayList<Furniture> furnitures)
    {
        JFrame frame= new JFrame("Cart");//frame called buy furniture
        frame.setSize(1250, 700);//chosen size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menu= new JPanel();//top part of frame
        menu.setBackground(Color.BLACK);
        menu.setPreferredSize(new Dimension(1200, 50));
        JLabel iFurnexLabel= new JLabel("iFurnex Cart");
        iFurnexLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        iFurnexLabel.setBounds(350, 40, 100, 100);
        menu.add(iFurnexLabel);
        iFurnexLabel.setForeground(Color.WHITE);
        frame.setLayout(new FlowLayout());
        frame.add(menu);

        JPanel main= new JPanel();//main part of page that has products
        main.setLayout(new GridLayout(2, 2, 40, 20));//have a layout of 2 rows and 4 cols in main
        frame.add(main);

        if (noChairsBuying>0)//if product is bought, display it as well as its details like quantity*price
        {
            JPanel chairContainer= new JPanel();
            chairContainer.setLayout(new FlowLayout());
            chairContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            JLabel chairLabel= new JLabel("Chair");
            chairContainer.add(chairLabel);
            JLabel chairBought= new JLabel("Chosen quantity " + String.valueOf(noChairsBuying));
            chairContainer.add(chairBought);
            ImageIcon chairImage= new ImageIcon("chair.jpeg");
            JLabel displayChair= new JLabel();
            Image newImg= chairImage.getImage();
            newImg= newImg.getScaledInstance(211, 200, Image.SCALE_SMOOTH);
            chairImage= new ImageIcon(newImg);
            displayChair.setIcon(chairImage);
            chairContainer.add(displayChair);
            JLabel chairPrice= new JLabel("Price: " + String.valueOf(furnitures.get(0).price*noChairsBuying) + "EGP");//calculate quanity*price
            chairContainer.add(chairPrice);
            main.add(chairContainer);
        }
        if (noTablesBuying>0)//same is repeated for all products
        {
            JPanel tableContainer= new JPanel();
            tableContainer.setLayout(new FlowLayout());
            tableContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            JLabel tableLabel= new JLabel("Table");
            tableContainer.add(tableLabel);
            JLabel tableBought= new JLabel("Chosen quantity " + String.valueOf(noTablesBuying));
            tableContainer.add(tableBought);
            ImageIcon tableImage= new ImageIcon("table.jpeg");
            JLabel displayTable= new JLabel();
            Image newImgT= tableImage.getImage();
            newImgT= newImgT.getScaledInstance(211, 200, Image.SCALE_SMOOTH);
            tableImage= new ImageIcon(newImgT);
            displayTable.setIcon(tableImage);
            tableContainer.add(displayTable);
            JLabel tablePrice= new JLabel("Price: " + String.valueOf(furnitures.get(1).price*noTablesBuying) + "EGP");
            tableContainer.add(tablePrice);
            main.add(tableContainer);
        }
        if (noCouchesBuying>0)
        {
            JPanel couchContainer= new JPanel();
            couchContainer.setLayout(new FlowLayout());
            couchContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            JLabel couchLabel= new JLabel("Couch");
            couchContainer.add(couchLabel);
            JLabel couchBought= new JLabel("Chosen quantity " + String.valueOf(noCouchesBuying));
            couchContainer.add(couchBought);
            ImageIcon couchImage= new ImageIcon("couch.jpeg");
            JLabel displayCouch= new JLabel();
            Image newImgC= couchImage.getImage();
            newImgC= newImgC.getScaledInstance(211, 200, Image.SCALE_SMOOTH);
            couchImage= new ImageIcon(newImgC);
            displayCouch.setIcon(couchImage);
            couchContainer.add(displayCouch);
            JLabel couchPrice= new JLabel("Price: " + String.valueOf(furnitures.get(2).price*noCouchesBuying) + "EGP");
            couchContainer.add(couchPrice);
            main.add(couchContainer);
        }
        if (noBedsBuying>0)
        {
            JPanel bedContainer= new JPanel();
            bedContainer.setLayout(new FlowLayout());
            bedContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            JLabel bedLabel= new JLabel("Bed");
            bedContainer.add(bedLabel);
            JLabel bedStock= new JLabel("Chosen quantity " + String.valueOf(noBedsBuying));
            bedContainer.add(bedStock);
            ImageIcon bedImage= new ImageIcon("bed.jpeg");
            JLabel displayBed= new JLabel();
            Image newImgB= bedImage.getImage();
            newImgB= newImgB.getScaledInstance(211, 200, Image.SCALE_SMOOTH);
            bedImage= new ImageIcon(newImgB);
            displayBed.setIcon(bedImage);
            bedContainer.add(displayBed);
            JLabel bedPrice= new JLabel("Price: " + String.valueOf(furnitures.get(3).price*noBedsBuying) + "EGP");
            bedContainer.add(bedPrice);
            main.add(bedContainer);
        }

        JPanel endOfPage= new JPanel();
        frame.add(endOfPage);
        endOfPage.setLayout(new GridLayout(2,3));
        JLabel totalPrice= new JLabel("Total Price: "+ cartSum + "EGP");//display total
        endOfPage.add(totalPrice);
        JButton confirmSale= new JButton("Confirm");
        endOfPage.add(confirmSale);
        confirmSale.addActionListener(new ActionListener() {//if sale is confirmed

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateProductsCSV(furnitures);//update products file
                    confirmed=true;//set confirmed to true
                } catch (IOException ex) {
                    System.err.println("Exception");
                }
            }
        });

        JButton notConfirmed= new JButton("Delete and start again");
        endOfPage.add(notConfirmed);
        notConfirmed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//if sale is not confirmed
                frame.setVisible(false);//close cart frame
                try {
                    furnitures.get(0).stock= furnitures.get(0).stock+ noChairsBuying;//return displayed stocks to their original values
                    furnitures.get(1).stock= furnitures.get(1).stock+ noTablesBuying;
                    furnitures.get(2).stock= furnitures.get(2).stock+ noCouchesBuying;
                    furnitures.get(3).stock= furnitures.get(3).stock+ noBedsBuying;
                    updateProductsCSV(furnitures);//update products file
                    Furniture.readProductsCSV();//read products file
                    confirmed=true;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Furniture.displayProducts();//go to furniture page
            }
        });
        confirmSale.setForeground(Color.BLACK);
        notConfirmed.setForeground(Color.BLACK);
        JLabel note= new JLabel("Confirm or delete");

        JLabel moreSales= new JLabel("Are there more sales to create?");
        endOfPage.add(moreSales);
        JButton createMoreSales= new JButton("Yes");
        JButton noMoreSales= new JButton("No");
        endOfPage.add(createMoreSales);
        endOfPage.add(noMoreSales);
        createMoreSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!confirmed){//if user tried to create another sale while current one is not confirmed
                    System.out.println("confirm or delete");
                    main.add(note);
                    confirmSale.setForeground(Color.RED);//set confirm and delete buttons' font to red to bring attention to it
                    notConfirmed.setForeground(Color.RED);
                }
                else {//if confirmed and want to make more sales
                    confirmSale.setForeground(Color.BLACK);
                    notConfirmed.setForeground(Color.BLACK);
                    main.remove(note);
                    frame.setVisible(false);
                    try {
                        Furniture.readProductsCSV();//read products file
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Furniture.displayProducts();//go to furniture page
                }
            }
        });
        noMoreSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!confirmed){//if user tried to create another sale while current one is not confirmed
                    System.out.println("confirm or delete");
                    main.add(note);
                    confirmSale.setForeground(Color.RED);//set confirm and delete buttons' font to red to bring attention to it
                    notConfirmed.setForeground(Color.RED);
                }
                else {//if confirmed and no more sales, close frame
                    confirmSale.setForeground(Color.BLACK);
                    notConfirmed.setForeground(Color.BLACK);
                    main.remove(note);
                    frame.setVisible(false);
                }
            }
        });
        confirmed=false;

        frame.setVisible(true);
    }

    public static void main(String[] args)
    {

        displayCart(Furniture.noChairsBuying, Furniture.noTablesBuying, Furniture.noCouchesBuying, Furniture.noBedsBuying,  Furniture.furnitures);
    }
}
