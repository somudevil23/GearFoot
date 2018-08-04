package com.niit.pistona.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique= true)
	private int prodId;
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	@Column(nullable= false, unique= true)
	@NotEmpty(message = "Product Name Cannot be empty")
	private String prodName;
	@Column(nullable = false)
	private int productQuantity;
	@Column
	@NotEmpty(message = "Product Description Cannot be empty")
	private String prodDescription;
	@Column(nullable = false)
	private int productPrice;
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDescription() {
		return prodDescription;
	}
	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}
	private int categoryId;
	
	
	
	
	
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public int getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	@Transient
	MultipartFile pimage;
	
	public MultipartFile getPimage() {
		return pimage;
	}
	public void setPimage(MultipartFile pimage) {
		this.pimage = pimage;
	}
}
