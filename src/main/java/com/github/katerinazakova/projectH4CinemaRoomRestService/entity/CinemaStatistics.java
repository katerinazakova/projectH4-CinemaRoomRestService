package com.github.katerinazakova.projectH4CinemaRoomRestService.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CinemaStatistics {
    private final int income;
    private final int available;
    private final int purchased;

    public CinemaStatistics(List<CinemaTicket> purchaseCinemaTicket) {
        this.income = calculateTotalIncomeOfCinema(purchaseCinemaTicket);
        this.available = viewNumberOfAvailableTickets(purchaseCinemaTicket);
        this.purchased = calculateTotalCountOfPurchaseTickets(purchaseCinemaTicket);
    }


    public int calculateTotalCountOfPurchaseTickets (List <CinemaTicket> purchaseCinemaTicket){
        return purchaseCinemaTicket.size();

    }

    public int viewNumberOfAvailableTickets(List <CinemaTicket> purchaseCinemaTicket){
        int totalSeatsOfCinema = 81;
        return totalSeatsOfCinema - calculateTotalCountOfPurchaseTickets(purchaseCinemaTicket);
    }

    public int findNumberOfPurchaseTicketsOfPrice10(List <CinemaTicket> purchaseCinemaTicket){
        return ((int) purchaseCinemaTicket.stream()
                .filter(cinemaTicket -> cinemaTicket.getTicket().getRow() <= 4)
                .count());
    }

    public int findNumberOfPurchaseTicketsOfPrice8(List <CinemaTicket> purchaseCinemaTicket){
        return ((int) purchaseCinemaTicket.stream()
                .filter(cinemaTicket -> cinemaTicket.getTicket().getRow() > 4)
                .count());
    }

    public int calculateTotalIncomeOfCinema (List <CinemaTicket> purchaseCinemaTicket){
        return findNumberOfPurchaseTicketsOfPrice10(purchaseCinemaTicket) * 10 + findNumberOfPurchaseTicketsOfPrice8(purchaseCinemaTicket) * 8;
    }



}
