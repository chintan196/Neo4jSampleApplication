import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Observable,Subject } from "rxjs";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  constructor(private employeeservice:EmployeeService) { }

  employeeArray: any[] = [];  
  dtOptions: DataTables.Settings = {};  
  dtTrigger: Subject<any>= new Subject();  

  employees: Employee[];  
  employee : Employee=new Employee();   
  employeelist:any;  
  isupdated = false;  

  ngOnInit(): void {
    this.isupdated=false;

    this.dtOptions = {  
      pageLength: 5,  
      stateSave:true,  
      lengthMenu:[[5, 10, 15, -1], [5, 10, 15, "All"]],  
      processing: true  
    };     
    this.employeeservice.getEmployeeList().subscribe(data =>{  
    this.employees = data;  
    this.dtTrigger.next();  
    })  
  }

}
