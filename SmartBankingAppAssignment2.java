import java.util.Scanner;

public class SmartBankingAppAssignment2{
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "💰 Welcome to Smart Banking App";
        final String OPEN_ACCOUNT = "💵 Open New Account";   
        final String DEPOSIT = "Deposit Money";
        final String WITHDRAW = "Withdraw Money";
        final String TRANSFER = "💸 Transfer Money";
        final String ACCOUNT_BALANCE = "Check Acoount Balance";
        final String DROP_ACCOUNT = "Drop Existing Account";

        String[][] accounts = new String[0][];
        String screen = DASHBOARD;

        do{
            final String APP_TITLE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, screen, RESET);
            
            System.out.println(CLEAR);
            System.out.println(APP_TITLE);

            switch(screen){
                case DASHBOARD:
                    System.out.println("\n[1]. Open New Account");
                    System.out.println("[2]. Deposit Money");
                    System.out.println("[3]. Withdraw Money");
                    System.out.println("[4]. Transfer Money");
                    System.out.println("[5]. Check Acoount Balance");
                    System.out.println("[6]. Drop Existing Account");
                    System.out.println("[7]. Exit\n");
                    System.out.print("Enter an option to continue > ");

                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen = OPEN_ACCOUNT; break;
                        case 2: screen = DEPOSIT; break;
                        case 3: screen = WITHDRAW; break;
                        case 4: screen = TRANSFER; break;
                        case 5: screen = ACCOUNT_BALANCE; break;
                        case 6: screen = DROP_ACCOUNT; break;
                        case 7: System.exit(0); break;
                        default: continue;
                    }
                    break;
                
                case OPEN_ACCOUNT:
                    String id = String.format("SDB-%05d", (accounts.length+1));
                    System.out.printf("ID: %s\n", id);

                    boolean valid;
                    String name;
                    
                    do{
                        valid = true;

                        System.out.print("Name: ");
                        name = SCANNER.nextLine().strip();
                        if(name.isBlank()){
                            System.out.printf("%sName can't be empty%s \n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }

                        for (int i = 0; i < name.length(); i++) {
                            if( !(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                                System.out.printf("%sInvalid Name%s \n", COLOR_RED_BOLD, RESET);
                                valid = false;
                                break;
                            }
                        }
                       
                    }while(!valid);

                    int initialDeposit;
                    do{
                        valid = true;
                        System.out.print("Initial Deposit: ");
                        initialDeposit = SCANNER.nextInt();
                        SCANNER.nextLine();
                        if (!(initialDeposit >= 5000)){
                            System.out.printf("%sInvalid Amount! This amount is not enough for initial deposit! %s \n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }
                    }while(!valid);

                    String [][] newAccounts = new String [accounts.length+1][3];
                    for (int i = 0; i < accounts.length; i++) {
                        newAccounts[i] = accounts[i];
                    }
        
                    newAccounts[newAccounts.length-1][0] = id;
                    newAccounts[newAccounts.length-1][1] = name;
                    newAccounts[newAccounts.length-1][2] = initialDeposit+"";
        
                    accounts = newAccounts;

                    System.out.println();
                    System.out.print(id +":"+ name + " Account has been added successfully. Do you want to add another account (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")){
                        continue;
                    } 

                    screen = DASHBOARD;
                    break;
                
                default:
                    System.exit(0);

                }
                
        }while(true);

    }
}