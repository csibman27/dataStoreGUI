package list;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.border.*;



/**

 * A graphical user interface for the EShop.  This class is responsible just for putting up the display on 

 * screen. It then refers to the "EShopModel" to do all the real work. 

 * The Update and Delete buttons are not implemented.

 * More checking of data should be done.

 * 

 * Each time the program starts the productId is reset to 0. This is an issue when you save and load products.

 * A simple solution would be to save the last used index in the file when data is saved.

 * 

 * The load and save methods use the xstream library as described in a previous lab. 

 * 

 * @author Joe Daly

 * @version Oct 2017

 */



public class ECommGUI 

{

	private EShopModel shop;

    private JFrame frame;

    JComboBox selectCategory;

    JComboBox selectSubCategory;

    private JTextArea displayProductList;

	private JTextField id, name, description, weight, cost;



	private String[] categories= { "Male", "Female", "Boy", "Girl", "All" };

	private String[] subCategories =  {"Fashion", "Sport", "Leisure", "Work", "Safety", "All"};

	private String category;

	private String subCategory;

	private JTextField feedback;



	/**

	 * Create a user interface for a given shopEngine.

	 */



	public ECommGUI(EShopModel engine)

	{

		shop = engine;

		category ="All";

		subCategory="All";

		

		displayProductList= new JTextArea();

		displayProductList.setPreferredSize( new Dimension( 270, 200) );



		id= new JTextField("id");

		name= new JTextField();

		description= new JTextField("description");

		weight= new JTextField();

		cost= new JTextField();



		displayProductList.setText("Display Products");

		feedback = new JTextField("User Feedback"); 

		feedback.setSize(new Dimension(200,40));

		makeFrame();



		frame.setVisible(true);

	}





	/**

	 * Make this interface visible again. (Has no effect if it is already

	 * visible.)

	 */

    public void setVisible(boolean visible)

	{

		frame.setVisible(visible);

	}



	/**

	 * Make the frame for the user interface.

	 */

	private void makeFrame()

