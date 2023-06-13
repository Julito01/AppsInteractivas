import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { firstValueFrom } from 'rxjs'
import { environment } from 'src/environments/environment'
import { Car } from '../model/car'

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private httpClient: HttpClient) { }

  public async getCars(): Promise<Car[]> {
    return await firstValueFrom(this.httpClient.get<Car[]>(`${environment.apiUrl}/cars`))
  }
}
