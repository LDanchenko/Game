package com.ldv.courseofexchange.rest;

import com.ldv.courseofexchange.rest.models.PrivatBankCourse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface PrivatBankApi {

    @GET("/p24api/pubinfo?json&exchange&coursid=5")

    Call <List<PrivatBankCourse>> getPrivatCurrentCourse();
}
