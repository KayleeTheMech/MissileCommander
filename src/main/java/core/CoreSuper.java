package core;

import java.util.Observable;

public abstract class CoreSuper extends Observable {

    public Missile[] removeMissile(Missile o, Missile[] arr) {
        Missile[] newObjectArray = new Missile[arr.length - 1];
        boolean found = false;
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == o) {
                found = true;
                k = i;
            }
        }
        if (found) {
            for (int i = 0; i < arr.length; i++) {
                if (i < k) newObjectArray[i] = arr[i];
                else if (i > k) newObjectArray[i - 1] = arr[i];
                else {
                }
            }
            return newObjectArray;
        } else return arr;
    }

    public Missile[] addMissile(Missile o, Missile[] arr) {
        Missile[] newObjectArray = new Missile[arr.length + 1];
        for (int i = 0; i < arr.length; i++) newObjectArray[i] = arr[i];
        newObjectArray[arr.length] = o;
        return newObjectArray;
    }

    public UFO[] removeUFO(UFO o, UFO[] arr) {
        UFO[] newObjectArray = new UFO[arr.length - 1];
        boolean found = false;
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == o) {
                found = true;
                k = i;
            }
        }
        if (found) {
            for (int i = 0; i < arr.length; i++) {
                if (i < k) newObjectArray[i] = arr[i];
                else if (i > k) newObjectArray[i - 1] = arr[i];
                else {
                }
            }
            return newObjectArray;
        } else return arr;
    }


    public UFO[] addUFO(UFO o, UFO[] arr) {
        UFO[] newObjectArray = new UFO[arr.length + 1];
        for (int i = 0; i < arr.length; i++) newObjectArray[i] = arr[i];
        newObjectArray[arr.length] = o;
        return newObjectArray;
    }

    public Explosion[] removeExplosion(Explosion o, Explosion[] arr) {
        Explosion[] newObjectArray = new Explosion[arr.length - 1];
        boolean found = false;
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == o) {
                found = true;
                k = i;
            }
        }
        if (found) {
            for (int i = 0; i < arr.length; i++) {
                if (i < k) newObjectArray[i] = arr[i];
                else if (i > k) newObjectArray[i - 1] = arr[i];
                else {
                }
            }
            return newObjectArray;
        } else return arr;
    }


    public Explosion[] addExplosion(Explosion o, Explosion[] arr) {
        Explosion[] newObjectArray = new Explosion[arr.length + 1];
        for (int i = 0; i < arr.length; i++) newObjectArray[i] = arr[i];
        newObjectArray[arr.length] = o;
        return newObjectArray;
    }
}




