package models;

public class Cart {
	
	private String productid;
	private String productname;
	private String userid;
	private int quantity;
	int totalpricequantity;
	
	public Cart(String productid, String productname, String userid, int quantity, int totalpricequantity) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.userid = userid;
		this.quantity = quantity;
		this.totalpricequantity = totalpricequantity;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalpricequantity() {
		return totalpricequantity;
	}
	public void setTotalpricequantity(int totalpricequantity) {
		this.totalpricequantity = totalpricequantity;
	}
	
	public String toString() {
		return productname;
	}
	
	

}
