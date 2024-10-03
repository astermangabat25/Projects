import { useState } from 'react'
import './App.css'

function App() {
  const [email, setEmail] = useState("");
  const [fName, setFName] = useState("");
  const [lName, setLName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const validatePhoneNumber = (phone) => {
    const phonePattern = /^09\d{2}-\d{3}-\d{4}$/;
    return phonePattern.test(phone);
  };

  const handleSubmit = (event) => {
    event.preventDefault(); 

    if (!validatePhoneNumber(phoneNumber)) {
      setErrorMessage("Please follow the format for phone number: 09xx-xxx-xxxx.");
      return;
    }
    setErrorMessage("");

    const userData = {
      email,
      first_name: fName,
      last_name: lName,
      phone_number: phoneNumber
    };

    console.log(userData);
  };

  return (
    <div className="flex items-center justify-center min-h-screen">
      <form onSubmit={handleSubmit} className="w-full max-w-md p-8 rounded-lg border border-black">
        <h1 className="text-3xl font-extrabold text-center mb-6">Form</h1>
        <p className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-1" htmlFor="email">
            Email:
          </label>
          <input
            type="email"
            id="email"
            placeholder="Enter email here"
            onChange={(event) => setEmail(event.target.value)}
            className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700"
            required />
        </p>
        {email}

        <p>
          <label className="block text-gray-700 text-sm font-bold mb-1" htmlFor="first_name">
            First Name:
          </label>
          <input 
            type="text" 
            placeholder="Enter your first name here" 
            onChange={(event)=>{setFName(event.target.value);}} 
            className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700"
            required/>
        </p>
        {fName}

        <p>
          <label className="block text-gray-700 text-sm font-bold mb-1" htmlFor="last_name">
            Last Name:
          </label>
          <input 
            type="text" 
            placeholder="Enter your last name here" 
            onChange={(event)=>{setLName(event.target.value);}} 
            className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700"
            required/>
        </p>
        {lName}

        <p>
          <label className="block text-gray-700 text-sm font-bold mb-1" htmlFor="phone_number">
            PhoneNumber:
          </label>
          <input 
            type="text" 
            placeholder="09xx-xxx-xxxx" 
            onChange={(event)=>{setPhoneNumber(event.target.value);}}
            className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700"
            required/>
        </p>
        {phoneNumber}
        <br />
        {errorMessage}
        <br />
        <button 
          type="submit" 
          className="w-full bg-blue-500 text-white font-bold py-2 rounded hover:bg-blue-600 transition duration-200">
            Submit</button>
      </form>
    </div>
  )
}

export default App
