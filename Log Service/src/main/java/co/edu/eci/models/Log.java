package co.edu.eci.models;

import java.util.Date;

public class Log {
  private String info;
  private Date date;
  
  public Log() {
  }

  public Log(String info) {
    this.info = info;
    this.date = new Date();
  }

  public Log(String info, Date date) {
    this.info = info;
    this.date = date;
  }
  
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
  
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
}