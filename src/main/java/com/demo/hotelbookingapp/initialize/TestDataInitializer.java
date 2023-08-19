package com.demo.hotelbookingapp.initialize;

import com.demo.hotelbookingapp.model.*;
import com.demo.hotelbookingapp.repository.*;
import com.demo.hotelbookingapp.model.enums.RoleType;
import com.demo.hotelbookingapp.model.enums.RoomType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final HotelManagerRepository hotelManagerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final HotelRepository hotelRepository;
    private final AvailabilityRepository availabilityRepository;

    @Override
    @Transactional
    public void run(String... args) {
        try {
            log.warn("Checking if test data persistence is required...");

            if (roleRepository.count() == 0 && userRepository.count() == 0) {
                log.info("Initiating test data persistence");

                Role adminRole = new Role(RoleType.ADMIN);
                Role customerRole = new Role(RoleType.CUSTOMER);
                Role hotelManagerRole = new Role(RoleType.HOTEL_MANAGER);

                roleRepository.save(adminRole);
                roleRepository.save(customerRole);
                roleRepository.save(hotelManagerRole);
                log.info("Role data persisted");

                User user1 = User.builder().username("admin@hotel.com").password(passwordEncoder.encode("1")).name("Admin").lastName("Admin").role(adminRole).build();
                User user2 = User.builder().username("customer1@hotel.com").password(passwordEncoder.encode("1")).name("Kaya Alp").lastName("Koker").role(customerRole).build();
                User user3 = User.builder().username("manager1@hotel.com").password(passwordEncoder.encode("1")).name("John").lastName("Doe").role(hotelManagerRole).build();
                User user4 = User.builder().username("manager2@hotel.com").password(passwordEncoder.encode("1")).name("Max").lastName("Mustermann").role(hotelManagerRole).build();
                User user5 = User.builder().username("manager3@hotel.com").password(passwordEncoder.encode("1")).name("Ahmed").lastName("Ben Salah").role(hotelManagerRole).build();
                User user6 = User.builder().username("manager4@hotel.com").password(passwordEncoder.encode("1")).name("Pierre").lastName("Dupont").role(hotelManagerRole).build();
                User user7 = User.builder().username("manager5@hotel.com").password(passwordEncoder.encode("1")).name("Karim").lastName("Benzema").role(hotelManagerRole).build();

                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(user4);
                userRepository.save(user5);
                userRepository.save(user6);
                userRepository.save(user7);

                Admin admin1 = Admin.builder().user(user1).build();
                Customer c1 = Customer.builder().user(user2).build();
                HotelManager hm1 = HotelManager.builder().user(user3).build(); // Turkey/Germany
                HotelManager hm2 = HotelManager.builder().user(user4).build(); // Turkey/Germany
                HotelManager hm3 = HotelManager.builder().user(user5).build(); // Tunisia
                HotelManager hm4 = HotelManager.builder().user(user6).build(); // France
                HotelManager hm5 = HotelManager.builder().user(user7).build(); // Algeria

                adminRepository.save(admin1);
                customerRepository.save(c1);
                hotelManagerRepository.save(hm1);
                hotelManagerRepository.save(hm2);
                hotelManagerRepository.save(hm3);
                hotelManagerRepository.save(hm4);
                hotelManagerRepository.save(hm5);
                log.info("User data persisted");

                // Turkey addresses
                Address addressIst1 = Address.builder().addressLine("Acısu Sokağı No:19, 34357").city("Istanbul")
                        .country("Turkey").build();
                Address addressIst2 = Address.builder().addressLine("Çırağan Cd. No:28, 34349 Beşiktaş").city("Istanbul")
                        .country("Turkey").build();
                Address addressIst3 = Address.builder().addressLine("Çırağan Cd. No:32, 34349 Beşiktaş").city("Istanbul")
                        .country("Turkey").build();

                // Germany addresses
                Address addressBerlin1 = Address.builder().addressLine("Unter den Linden 77").city("Berlin")
                        .country("Germany").build();
                Address addressBerlin2 = Address.builder().addressLine("Potsdamer Platz 3, Mitte, 10785").city("Berlin")
                        .country("Germany").build();
                Address addressBerlin3 = Address.builder().addressLine("Budapester Str. 2, Mitte, 10787").city("Berlin")
                        .country("Germany").build();

                // Tunisia addresses
                Address addressTunis1 = Address.builder().addressLine("Zone Touristique Yasmine Hammamet").city("Hammamet")
                        .country("Tunisia").build();
                Address addressTunis2 = Address.builder().addressLine("La Medina, Tunis 1006").city("Tunis")
                        .country("Tunisia").build();
                Address addressTunis3 = Address.builder().addressLine("Zone Touristique Sousse").city("Sousse")
                        .country("Tunisia").build();

                // France addresses
                Address addressParis1 = Address.builder().addressLine("15 Place Vendôme").city("Paris")
                        .country("France").build();
                Address addressParis2 = Address.builder().addressLine("19 Rue du Commandant Rivière").city("Paris")
                        .country("France").build();
                Address addressParis3 = Address.builder().addressLine("10 Avenue d'Iéna").city("Paris")
                        .country("France").build();

                // Algeria addresses
                Address addressAlgiers1 = Address.builder().addressLine("Route Nationale N°11, Staoueli").city("Algiers")
                        .country("Algeria").build();
                Address addressAlgiers2 = Address.builder().addressLine("24 Rue Souidani Boudjemaa").city("Algiers")
                        .country("Algeria").build();
                Address addressAlgiers3 = Address.builder().addressLine("Cité 5 Juillet, Hydra").city("Algiers")
                        .country("Algeria").build();

                // Save all addresses
                addressRepository.save(addressIst1);
                addressRepository.save(addressIst2);
                addressRepository.save(addressIst3);
                addressRepository.save(addressBerlin1);
                addressRepository.save(addressBerlin2);
                addressRepository.save(addressBerlin3);
                addressRepository.save(addressTunis1);
                addressRepository.save(addressTunis2);
                addressRepository.save(addressTunis3);
                addressRepository.save(addressParis1);
                addressRepository.save(addressParis2);
                addressRepository.save(addressParis3);
                addressRepository.save(addressAlgiers1);
                addressRepository.save(addressAlgiers2);
                addressRepository.save(addressAlgiers3);

                // Turkey hotels
                Hotel hotelIst1 = Hotel.builder().name("Swissotel The Bosphorus Istanbul")
                        .address(addressIst1).hotelManager(hm1).build();
                Hotel hotelIst2 = Hotel.builder().name("Four Seasons Hotel Istanbul")
                        .address(addressIst2).hotelManager(hm1).build();
                Hotel hotelIst3 = Hotel.builder().name("Ciragan Palace Kempinski Istanbul")
                        .address(addressIst3).hotelManager(hm1).build();

                // Germany hotels
                Hotel hotelBerlin1 = Hotel.builder().name("Hotel Adlon Kempinski Berlin")
                        .address(addressBerlin1).hotelManager(hm2).build();
                Hotel hotelBerlin2 = Hotel.builder().name("The Ritz-Carlton Berlin")
                        .address(addressBerlin2).hotelManager(hm2).build();
                Hotel hotelBerlin3 = Hotel.builder().name("InterContinental Berlin")
                        .address(addressBerlin3).hotelManager(hm2).build();

                // Tunisia hotels
                Hotel hotelTunis1 = Hotel.builder().name("The Sindbad")
                        .address(addressTunis1).hotelManager(hm3).build();
                Hotel hotelTunis2 = Hotel.builder().name("La Villa Bleue")
                        .address(addressTunis2).hotelManager(hm3).build();
                Hotel hotelTunis3 = Hotel.builder().name("Mövenpick Resort & Marine Spa Sousse")
                        .address(addressTunis3).hotelManager(hm3).build();

                // France hotels
                Hotel hotelParis1 = Hotel.builder().name("The Ritz Paris")
                        .address(addressParis1).hotelManager(hm4).build();
                Hotel hotelParis2 = Hotel.builder().name("Hôtel de Crillon")
                        .address(addressParis2).hotelManager(hm4).build();
                Hotel hotelParis3 = Hotel.builder().name("Shangri-La Hotel Paris")
                        .address(addressParis3).hotelManager(hm4).build();

                // Algeria hotels
                Hotel hotelAlgiers1 = Hotel.builder().name("Sheraton Club des Pins Resort")
                        .address(addressAlgiers1).hotelManager(hm5).build();
                Hotel hotelAlgiers2 = Hotel.builder().name("El Aurassi Hotel")
                        .address(addressAlgiers2).hotelManager(hm5).build();
                Hotel hotelAlgiers3 = Hotel.builder().name("Hotel Sofitel Algiers Hamma Garden")
                        .address(addressAlgiers3).hotelManager(hm5).build();

                // Turkey rooms
                Room singleRoomIst1 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(370).roomCount(35).hotel(hotelIst1).build();
                Room doubleRoomIst1 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(459).roomCount(45).hotel(hotelIst1).build();

                Room singleRoomIst2 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(700).roomCount(25).hotel(hotelIst2).build();
                Room doubleRoomIst2 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(890).roomCount(30).hotel(hotelIst2).build();

                Room singleRoomIst3 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(691).roomCount(30).hotel(hotelIst3).build();
                Room doubleRoomIst3 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(800).roomCount(75).hotel(hotelIst3).build();

                // Germany rooms
                Room singleRoomBerlin1 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(120.0).roomCount(25).hotel(hotelBerlin1).build();
                Room doubleRoomBerlin1 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(250.0).roomCount(15).hotel(hotelBerlin1).build();

                Room singleRoomBerlin2 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(300).roomCount(50).hotel(hotelBerlin2).build();
                Room doubleRoomBerlin2 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(400).roomCount(50).hotel(hotelBerlin2).build();

                Room singleRoomBerlin3 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(179).roomCount(45).hotel(hotelBerlin3).build();
                Room doubleRoomBerlin3 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(256).roomCount(25).hotel(hotelBerlin3).build();

                // Tunisia rooms
                Room singleRoomTunis1 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(120.0).roomCount(30).hotel(hotelTunis1).build();
                Room doubleRoomTunis1 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(180.0).roomCount(25).hotel(hotelTunis1).build();

                Room singleRoomTunis2 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(150.0).roomCount(15).hotel(hotelTunis2).build();
                Room doubleRoomTunis2 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(220.0).roomCount(10).hotel(hotelTunis2).build();

                Room singleRoomTunis3 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(110.0).roomCount(40).hotel(hotelTunis3).build();
                Room doubleRoomTunis3 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(160.0).roomCount(35).hotel(hotelTunis3).build();

                // France rooms
                Room singleRoomParis1 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(450.0).roomCount(20).hotel(hotelParis1).build();
                Room doubleRoomParis1 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(650.0).roomCount(15).hotel(hotelParis1).build();

                Room singleRoomParis2 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(400.0).roomCount(25).hotel(hotelParis2).build();
                Room doubleRoomParis2 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(600.0).roomCount(20).hotel(hotelParis2).build();

                Room singleRoomParis3 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(380.0).roomCount(30).hotel(hotelParis3).build();
                Room doubleRoomParis3 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(550.0).roomCount(25).hotel(hotelParis3).build();

                // Algeria rooms
                Room singleRoomAlgiers1 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(100.0).roomCount(50).hotel(hotelAlgiers1).build();
                Room doubleRoomAlgiers1 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(150.0).roomCount(40).hotel(hotelAlgiers1).build();

                Room singleRoomAlgiers2 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(80.0).roomCount(30).hotel(hotelAlgiers2).build();
                Room doubleRoomAlgiers2 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(120.0).roomCount(25).hotel(hotelAlgiers2).build();

                Room singleRoomAlgiers3 = Room.builder().roomType(RoomType.SINGLE)
                        .pricePerNight(90.0).roomCount(35).hotel(hotelAlgiers3).build();
                Room doubleRoomAlgiers3 = Room.builder().roomType(RoomType.DOUBLE)
                        .pricePerNight(140.0).roomCount(30).hotel(hotelAlgiers3).build();

                // Add rooms to hotels
                hotelIst1.getRooms().addAll(Arrays.asList(singleRoomIst1, doubleRoomIst1));
                hotelIst2.getRooms().addAll(Arrays.asList(singleRoomIst2, doubleRoomIst2));
                hotelIst3.getRooms().addAll(Arrays.asList(singleRoomIst3, doubleRoomIst3));
                hotelBerlin1.getRooms().addAll(Arrays.asList(singleRoomBerlin1, doubleRoomBerlin1));
                hotelBerlin2.getRooms().addAll(Arrays.asList(singleRoomBerlin2, doubleRoomBerlin2));
                hotelBerlin3.getRooms().addAll(Arrays.asList(singleRoomBerlin3, doubleRoomBerlin3));
                hotelTunis1.getRooms().addAll(Arrays.asList(singleRoomTunis1, doubleRoomTunis1));
                hotelTunis2.getRooms().addAll(Arrays.asList(singleRoomTunis2, doubleRoomTunis2));
                hotelTunis3.getRooms().addAll(Arrays.asList(singleRoomTunis3, doubleRoomTunis3));
                hotelParis1.getRooms().addAll(Arrays.asList(singleRoomParis1, doubleRoomParis1));
                hotelParis2.getRooms().addAll(Arrays.asList(singleRoomParis2, doubleRoomParis2));
                hotelParis3.getRooms().addAll(Arrays.asList(singleRoomParis3, doubleRoomParis3));
                hotelAlgiers1.getRooms().addAll(Arrays.asList(singleRoomAlgiers1, doubleRoomAlgiers1));
                hotelAlgiers2.getRooms().addAll(Arrays.asList(singleRoomAlgiers2, doubleRoomAlgiers2));
                hotelAlgiers3.getRooms().addAll(Arrays.asList(singleRoomAlgiers3, doubleRoomAlgiers3));

                // Save all hotels
                hotelRepository.save(hotelIst1);
                hotelRepository.save(hotelIst2);
                hotelRepository.save(hotelIst3);
                hotelRepository.save(hotelBerlin1);
                hotelRepository.save(hotelBerlin2);
                hotelRepository.save(hotelBerlin3);
                hotelRepository.save(hotelTunis1);
                hotelRepository.save(hotelTunis2);
                hotelRepository.save(hotelTunis3);
                hotelRepository.save(hotelParis1);
                hotelRepository.save(hotelParis2);
                hotelRepository.save(hotelParis3);
                hotelRepository.save(hotelAlgiers1);
                hotelRepository.save(hotelAlgiers2);
                hotelRepository.save(hotelAlgiers3);
                log.info("Hotel data persisted");

                // Create some availability records
                Availability av1Berlin1 = Availability.builder().hotel(hotelBerlin1)
                        .date(LocalDate.of(2023,9,1)).room(singleRoomBerlin1).availableRooms(5).build();
                Availability av2Berlin1 = Availability.builder().hotel(hotelBerlin1)
                        .date(LocalDate.of(2023,9,2)).room(doubleRoomBerlin1).availableRooms(7).build();

                Availability av1Paris1 = Availability.builder().hotel(hotelParis1)
                        .date(LocalDate.of(2023,9,1)).room(singleRoomParis1).availableRooms(8).build();
                Availability av2Paris1 = Availability.builder().hotel(hotelParis1)
                        .date(LocalDate.of(2023,9,2)).room(doubleRoomParis1).availableRooms(5).build();

                Availability av1Tunis1 = Availability.builder().hotel(hotelTunis1)
                        .date(LocalDate.of(2023,9,1)).room(singleRoomTunis1).availableRooms(15).build();
                Availability av2Tunis1 = Availability.builder().hotel(hotelTunis1)
                        .date(LocalDate.of(2023,9,2)).room(doubleRoomTunis1).availableRooms(10).build();

                Availability av1Algiers1 = Availability.builder().hotel(hotelAlgiers1)
                        .date(LocalDate.of(2023,9,1)).room(singleRoomAlgiers1).availableRooms(20).build();
                Availability av2Algiers1 = Availability.builder().hotel(hotelAlgiers1)
                        .date(LocalDate.of(2023,9,2)).room(doubleRoomAlgiers1).availableRooms(15).build();

                availabilityRepository.save(av1Berlin1);
                availabilityRepository.save(av2Berlin1);
                availabilityRepository.save(av1Paris1);
                availabilityRepository.save(av2Paris1);
                availabilityRepository.save(av1Tunis1);
                availabilityRepository.save(av2Tunis1);
                availabilityRepository.save(av1Algiers1);
                availabilityRepository.save(av2Algiers1);
                log.info("Availability data persisted");

            } else {
                log.info("Test data persistence is not required");
            }
            log.warn("App ready");
        } catch (DataAccessException e) {
            log.error("Exception occurred during data persistence: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected exception occurred: " + e.getMessage());
        }
    }
}