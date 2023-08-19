package com.demo.hotelbookingapp.service;

import com.demo.hotelbookingapp.model.Hotel;
import com.demo.hotelbookingapp.model.Room;
import com.demo.hotelbookingapp.model.dto.RoomDTO;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room saveRoom(RoomDTO roomDTO, Hotel hotel);

    List<Room> saveRooms(List<RoomDTO> roomDTOs, Hotel hotel);

    Optional<Room> findRoomById(Long id);

    List<Room> findRoomsByHotelId(Long hotelId);

    Room updateRoom(RoomDTO roomDTO);

    void deleteRoom(Long id);

    Room mapRoomDtoToRoom(RoomDTO roomDTO, Hotel hotel);

    RoomDTO mapRoomToRoomDto(Room room);

}
