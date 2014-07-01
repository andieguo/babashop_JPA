package com.bean;

import java.util.List;

public class PageView<T> {
	private List<T> records;//��ҳ����
	private PageIndex pageindex;//ҳ�뿪ʼ���� �� ��������
	private long totalpage = 1;//��ҳ��
	private int maxresult = 12;//ÿҳ��ʾ��¼��
	private int currentpage = 1;//��ǰҳ��
	private long totalrecord;//�ܼ�¼��
	private int pagecode = 3 ;//ÿҳ��ʾ��ҳ������
	
	public int getFirstResult(){
		return (currentpage-1)*maxresult;
	}
	
	public void setQueryResult(QueryResult<T> qr){
		setRecords(qr.getResultlist());
		setTotalrecord(qr.getTotalrecord());
	}
	
	public PageView(int maxresult, int currentpage) {
		super();
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	public int getPagecode() {
		return pagecode;
	}
	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(totalrecord%maxresult==0?totalrecord/maxresult:totalrecord/maxresult+1);
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}

	public PageIndex getPageindex() {
		return pageindex;
	}

	public long getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageindex = PageIndex.getPageIndex(pagecode, currentpage, totalpage);
		
	}
	public int getMaxresult() {
		return maxresult;
	}
	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	
	
}
