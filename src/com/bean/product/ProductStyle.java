package com.bean.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ProductStyle implements Serializable{
	private static final long serialVersionUID = 1L;
	/**	��ʽid*/
	private Integer id;
	/**	��ʽ����*/
	private String name;
	/**	��ʽͼƬ����*/
	private String imagename;
	/**	�Ƿ�ɼ�*/
	private Boolean visible=true;
	/**	��ʽ������Ʒ*/
	private ProductInfo product;
	@Transient
	public String getImageFullPath(){
		return "/images/product/"+this.getProduct().getProducttype().getTypeid()+"/"+this.getProduct().getId()+"/prototype/" +
				this.imagename;
	}
	@Transient
	public String getImageFullPath140(){
		return "/images/product/"+this.getProduct().getProducttype().getTypeid()+"/"+this.getProduct().getId()+"/140x/" +
				this.imagename;
	}
	
	public ProductStyle(String name, String imagename, Boolean visible) {
		super();
		this.name = name;
		this.imagename = imagename;
		this.visible = visible;
	}
	public ProductStyle() {
		super();
	}
	/**
	 * һ����Ʒ�ж����ʽ����ʽΪ���һ������ʽΪά���ˣ�����ڶ��һ��
	 * @return
	 */
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name="productid")
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=30,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=40,nullable=false)
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	@Column(nullable=false)
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductStyle other = (ProductStyle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
