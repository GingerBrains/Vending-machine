# Candy Vending Machine

This project simulates a **candy vending machine** that allows users to purchase candies by depositing money. The machine supports four types of candies, each with a fixed price, and handles transactions, change management, and inventory tracking. It also logs all transactions and persists its state (inventory and coin bank) between sessions.

---

## Features

1. **Candy Selection:**
   - Users can choose from four types of candies:
     - Bertie Botts Every Flavor Beans ($1.0)
     - Chocolate Frogs ($2.5)
     - Exploding Bon Bons ($5.0)
     - Fudge Flies ($6.5)

2. **Money Handling:**
   - Accepts money in the form of dollars and cents.
   - Calculates and returns change if the deposited amount exceeds the candy price.
   - Voids the transaction and returns the full amount if insufficient change is available.

3. **Inventory Management:**
   - Tracks the quantity of each candy in stock.
   - Prevents sales if a candy is out of stock.

4. **Transaction Logging:**
   - Logs all successful and failed transactions for auditing purposes.

5. **State Persistence:**
   - Saves the machine's state (candy inventory and coin bank) to a file (`DataStore.dat`) on shutdown.
   - Restores the state from the file on restart.

6. **Error Handling:**
   - Handles invalid inputs and logs errors for debugging.

---

## How It Works

1. **User Interaction:**
   - The user deposits money and selects a candy from the menu.
   - The machine checks if the candy is in stock and if sufficient change is available.
   - If both conditions are met, the candy is dispensed, and change is returned.
   - If not, the transaction is voided, and the money is returned.

2. **Coin Bank:**
   - The machine maintains a coin bank with denominations:
     - $5 bills
     - $1 bills
     - 50-cent coins
     - 25-cent coins
   - The coin bank is updated after every transaction.

3. **Logging:**
   - All transactions (successful or failed) are logged in `candyLogs.log`.

4. **State Persistence:**
   - The machine's state is saved to `DataStore.dat` on shutdown and restored on restart.

---

## Code Structure

The project is organized into the following packages and classes:

### **`bootstrap` Package**
- **`Driver`:** The main class that runs the vending machine. It handles user interaction, transaction processing, and state persistence.

### **`domain` Package**
- **`Candy`:** Represents a candy with attributes like name, cost, and quantity.
- **`CandyBuilder`:** A builder class for creating `Candy` objects.
- **`CandyFactory`:** A factory class that creates predefined `Candy` objects.
- **`DataStore`:** Stores the machine's state, including the coin bank and candy inventory.
- **`Processor`:** Handles transaction logic, including money breakdown, change calculation, and inventory updates.

---

## Usage

### Prerequisites
- Java Development Kit (JDK) 8 or later.
- Apache Log4j for logging (included in the project).

### Running the Program
1. Clone the repository or download the source code.
2. Navigate to the project directory.
3. Compile and run the program:
   ```bash
   javac bootstrap/Driver.java
   java bootstrap/Driver
