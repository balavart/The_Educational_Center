import java.util.List;
import java.util.Map;

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
  private boolean contractSigned;
  private Curriculum curriculum;
  private Map<Task, String> taskResults;

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

  public void setCurriculum(Curriculum curriculum) {
    this.curriculum = curriculum;
  }

  public void setTaskResults(Map<Task, String> taskResults) {
    this.taskResults = taskResults;
  }

  @Override
  public String toString() {
    return "Student{"
        + "id="
        + id
        + ", Full Name='"
        + fullName
        + '\''
        + ", City='"
        + city
        + '\''
        + ", E-mail='"
        + email
        + '\''
        + ", Start Date='"
        + startDate
        + '\''
        + ", Contract Signed="
        + (contractSigned ? "Yes" : "No")
        + ", Curriculum="
        + curriculum
        + ", Task result="
        + taskResults
        + '\''
        + '}';
  }
}
