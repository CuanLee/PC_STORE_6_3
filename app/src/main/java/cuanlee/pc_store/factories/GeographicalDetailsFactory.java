package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.Employee.GeographicalDetails;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface GeographicalDetailsFactory {
    GeographicalDetails createGeographicalDetails(String country, String province, String city, String suburb, String street, Integer houseNumber);
}
