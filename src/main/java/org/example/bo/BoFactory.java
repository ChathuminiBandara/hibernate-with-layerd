package org.example.bo;


import org.example.bo.custom.impl.StudentBoImpl;

public class BoFactory {

    private  static  BoFactory boFactory;

    private BoFactory (){
    }
    public static BoFactory getBoFactory(){
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }
    public enum  BOTypes{
        STUDENT
    }
    public SuperBo getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return new StudentBoImpl();
            default:
                return null;
        }
    }



}
