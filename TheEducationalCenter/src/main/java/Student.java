/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class Student {

  private Integer id;
  private String fullName;
  private String city;
  private String email;
  private String startDate;
  private Boolean contractSigned;


  public void setId(Integer id) {
    this.id = id;
  }


  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  public void setCity(String city) {
    this.city = city;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }


  public void setContractSigned(boolean contractSigned) {
    this.contractSigned = contractSigned;
  }

  @Override
  public String toString() {
    return "Student{"
        + "id="
        + id
        + ", Full Name='"
        + fullName
        + '\''
        + ", Region='"
        + city
        + '\''
        + ", E-mail='"
        + email
        + '\''
        + ", Start Date='"
        + startDate
        + '\''
        + ", Contract Signed="
        + (contractSigned ? "yes" : "no")
        + '}';
  }
}
