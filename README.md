# LSRestaurant_Server
Part of LSRestaurant project. Implementation of the server, which provides and stores the users, reservations and the menus that the restaurant offers.

It uses a connection to a local MySQL database, and to connect with our users we do it with Server sockets, running on a different thread for each user so if we have different connections they can connect.
