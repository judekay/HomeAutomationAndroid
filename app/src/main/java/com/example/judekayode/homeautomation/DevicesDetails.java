package com.example.judekayode.homeautomation;

public class DevicesDetails {

	private String device_id;
	private String device_name;
    private String device_type_id;
    private String device_value;

	public DevicesDetails(String device_id, String device_name,String device_type_id,
                           String device_value
    ) {
        this.device_id = device_id;
        this.device_name = device_name;
        this.device_type_id = device_type_id;
        this.device_value = device_value;

	}

    public void setDevice_id(String device_id)
    {
        this.device_id = device_id;
    }

    public String getDevice_id()
    {
        return device_id;
    }

    public void setDevice_name(String device_name)
    {
        this.device_name = device_name;
    }

    public String getDevice_name()
    {
        return device_name;
    }


    public void setDevice_value(String device_value)
    {
        this.device_value = device_value;
    }

    public String getDevice_value()
    {
        return device_value;
    }

    public void setDevice_type_id(String device_type_id) { this.device_type_id = device_type_id; }

    public String getDevice_type_id() { return device_type_id; }

}