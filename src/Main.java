import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int userChoice;
        RealEstate realEstate = new RealEstate();
        do {
            userChoice=startWindow();
        if(userChoice==1){
           realEstate.createUser();
        }
        else if(userChoice==2){
            realEstate.userLogIn();
        }

        }while (userChoice!=3);
    }
    public static int startWindow() {
        Scanner s=new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1-Create an account:");
        System.out.println("2-Login to your account account:");
        System.out.println("3-End the program:");
        int userChoice;
        do {
            userChoice = s.nextInt();
            if (userChoice > 3 |
                    userChoice < 1) {
                System.out.println("Please enter a valid option:");
            }
        }while (userChoice<1|userChoice>3);
            return userChoice;
        }

    }