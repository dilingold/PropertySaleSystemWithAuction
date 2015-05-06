
public class PropertySale {
	
	private String saleID;
	private String propertyAddress;
	private int reservePrice;
	private int currentOffer=0;
	private boolean saleStatus;
	
	public PropertySale(String ID, String address, int reserve) {
		
		saleID=ID;
		propertyAddress=address;
		reservePrice=reserve;
		
	}
	
	public String getSaleID() {
		return saleID;
	}
	
	public String getPropertyAddress() {
		return propertyAddress;
	}
	
	public int getCurrentOffer() {
		return currentOffer;
	}
	
	public int getReservePrice() {
		return reservePrice;
	}
	
	public boolean getSaleStatus() {
		return saleStatus;
	}

	public boolean reserveMet() {
		if (currentOffer>=reservePrice) {
			return true;
		}
		else return false;
	}
	
	public String getSaleOutcome() {
		String outcome;
		if (saleStatus==false) {
			outcome="SOLD";
		}
		else outcome="ON SALE";
		return outcome;
	}
	
	public void currentOffer(int amount) {
		if (amount>currentOffer) {
			currentOffer=amount;
		}
	}
	
	public boolean saleStatus() {
		if (currentOffer>=reservePrice) {
			saleStatus=false;
		}
		else saleStatus=true;
		return saleStatus;
	}
	
	public boolean makeOffer(int offerPrice) {
		boolean result=true;
		if (saleStatus()==false) {
			System.out.println("Sorry, the property has already been sold");
			result=false;
		}
		else if (offerPrice<=currentOffer) {
			System.out.println("Sorry, your offer was not high enough. " +
					"Please come back with a higher offer");
			result=false;
		}
		else if (offerPrice>currentOffer) {
			currentOffer=offerPrice;
			currentOffer(currentOffer);
			saleStatus();
		}
		return result;
	}
	
	public void printSaleDetails() {
		System.out.println("Sale ID: "+saleID+"\nProperty Address: "
				+getPropertyAddress()+"\nReserve Price: "+getReservePrice()+
				"\nCurrent Offer: "+currentOffer+"\nSale Outcome: "
				+getSaleOutcome());
	}
}


