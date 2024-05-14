package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.DispatchRequest;
import com.chowe.depotmanagementsystem.api.dto.DispatchResponse;
import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.api.dto.RouteRequest;
import com.chowe.depotmanagementsystem.domain.Dispatch;
import com.chowe.depotmanagementsystem.domain.Route;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RouteService {
    ResponseEntity<Response> addNewRoutes(RouteRequest request);
    ResponseEntity<Response> createDispatch(DispatchRequest request);

    List<Dispatch> getAllDispatches();

    List<Route> getAllRoutes();


}
