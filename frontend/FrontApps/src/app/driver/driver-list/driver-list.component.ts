import { Component, OnInit, TemplateRef } from '@angular/core'
import { Driver, DriverResponse } from '../model/driver'
import { DriverService } from '../services/driver.service'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { CarService } from '../services/car.service'
import { Car } from '../model/car'
import { HttpErrorResponse } from '@angular/common/http'
import { JsonResponse } from '../model/json-response'

@Component({
  selector: 'app-driver-list',
  templateUrl: './driver-list.component.html',
  styles: [
  ]
})
export class DriverListComponent implements OnInit {
  public drivers: DriverResponse[] = []
  private driver: DriverResponse | undefined = <DriverResponse>{}
  public isEdit: boolean = false
  public cars: Car[] = []
  public driverForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    lastName: ['', Validators.required],
    car: ['', Validators.required]
  })
  public controlValidities: { [key: string]: boolean } = {}
  public driverCreated: boolean = false
  public driverEdited: boolean = false
  public errorMessage: string = ''
  public jsonResponse: JsonResponse = <JsonResponse>{}

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
          if (this.isEdit) {
            this.editDriver()
            this.isEdit = false
          } else {
            this.addDriver()
          }
        },
        () => {
          this.driverForm.reset()
          this.isEdit = false
        },
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
    } catch (err: unknown) {
      const error = err as HttpErrorResponse
      this.errorMessage = error.error.message
    }
  }

  private async editDriver() {
    const driver: Driver = new Driver(
      this.driverForm.get('name')?.value,
      this.driverForm.get('lastName')?.value,
      this.driverForm.get('car')?.value,
      this.driver?.id
    )

    try {
      await this.driverService.updateDriver(driver)

      this.driverEdited = true
      this.driverForm.reset()
      this.drivers = await this.driverService.getDrivers()
    } catch (err: unknown) {
      const error = err as HttpErrorResponse
      this.errorMessage = error.error.message
    }
  }

  public async deleteDriver(driverId: number) {
    try {
      const response = await this.driverService.deleteDriver(driverId)
      this.jsonResponse = response
      this.drivers = await this.driverService.getDrivers()
    } catch (err: unknown) {
      const error = err as HttpErrorResponse
      this.errorMessage = error.error.message
    }
  }

  public openEditModal(content: TemplateRef<NgbModal>, driverId: number) {
    this.isEdit = true
    this.driver = this.drivers.find((driver) => driver.id === driverId)
    this.patchFormValues()
    this.open(content)
  }

  private patchFormValues() {
    const driver = {
      name: this.driver?.name,
      lastName: this.driver?.lastName,
    }

    this.driverForm.patchValue(driver)
    this.driverForm.get("car")?.setValue(this.driver?.car.id)
  }

  public checkInputValidation(controlName: string): void {
    const control = this.driverForm.get(controlName)
    this.controlValidities[controlName] = control?.invalid && (control?.dirty || control?.touched) || false
  }
}
