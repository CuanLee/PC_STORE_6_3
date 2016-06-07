package cuanlee.pc_store.factories.impl;

import cuanlee.pc_store.domain.Employee.ContactDetails;
import cuanlee.pc_store.factories.ContactDetailsFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class ContactDetailsFactoryImpl implements ContactDetailsFactory {
    private static ContactDetailsFactoryImpl factory = null;

    private ContactDetailsFactoryImpl(){

    }

    public static ContactDetailsFactoryImpl getInstance(){
        if (factory == null)
            factory = new ContactDetailsFactoryImpl();

        return factory;
    }

    public ContactDetails createContactDetails(String telephone, String email){
        ContactDetails contactDetails = new ContactDetails.Builder()
                .email(email)
                .telephone(telephone)
                .build();
        return contactDetails;
    }
}
