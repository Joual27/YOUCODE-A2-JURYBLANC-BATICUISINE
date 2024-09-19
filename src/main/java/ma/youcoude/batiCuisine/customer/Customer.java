package ma.youcoude.batiCuisine.customer;

public class Customer {
    private String fullName;
    private String address;
    private String phoneNumber;
    private boolean isProfessional;

    public Customer(){}

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public boolean isProfessional() {
        return isProfessional;
    }
    public void setProfessional(boolean isProfessional) {
        this.isProfessional = isProfessional;
    }
}
