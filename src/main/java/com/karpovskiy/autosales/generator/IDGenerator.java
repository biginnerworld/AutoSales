package com.karpovskiy.autosales.generator;

import java.util.UUID;

/**
 * This class generates a string ID
 * */
public class IDGenerator {
    public static String generateID(){
        return UUID.randomUUID().toString();
    }
}