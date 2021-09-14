# Vending-machine
The gumball machine is a vending machine that accepts cash and coins. It has 4 types of sweets – each with a fixed price.

Features:
1. Machine should allow the user to deposit money in cash and coins. For e.g. ( {cash:5.0} – is Rs
5.00 in a Rs5 note. {coin:5.25} is 1 coin of Rs 5.0 and 1 coin of 25 paise. )
2. If money is deposited, the machine should allow the user to select one of 4 sweets to purchase.
3. Once a sweet selection is made,
  3.1. if the money deposited is the exact price of the sweet → the machine will deliver that sweet and decrement its own inventory of that sweet by 1.
  3.2. if the money deposited is more than the price of the sweet → the machine will return the remaining change along with the sweet.
  3.3. If the money deposited is more than the price of the sweet and the machine does not have enough coins to return the exact change, it will void the           
       transaction and return the full amount back to the user – it will not release the sweet.
  3.4. If the machine is out of a particular sweet then it will either prevent the user from selecting that sweet or it will return the full amount deposited along        with a message that the machine is out of stock for the sweet selected.
4. Once the money is returned and the sweet is given out – the transaction is complete.
5. Machine should keep a log of all the transactions it has completed or cancelled.
6. Machine will begin with an initial inventory of 10 sweets of each kind and money in coins for change → ( 50 coins each of denomination)
7. You will create a command-line interface to enter all these options, the menu should repeat itself in a loop once a transaction is complete or cancelled.
8. You will allow the user to shut down the machine. This will exit the program.
9. When the machine is shut down – it will save its current state i.e. sweets inventory and coins inventory in a serialized object.
10. When you restart the machine, the machine will start back up from the saved off state.
