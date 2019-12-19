package module;

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

  public String getFullName() {
    return fullName;
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

  public Curriculum getCurriculum() {
    return curriculum;
  }

  public void setCurriculum(Curriculum curriculum) {
    this.curriculum = curriculum;
  }

  public Map<Task, String> getTaskResults() {
    return taskResults;
  }

  public void setTaskResults(Map<Task, String> taskResults) {
    this.taskResults = taskResults;
  }

  @Override
  public String toString() {
    return "Full Name: "
        + fullName
        + "\n"
        + "City: "
        + city
        + "\n"
        + "E-mail: "
        + email
        + "\n"
        + "Start Date: "
        + startDate
        + "\n"
        + "Contract Signed: "
        + (contractSigned ? "Yes" : "No");
  }
}
