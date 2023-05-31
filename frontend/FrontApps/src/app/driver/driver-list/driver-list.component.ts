import { Component, OnInit, TemplateRef } from '@angular/core'
import { Driver } from '../model/driver'
import { DriverService } from '../services/driver.service'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'

@Component({
  selector: 'app-driver-list',
  templateUrl: './driver-list.component.html',
  styles: [
  ]
})
export class DriverListComponent implements OnInit {
  public drivers: Driver[] = []
  public driverForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    lastName: ['', Validators.required],
    car: ['', Validators.required]
  })

  constructor(private driverService: DriverService, private modalService: NgbModal, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.driverService.getDrivers().then((drivers) => {
      this.drivers = drivers
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

  public addDriver() {
    console.log('Adding driver')
  }
}
