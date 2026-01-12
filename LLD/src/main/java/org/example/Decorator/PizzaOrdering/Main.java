package org.example.Decorator.PizzaOrdering;

public class Main {

//    public static void decoratorRunner1() {
//        IPizza pizza = new BasePizza("Pizza", 8);
//        IPizza cheesePizza = new DecoratedPizza("Cheese", 1.5, pizza);
//        IPizza pepporoniCheesePizza = new DecoratedPizza("Pepporoni", 2, cheesePizza);
//        IPizza mushroomsPepporoniCheesePizza = new DecoratedPizza("Mushrooms", 1, pepporoniCheesePizza);
//        IPizza olivesMushroomsPepporoniCheesePizza = new DecoratedPizza("Olives", 0.75, mushroomsPepporoniCheesePizza);
//
//        System.out.println(olivesMushroomsPepporoniCheesePizza.getDescription() + " cost is : " + olivesMushroomsPepporoniCheesePizza.getPrice());
//    }
//
//    public static void decoratorRunner2() {
//        IPizza pizza = new BasePizza("Pizza", 8);
//        IPizza cheesePizza = new DecoratedPizza("Cheese", 1.5, pizza);
//        IPizza pepporoniCheesePizza = new DecoratedPizza("Pepporoni", 2, cheesePizza);
//        IPizza mushroomsPepporoniCheesePizza = new DecoratedPizza("Mushrooms", 1, pepporoniCheesePizza);
//        IPizza mushroomsPepporoniCheesePizza1 = new DecoratedPizza("Mushrooms", 1, mushroomsPepporoniCheesePizza);
//
//        System.out.println(mushroomsPepporoniCheesePizza1.getDescription() + " cost is : " + mushroomsPepporoniCheesePizza1.getPrice());
//    }


    public static void decoratorRunner3() {
        IPizza pizza = new BasePizza("Pizza", 8);
        IPizza cheesePizza = new ToppingsDecorator("Cheese", 1.5, pizza, 1);
        IPizza pepporoniCheesePizza = new ToppingsDecorator("Pepporoni", 2, cheesePizza, 1);
    }

    public static void main(String[] args) {
        //decoratorRunner1();
        //decoratorRunner2();
        decoratorRunner3();
    }
}
