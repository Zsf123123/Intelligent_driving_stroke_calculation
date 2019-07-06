package com.muheda.domain;


/**
 *
 * @desc 车辆发生了哪一种事件的枚举，目前分别有急加速，急减速，急转弯
 *
 */
public enum EventType {



    /**
     * @desc 0:急加速 1:急减速 2:急转弯
     *
     */
    RAPIDACCE(0),RAPIDDECE(1),SHARPTURN(2),NONE(-1);

    private final int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EventType[] getValues() {
        return values;
    }

    private static   EventType[] values = EventType.values();


    /**
     * @desc 数值反匹配枚举
     * @param type
     * @return
     */
    public static  EventType findEnumType(int type){

        for (EventType  eventType : values) {

            if(type == eventType.value){
                return eventType;
            }
        }

        return null;
    }



}
