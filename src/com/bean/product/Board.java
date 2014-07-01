package com.bean.product;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Board {
	/**���ID*/
	private Integer typeid;
	/**������� */
	private String name;
	/**��ע*/
	private String note;
	/**�Ƿ�ɼ� */
	private Boolean visible = true;
	/**�����-----------------�����丸���ȡ�����õ����࣬���������������Զ���Ϊ˫��*/
	private Set<Board> childtypes = new HashSet<Board>();
	/**��������**/
	private Board parent;
	/**��ӵ�еĲ�Ʒ**///---------------------���ݲ�Ʒ���ͻ�ȡ�����õĲ�Ʒ
	private Set<ProductInfo> productInfos = new HashSet<ProductInfo>();
	
	public Board() {
		super();
	}

	public Board(Integer typeid) {
		super();
		this.typeid = typeid;
	}
	
	/**
	 * һ��һ��Ϊ��ά���ˣ����һ��Ϊά����
	 * @return
	 */
	@OneToMany(mappedBy="producttype",cascade=CascadeType.REMOVE)
	public Set<ProductInfo> getProductInfos() {
		return productInfos;
	}

	public void setProductInfos(Set<ProductInfo> productInfos) {
		this.productInfos = productInfos;
	}

	/**
	 * ��ɾ��һ��һ��ʱ��ɾ�������ж�Ӧ�Ķ��һ��
	 * һ��һ��Ϊ��ά���� �����һ��Ϊά����
	 * @return
	 */
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	public Set<Board> getChildtypes() {
		return childtypes;
	}

	public void setChildtypes(Set<Board> childtypes) {
		this.childtypes = childtypes;
	}

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parantid")
	public Board getParent() {

		return parent;
	}

	public void setParent(Board parent) {
		this.parent = parent;
	}

	@Column(length=36,nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=200)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(unique=false)
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getTypeid() {
		return typeid;
	}
	
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
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
		Board other = (Board) obj;
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		return true;
	}
  
  
}
