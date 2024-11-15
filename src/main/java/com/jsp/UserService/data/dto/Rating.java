package com.jsp.UserService.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rating {

    private String userId;
    private String hotelId;
    private String feedBack;
    private int rating;
    private Hotel hotel;
}
