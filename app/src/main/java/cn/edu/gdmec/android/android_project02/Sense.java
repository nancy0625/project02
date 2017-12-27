package cn.edu.gdmec.android.android_project02;

/**
 * Created by asus on 2017/12/25.
 */

public class Sense {
    public int ID;
    public int Temperature;
    public int Humidity;
    public int LightIntensity;
    public int CO2;
    public int Pm;
    public int Status;
    public String timer;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public int getHumidity() {
        return Humidity;
    }

    public void setHumidity(int humidity) {
        Humidity = humidity;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int lightIntensity) {
        LightIntensity = lightIntensity;
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public int getPm() {
        return Pm;
    }

    public void setPm(int pm) {
        Pm = pm;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }


}
