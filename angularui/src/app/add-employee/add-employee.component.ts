import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';  
import {FormControl,FormGroup,Validators} from '@angular/forms';  
import { Employee } from '../employee';  

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  constructor(private employeeService:EmployeeService) { }

  employee : Employee=new Employee();  
  submitted = false;  
  
  ngOnInit() {  
    this.submitted=false;  
  }  

  employeesaveform=new FormGroup({  
    id:new FormControl('' , [Validators.required , Validators.min(1) ] ),  
    name:new FormControl('',[Validators.required,Validators.minLength(2)])
  });

  saveEmployee(){  
    this.employee =new Employee();     
    this.employee.id= this.Id.value;
    this.employee.name= this.Name.value;
    this.submitted = true;  
    this.save();   
  }  

  save() {  
    this.employeeService.createEmployee(this.employee)  
      .subscribe(data => console.log(data), error => console.log(error));  
    this.employee = new Employee();  
  }  

  get Id(){  
    return this.employeesaveform.get('id');  
  }  
  
  get Name(){  
    return this.employeesaveform.get('name');  
  }  

  addEmployeeForm(){  
    this.submitted=false;  
    this.employeesaveform.reset();  
  }  
}
