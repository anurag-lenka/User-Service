package com.jsp.UserService.service.impl;

import com.jsp.UserService.dao.service.UserDAOService;
import com.jsp.UserService.data.dto.ApiResponse;
import com.jsp.UserService.data.dto.Hotel;
import com.jsp.UserService.data.dto.Rating;
import com.jsp.UserService.data.dto.UserDTO;
import com.jsp.UserService.data.entities.User;
import com.jsp.UserService.external.service.HotelService;
import com.jsp.UserService.service.UserService;
import com.jsp.UserService.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAOService userDAOService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public ResponseEntity<ApiResponse> createUser(UserDTO userDTO) {
        String id = UUID.randomUUID().toString();
        User user= User.builder().name(userDTO.getName()).email(userDTO.getEmail()).contact(userDTO.getContact()).
                userId(id).build();
        return ResponseUtil.getCreateResponse(userDAOService.createUser(user));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllUsers() {

        List<User> allUser = userDAOService.getAllUser();

        List<User> userList = allUser.stream().map(user -> {
            Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class);
            assert ratings != null;
            List<Rating> ratingList = Arrays.stream(ratings).toList();

            List<Rating> ratingFinalList = ratingList.stream().map(rating -> {
//                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotel/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingFinalList);

            return user;
        }).collect(Collectors.toList());

        return ResponseUtil.getOkResponse(userList);
    }

    @Override
    public ResponseEntity<ApiResponse> getUser(String userId) {

        User user = userDAOService.getUser(userId);
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + userId, Rating[].class);
        assert ratings != null;
        List<Rating> ratingList = Arrays.stream(ratings).toList();

        List<Rating> ratingFinalList = ratingList.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotel/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingFinalList);

        return ResponseUtil.getOkResponse(user);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteUser(String userId) {

        userDAOService.deleteUser(userId);
        return ResponseUtil.getOkResponse("Deleted Successfully");
    }

    @Override
    public ResponseEntity<ApiResponse> updateUser(String userId, UserDTO userDTO) {

        User user = userDAOService.getUser(userId);
        user.setContact(userDTO.getContact());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        return ResponseUtil.getOkResponse(userDAOService.createUser(user));
    }


}
