import { Component, OnInit, TemplateRef } from '@angular/core'
import { Driver } from '../model/driver'
import { DriverService } from '../services/driver.service'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { CarService } from '../services/car.service'
import { Car } from '../model/car'
import { HttpErrorResponse } from '@angular/common/http'

@Component({
  selector: 'app-driver-list',
  templateUrl: './driver-list.component.html',
  styles: [
  ]
})
export class DriverListComponent implements OnInit {
  public drivers: Driver[] = []
  public cars: Car[] = []
  public driverForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    lastName: ['', Validators.required],
    car: ['', Validators.required]
  })
  public controlValidities: { [key: string]: boolean } = {}
  public driverCreated: boolean = false
  public errorMessage: string = ''

  constructor(private driverService: DriverService, private carService: CarService, private modalService: NgbModal, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.driverService.getDrivers().then((drivers) => {
      this.drivers = drivers
    })
    this.carService.getCars().then((cars) => {
      this.cars = cars
    })
  }

  open(content: TemplateRef<NgbModal>) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result
      .then(
        (result) => {
          this.addDriver()
        },
        () => { },
      )
  }

  public async addDriver() {
    const driver: Driver = new Driver(
      this.driverForm.get('name')?.value,
      this.driverForm.get('lastName')?.value,
      this.driverForm.get('car')?.value
    )

    try {
      await this.driverService.addDriver(driver)

      this.driverCreated = true
      this.driverForm.reset()
      this.drivers = await this.driverService.getDrivers()
      this.cars = await this.carService.getCars()

    } catch (err: unknown) {
      const error = err as HttpErrorResponse
      this.errorMessage = error.error.message
    }
  }

  public checkInputValidation(controlName: string): void {
    const control = this.driverForm.get(controlName)
    this.controlValidities[controlName] = control?.invalid && (control?.dirty || control?.touched) || false
  }
}
