import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  email: string = "";
  firstName: string = "";
  lastName: string = "";
  phoneNumber: string = "";
  submitted: boolean = false;
  errorMessage: string = "";

  submitForm() {
    this.submitted = true;

    const validationMessage = this.formValid();
    const userData = {
      email: this.email,
      first_name: this.firstName,
      last_name: this.lastName,
      phone_number: this.phoneNumber
    };
    if (validationMessage == "") {
      console.log(userData);
      this.errorMessage = ""; 
    } else {
      this.errorMessage = validationMessage;
    }
  }

  formValid(): string {
    if (this.email == '' || this.firstName == '' || this.lastName == '' || this.phoneNumber == '') {
      return "Please fill out all fields.";
    }

    if (!this.numberValid()) {
      return "Please follow format: 09xx-xxx-xxxx.";
    }
    if(!this.emailValid()){
      return "Please enter a valid email."
    }
    return "";
  }

  numberValid(): boolean {
    const phoneRegex = /^09\d{2}-\d{3}-\d{4}$/;
    return phoneRegex.test(this.phoneNumber);
  }

  emailValid(): boolean{
    const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return emailRegex.test(this.email);
  }
}
