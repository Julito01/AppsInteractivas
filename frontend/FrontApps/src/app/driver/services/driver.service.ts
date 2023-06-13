import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { firstValueFrom } from 'rxjs'
import { environment } from 'src/environments/environment'
import { Driver } from '../model/driver'

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  constructor(private httpClient: HttpClient) { }

  public async getDrivers(): Promise<Driver[]> {
    return await firstValueFrom(this.httpClient.get<Driver[]>(`${environment.apiUrl}/drivers/cars`))
  }

  public async addDriver(driver: Driver): Promise<Driver> {
    return await firstValueFrom(this.httpClient.post<Driver>(`${environment.apiUrl}/drivers`, driver))
  }
}
