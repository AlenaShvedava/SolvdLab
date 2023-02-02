package pl.solvd.concerthall.models;

public class Authors {
    private String firstName;
    private String lastName;
    private String AuthorTypes ;

    public Authors() {
    }

    public Authors(String authorTypes, String firstName, String lastName) {
        AuthorTypes = authorTypes;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getAuthorTypes() {
        return AuthorTypes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "\nAuthor: \n" +
                "author type: " + AuthorTypes + "\n" +
                "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n";
    }
}
