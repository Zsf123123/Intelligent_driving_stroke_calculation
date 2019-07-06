package com.muheda.domain;


/**
 * @desc  发生的事件的紧急级别
 */
public enum UrgentLevel {

    FIRST(1),SECONDARY(2),THIRD(3);

    private final int  value;

    UrgentLevel(int value) {
        this.value = value;
    }

    private  static  UrgentLevel[] urgentLevels = UrgentLevel.values();

    public int getValue() {
        return value;
    }


    /**
     * @param level
     * @return
     */
    public  static UrgentLevel findUrgentLevel(int level){

        for (UrgentLevel urgentLevel  : urgentLevels) {

            if(level == urgentLevel.value ){
                return urgentLevel;
            }
        }

        return null;
    }



}
