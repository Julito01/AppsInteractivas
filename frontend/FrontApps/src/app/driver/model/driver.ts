import { Car } from "./car"

export class Driver {
  public id?: number
  public name: string
  public lastName: string
  public car?: Car
  public carId: number

  constructor(name: string, lastName: string, carId: number, car?: Car, id?: number) {
    this.id = id
    this.name = name
    this.lastName = lastName
    this.carId = carId
    this.car = car
  }
}
