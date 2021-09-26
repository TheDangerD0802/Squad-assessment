package files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class systems {
    private HashMap<vehicle, Integer> carMap;
    private HashMap<Integer, HashSet<Integer>> ageChart;
    private HashMap<Integer, vehicle> slotChart;
    private PriorityQueue<Integer> slotsAvailable;
    private int size;


    /* 
        Constructor of systems initializes all variable and Complexity id O(n) where n is size of parking lot. 
     */
    public systems(int n) {
        ArrayList<Integer> slots = new ArrayList<>();
        int i=1;
        while(i<=n)
        {
            slots.add(i);
            i++;
        }
        slotsAvailable = new PriorityQueue<>(slots);
        carMap = new HashMap<>();
        slotChart = new HashMap<>();
        ageChart = new HashMap<>();
        size = n;
    }


    /*       
        AddCar Method takes arguments registration id and Driver Age. This method is used to add new car in parking lot.
        To make sure of availability of slot we use checkAvailabilty method.
        Time Complexity of this method : O(log n) where n is size of parking lot.
     */
    public int AddCar(String registrationId, Integer driverAge) {
        int spot = getSlot();
        vehicle car = new vehicle(registrationId, driverAge);
        carMap.put(car, spot);
        slotChart.put(spot, car);
        if (!ageChart.containsKey(driverAge)) {
            ageChart.put(driverAge, new HashSet<>());
        }
        ageChart.get(driverAge).add(spot);
        return spot;
    }

    /*
        chechAvailability method checks for empty space present in parking lot.
        Time Complexity : o(1) constant .
     */

    public boolean checkAvailability() {
        return !slotsAvailable.isEmpty();
    }

    /*
        GetSlot method is used to take acquire a slot in the parking lot
        It's time complexity is O(logn)
     */

    private int getSlot() {
        return slotsAvailable.poll();
    }

    /*
        getSlotNumbersByAge method is used to get the list of slots that are acquired by people of certain age.
        It's time complexity is O(1)
     */

    public ArrayList<Integer> getSlotNumbersByAge(int driverAge) {
        if (ageChart.get(driverAge) == null)
            return null;
        return new ArrayList<>(ageChart.get(driverAge));
    }

    /*
        getSlotNumberByCar method takes registrationid as a argument and is used to find the slot number of a car parked in the parking lot.
        It returns null if the car is not found in the parking lot.
        Time complexity : O(1)
     */

    public Integer getSlotNumberByCar(String registrationId) {
        vehicle car = new vehicle(registrationId, 0);
        return carMap.getOrDefault(car, null);
    }

    /*              
        VacateSlot help to vacate any slot present in the parking lot , if already vacates return null .
        Time Complexity : O(log n).
        It also consider isInRange Method that check whether the slot is valid or not by comparing it with the parking lot size.
    */

    public vehicle vacateSlot(int slotnumber) {
        vehicle car = slotChart.get(slotnumber);
        if (car == null)
            return null;
        slotChart.remove(slotnumber);
        HashSet<Integer> slots = ageChart.get(car.getDriverAge());
        ageChart.get(car.getDriverAge()).remove(slotnumber);
        slots.remove(slotnumber);
        if (slots.isEmpty())
            ageChart.remove(car.getDriverAge());
        carMap.remove(car);
        slotsAvailable.add(slotnumber);
        return car;
    }

    /*
        This method checks whether or not the slot is valid or not.
        It's time complexity is O(1).
     */

    public boolean isInRange(int slotnumber) {
        return slotnumber <= size;
    }

    /*
        getRegistrationNumberByAge method returns the list of registration Id's of those cars whose driver's age has been provided.
        If there is no driver with that age then it will return null
        It's time complexity is O(1)
     */

    public ArrayList<String> getRegistrationNumberByAge(int driverAge) {
        ArrayList<Integer> slotAge = getSlotNumbersByAge(driverAge);
        if (slotAge == null)
            return null;
        ArrayList<String> registrationIds = new ArrayList<>();
        for (Integer slot : slotAge) {
            registrationIds.add(slotChart.get(slot).getRegistrationId());
        }
        if (registrationIds.isEmpty())
            return null;
        return registrationIds;
    }
}
