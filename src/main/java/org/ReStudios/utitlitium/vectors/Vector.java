package org.ReStudios.utitlitium.vectors;

@SuppressWarnings("unused")
public abstract class Vector {

    public static Vector2 of(int x, int y){
        return new Vector2(x,y);
    }
    public static Vector2f of(float x, float y){
        return new Vector2f(x,y);
    }
    public static Vector2d of(double x, double y){
        return new Vector2d(x,y);
    }

    public static Vector3 of(int x, int y, int z){
        return new Vector3(x,y,z);
    }
    public static Vector3f of(float x, float y, float z){
        return new Vector3f(x,y,z);
    }
    public static Vector3d of(double x, double y, double z){
        return new Vector3d(x,y,z);
    }


}
