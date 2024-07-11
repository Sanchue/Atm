package atm;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Atm{
	
	//store cardnumber and ArrayList store the pin of the card and balance
    HashMap<Integer,ArrayList<String>> users=new HashMap<>();
    
    //store the cardnumber and Transaction History 
    HashMap<Integer,ArrayList<ArrayList<String>>> Transaction=new HashMap<>();
    int UserAccountnumber;
    String Balance;
    String pin;
    
    //serial number for transaction
    int si=0;
    
    //step:1
    // Registration for new User
    public void register()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your CardNumber:");
        int CardNumber=sc.nextInt();
        
     //To check the cardNumber already existed
        if(users.containsKey(CardNumber))
        {
        	System.out.println("-------------------------------------------------");
            System.out.println("Acount Already exist");
            System.out.println("-------------------------------------------------");
        }
        else{
        	System.out.println("Enter your PIN ");
        	pin =sc.next();
            do{
            	System.out.println("Please deposite minimal amount for creating account >=1000 :");
                Balance= sc.next();
            	}while(Integer.parseInt(Balance)<1000);
            //Adding pin and Balance in users 
            ArrayList<String> details=new ArrayList<>();
            details.add(pin);
            details.add(Balance);
            users.put(CardNumber, details);
            
            //Adding cardNumber and add empty list,because initially no transaction
            ArrayList<ArrayList<String>> list=new ArrayList<>();
            Transaction.put(CardNumber,list);
            System.out.println("-------------------------------------------------");
            System.out.println("Registered sucessful");
            System.out.println("-------------------------------------------------");
        }
    }
    // step 2
    //To validate the user 
    public void Authenticate(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your CardNumber:");
		int cardnumber=sc.nextInt();
		
		//check weather card exist or not
		if(users.containsKey(cardnumber))
		{
			System.out.println("Enter your Pin:");
			String pin=sc.next();
			ArrayList<String> details=users.get(cardnumber);
			
			//card is exist to check pin is correct or not
			if(details.get(0).equals(pin))
			{
				System.out.println("-------------------------------------------------");
				System.out.println("Login Sucessfull");
				System.out.println("-------------------------------------------------");
				functions(cardnumber);
			}
			else
			{
				System.out.println("Enter Correct pin");
			}
		}
		//cardNumber is not found
		else
		{
			System.out.println("-------------------------------------------------");
			System.out.println("User Not Found");
			System.out.println("-------------------------------------------------");
		}
	}
    
    //what are the function are show in ATM Machine
	public void functions(int cardnumber)
	{
		Scanner sc= new Scanner(System.in);
		boolean a=true;
		while(a)
		{
			System.out.println("1.Deposit");
			System.out.println("2.Withdrawal");
			System.out.println("3.Balance");
			System.out.println("4.PinChange");
			System.out.println("5.Transaction");
			System.out.println("6.Exit");
			System.out.println("-------------------------------------------------");
			System.out.println("Enter Your Choice");
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1:
					Deposit(cardnumber);
					break;
				case 2:
					Withdrawal(cardnumber);
					break;
				case 3:
					Balance(cardnumber);
					break;
				case 4:
					pinChange(cardnumber);
					break;
				case 5:
					Transaction(cardnumber);
					break;
				case 6:
					a=false;
					break;
			}
		}
	}
	
	//update the value in transaction for every deposit,withdrawal
	public void update(int cardnumber,int amount,String st)
	{
		DateTimeFormatter ft=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime now =LocalDateTime.now();
		String str=ft.format(now);
		String arr[]=str.split(" ");
		ArrayList<String> list=new ArrayList<>();
		list.add(String.valueOf(si+1));
		list.add(arr[0]);
		list.add(arr[1]);
		list.add(st);
		list.add(String.valueOf(amount));
		ArrayList<ArrayList<String>> lists=Transaction.get(cardnumber);
		lists.add(list);
		Transaction.put(cardnumber,lists);
	}
	
	//user to deposit the amount in user account
	public void Deposit(int cardnumber)
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Amount:");
		int amount=sc.nextInt();
		
	//user can deposit only 100,500,1000
		if(amount%100==0)
		{
			ArrayList<String> details=users.get(cardnumber);
			float balance=Float.parseFloat(details.get(1));
			balance+=amount;
			details.set(1,String.valueOf(balance));
			String str="Deposit";
			users.put(cardnumber,details);
			update(cardnumber,amount,str);
			System.out.println("-------------------------------------------------");
			System.out.println("Deposit Sucessfully");
			System.out.println("-------------------------------------------------");
		}
		else
		{
			System.out.println("-------------------------------------------------");
			System.out.print("Enter Correct Amount");
			System.out.println("-------------------------------------------------");
		}
	}
	
	// user can withdrawal amount
	public void Withdrawal(int cardnumber)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount: ");
		int amount=sc.nextInt();
		ArrayList<String> details=users.get(cardnumber);
		
	// only withdrawal amount 100,500,1000	
		if(amount%100==0)
		{
			float balance=Float.parseFloat(details.get(1));
			if(balance>amount)
			{
				balance-=amount;
				details.set(1,String.valueOf(balance));
				String str="Withdrawal";
				users.put(cardnumber,details);
				update(cardnumber,amount,str);
				System.out.println("-------------------------------------------------");
				System.out.println("Withdrawal Sucessfully");
				System.out.println("-------------------------------------------------");
			}
			else 
			{
				System.out.println("-------------------------------------------------");
				System.out.println("Insufficient Balance");
				System.out.println("-------------------------------------------------");
			}
		}
		else
		{	
			System.out.println("-------------------------------------------------");
			System.out.println("Enter correct Amount");
			System.out.println("-------------------------------------------------");
		}
	}
	// To check the balance of user Balance
	public void Balance(int cardnumber)
	{
		ArrayList<String> details=users.get(cardnumber);
		float balance=Float.parseFloat(details.get(1));
		System.out.println("-------------------------------------------------");
		System.out.println(balance);
		System.out.println("-------------------------------------------------");
	}
	// Transaction History of the user account
	public  void Transaction(int cardnumber)
	{
		System.out.println("SINo     Date     Time       Transaction     amount");
		ArrayList<ArrayList<String>> lists=Transaction.get(cardnumber);
		int n=lists.size();
		for(int i=0;i<n;i++)
		{
			ArrayList<String> list=lists.get(i);
			int m=list.size();
			for(int j=0;j<m;j++)
			{
				System.out.print(list.get(j)+"    ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}
	
	// change the pin for ATM Card
	public void pinChange(int cardnumber)
	{
		Scanner sc= new Scanner(System.in);
		ArrayList<String> list=users.get(cardnumber);
		System.out.println("Enter your NewPin");
		int newPin=sc.nextInt();
		list.set(0,String.valueOf(newPin));
		System.out.println("-------------------------------------------------");
		System.out.println("PinChange Sucessfull");
		System.out.println("-------------------------------------------------");
	}
	
	// Program start with main method
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Atm atm=new Atm();
        System.out.println("-------------------------------------------------");
        System.out.println("                                        ");
        System.out.println("              Hey Welcome!               ");
        System.out.println("                                          ");
        System.out.println("-------------------------------------------------");
        while(true)
        {
        	System.out.println("1.Register");
        	System.out.println("2.Login");
        	System.out.println("3.Exit");
        	System.out.println("-------------------------------------------------");
        	System.out.println("Enter your choice:");
        	int choice=sc.nextInt();
        	switch(choice)
        	{
            	case 1:
            		atm.register();
            		break;
            	case 2:
            		atm.Authenticate();
            		break;
            	case 3:
            		System.out.println("-------------------------------------------------");
            		System.out.println();
            		System.out.println("                              Thank You!                         ");
            		System.out.println();
            		System.out.print("----------------------------------------------------");
            		System.exit(0);
        }
        }
    }
}
