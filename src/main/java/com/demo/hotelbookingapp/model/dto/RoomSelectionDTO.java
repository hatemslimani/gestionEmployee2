package com.demo.hotelbookingapp.model.dto;

import com.demo.hotelbookingapp.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSelectionDTO {

    private RoomType roomType;
    private int count;
    private double pricePerNight;
}
