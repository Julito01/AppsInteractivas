import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { firstValueFrom } from 'rxjs'
import { environment } from 'src/environments/environment'
import { Driver, DriverResponse } from '../model/driver'
import { JsonResponse } from '../model/json-response'

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  constructor(private httpClient: HttpClient) { }

  public async getDrivers(): Promise<DriverResponse[]> {
    return await firstValueFrom(this.httpClient.get<DriverResponse[]>(`${environment.apiUrl}/drivers/cars`))
  }

  public async addDriver(driver: Driver): Promise<Driver> {
    return await firstValueFrom(this.httpClient.post<Driver>(`${environment.apiUrl}/drivers`, driver))
  }

  public async updateDriver(driver: Driver): Promise<Driver> {
    return await firstValueFrom(this.httpClient.put<Driver>(`${environment.apiUrl}/drivers/${driver.id}`, driver))
  }

  public async deleteDriver(driverId: number): Promise<JsonResponse> {
    return await firstValueFrom(this.httpClient.delete<JsonResponse>(`${environment.apiUrl}/drivers/${driverId}`))
  }
}
