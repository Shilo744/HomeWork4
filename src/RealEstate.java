import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class RealEstate {
    public static final int SKIP = -999;
    private User[] users;
    private Property[] properties;
    private final Address[] addresses;

    RealEstate(){
        this.users = new User[0];
        this.properties = new Property[0];
        this.addresses = new Address[10];
        this.addresses[0] = new Address("Jerusalem", "Komemiut");
        this.addresses[1] = new Address("Jerusalem", "Talpiut");
        this.addresses[2] = new Address("Jerusalem", "Neve shanan");
        this.addresses[3] = new Address("Petah tikva", "Shmuel Slent");
        this.addresses[4] = new Address("Petah tikva", "Kriat sapir");
        this.addresses[5] = new Address("Petah tikva", "Cfar ganim");
        this.addresses[6] = new Address("Tel Aviv", "Dizingof");
        this.addresses[7] = new Address("Tel Aviv", "Sharona");
        this.addresses[8] = new Address("Tel Aviv", "Karlibach");
        this.addresses[9] = new Address("Sderot", "Poaley tzion");
    }
    public void createUser() {
        User user = new User(createUsername(), createPassword(), addPhoneNumber(), ifMediator());addUserToArray(user);}

    public String createUsername() {
        Scanner scanner = new Scanner(System.in);
        String username;
        do {System.out.println("Enter username: ");
            username = scanner.nextLine();
            if (usernameIsExists(username)) {
                System.out.println("The username " + username + " already exists");}
        }while (usernameIsExists(username));
        return username;}

    public boolean usernameIsExists(String usernameCheck) {
        boolean exists = false;
        for (User currentUser : this.users) {
            if (currentUser.getUsername().equals(usernameCheck)) {
                exists = true;
                break;}}

        return exists;}

    public String createPassword() {
        Scanner scanner = new Scanner(System.in);
        String password;
        do {System.out.println("Enter password: ");
            password = scanner.nextLine();
            if (!strongPassword(password)) {
                System.out.println("The password you choose is to weak.");
            }
        } while (!strongPassword(password));
        return password;
    }

    public boolean strongPassword(String passwordCheck) {
        boolean specialSign = false;
        boolean number = false;

        boolean strongPassword = false;
        for (int i = 0; i < passwordCheck.length(); i++) {
            char symbol = passwordCheck.charAt(i);
            if (Character.isDigit(symbol)){
                number = true;}
            if (passwordCheck.charAt(i) == '_' || passwordCheck.charAt(i) == '&' ||
                    passwordCheck.charAt(i) == '%') {
                specialSign = true;
            }}
        if (number && specialSign) {
            strongPassword = true;
        }
        return strongPassword;}


    public void addUserToArray(User user) {
        User[] newUsersArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            newUsersArray[i] = this.users[i];
        }
        newUsersArray[this.users.length] = user;
        this.users = newUsersArray;
    }
    public String addPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.println("Enter your phone number: ");
            phoneNumber = scanner.nextLine();
            if (!validNumber(phoneNumber)) {
                System.out.println("Invalid number!");
            }
        } while (!validNumber(phoneNumber));
        return phoneNumber;
    }

    public boolean validNumber(String phoneNumberToCheck) {
        boolean correctNumber = true;
        if (phoneNumberToCheck.length() != 10) {
            correctNumber = false;
        } else if (phoneNumberToCheck.charAt(0) != '0' | phoneNumberToCheck.charAt(1) != '5') {
            correctNumber = false;
        } else {
            for (int i = 2; i < phoneNumberToCheck.length(); i++) {
                char checkIfNum = phoneNumberToCheck.charAt(i);
                if (!Character.isDigit(checkIfNum)) {
                    correctNumber = false;
                    break;
                }}}
        return correctNumber;
    }
    public boolean ifMediator() {
        Scanner scanner = new Scanner(System.in);
        boolean mediator = false;
        System.out.println("Are you a broker or regular user?");
        System.out.println("1-broker");
        System.out.println("2-regular user");
        int userChoice = scanner.nextInt();
        if (userChoice == 1) {
            mediator = true;
        } else if (userChoice != 2) {
            System.out.println("Invalid choice!");
            ifMediator();
        }
        return mediator;}

    public String toString() {
        return "All users: " + Arrays.toString(this.users);}

    public void userLogIn() {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        int choice;
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if (usernameIsExists(username)) {
            user = ifUserExist(username, password);
        }
        if (user == null) {
            System.out.println("The username you enter doesn't exist.");
        } else {
            do {
                System.out.println("1-Post property");
                System.out.println("2-Delete property");
                System.out.println("3-Show all properties");
                System.out.println("4-Show all user's properties");
                System.out.println("5-Search for property");
                System.out.println("6-Log out-exit to main menu");
                choice = scanner.nextInt();
                switch (choice) {

                    case 1: addProperty(user);
                        break;
                    case 2: removeProperty(user);
                        break;
                    case 3: printAllProperties();
                        break;
                    case 4: printUserProperties(user);
                        break;
                    case 5: search();
                        break;
                }
            } while (choice != 6);
        }
    }

    public User ifUserExist(String usernameToCheck, String passwordToCheck) {
        Scanner scanner = new Scanner(System.in);
        int i;
        for (i = 0; i < this.users.length; i++) {
            if (usernameToCheck.equals(this.users[i].getUsername())) {
                if (!passwordToCheck.equals(this.users[i].getPassword())) {
                    do {
                        System.out.println("Invalid password. Try again: ");
                        passwordToCheck = scanner.nextLine();
                    } while (!passwordToCheck.equals(this.users[i].getPassword()));}
                break;}}
        return users[i];
    }

    public String propertyType() {
        Scanner scanner = new Scanner(System.in);
        String propertyType = "";
        int type;
        do {
            System.out.println("What type of property would you like to post?");
            System.out.println("1-Regular apartment");
            System.out.println("2-Penthouse");
            System.out.println("3-Private house");
            type = scanner.nextInt();
            if (type == 1) {
                propertyType = "regular apartment";
            } else if (type == 2) {
                propertyType = "penthouse";
            } else if (type == 3) {
                propertyType = "private house";
            } else {
                System.out.println("Invalid type. Enter your choice again.");
            }
        } while (type != 1 && type != 2 && type != 3);
        return propertyType;
    }

    public boolean forRent() {
        Scanner scanner = new Scanner(System.in);
        boolean forRent = false;
        String forRentOrForSale;
        do {
            System.out.println("Is the property for rent or for sale?");
            forRentOrForSale = scanner.nextLine();
            if (!Objects.equals(forRentOrForSale, "for rent") && !Objects.equals(forRentOrForSale, "for sale")) {
                System.out.println("Invalid answer.");
            }
        } while (!Objects.equals(forRentOrForSale, "for rent") && !Objects.equals(forRentOrForSale, "for sale"));
        if (Objects.equals(forRentOrForSale, "for rent")) {
            forRent = true;
        }
        return forRent;
    }

    public int numberOfRooms() {
        Scanner scanner = new Scanner(System.in);
        int roomsNumber;
        do {
            System.out.println("What is your rooms number?");
            roomsNumber = scanner.nextInt();
            if (roomsNumber < 1) {
                System.out.println("Invalid number");
            }
        } while (roomsNumber < 1);
        return roomsNumber;
    }

    public int propertyPrice() {
        Scanner scanner = new Scanner(System.in);
        int price;
        do {
            System.out.println("What is the price?");
            price = scanner.nextInt();
            if (price < 0) {
                System.out.println("Invalid price");
            }
        } while (price < 0);
        return price;
    }

    public int houseNumber() {
        Scanner scanner = new Scanner(System.in);
        int houseNumber;
        do {
            System.out.println("What is the building's number?");
            houseNumber = scanner.nextInt();
            if (houseNumber < 1) {
                System.out.println("Invalid number");
            }
        } while (houseNumber < 1);
        return houseNumber;}

    public int floorNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the floor number?");
        return scanner.nextInt();}

    public boolean postProperty(User user) {
        boolean canAddProperty = true;
        if ((user.getIsBroker())) {
            if (usersPropertiesNumber(user) == 10) {
                canAddProperty = false;}
        } else {
            if (usersPropertiesNumber(user) == 3) {
                canAddProperty = false;}}
        return canAddProperty;}

    public int usersPropertiesNumber(User user) {
        int propertiesPosted = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getOwnerName() == user) {
                propertiesPosted++;}}
        return propertiesPosted;}

    public void addProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        if (postProperty(user)) {
            System.out.println("Choose a city from this options:");
            System.out.println(Arrays.toString(availableCities(this.addresses)));
            String city = scanner.nextLine();
            if (stringExist(availableCities(this.addresses), city)){
                System.out.println("Choose a street from this options:");
                System.out.println(Arrays.toString(streetsInCity(city)));
                String street = scanner.nextLine();
                if (stringExist(streetsInCity(city), street)){
                    String type = propertyType();
                    int floor = 0;
                    if (!Objects.equals(type, "private house")) {
                        floor = floorNumber();
                    }
                    int rooms = numberOfRooms();
                    int buildingNumber = houseNumber();
                    boolean forRent = forRent();
                    int price = propertyPrice();
                    Address address = this.addresses[setAddress(city, street)];
                    Property property = new Property(rooms, price, type, forRent, floor, buildingNumber, address , user);
                    addProperty(property);
                    if (propertyAdded(property)){
                        System.out.println("Property added!");}}}
        } else {
            System.out.println("You can't post more property.");
        }}

    public void printAllProperties(){
        System.out.println("All properties: " + Arrays.toString(this.properties));}

    public void printUserProperties(User user){
        System.out.println("Your all properties: ");
        int number = 1;
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getOwnerName() == user){
                System.out.println(number + "." + this.properties[i]);
                number++;}}}

    public void addProperty(Property property) {
        Property[] newArrayOfProperties = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            newArrayOfProperties[i] = this.properties[i];}
        newArrayOfProperties[this.properties.length] = property;
        this.properties = newArrayOfProperties;}



    public int setAddress(String city, String street){
        int i;
        for (i = 0; i < this.addresses.length; i++){
            if (Objects.equals(this.addresses[i].getCityName(), city) && Objects.equals(this.addresses[i].getStreetName(), street)){
                break;}}
        return i;}

    public boolean propertyAdded(Property property){
        boolean added = false;
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i] == property){
                added = true;}}
        return added;}

    public String [] streetsInCity(String city){
        int counter = 0;
        for (int i = 0; i < this.addresses.length; i++){
            if (Objects.equals(this.addresses[i].getCityName(), city)){
                counter++;}}
        int index = 0;
        String [] streets = new String[counter];
        for (int i = 0; i < this.addresses.length; i++){
            if (index == counter){
                break;
            } else if (Objects.equals(this.addresses[i].getCityName(), city)){
                streets[index] = this.addresses[i].getStreetName();
                index++;}}
        return streets;}

    public boolean stringExist (String[] strings, String str){
        boolean exist = false;
        for (int i = 0; i < strings.length; i++){
            if (Objects.equals(strings[i], str)){
                exist = true;
                break;}}
        return exist;}

    public String [] availableCities(Address[] addresses) {
        int counter = 0;
        for (int i = 0; i < this.addresses.length; i++) {
            for (int j = i+1; j < this.addresses.length; j++) {
                if (Objects.equals(addresses[i].getCityName(), this.addresses[j].getCityName())){
                counter++;
                break;}}}
        int index = 0;
        String[]cities = new String[addresses.length - counter];
        for (int i = 0; i < addresses.length; i ++){
            if (index == cities.length){
                break;}
            for (int j = 0; j < cities.length; j++) {
                if (Objects.equals(addresses[i].getCityName(), cities[j]) && j<= index) {
                    break;
                } else if (!Objects.equals(addresses[i].getCityName(), cities[j]) && j == index) {
                    cities[index] = addresses[i].getCityName();
                    index++;
                    break;}}}
        return cities;}

    public Property[] usersProperties (User user){
        int index = 0;
        Property[] usersPropsArray = new Property[usersPropertiesNumber(user)];
        for (int i = 0; i < this.properties.length; i++) {
            if (index == usersPropsArray.length){
                break;
            } else if (this.properties[i].getOwnerName() == user) {
                usersPropsArray[index] = this.properties[i];
                index++;}}
        return usersPropsArray;}

    public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int number;
        if (usersPropertiesNumber(user) == 0){
            System.out.println("You didn't post any property!");
        } else {
            printUserProperties(user);
            do {
                System.out.println("Enter the number of the property you want to delete: ");
                number = scanner.nextInt();
            } while (number < 0 || number > usersProperties(user).length);
            Property propertyToDelete = usersProperties(user)[number - 1];
            Property[] newProperties = new Property[this.properties.length - 1];
            int index = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (this.properties[i] != propertyToDelete){
                    newProperties[index] = this.properties[i];}}
            this.properties = newProperties;}}

    public void search(){
        Scanner scanner = new Scanner(System.in);
        Property [] wantedProperties = this.properties;
        System.out.println("Do you look for rent or buy?");
        System.out.println("1-Rent.");
        System.out.println("2-Buy.");
        System.out.println("-999 Both");
        int forRentChoice;
        do {
            forRentChoice= scanner.nextInt();
            if (forRentChoice != 1 && forRentChoice != 2 && forRentChoice != SKIP){
                System.out.println("Invalid choice");}
        } while (forRentChoice != 1 && forRentChoice != 2 && forRentChoice != SKIP);
        if (forRentChoice == 1){
            int counter = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (this.properties[i].getForRent()){
                    counter++;}}
            Property [] forRentProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (index == counter){
                    break;
                }else if (this.properties[i].getForRent()){
                    forRentProperties[index] = this.properties[i];
                    index++;}}
            wantedProperties = forRentProperties;
        } else if (forRentChoice == 2){
            int counter = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (!this.properties[i].getForRent()){
                    counter++;}}
            Property [] forSaleProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (index == counter){
                    break;
                }else if (!this.properties[i].getForRent()){
                    forSaleProperties[index] = this.properties[i];
                    index++;}}
            wantedProperties = forSaleProperties;}
        System.out.println("What type of the property do you search?");
        int typeChoice;
        System.out.println("1-Regular apartment.");
        System.out.println("2-Penthouse.");
        System.out.println("3-Private house.");
        System.out.println("-999 For all.");
        do {
            typeChoice = scanner.nextInt();
            if (typeChoice != 1 && typeChoice != 2 && typeChoice != 3 && typeChoice != SKIP){
                System.out.println("Invalid choice");}
        } while (typeChoice != 1 && typeChoice != 2 && typeChoice != 3 && typeChoice != SKIP);

        if (typeChoice == 1){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (Objects.equals(wantedProperties[i].getHouseType(), "regular apartment")){
                    counter++;}}
            Property [] regularApartments = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                }else if (Objects.equals(wantedProperties[i].getHouseType(), "regular apartment")){
                    regularApartments[index] = wantedProperties[i];
                    index++;
                }}
            wantedProperties = regularApartments;
        } else if (typeChoice == 2){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (Objects.equals(wantedProperties[i].getHouseType(), "penthouse")){
                    counter++;}}
            Property [] penthouses = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                }else if (Objects.equals(wantedProperties[i].getHouseType(), "penthouse")){
                    penthouses[index] = wantedProperties[i];
                    index++;}}
            wantedProperties = penthouses;
        } else if (typeChoice == 3){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (Objects.equals(wantedProperties[i].getHouseType(), "private house")){
                    counter++;
                }}
            Property [] privateHouses = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                }else if (Objects.equals(wantedProperties[i].getHouseType(), "private house")){
                    privateHouses[index] = wantedProperties[i];
                    index++;
                }}
            wantedProperties = privateHouses;}
        System.out.println("What number of rooms are you searching for?");
        System.out.println("-999 - For all options.");
        int numberOfRooms;
        do {
            numberOfRooms = scanner.nextInt();
            if (numberOfRooms < 1 && numberOfRooms != SKIP){
                System.out.println("Invalid number");
            }
        } while (numberOfRooms < 1 && numberOfRooms != SKIP);
        if (numberOfRooms != SKIP){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (wantedProperties[i].getRoomNumber() == numberOfRooms){
                    counter++;
                }}
            Property[] wantedRoomsNumberProps = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++) {
                if (index == counter){
                    break;
                } else if (wantedProperties[i].getRoomNumber() == numberOfRooms) {
                    wantedRoomsNumberProps[index] = wantedProperties[i];
                    index++;
                }}
            wantedProperties = wantedRoomsNumberProps;}
        System.out.println("What is minimum price?");
        int minPrice;
        do {
            minPrice = scanner.nextInt();
            if (minPrice < 0 && minPrice != SKIP){
                System.out.println("Invalid price");}
        } while (minPrice < 0 && minPrice != SKIP);
        System.out.println("What is maximum price?");
        int maxPrice;
        do {
            maxPrice = scanner.nextInt();
            if (maxPrice < minPrice && maxPrice != SKIP){
                System.out.println("Invalid price");
            }} while (maxPrice < minPrice && maxPrice != SKIP);

        if (minPrice >= 0){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if(wantedProperties[i].getCost() >= minPrice){
                    counter++;
                }}
            Property [] notCheaperThanMinPricesProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                } else if (wantedProperties[i].getCost() >= minPrice){
                    notCheaperThanMinPricesProperties[index] = wantedProperties[i];
                    index++;
                }}
            wantedProperties = notCheaperThanMinPricesProperties;}

        if (maxPrice >= minPrice &&(minPrice != SKIP || maxPrice != SKIP)){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if(wantedProperties[i].getCost() <= maxPrice){
                    counter++;}}
            Property [] lessOrEqualsMaxPriceProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                } else if (wantedProperties[i].getCost() <= maxPrice){
                    lessOrEqualsMaxPriceProperties[index] = wantedProperties[i];
                    index++;}}
            wantedProperties = lessOrEqualsMaxPriceProperties;}
        System.out.println(Arrays.toString(wantedProperties));}}
