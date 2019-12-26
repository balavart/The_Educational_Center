package com.epam.training_center.balaian.model;

/**
 * The type Task.
 *
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class Task {

  private int id;
  private String type;
  private String title;
  private int duration;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Type: " + type + "\n" + "Title: " + title + "\n" + "Duration: " + duration;
  }
}
