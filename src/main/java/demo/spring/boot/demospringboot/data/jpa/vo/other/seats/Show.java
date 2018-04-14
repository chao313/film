/**
  * Copyright 2018 bejson.com 
  */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;
import java.util.Date;

public class Show {

    private int buyNumLimit;
    private String dim;
    private String lang;
    private int langWarn;
    private String seqNo;
    private Date showDate;
    private long showId;
    private String showTime;
    private String watermark;
    public void setBuyNumLimit(int buyNumLimit) {
         this.buyNumLimit = buyNumLimit;
     }
     public int getBuyNumLimit() {
         return buyNumLimit;
     }

    public void setDim(String dim) {
         this.dim = dim;
     }
     public String getDim() {
         return dim;
     }

    public void setLang(String lang) {
         this.lang = lang;
     }
     public String getLang() {
         return lang;
     }

    public void setLangWarn(int langWarn) {
         this.langWarn = langWarn;
     }
     public int getLangWarn() {
         return langWarn;
     }

    public void setSeqNo(String seqNo) {
         this.seqNo = seqNo;
     }
     public String getSeqNo() {
         return seqNo;
     }

    public void setShowDate(Date showDate) {
         this.showDate = showDate;
     }
     public Date getShowDate() {
         return showDate;
     }

    public void setShowId(long showId) {
         this.showId = showId;
     }
     public long getShowId() {
         return showId;
     }

    public void setShowTime(String showTime) {
         this.showTime = showTime;
     }
     public String getShowTime() {
         return showTime;
     }

    public void setWatermark(String watermark) {
         this.watermark = watermark;
     }
     public String getWatermark() {
         return watermark;
     }

}