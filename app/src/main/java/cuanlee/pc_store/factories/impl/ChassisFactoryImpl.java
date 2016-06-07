package cuanlee.pc_store.factories.impl;

import cuanlee.pc_store.domain.PC.Chassis;
import cuanlee.pc_store.factories.ChassisFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class ChassisFactoryImpl implements ChassisFactory {
    private static ChassisFactoryImpl factory = null;

    private ChassisFactoryImpl(){

    }

    public static ChassisFactoryImpl getInstance(){
        if (factory == null)
            factory = new ChassisFactoryImpl();

        return factory;
    }

    public Chassis createComputer(Long id, String code, String description, Integer active, Integer hddBays, Integer caseFans, Integer stock, String formFactor){
        Chassis chassis = new Chassis.Builder()
                .id(id)
                .code(code)
                .description(description)
                .hddBays(hddBays)
                .caseFans(caseFans)
                .formFactor(formFactor)
                .stock(stock)
                .active(active)
                .build();
        return chassis;
    }
}
