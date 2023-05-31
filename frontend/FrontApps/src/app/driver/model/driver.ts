import { Car } from "./car"

export class Driver {
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
