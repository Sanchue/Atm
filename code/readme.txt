ATM System Project
	This project simulates an ATM system implemented in Java. It allows users to create new bank cards, perform transactions such as balance inquiries, withdrawals, deposits, and view transaction histories. Below is an overview of the project components:

Files Included:
	Atm.java: Contains the main application logic including user interface and card operations handling
Key Features:
	User Interface: Simple console-based interface for interacting with the ATM system.
Card Management: 
	Supports creation of new cards and storage in a HashMap.
Transaction Handling: 
	Allows users to perform operations like balance checks, withdrawals, deposits, and view recent transaction history.
Data Persistence: 
	Uses collections (Two HashMap are use ,One for store the user cardnumber and pin,and another for store the transaction history for particular userand ) to store and manage card and transaction data.


How to Use:
	Compile:Atm.java
	javac Atm.java

Run: 
	Execute the Atm class to start the ATM application.

	java Atm

Follow On-Screen Instructions: 
	The application will prompt users with options to either continue with an existing card or create a new one. Subsequent options include various banking operations and the ability to exit the application.

Project Structure:
	Main Class (Atm.java):

	Manages user interaction and main control flow.
	User HashMap to store user pin and balance keyed by card number.
	Transaction HashMap store  attributes like card number,Time,Date, type of Transaction.
	Provides methods for deposit, withdrawal, and viewing transaction history

Additional Notes:

	Transaction Timestamps: 
		Each transaction records the date and time using Java's LocalDateTime and DateTimeFormatter.
	
	Security: 
		PIN validation ensures secure access to card operations.

	Input Validation:
		 Basic validation for withdrawal and deposit amounts (multiples of 100 or 500) is implemented.

Future Enhancements:
	Implement more robust error handling and edge case management.	
	Integrate with a persistent data store (like databases) for more reliable data storage.
	Improve user interface for better user experience.
	This ATM system project serves as a foundational implementation demonstrating basic banking functionalities in a console-based Java application