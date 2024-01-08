package com.github.katerinazakova.projectH4CinemaRoomRestService.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seats {
    private int row;
    private int column;

    public Seats (){

    }

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
