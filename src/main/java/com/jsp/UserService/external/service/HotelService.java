package com.jsp.UserService.external.service;

import com.jsp.UserService.data.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Hotel-Service")
public interface HotelService {

    @GetMapping("/hotel/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId);
}
