import java.util.*;
import java.io.*;
public class PropertySaleSystem {

		public static void main(String[] args) throws Exception {
			
			PropertySale propertySaleArray[]=new PropertySale[100];
			int arrayPosition=0;
					
			String saleID;
			String address;
			int reserve;
			String saleType;
			int currentOffer;
			String saleStatus;
			
			int offerPrice;
			
			Scanner console=new Scanner(System.in);
			
			try {
				Scanner input=new Scanner(new File("property.txt"));
				
				while (input.hasNext()) {
					saleType=input.next();
					saleID=input.next();
					address=input.next();
					reserve=input.nextInt();
					currentOffer=input.nextInt();
					saleStatus=input.next();
					saleType=input.next();
					if (saleType.matches("[propertySale]")) {
						propertySaleArray[arrayPosition]=new PropertySale(saleID,address,reserve);
					}
					else {
						propertySaleArray[arrayPosition]=new AuctionSale(saleID,address,reserve);
					}
					propertySaleArray[arrayPosition].makeOffer(currentOffer);
					propertySaleArray[arrayPosition].saleStatus();
					arrayPosition++;
				}
				input.close();
			}
			
			catch (FileNotFoundException e) {
				System.out.println("Data file not found");
			}
			
			String selection="A";
			
			while  (!selection.matches("[Xx]")) {
				System.out.println("   ***** Property Sale System Menu *****");
				System.out.println("A. Add New Property Sale");
				System.out.println("B. Display All Open Sales");
				System.out.println("C. Submit offer for Property");
				System.out.println("D. Display Property Sale System Summary");
				System.out.println("E. Add New Auction Sale");
				System.out.println("F. Close Auction Sale");
				System.out.println("X. Exit the program");
				System.out.print("\nEnter your selection: ");
			
				selection=console.nextLine();
				
				if (selection.matches("[Aa]")) {
					System.out.println("Please enter the Sale ID: ");
					saleID=console.nextLine();
					System.out.println("Please enter the property address: ");
					address=console.nextLine();
					System.out.println("Please enter the reserve price: ");
					reserve=console.nextInt();
					console.nextLine();
					
					
					boolean match=false;
					
					for (int i=0; i<arrayPosition; i++) {
						if (propertySaleArray[i].getSaleID().matches(saleID)) {
							System.out.println("I am sorry. That Sale ID already exists. Please select another option from the main menu.");
							match=true;
						}
					}
					if (match==false) {
					propertySaleArray[arrayPosition]=new PropertySale(saleID,address,reserve);
					arrayPosition=arrayPosition+1;
					}
				}
				
				else if (selection.matches("[Bb]")) {
					for (int i=0; i<arrayPosition; i++) {
						if (propertySaleArray[i].getSaleStatus()==false) {
							System.out.printf(propertySaleArray[i].getSaleID()+" "+
								propertySaleArray[i].getPropertyAddress()+" "+
								propertySaleArray[i].getCurrentOffer()+"\n");
						}
					}
				}
				
				else if (selection.matches("[Cc]")) {
					
					System.out.println("Please enter the Sale ID you would like to make an offer for: ");
					saleID=console.nextLine();
					
					boolean found=false;
					boolean acceptance;
					
					for (int i=0; i<arrayPosition; i++) {
						if (saleID.matches(propertySaleArray[i].getSaleID())) {
							found=true;
							System.out.println("Please enter an offer price: ");
							offerPrice=console.nextInt();
							console.nextLine();
							acceptance=propertySaleArray[i].makeOffer(offerPrice);
							if (acceptance==true) {
								System.out.println("Congratulations! Your offer was accepted and is now the highest bid.");
								propertySaleArray[i].currentOffer(offerPrice);
								if (propertySaleArray[i].reserveMet()==true) {
									System.out.println("Congratulations! The reserve has been met/exceeded");
									
								}
								else {
									System.out.println("However, your offer is below reserve price.");
								}
							}
							else if (acceptance==false) {
								System.out.println("I am sorry, your offer was rejected.");	
							}
						}
					}
					if (found==false) {
						System.out.println("Sorry, the Sale ID you have entered is not found. Please select another option from the main menu");
					}

				}
				else if (selection.matches("[Dd]")) {
					for (int i=0; i<arrayPosition; i++) {
						propertySaleArray[i].printSaleDetails();
					}
				}
				
				else if (selection.matches("[Ee]")) {
					System.out.println("Please enter the Sale ID: ");
					saleID=console.nextLine();
					System.out.println("Please enter the property address: ");
					address=console.nextLine();
					System.out.println("Please enter the reserve price: ");
					reserve=console.nextInt();
					console.nextLine();
					
					
					boolean match=false;
					
					for (int i=0; i<arrayPosition; i++) {
						if (propertySaleArray[i].getSaleID().matches(saleID)) {
							System.out.println("I am sorry. That Sale ID already exists. Please select another option from the main menu.");
							match=true;
						}
					}
					if (match==false) {
						propertySaleArray[arrayPosition]=new AuctionSale(saleID,address,reserve);
						arrayPosition=arrayPosition+1;
						}
				}
				
				else if (selection.matches("[Ff]")) {
					System.out.println("Please enter the Sale ID of the Auction Sale you would like to close: ");
					saleID=console.nextLine();
					
					for (int i=0; i<arrayPosition; i++) {
						if (propertySaleArray[i].getSaleID().matches(saleID)) {
							if (propertySaleArray[i] instanceof AuctionSale) {
								if (((AuctionSale)propertySaleArray[i]).closeAuction()) {
									System.out.println("The Auction you have selected has been successfully closed.");
								}
								else {
									System.out.println("The Auction you have selected is already closed");
								}
							}
							else {
								System.out.println("The sale ID you have entered is not an Auction Sale. Please select another option from the main menu.");
							}
							
						}
						else {
							System.out.println("The sale ID you have entered is not found. Please select another option from the main menu.");
						}
					}
				}
				
				else if (!selection.matches("[A-FXa-fx]")) {
					System.out.println("Invalid entry. Please enter a valid selection from the main menu.");
				}

				PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("property.txt")));
				for (int i=0; i<arrayPosition; i++) {
					if (propertySaleArray[i] instanceof AuctionSale) {
						saleType="auctionSale";
					}
					else {
						saleType="propertySale";
					}
					pw.println(saleType+"\t"+propertySaleArray[i].getSaleID()+"\t"+
								propertySaleArray[i].getPropertyAddress()+"\t"+propertySaleArray[i].getReservePrice()+"\t"+
								propertySaleArray[i].getCurrentOffer()+"\t"+propertySaleArray[i].getSaleStatus());
				}
				pw.close();

			}
		
		}
}
