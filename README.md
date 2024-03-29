# Board Game Local

## Description

My BoardGameLocal System is a Spring-powered application tailored for a modern board game local. This system simplifies inventory management, and customer engagement. Users can effortlessly browse board games, order food and beverages, and register for in-store events. With features like loyalty programs and collaboration tools for developers, the BoardGameShop System aims to elevate the overall customer experience while ensuring secure transactions and real-time updates. Streamline your board game shop operations and enhance customer satisfaction with this comprehensive management solution.

## Business Requirements

1. Game Catalog:

   * The system must maintain a catalog of available board games, including details such as title, description, price, players required and game time.
  
2. Inventory Management:

   * The application should track the stock levels of board games, food, and water in the shop.

3. Events Management:

   * The system should allow the creation and management of board game events, including details on date, time and prizes.

4. Developer Collaboration:

   * Facilitate collaboration with board game developers by providing a platform for them to showcase their games and interact with customers.

5. Food and Beverage Management:

   * Maintain a menu for food and beverage items available in the shop, including prices.

6. Table Reservation System:

   * Allow customers to reserve tables for playing board games or participating in events.

7. Staff Management:

   * The system should support staff accounts with varying levels of access, including cashier, manager, and administrator roles.

8. Event Registration:

   * Users should be able to register for board game events, with options for both free and paid events.

9. Table Reservation System:

   * Allow customers to reserve tables for playing board games or participating in events.

10. Notification System:

    * Implement a notification system for order updates, event reminders, and promotions.

## MVP features 

1. Game Catalog:
    * Allow manager to edit the game catalog at will (we do this by having at least a game and a shop entity created, we then add to the shop's game catalogue, an existing game from the database)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/d6493dd2-eb64-4763-b36b-8acc2636746e)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/22334d87-ce07-4e63-bef9-d7df19fc98de)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/5fed8b1f-bb2d-4ea6-a864-af7060b29e29)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/e18f5341-dec4-4ed5-b558-89b0cf739a10)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/e3baeebd-41fa-4de8-9ba5-88e08766521a)

2. Food and beverage catalog:
    * Allow manager to edit the food and drinks menu at will (we do this by having at least a refreshment and a shop entity created, we then add to the shop's food/drink menu, an existing refreshment from the database)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/bb5c3610-c9fc-42a3-b8cb-54d4bf0390e5)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/5fed8b1f-bb2d-4ea6-a864-af7060b29e29)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/4455bf65-53d6-463e-9c10-884b02669867)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/1f090d85-76d0-442f-96d9-8f105e652fa0)

3. Event manager:
    * Allow manager to add(host) new events to the at will (we do this by creating an entity with a POST command, before this we must first add a game enitity and a shop enitity)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/d6493dd2-eb64-4763-b36b-8acc2636746e)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/22334d87-ce07-4e63-bef9-d7df19fc98de)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/5fed8b1f-bb2d-4ea6-a864-af7060b29e29)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/b3313eef-593a-4f3d-b2e5-dbcc33354b40)

4. Join Event:
    * Allow user to join an event (we do this by having at least a client and an event entity created, we then add to the client's event reservations, an existing event from the database)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/d6493dd2-eb64-4763-b36b-8acc2636746e)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/22334d87-ce07-4e63-bef9-d7df19fc98de)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/5fed8b1f-bb2d-4ea6-a864-af7060b29e29)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/b3313eef-593a-4f3d-b2e5-dbcc33354b40)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/47f7df70-4a10-4306-9c8e-7f3715d43ecd)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/c8de7d64-e880-49c0-932e-dc8194377e6a)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/8d35c4a8-a608-449b-a71a-dec057ce7ec7)

5. Client manager:
    * Allow user to register as client (we do this by simply creating an entity with a POST command, the only requirements are that the username is unique and the password has at least 10 characters, at least a big and a small letter and at least a special character)
    ![image](https://github.com/vladyc1234/BoardGameLocal/assets/73032808/47f7df70-4a10-4306-9c8e-7f3715d43ecd)
