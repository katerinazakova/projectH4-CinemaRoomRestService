# Cinema Room REST Service
Let's make our virtual movie theater with the help of a REST service.
## 1. stage:
Implement the /seats endpoint that handles GET requests and returns the information about the movie theatre.
The response should contain information about the rows, columns, and available seats. The response is a JSON object.
Our cinema room has 9 rows with 9 seats each, so the total number of respective rows and columns also amounts to 9 each.
## 2. stage:
Implement the /purchase endpoint that handles POST requests and marks a booked ticket as purchased.

A request should contain the following data:

row — the row number;
column — the column number.
Take these variables and check if the specified ticket is available. If the ticket is booked, mark the seat as purchased and don't show it in the list. If the purchase is successful, the response body should be as follows:

{
    "row": 5,
    "column": 7,
    "price": 8
}

The ticket price is determined by a row number. If the row number is less or equal to 4, set the price at 10. All other rows cost 8 per seat.

If the seat is taken, respond with a 400 (Bad Request) status code. The response body should contain the following:

{
    "error": "The ticket has been already purchased!"
}


If users pass a wrong row/column number, respond with a 400 status code and the following line:

{
    "error": "The number of a row or a column is out of bounds!"
}

Show the ticket price when the /seats endpoint is accessed. 
