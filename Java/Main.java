import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
//      list/ array of accounts saving and checking account
        Account[] banks = {new SavingsAccount(1, 100.00), new SavingsAccount(2,2000.00),
                new SavingsAccount(3,1000.00), new SavingsAccount(4, 300.00),
                new SavingsAccount(5,33.80 ), new CheckingAccount(6,1500.00),new CheckingAccount(7,860.52),
                new CheckingAccount(8,956.20),new CheckingAccount(9,128.15),new CheckingAccount(10,85.60)};
        int menu = 4; // when the user enters 4 the system exits
        Scanner sc =  new Scanner(System.in);
        do { // menu
                System.out.println("Enter your unique integer id: ");
//                Have to subtract the value one from the returned id since indexing start at 0 & end at length - 1
                int compareId = compareId(sc, banks) - 1 ;
//                System.out.println(compareId);
                System.out.println("Main menu\n" +
                        "1. check balance\n" +
                        "2. withdraw\n" +
                        "3. deposit\n" +
                        "4. exit");
//                switch statement that selects user input
                menu = sc.nextInt();
                switch (menu){
                    case 1:
                        System.out.println("check balance from " + banks[compareId].getClass().getName());
                        double balance =  banks[compareId].getBalance();
                        System.out.println("Your balance is R" + decimalFormat.format(balance));
//                        System.out
                        banks[compareId].setDateCreated(banks[compareId].getDateCreated());
                        System.out.println("Created on " + banks[compareId].getDateCreated());
                        break;
                    case 2:
                        double withdraw;
                        double amount;
                        System.out.println("withdraw from " + banks[compareId].getClass().getName());
                        if(banks[compareId].getClass().getName().equals("CheckingAccount")){
//                            performing an explicit casting
                            CheckingAccount mybank = (CheckingAccount)banks[compareId];
                            System.out.println("Enter the amount to withdraw");
                            amount = sc.nextDouble();
                            if (amount > mybank.getBalance()){
                                System.out.printf("Overdraft limit is: R%.2f" + mybank.getOverdraft());
                                System.out.println("You are withdrawing R" + amount);
                                withdraw = mybank.withdraw(amount);
                                mybank.setBalance(withdraw);
                                System.out.println("You have R" + mybank.getBalance() + " remaining");
                                mybank.setDateCreated(mybank.getDateCreated());
                                System.out.println("Created on " + mybank.getDateCreated());
                                System.out.println("Thanks you for using this service");
                                System.out.println();
                            }else {
                                System.out.println("Overdraft is: R" + mybank.getOverdraft());
                                System.out.println("You are withdrawing R" + amount);
                                withdraw = mybank.withdraw(amount);
                                mybank.setBalance(withdraw);
                                System.out.println("You have R" + mybank.getBalance() + " remaining");
                                mybank.setDateCreated(mybank.getDateCreated());
                                System.out.println("Created on " + mybank.getDateCreated());
                                System.out.println("Thanks you for using this service");
                                System.out.println();
                            }
                        }else {
//                            explicit casting in order to have access to child class methods and attributes
                            SavingsAccount mySavingbank = (SavingsAccount) banks[compareId];
                            System.out.println("Your monthly interest " + mySavingbank.getMonthlyInterestRate() + "%");
                            System.out.println("Your balance R" + mySavingbank.getBalance());
                            System.out.println("You will earn R" + mySavingbank.getMonthlyInterest());
                            System.out.println("Enter the amount to withdraw");
                            amount = sc.nextDouble();
                            if (amount > mySavingbank.getBalance()){
                                System.out.println("You have insufficitian funds\nyour balance is R" + mySavingbank.getBalance());
                                mySavingbank.setDateCreated(mySavingbank.getDateCreated());
                                System.out.println("Created on " + mySavingbank.getDateCreated());
                                System.out.println();
                            }else {
                                withdraw = mySavingbank.withdraw(amount);
                                mySavingbank.setBalance(withdraw);
                                System.out.println("You have R" + mySavingbank.getBalance() + " remaining");
                                mySavingbank.setDateCreated(mySavingbank.getDateCreated());
                                System.out.println("Created on " + mySavingbank.getDateCreated());
                                System.out.println("Thanks you for using this service");
                                System.out.println();

                            }
                        }
                        break;
                        case 3:
                            System.out.println("Depositing into");
                            System.out.println("Id: " + banks[compareId].getId() + " Name " + banks[compareId].getClass().getName());
                            System.out.println("Previous balance: R" + banks[compareId].getBalance());
                            System.out.println("Enter the amount you wish to deposit");
                            double depositAmount = banks[compareId].deposit(sc.nextDouble());
                            banks[compareId].setBalance(depositAmount);
                            System.out.println("Your balance is R" + banks[compareId].getBalance());
                            banks[compareId].setDateCreated(banks[compareId].getDateCreated());
                            System.out.println(banks[compareId].getDateCreated());
                            System.out.println("Thank you for using this service");
                            break;
                            default:
                                System.out.println("Thank you, Good bye!");
                                return;


                }

        }while(menu != 4);
    }
// Method to search and match account id with user input
    public static int compareId(Scanner sc, Account [] bankList){
        boolean value = true;
        int id;
        do{
            id = sc.nextInt();
            for (int i = 0; i < bankList.length; i++){
                if (bankList[i].getId() == id){
//                    System.out.println("Bank Id " + bankList[i].getId() + " ID " + id);
                  value = false;
                }
            }
            if (value == true){
                    System.out.println("Incorrect id please try again");
                    value = true;
                    System.out.println("Enter your unique integer id: ");
            }

        }while (value);
        return id;
    }
}
// super class Account with private data fields and and public methods
class Account{
    private int id;
    private double balance;
    private Date dateCreated;


    public Account(int id, double balance){
        this.id = id;
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Date getDateCreated() {
        dateCreated = new Date();
        Calendar calendar = Calendar.getInstance();
        dateCreated = calendar.getTime();
        return dateCreated;
    }

    public double withdraw(double amountToWithdraw){
        double bankBalance = getBalance();
        bankBalance -= amountToWithdraw;
        return bankBalance;
    }

    public double deposit(double amountDepositited){
        double newBankBalance = getBalance();
        newBankBalance += amountDepositited;
        return newBankBalance;
    }
}
// child class of account
class SavingsAccount extends Account{
    private double annualInteresRate = 6;

    public SavingsAccount(int id, double balance){
        super(id, balance);
    }
    public double getMonthlyInterestRate(){
        return (annualInteresRate/100)*12;
    }
    public double getMonthlyInterest(){
        return getBalance() * getMonthlyInterestRate();
    }

}
// child class of account
class CheckingAccount extends Account{
    private final double overdraft = 100.00;
    public CheckingAccount(int id, double balance){
        super(id, balance);
    }

    public double getOverdraft() {
        return overdraft;
    }
}