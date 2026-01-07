package com.github.cPajor.pcore.data.top;

public class CUser implements Comparable<CUser> {

	private String name;
	private int rank;
	
	public String getName() {
		return this.name;
	}
	
	public void setRank(int v) {
		this.rank = v;
	}
	
	public int getRank() {
		return rank;
	}
	
	@Override
	public int compareTo(CUser o) {
		return rank - o.getRank();
	}
}
