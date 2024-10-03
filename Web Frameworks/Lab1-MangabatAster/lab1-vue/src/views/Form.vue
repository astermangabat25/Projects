<template>
  <div class="flex items-center justify-center min-h-screen">
    <form @submit.prevent="submits()" class="w-full max-w-md p-8 rounded-lg border bg-white">
      <h1 class="text-3xl font-extrabold text-center mb-6 text-black">Form</h1>

      <p class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-1" htmlFor="email">
          Email:
        </label>
        <input
          type="email"
          id="email"
          v-model="email"
          placeholder="Enter email here"
          class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700 text-black"
          required />
      </p>
      <p class="text-black">{{email}}</p>

      <p>
      <label class="block text-gray-700 text-sm font-bold mb-1" htmlFor="first_name">
        First Name:
      </label>
      <input 
        type="text" 
        placeholder="Enter your first name here" 
        v-model="fname"
        class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700 text-black"
        required/>
      </p>
      <p class="text-black">{{fname}}</p>
  
      <p>
        <label class="block text-gray-700 text-sm font-bold mb-1" htmlFor="last_name">
          Last Name:
        </label>
        <input 
          type="text" 
          placeholder="Enter your last name here" 
          v-model="lname"
          class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700 text-black"
          required/>
      </p>
      <p class="text-black">{{lname}}</p>

      <p>
        <label class="block text-gray-700 text-sm font-bold mb-1" htmlFor="phone_number">
          PhoneNumber:
        </label>
        <input 
          type="text" 
          name="phone_num" 
          id="phone_num" 
          v-model="phone_num" 
          @input="validatePhoneNumber"
          class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-blue-700 text-black" 
          placeholder="09xx-xxx-xxxx" 

          required>
      </p>
      <p class="text-black">{{ phone_num }}</p>

      <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>

      <button type="submit" :disabled="!isPhoneValid" class="w-full bg-blue-500 text-white font-bold py-2 rounded hover:bg-blue-600 transition duration-200">Submit</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
const email = ref('')
const fname = ref('')
const lname = ref('')
const phone_num = ref('')
const errorMessage = ref('')
const isPhoneValid = ref(false)

function validatePhoneNumber() {
  const phonePattern = /^09\d{2}-\d{3}-\d{4}$/
  if (!phonePattern.test(phone_num.value)) {
    errorMessage.value = "Please follow the format for the phone number: 09xx-xxx-xxxx."
    isPhoneValid.value = false
  }
  else {
    errorMessage.value = ""
    isPhoneValid.value = true
  }
}

function submits() {

  errorMessage.value = ''

  const userData = {
    email: email.value,
    first_name: fname.value,
    last_name: lname.value,
    phone_number: phone_num.value
  }

  console.log(userData)
}
</script>

<style>
.about {
  padding: 20px;
}
</style>