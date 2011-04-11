/**
 * 
 */
package com.combanc.itsm.htmlobj;

/**
 * @author Administrator
 * 
 */
public class AssetsStateCount {

	int buy;// 购买
	int storage;// 入库
	int repair;// 维修

	int loan;// 借调
	int borrow;// 领用
	int depreciation; // 折旧
	int discard;

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public int getRepair() {
		return repair;
	}

	public void setRepair(int repair) {
		this.repair = repair;
	}

	public int getLoan() {
		return loan;
	}

	public void setLoan(int loan) {
		this.loan = loan;
	}

	public int getBorrow() {
		return borrow;
	}

	public void setBorrow(int borrow) {
		this.borrow = borrow;
	}

	public int getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(int depreciation) {
		this.depreciation = depreciation;
	}

	public int getDiscard() {
		return discard;
	}

	public void setDiscard(int discard) {
		this.discard = discard;
	} // 报废

}
