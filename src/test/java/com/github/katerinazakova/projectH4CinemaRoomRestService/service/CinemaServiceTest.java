package com.github.katerinazakova.projectH4CinemaRoomRestService.service;

import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.CinemaSeats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.CinemaTicket;
import com.github.katerinazakova.projectH4CinemaRoomRestService.entity.exceptionHandling.CustomBadRequestException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;

public class CinemaServiceTest {
    CinemaService testCinemaService = new CinemaService();

    @Test
    void givenValidRowAndValidColumn_whenFindRequiredSeat_thenRequiredValidResult() {
        //arrange
        int row = 5;
        int column = 2;
        //act
        CinemaSeats actualResult = testCinemaService.findRequiredSeat(row, column);
        //assert
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getRow()).isEqualTo(row);
        assertThat(actualResult.getColumn()).isEqualTo(column);
    }

    @Test
    void givenInvalidRowAndInvalidColumnAboveBorderCinemaPlan_whenFindRequiredSeat_thenReturnNull() {
        int row = 100;
        int column = 50;
        CinemaSeats actualResult = testCinemaService.findRequiredSeat(row, column);
        assertThat(actualResult).isNull();
    }

    @Test
    void givenInvalidRowAndInvalidColumnUnderBorderCinemaPlan_whenFindRequiredSeat_thenReturnNull() {
        int row = 0;
        int column = 0;
        CinemaSeats actualResult = testCinemaService.findRequiredSeat(row, column);
        assertThat(actualResult).isNull();
    }

    @Test
    void givenExistTicketToken_whenFindPurchaseTicket_thenReturnPurchasedTicket() {
        //arrange
        CinemaSeats cinemaSeats = new CinemaSeats(1, 3, 10);
        String testToken = "loke-245l57-874tM-54ok";
        CinemaTicket expectedResult = new CinemaTicket(testToken, cinemaSeats);
        testCinemaService.getPurchaseCinemaTicket().add(expectedResult);
        //act
        CinemaTicket actualResult = testCinemaService.findPurchaseTicket(testToken);
        //assert
        assertThat(actualResult).isNotNull();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void givenNotExistTicketToken_whenFindPurchaseTicket_thenReturnNull() {
        //arrange
        String testToken = "uh2k2-245l57-874tM-54ok";
        //act
        CinemaTicket actualResult = testCinemaService.findPurchaseTicket(testToken);
        //assert
        assertThat(actualResult).isNull();
    }

    @Test
    void givenValidRowAndValidColumn_whenPurchaseTicket_thenReturnTicketWithToken() {
        //arrange
        int row = 2;
        int column = 3;
        int price = 10;

        //act
        CinemaTicket actualResult = testCinemaService.purchaseTicket(row, column);
        //assert
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getTicket().getRow()).isEqualTo(row);
        assertThat(actualResult.getTicket().getColumn()).isEqualTo(column);
        assertThat(actualResult.getTicket().getPrice()).isEqualTo(price);
        assertFalse(actualResult.getToken().isEmpty());

    }

    @Test
    void givenTakenSeatCoordinates_whenPurchaseTicket_thenThrowsCustomBadRequestException() {
        //arrange
        int row = 2;
        int column = 3;
        testCinemaService.purchaseTicket(row, column);

        //assert
        assertThrows(CustomBadRequestException.class, () -> {
            testCinemaService.purchaseTicket(row, column);
        });

    }

    @Test
    void givenInvalidRowAndColumn_whenPurchaseTicket_thenThrowCustomBadRequestException() {
        //arrange
        int row = 20;
        int column = 30;

        //assert
        assertThrows(CustomBadRequestException.class, () -> {
            testCinemaService.purchaseTicket(row, column);
        });

    }

}
