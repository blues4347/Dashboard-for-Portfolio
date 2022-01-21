package me.lijf.service;

import me.lijf.PriceData;

import java.util.List;

public interface FetchDataService {
    List<PriceData> refresh();
}
