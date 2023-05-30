import { Component, Input, OnInit } from '@angular/core';
import { CarService } from 'src/app/services/car.service';
import { Car } from 'src/app/models/car';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup,   FormControl, Validators, ValidationErrors } from '@angular/forms';


@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent {

  constructor (private carService : CarService, private modalService :NgbModal){}
  public cars : Car[] = []


  car = new Car
  carForm : FormGroup
  
  //plate = ""
  id : number
  
  //Auxiliares:
  inputId: number
  inputPlate = ""


  getCars() {
    this.carService.getAll().subscribe(response => {
      this.cars = response 
    }, error => {alert ("No se pudieron obtener los autos")})
  }


  ngOnInit() : void{  
    this.car.plate = ''
    this.carForm = new FormGroup({
      'plate' : new FormControl(this.car.plate, { validators: [Validators.required],updateOn: 'blur' })})
    this.getCars()
  }

  get plate(){return this.carForm.get('plate')}


  view (ver: any, car : Car){
    this.inputId = car.id
    this.inputPlate = car.plate
    
    this.modalService.open(ver).result.then(() => {
      let car = new Car()
      car.id = this.inputId
      car.plate = this.inputPlate
      this.carService.edit(car, car.id).subscribe(() => {
        alert("Alta Exitosa")
        this.getCars()
        //location.reload
      }, error =>{
        console.error(error)
      })
    })
    }


  delete (id : number){
    this.carService.delete(id).subscribe (() =>{
      alert ("Baja exitosa")
      this.getCars()
    }, error =>{
      console.error(error)
    })
  }

  add () {
    if (
      this.carForm.invalid
    ){
      alert ('campo vacio')
      return
    }
    let car = new Car()
    car.plate = this.plate.value
    this.carService.add(car).subscribe(() =>{
      this.getCars()
      alert("Alta exitosa")
    }, error =>{
      console.error(error)
    })
  }
}
