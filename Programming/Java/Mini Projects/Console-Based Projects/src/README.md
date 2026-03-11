# 🚗 Car Rental Management System (Java)

A **console-based Java program** that lets users **rent cars, return cars, and view rental details** using a menu-driven interface.

---

## 💻 Source Code (Short Version)

```java
import java.util.*;

class Car {
    String id, brand, model; double price; boolean available=true;
    Car(String id, String brand, String model, double price){ this.id=id; this.brand=brand; this.model=model; this.price=price; }
    double calcPrice(int days){ return price*days; }
    void rent(){ available=false; } void returnCar(){ available=true; }
}

class Customer { String id,name; Customer(String id,String name){this.id=id;this.name=name;} }

class Rental { Car car; Customer customer; int days; Rental(Car c,Customer cu,int d){car=c;customer=cu;days=d;} }

class CarRentalSystem {
    List<Car> cars=new ArrayList<>(), rented=new ArrayList<>(); 
    List<Rental> rentals=new ArrayList<>();
    void addCar(Car c){cars.add(c);}
    void addCustomer(Customer cu){/* optional for demo */ }
    void rentCar(Car c,Customer cu,int days){if(c.available){c.rent(); rentals.add(new Rental(c,cu,days));}}
    void returnCar(Car c){c.returnCar(); rentals.removeIf(r->r.car==c);}
    void showRentals(){ if(rentals.isEmpty()){System.out.println("No rentals"); return;} 
        for(Rental r:rentals) System.out.println(r.customer.name+" rents "+r.car.brand+" "+r.car.model+" for "+r.days+" days"); }
    void menu(){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("\n1.Rent 2.Return 3.Show Rentals 4.Exit\nChoice:");
            int ch=sc.nextInt(); sc.nextLine();
            if(ch==1){ System.out.print("Customer name: "); String name=sc.nextLine();
                       System.out.println("Available cars:"); for(Car c:cars) if(c.available) System.out.println(c.id+"-"+c.brand+" "+c.model);
                       System.out.print("Car ID: "); String id=sc.nextLine(); System.out.print("Days: "); int days=sc.nextInt(); sc.nextLine();
                       Car sel=null; for(Car c:cars) if(c.id.equals(id) && c.available){sel=c; break;}
                       if(sel!=null) rentCar(sel,new Customer("CUS"+(new Random().nextInt(100)),name),days); }
            else if(ch==2){ System.out.print("Car ID to return: "); String id=sc.nextLine(); 
                            for(Car c:cars) if(c.id.equals(id) && !c.available) {returnCar(c); break;} }
            else if(ch==3) showRentals();
            else if(ch==4){ System.out.println("Exiting..."); break; }
        }
    }
}

public class CarRentalManagementSystem {
    public static void main(String[] args){
        CarRentalSystem crs=new CarRentalSystem();
        crs.addCar(new Car("C1","Toyota","Corolla",50));
        crs.addCar(new Car("C2","Honda","Civic",60));
        crs.menu();
    }
}
