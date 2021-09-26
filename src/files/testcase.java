package files;

import java.util.ArrayList;

public class testcase {

    private static systems system;

    /*
    This method is going to break entire query sentence to know about each of the query.
     */

   
    public static void division(String query)
    {
    	String[] querydivision = query.split(" ");
    	try {
    		if ("Park".equals(querydivision[0])) {
                String registrationId = querydivision[1];
                int driverAge = Integer.parseInt(querydivision[3]);
                parkCar(registrationId, driverAge);
            }else if ("Leave".equals(querydivision[0])) {
                int slot = Integer.parseInt(querydivision[1]);
                vacateSlot(slot);
            } else if ("Slot_numbers_for_driver_of_age".equals(querydivision[0])) {
                int driverAge = Integer.parseInt(querydivision[1]);
                slotByAge(driverAge);
            } else if ("Vehicle_registration_number_for_driver_of_age".equals(querydivision[0])) {
                 int slot = Integer.parseInt(querydivision[1]);
                 registrationIdByAge(slot);
             }else if ("Slot_number_for_car_with_number".equals(querydivision[0])) {
                 String registrationId = querydivision[1];
                 slotByCar(registrationId);
             }else  if ("Create_parking_lot".equals(querydivision[0])) {
                 int n = Integer.parseInt(querydivision[1]);
                 createParkingLot(n);
             }else {
                 System.out.println("Invalid command");
             }
    	}catch (Exception e) {
            System.out.println("Invalid command");
        }
    }
    // creation of a new parking lot system with n size

    private static void createParkingLot(int n) {
        system = new systems(n);
        System.out.println("Created parking of " + n + " slots");
    }

    /* 
        This methods checks if there is space or not in parking lot ,  if available park to nearest location of entry and show details
     */

    private static void parkCar(String registrationId, int driverAge) {
        if (!system.checkAvailability()) {
            System.out.println("Parking lot already full");
            return;
        }
        else {
        int spot = system.AddCar(registrationId, driverAge);
        System.out.println("Car with vehicle registration number \"" + registrationId + "\" has been parked at slot number " + spot);
        }
    }
    
    /*
    Checks validity of input slot and if valid then vacates the spot and print details of parked car if present.
 */

		private static void vacateSlot(int slot) {
		    if (!system.isInRange(slot)) {
		        System.out.println("Invalid slot");
		    return;
		}
		vehicle car = system.vacateSlot(slot);
		if (car == null) {
		    System.out.println("Slot already vacant");
		} else 
			{System.out.println("Slot number " + slot + " vacated, the car with vehicle registration number \""
		                      + car.getRegistrationId()
		                      + "\" left the space, the driver of the car was of age " + car.getDriverAge());
		    	}
		}
    /*
    Requirement No 1
    This prints all registration numbers of the driver with certain age.
     */

    private static void registrationIdByAge(int driverAge) {
        ArrayList<String> registrationNumbers = system.getRegistrationNumberByAge(driverAge);
        if (registrationNumbers == null) {
            return;
        }
       int i = 0;
       while(i<registrationNumbers.size()) {
            System.out.print(registrationNumbers.get(i));
            if (i != registrationNumbers.size() - 1)
                System.out.print(", ");
            
            i++;
        }
        System.out.println();
    }


	/*
		Requirement No 2
 		Slot number in which a car with a given vehicle registration plate is parked. 
	 */

	private static void slotByCar(String registrationId) {
			Integer spot = system.getSlotNumberByCar(registrationId);
				if (spot == null)
					{
						return;
					}
				else
				{System.out.println(spot);}
	}


    /*
      Requirement No 3  
        If there is driver then it show list of all acquired slot by driver of particular age,
        
     */

    private static void slotByAge(int driverAge) {
        ArrayList<Integer> spots = system.getSlotNumbersByAge(driverAge);
        if (spots == null) {
            return;
        }
        int i=0;
        while(i<spots.size()) {
            System.out.print(spots.get(i));
            if (i != spots.size() - 1)
                System.out.print(",");
            i++;
        }
        System.out.println();
    }

   
    
    }
   