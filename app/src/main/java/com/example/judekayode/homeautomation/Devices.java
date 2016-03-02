package com.example.judekayode.homeautomation;

public class Devices {

	private String device_group_name;
	private String device_group_count;
	private String device_on_count;
    private Integer device_group_image;
    private String device_group_id;

	public Devices(String device_group_name, String device_group_count, String device_on_count,
                   Integer device_group_image, String device_group_id
				   ) {

		this.device_group_name = device_group_name;
        this.device_group_count = device_group_count;
        this.device_on_count = device_on_count;
        this.device_group_image = device_group_image;
        this.device_group_id = device_group_id;
	}

    public void setDevice_group_name(String device_group_name)
    {
        this.device_group_name = device_group_name;
    }

    public String getDevice_group_name()
    {
        return device_group_name;
    }

    public void setDevice_group_count(String device_group_count)
    {
        this.device_group_count = device_group_count;
    }

    public String getDevice_group_count()
    {
        return device_group_count;
    }

    public void setDevice_on_count(String device_on_count)
    {
        this.device_on_count = device_on_count;
    }



    public String getDevice_on_count()
    {
        return device_on_count;
    }

    public void setDevice_group_image(Integer device_group_image)
    {
        this.device_group_image = device_group_image;
    }

    public Integer getDevice_group_image()
    {
        return device_group_image;
    }

    public void setDevice_group_id(String device_group_id)
    {
        this.device_group_id = device_group_id;
    }

    public String getDevice_group_id()
    {
        return device_group_id;
    }


}