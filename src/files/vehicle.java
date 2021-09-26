package files;

import files.vehicle;

public class vehicle {


    // This specifies the age of the driver
    private Integer driverAge;
    
    // This specifies the unique registration Id of the car
    private String registrationId;


    public vehicle(String registrationId, Integer driverAge) {
    	this.driverAge = driverAge;
    	this.registrationId = registrationId;
        
    }

    /* 
    This method is overriden because the cars have a unique id in the form of registration Id
   and hence the registration Id can be used to compare whether or not the two objects of the class
   belong to the same car or not.
    */
   @Override
   public boolean equals(Object car) {
       if (car instanceof vehicle) {
           return this.registrationId.equals(((vehicle) car).registrationId);
       }
       return false;
   }
   

    /* This method is overridden because the car is being searched by the hashmap
    and the hashmap uses the hashcode to calculate the bucket of the object.
     */
    @Override
    public int hashCode() {
        return this.registrationId.hashCode();
    }
    
    
    public String getRegistrationId() {
        return this.registrationId;
    }
    
    public int getDriverAge() {
        return this.driverAge;
    }

    
}
