/**
 * Copyright 2018 bejson.com
 */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Seat {
    private boolean displayAd;
    private int isShowRecommendation;
    private List<NewSections> sections;
    private List<String> selectSeatImages;
    private List<String> selectedImages;
    private String selectedSeatType;
    private String selectedSeats;
    private String selectedSectionId;

}