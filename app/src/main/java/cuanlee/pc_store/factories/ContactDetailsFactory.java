package cuanlee.pc_store.factories;


import cuanlee.pc_store.domain.Employee.ContactDetails;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface ContactDetailsFactory {
    ContactDetails createContactDetails(String telephone, String email);
}
