package com.github.katerinazakova.projectH4CinemaRoomRestService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaSeats {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean available = true;

    public CinemaSeats(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

}
