import { Car } from "./car"

export class DriverResponse {
  public id: number
  public name: string
  public lastName: string
  public car: Car

  constructor(id: number, name: string, lastName: string, car: Car) {
    this.id = id
    this.name = name
    this.lastName = lastName
    this.car = car
  }
}

export class Driver {
  public name: string
  public lastName: string
  public carId: number
  public id?: number

  constructor(name: string, lastName: string, carId: number, id?: number) {
    this.name = name
    this.lastName = lastName
    this.carId = carId
    this.id = id
  }
}
