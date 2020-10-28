/**
 * Copyright (C), 2015-2020
 * FileName: PlaceOfBirth
 * Author:   luo.yongqian
 * Date:     2020/10/14 22:09
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/10/14 22:09      1.0.0               创建
 */
package framework.dto;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/10/14
 * @since 1.0.0
 */
public class PlaceOfBirth {

    private String city;
    private String country;

    public PlaceOfBirth(String city) {
        this.city=city;
    }

    public PlaceOfBirth(String city, String country) {
        this(city);
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        this.city = s;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}