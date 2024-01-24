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

## 3. stage:
Change the JSON response when a customer purchases a ticket by making a POST request to the /purchase endpoint.

{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}

Implement the /return endpoint, which will handle POST requests and allow customers to refund their tickets.

The request should have the token feature that identifies the ticket in the request body. Once you have the token, you need to identify the ticket it relates to and mark it as available.The response body should be as follows:

{
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}

The ticket should contain the information about the returned ticket.

If you cannot identify the ticket by the token, make your program respond with a 400 status code and the following response body: {"error": "Wrong token!"}

If users pass a wrong row/column number, respond with a 400 status code and the following line: { "error": "The number of a row or a column is out of bounds!"}

Show the ticket price when the /seats endpoint is accessed. 

## 4. stage
Implement the /stats endpoint that will handle GET requests with URL parameters. If the URL parameters contain a password key with a super_secret value, return the movie theatre statistics in the following format:

{
    "income": 0,
    "available": 81,
    "purchased": 0
}
Take a look at the description of keys:

income — shows the total income of sold tickets.
available — shows how many seats are available.
purchased — shows how many tickets were purchased.
If the parameters don't contain a password key or a wrong value has been passed, respond with a 401 status code. The response body should contain the following:

{
    "error": "The password is wrong!"
}

Project from Hyperskill: https://hyperskill.org/
