/**
  * Copyright 2018 bejson.com 
  */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;
import java.util.List;

public class Reminder {

    private String defaultReminder;
    private List<String> notice;
    private String popUpReminder;
    public void setDefaultReminder(String defaultReminder) {
         this.defaultReminder = defaultReminder;
     }
     public String getDefaultReminder() {
         return defaultReminder;
     }

    public void setNotice(List<String> notice) {
         this.notice = notice;
     }
     public List<String> getNotice() {
         return notice;
     }

    public void setPopUpReminder(String popUpReminder) {
         this.popUpReminder = popUpReminder;
     }
     public String getPopUpReminder() {
         return popUpReminder;
     }

}