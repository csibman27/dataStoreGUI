package list;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;



public class EShopModel {

	private ArrayList <Product> stock;
	private static int stockId = 0;
	
	public EShopModel() {
		
		stock = new ArrayList<Product>();
	}
	
	public void addProduct(String name, String description, double weight, double cost, String cat, String subCat ) {
		
		Product product = new Product(name,description,weight,cost,cat,subCat);
		stock.add(product);
	}
	
	public String listProducts() {
		
		int i = 0;
		String displayProducts = "All Products";
		for (Product prod:stock) {
			
			displayProducts += "\n"+ (i++) + " : " + prod;
		}
		return displayProducts;
	}
	
	public String listCatProducts(String cat) {
		
		int i =0;
		String displayProducts = "All Products";
		for (Product prod:stock) {
			if (prod.getCategory().equals(cat)) {
				displayProducts += "\n"+ (i++) + " : " + prod;
			}
		}
		return displayProducts;
	}
	
	public String listCatAndSubCatProducts(String cat, String subCat) {
		int i = 0;
		String displayProducts;
		displayProducts = " " + cat + "-------" + subCat;
		for (Product prod:stock) {
			if (prod.getCategory().equals(cat) && prod.getSubCategory().equals(subCat)) {
				displayProducts += "\n"+ prod.getId() + " : " + prod.getName();
			}
			else if (cat.equals("All")) {
				displayProducts += "\n" + prod.getId() + " : " + prod.getName();
			}
		}
		return displayProducts;
	}
	
	public void deleteProducts(int index) {
		
		stock.remove(index);
	}
	
	public String getTitle() {
		
		return " Model 1";
	}
	
	 public Product getProdDetails(String id){

    	 int id1 = (Integer.parseInt(id));  //Test only

    	   

    	   for (Product prod:stock){

            	if (prod.getId()==id1){

            		return prod;

            	}

    	   }

    	   return null;

       }
     public void saveProducts() throws Exception

     {

     XStream xstream = new XStream(new DomDriver());

     ObjectOutputStream out = xstream.createObjectOutputStream

     (new FileWriter("products.xml"));

     out.writeObject(stock);

     out.close();

     }



     /**

     * load() - This method reads the contents of the XML file called 

     * products.xml stored in the project directory.  The file should contain two components -

     *    an ArrayList of Product, and

     *    an ArrayList of BargainBundle.

     * The contents are casted as an ArrayList of Product and stored in the stock variable. 

     * The remaining contents are casted as an ArrayList of BargainBundle and stored in the bargains variable.

     * */

     public void loadProducts() throws Exception

     {

     XStream xstream = new XStream(new DomDriver());

     ObjectInputStream is = xstream.createObjectInputStream

     (new FileReader("products.xml"));

     stock = (ArrayList<Product>) is.readObject();

     is.close();

     }


}