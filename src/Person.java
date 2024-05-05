public class Person {

    private String name;
    private char gender;
    private int age;
    private String address;
    private String phoneNumber;



    public Person(String name, char gender, int age, String address, String phoneNumber) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        if (!newAddress.equals(""))
            address = newAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) {

        if (!newPhoneNumber.equals(""))
            phoneNumber = newPhoneNumber;

    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {

        if (!newName.equals(""))
            name = newName;

    }


    public char getGender() {
        return gender;
    }

    public void setGender(char newGender) {

        if ( newGender == 'm' || newGender == 'f')
            gender = newGender;
        else gender = 'm';

    }


    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {

        if (newAge >= 0)
            age = newAge;

    }

    public String toString() {

        String personInfo = "Name: " + getName() + "," + " Gender: ";
        if ( getGender() == 'm')
            personInfo = personInfo + "Male, ";
        else personInfo = personInfo + "Female, ";
        personInfo = personInfo + "Age: " + getAge();
        return personInfo;

    }

}
