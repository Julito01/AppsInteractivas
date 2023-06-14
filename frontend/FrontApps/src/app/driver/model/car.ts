export class Car {
  public id?: number
  public plate: string

  constructor(plate: string, id?: number) {
    this.id = id
    this.plate = plate
  }
}
