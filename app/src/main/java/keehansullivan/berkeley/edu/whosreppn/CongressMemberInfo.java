package keehansullivan.berkeley.edu.whosreppn;

public class CongressMemberInfo {

    public String firstName;  // e.g. Kamala
    public String lastName;  // e.g. Harris
    public String representativeType;  // e.g.  Senator
    public String url;  // e.g. https://www.harris.senate.gov
    public String phoneNumber;  // e.g. 202-224-3553
    public String contactForm;  // e.g. https://www.harris.senate.gov/contact
    public String party;  // e.g. Democrat
    public String state;  // e.g. CA
    public String district;  // e.g. Congressional District 13

    public CongressMemberInfo(String firstName, String lastName,
                              String representativeType, String party,
                              String state, String district,
                              String url, String phoneNumber, String contactForm) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.representativeType = representativeType;
        this.party = party;
        this.state = state;
        this.district = district;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.contactForm = contactForm;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        CongressMemberInfo legislator = (CongressMemberInfo) object;
        if (this.firstName.equals(legislator.firstName) && this.lastName.equals(legislator.lastName)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String fullName = this.firstName + this.lastName;
        return fullName.hashCode();
    }

}