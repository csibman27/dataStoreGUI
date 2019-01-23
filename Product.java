package list;
/**
 * 
 */
/**
 * @author csib
 *
 */


public class Product {

	private static int productId = 201901;
	
	
	private int Id;
	private String name;
	private String description;
	private double weight;
	private double cost;
	private boolean inStock;
	private String category;	//e.g. Shoes --  Men, Women, Boy, Girl
	private String subCategory;	//e.g. --  Fashion, Sport, Leisure
	
	public Product(String name, String description, double weight, double cost, String cat, String subCat) {
		
		super();
		this.Id = productId++;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.cost = cost;
		this.inStock = true;
		this.category = cat;
		this.subCategory = subCat;
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "name =" + name + ", weight =" + weight  +"\n"
				+ ", cost =" + cost + ", inStock =" + inStock;
	}
	
}