	{

		frame = new JFrame(shop.getTitle());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = (JPanel)frame.getContentPane();

		contentPane.setLayout(new BorderLayout(8, 8));

		contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));





		contentPane.add(makeTopPanel(), BorderLayout.NORTH); // Calling method to return top Panel



		contentPane.add(makeLeftPanel(), BorderLayout.WEST);// Calling method to return left Panel



		contentPane.add(makeRightPanel(), BorderLayout.EAST);// Calling method to return right Panel



		contentPane.add(feedback, BorderLayout.SOUTH);



		frame.pack();



	} //end makePanel

	

	

	/**Left Panel 

	 * 

	 * This method just has the field displayProductList which is a JTextArea 

	 */

	private JPanel makeLeftPanel()

	{

			JPanel leftPanel = new JPanel(new FlowLayout());

			leftPanel.add(displayProductList);

			return leftPanel;

	}//end makeLeftPanel

	

	private JPanel makeRightPanel()

	{

		//Right Panel  -- 10 rows of 2 columns. 6 rows used for displaying product information. 

		//Other rows for the buttons Create, Read, Load and Save. I didn't implement Update or delete yet.



				JPanel rightPanel = new JPanel(new GridLayout(10, 2));

				rightPanel.add(new JLabel("Product Details"));

				rightPanel.add(new JLabel(""));

				rightPanel.add(new JLabel("============"));

				rightPanel.add(new JLabel("============"));

				rightPanel.add(new JLabel("id"));

				rightPanel.add(id);

				rightPanel.add(new JLabel("Name"));

				rightPanel.add(name);

				rightPanel.add(new JLabel("Description"));

				rightPanel.add(description);

				rightPanel.add(new JLabel("Weight"));

				rightPanel.add(weight);

				rightPanel.add(new JLabel("Cost"));

				rightPanel.add(cost);

				

				//Create button calls createProduct() method from shop model class

				JButton create = new JButton("Create");

				create.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {

						 createProduct(e);

					 }

					 });

				rightPanel.add(create);



				//Read button calls readProduct() method from shop model class

				JButton read = new JButton("Read");

				read.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {

						 readProduct(e);

					 }

					 });

				rightPanel.add(read);



				//Save button calls saveProducts() method from shop model class

				JButton save = new JButton("Save");

				save.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {

						 try{

							shop.saveProducts();

							 feedback.setText("Products Saved to file");

						 }

						 catch(Exception a){

								System.out.println("Error reading from file: " + a);

						 }

					 }

					 });

				rightPanel.add(save);



				//load button calls loadProducts() method from shop model class

				JButton load = new JButton("Load");

				load.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {

						 try{

							 shop.loadProducts();

							 feedback.setText("Products Loaded");

						 }

						 catch(Exception a){

								System.out.println("Error reading from file: " + a);

						 }

					 }

					 });

				rightPanel.add(load);

				

				return rightPanel;



	}//end makeRightPanel 

	

	//Top panel  - allows Category and sub-Category to be selected

	private JPanel makeTopPanel()

	{

				JPanel topPanel = new JPanel(new GridLayout(2, 2));



				//Implement the ComboBoxes

				selectCategory = new JComboBox(categories);

				selectCategory.setSelectedIndex(4);

				

				selectCategory.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {

						 category = getCatName(e);

						 String displayProducts = shop.listCatAndSubCatProducts(category, subCategory);

						 redisplay(displayProducts);

						 feedback.setText("Category: "+getCatName(e)+ " SubCategory: "+getSubCatName(e));

					 }

					 });



				selectSubCategory = new JComboBox(subCategories);

				selectSubCategory.setSelectedIndex(4);



				selectSubCategory.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {

						 subCategory = getCatName(e);

						 String displayProducts = shop.listCatAndSubCatProducts(category, subCategory);

						 redisplay(displayProducts);

						 feedback.setText("Category: "+getCatName(e)+ " SubCategory: "+getSubCatName(e));

						 }

						 });



				topPanel.add(selectCategory);

				topPanel.add(selectSubCategory);

				return (topPanel);

	} //end of makeTopPanel



	

	private void createProduct(ActionEvent e){



		String pName = name.getText();

		String pDesc = description.getText();

		String pCat= (String)selectCategory.getSelectedItem(); 

		String pSubCat= (String)selectSubCategory.getSelectedItem();

		double pWeight = Double.parseDouble(weight.getText());   //convert textfield data to a double using Double wrapper class method

		double pCost = Double.parseDouble(cost.getText());



		shop.addProduct(pName, pDesc, pWeight, pCost, pCat, pSubCat);

		feedback.setText("Product Created");



	}

	

	private void readProduct(ActionEvent e){

	//	String pId= id.getText();

	//	Product pr=shop.getProdDetails(pId);

	//	name.setText(pr.getName());

	//	description.setText(pr.getDescription());

	//	selectCategory.setSelectedItem((String)pr.getCategory()); //setSelectedItem requires an Object -- casted to String

	//	selectSubCategory.setSelectedItem((String)pr.getSubCategory());

	//	weight.setText(""+pr.getWeight()); //""+ added so that double value is converted to String

	//	cost.setText(""+pr.getCost());
		
		displayProductList.setText(shop.listProducts());

		feedback.setText("Product Read");

	}



	private String getCatName(ActionEvent e)

	{

	JComboBox cb = (JComboBox)e.getSource();

    String catName = (String)cb.getSelectedItem();

    return catName;

	}



	private String getSubCatName(ActionEvent e)

	{

	JComboBox cb = (JComboBox)e.getSource();

    String catName = (String)cb.getSelectedItem();

    return catName;

	}



	/**

	 * Update the interface display to show the current value of the 

	 * EShop.

	 */

	private void redisplay(String products)

	{

		displayProductList.setText(products);

	}



}