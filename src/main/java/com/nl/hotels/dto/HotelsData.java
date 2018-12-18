package com.nl.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class HotelsData implements Serializable {
    String name;
    String address;
    String phone;
    Double rate;
}
