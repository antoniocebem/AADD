package org.example;

public class ProductoItem {
	private String ProductName;
	private int SupplierID;
	private int CategoryID;
	private String QuantityPerUnit;
	private double UnitPrice;
	private int UnitsInStock;
	private int UnitsOnOrder;
	private int ReorderLevel;
	private int Discontinued;

	// Getters y setters
	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public String getQuantityPerUnit() {
		return QuantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		QuantityPerUnit = quantityPerUnit;
	}

	public double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}

	public int getUnitsInStock() {
		return UnitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		UnitsInStock = unitsInStock;
	}

	public int getUnitsOnOrder() {
		return UnitsOnOrder;
	}

	public void setUnitsOnOrder(int unitsOnOrder) {
		UnitsOnOrder = unitsOnOrder;
	}

	public int getReorderLevel() {
		return ReorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		ReorderLevel = reorderLevel;
	}

	public int getDiscontinued() {
		return Discontinued;
	}

	public void setDiscontinued(int discontinued) {
		Discontinued = discontinued;
	}

	@Override
	public String toString() {
		return "ProductoItem{" +
				"ProductName='" + ProductName + '\'' +
				", SupplierID=" + SupplierID +
				", CategoryID=" + CategoryID +
				", QuantityPerUnit='" + QuantityPerUnit + '\'' +
				", UnitPrice=" + UnitPrice +
				", UnitsInStock=" + UnitsInStock +
				", UnitsOnOrder=" + UnitsOnOrder +
				", ReorderLevel=" + ReorderLevel +
				", Discontinued=" + Discontinued +
				'}';
	}
}
