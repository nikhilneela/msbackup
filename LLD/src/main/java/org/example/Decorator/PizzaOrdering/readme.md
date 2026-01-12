Scenario: You're building a pizza ordering system.

Base pizza costs `$8` and has description "Plain Pizza"
Customers can add toppings: Cheese (+`$1.5`), Pepperoni (+`$2`), Mushrooms (+`$1`), Olives (+`$0.75`)
Each topping should be added to both description and cost
Customer should be able to add multiple toppings of the same type

Requirements:

Create a Pizza interface with getDescription() and getCost() methods
Implement base pizza class
Create decorators for each topping
Show example usage where you create a pizza with Cheese, Pepperoni, and double Mushrooms

Your turn! Write the complete code. Once you're done, I'll review it and give you the next scenario.
Interview Tip: In LLD interviews, they often ask "How would you handle if a topping runs out of stock?" Think about how you'd modify your decorator to handle this.