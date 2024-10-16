package models;

public class Product {
	
	private String productid;
	private String productname;
	private int productprice;
	private String productdes;
	
	
	public Product(String productid, String productname, int productprice, String productdes) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productprice = productprice;
		this.productdes = productdes;
	}
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	public String getProductdes() {
		return productdes;
	}
	public void setProductdes(String productdes) {
		this.productdes = productdes;
	}
	
	
	

}
