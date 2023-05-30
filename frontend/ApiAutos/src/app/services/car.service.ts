import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { Car } from "../models/car";

@Injectable({
    providedIn: 'root'
})

export class CarService {
    private url = 'http://localhost:8080/car'
    constructor(private http: HttpClient){}

    getAll() : Observable <any> {
        return this.http.get(this.url )
    }

    delete(id: number) : Observable <any>{
        return this.http.post(this.url + "/" + id + "/delete", null )
    }

    add(car: Car) : Observable <any>{
        return this.http.post(this.url,car)
    }
    edit(car: Car, id : number) : Observable <any>{
        return this.http.post(this.url + '/' + id + '/update', car)
    }

}