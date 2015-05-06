import java.util.Scanner;


public class AuctionSale extends PropertySale {
	
	Scanner console=new Scanner(System.in);

	String highestBidder;
	
	public AuctionSale(String ID, String address, int reserve) {
		super(ID, address, reserve);
		
		highestBidder="NO BIDS PLACED";
		
	}
	
	public void highestBidder(String name, int amount) {
		if (amount>getCurrentOffer()) {
			currentOffer(amount);
			highestBidder=name;
		}
	}
	
	public String getHighestBidder() {
		return highestBidder;
	}

	public String getSaleOutcome() {
		String outcome;
		if (getSaleStatus()) {
			outcome="ACCEPTING BIDS";
		}
		else {
			if (reserveMet()) {
				outcome="SOLD";
			}
			else {
				outcome="PASSED IN";
			}
		}
		return outcome;
	}
	
	public boolean closeAuction() {
		boolean result;
		if (!getSaleStatus()) {
			result=false;
		}
		else {
			saleStatus();
			result=true;
		}
		return result;
	}
	
	public boolean makeOffer(int offerPrice) {
		boolean result;
		if (!getSaleStatus()) {
			System.out.println("Auction is no longer open for offers");
			result=false;
		}
		else if (offerPrice<=getCurrentOffer()) {
			System.out.println("Offer not accepted");
			result=false;
		}
		else {
			currentOffer(offerPrice);
			System.out.println("You are currently the highest bidder! Please enter your name: ");
			highestBidder=console.nextLine();
			result=true;
		}
		return result;
	}
	
	public void printSaleDetails() {
		System.out.println("Sale ID: "+getSaleID()+"\nProperty Address: "
				+getPropertyAddress()+"\nReserve Price: "+getReservePrice()+
				"\nCurrent Offer: "+getCurrentOffer()+"\nSale Outcome: "
				+getSaleOutcome()+"\nHighest Bidder: "+highestBidder);
	}
}
