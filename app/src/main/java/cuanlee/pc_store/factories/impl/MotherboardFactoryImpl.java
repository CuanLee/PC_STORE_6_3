package cuanlee.pc_store.factories.impl;


import cuanlee.pc_store.domain.PC.Motherboard;
import cuanlee.pc_store.factories.MotherboardFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class MotherboardFactoryImpl implements MotherboardFactory {
    private static MotherboardFactoryImpl factory = null;

    private MotherboardFactoryImpl(){

    }

    public static MotherboardFactoryImpl getInstance(){
        if (factory == null)
            factory = new MotherboardFactoryImpl();

        return factory;
    }

    public Motherboard createMotherboard(Long id, String code, String description, String chipset, Integer sataPorts, Integer usb2, Integer usb3, String formFactor, Integer stock, Integer active){
        Motherboard motherboard = new Motherboard.Builder()
                .id(id)
                .code(code)
                .description(description)
                .chipset(chipset)
                .sataPorts(sataPorts)
                .usb2(usb2)
                .usb3(usb3)
                .formFactor(formFactor)
                .stock(stock)
                .active(active)
                .build();
        return motherboard;
    }
}
