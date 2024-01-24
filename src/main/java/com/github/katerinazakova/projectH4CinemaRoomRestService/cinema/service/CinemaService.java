package com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.service;

import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.CinemaTicket;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.CinemaSeats;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.CinemaStatistics;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.InvalidSeatCoordinatesException;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.PurchaseTicketException;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.UnauthorizedException;
import com.github.katerinazakova.projectH4CinemaRoomRestService.cinema.entity.cinemaExceptionHandling.WrongTokenException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private final List<CinemaSeats> cinemaSeats;

    private final List<CinemaTicket> purchaseCinemaTicket;

    public CinemaService(List<CinemaTicket> purchaseCinemaTicket) {
        this.cinemaSeats = createPlanOfCinemaSeats();
        this.purchaseCinemaTicket = purchaseCinemaTicket;
    }

    private List<CinemaSeats> createPlanOfCinemaSeats() {
        List<CinemaSeats> listOfSeats = new ArrayList<>();
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLUMNS; j++) {
                CinemaSeats seat = new CinemaSeats(i, j, i <= 4 ? 10 : 8);
                listOfSeats.add(seat);
            }
        }
        return listOfSeats;
    }

    public Map<String, Object> getCinemaInfo() {
        Map<String, Object> cinemaInfo = new HashMap<>();
        cinemaInfo.put("rows", ROWS);
        cinemaInfo.put("columns", COLUMNS);
        cinemaInfo.put("seats", cinemaSeats);
        return cinemaInfo;
    }

    public CinemaTicket purchaseTicket(int row, int column) {
        CinemaSeats requiredSeat = findRequiredSeat(row, column);
        if (requiredSeat != null) {
            if (requiredSeat.isAvailable()) {
                requiredSeat.setAvailable(false);
                CinemaTicket cinemaTicket = new CinemaTicket(UUID.randomUUID().toString(), requiredSeat);
                purchaseCinemaTicket.add(cinemaTicket);
                return cinemaTicket;
            }
            throw new PurchaseTicketException("The ticket has been already purchased!");
        }
        throw new InvalidSeatCoordinatesException("Invalid seat coordinates!");
    }


    public CinemaSeats findRequiredSeat(int row, int column) {
           return cinemaSeats.stream()
                   .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                   .findFirst()
                   .orElse(null);
    }

    public CinemaTicket findPurchaseTicket(String token) {
        return purchaseCinemaTicket.stream()
                .filter(cinemaTicket -> cinemaTicket.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }


    public Map<String, Object> refundPurchaseTicket(String token) {
        CinemaTicket cinemaTicketWithToken = findPurchaseTicket(token);

        if (cinemaTicketWithToken != null) {
            CinemaSeats availabilityOfSeat = findRequiredSeat(cinemaTicketWithToken.getTicket().getRow(), cinemaTicketWithToken.getTicket().getColumn());
            availabilityOfSeat.setAvailable(true);
            Map<String, Object> refundTicket = new HashMap<>();
            refundTicket.put("ticket", cinemaTicketWithToken.getTicket());
            purchaseCinemaTicket.remove(cinemaTicketWithToken);
            return refundTicket;
        }
        throw new WrongTokenException("Token is wrong");

    }

    public CinemaStatistics viewStatistics(String password) {
        if ("super_secret".equals(password)) {
            return (new CinemaStatistics(purchaseCinemaTicket));
        }
        throw new UnauthorizedException("Password is wrong!");
    }
}