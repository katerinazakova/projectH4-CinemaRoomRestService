package com.github.katerinazakova.projectH4CinemaRoomRestService.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Statistics {
    private int income;
    private int available;
    private int purchased;

    public Statistics(List<Ticket> purchaseTicket) {
        this.income = calculateTotalIncomeOfCinema(purchaseTicket);
        this.available = viewNumberOfAvailableTickets(purchaseTicket);
        this.purchased = calculateTotalCountOfPurchaseTickets(purchaseTicket);
    }

    public int calculateTotalCountOfPurchaseTickets (List <Ticket> purchaseTicket){
        return purchaseTicket.size();

    }

    public int viewNumberOfAvailableTickets(List <Ticket> purchaseTicket){
        int totalSeatsOfCinema = 81;
        return totalSeatsOfCinema - calculateTotalCountOfPurchaseTickets(purchaseTicket);
    }

    public int findNumberOfPurchaseTicketsOfPrice10(List <Ticket> purchaseTicket){
        return ((int) purchaseTicket.stream()
                .filter(ticket -> ticket.getTicket().getRow() <= 4)
                .count());
    }

    public int findNumberOfPurchaseTicketsOfPrice8(List <Ticket> purchaseTicket){
        return ((int) purchaseTicket.stream()
                .filter(ticket -> ticket.getTicket().getRow() > 4)
                .count());
    }

    public int calculateTotalIncomeOfCinema (List <Ticket> purchaseTicket){
        return findNumberOfPurchaseTicketsOfPrice10(purchaseTicket) * 10 + findNumberOfPurchaseTicketsOfPrice8(purchaseTicket) * 8;
    }







}
