import java.util.Objects;

public class Property {
    private int roomNumber;
    private int cost;
    private String houseType;
    private boolean forRent;
    private int floorNumber;
    private int houseNumber;

    private Address houseAddress;
    private User ownerName;

    public Property(int roomNumber, int cost, String houseType, boolean forRent,
        int floorNumber, int houseNumber, Address houseAddress, User ownerName){
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.houseType = houseType;
        this.forRent = forRent;
        this.floorNumber = floorNumber;
        this.houseNumber = houseNumber;
        this.houseAddress = houseAddress;
        this.ownerName = ownerName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public boolean getForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Address getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(Address houseAddress) {
        this.houseAddress = houseAddress;
    }

    public User getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(User ownerName) {
        this.ownerName = ownerName;
    }

    public String toString() {
        String info;
        if (Objects.equals(houseType, "private house")){
          info = "Type of apartment: " + this.houseType + " - " + (this.forRent ? "for rent":"for sale")+
                  ", rooms number: " + this.roomNumber + "." +
                  "\n Price: " + this.cost + "$" +
                  "\n Contact info: " + this.ownerName;}

        else {info = "Type of apartment: " + this.houseType + " - " + (this.forRent ? "for rent":"for sale")+
                    ", floor number: " + this.floorNumber + ", rooms number: " +
                    this.roomNumber + "." + "\n Price: " + this.cost + "$" +
                    "\n Contact info: " + this.ownerName;}
    return info;}
}
