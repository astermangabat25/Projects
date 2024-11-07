<template>
  <div class="condrend">
    <div class="categ">
      <h1>CATEGORIES</h1>
      <ul>
        <li v-for="category in categories" @click.prevent="selectCategory(category)" :key="category">
          <a href="#">{{ category }}</a>
        </li>
      </ul>
    </div>

    <div v-if="selectedCategory">
      <h1>PRODUCTS</h1>
      <ul>
        <li v-for="product in filteredProducts" :key="product.id" @click="selectProduct(product)">
          <a href="#">{{ product.title }}</a>
        </li>
      </ul>
    </div>

    <div v-if="selectedProduct">
      <h1>Product Details</h1>
      <h2>{{ selectedProduct.title }}</h2>
      <div v-for="image in selectedProduct.images" :key="image">
        <img :src="image" style="max-width: 300px; margin: 10px;" />
      </div>
      <p>{{ selectedProduct.description }}</p>
      <p>${{ selectedProduct.price }}</p>
      <p>{{ selectedProduct.rating }}✬</p>
      <p>{{ selectedProduct.stock }} stocks left</p>
      <p>{{ selectedProduct.brand }}</p>
      <p>Tags: {{ selectedProduct.tags.join(', ') }}</p>
      <p>{{ selectedProduct.returnPolicy }}</p>
      <p>{{ selectedProduct.shippingInformation }}</p>
      <p>{{ selectedProduct.availabilityStatus }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { onMounted } from 'vue';
import axios from 'axios';

const categories = ref([]);
const selectedCategory = ref('');
const filteredProducts = ref([]);
const selectedProduct = ref(null); 

function selectCategory(category) {
  axios.get(`https://dummyjson.com/products/category/${category}`)
    .then((res) => {
      filteredProducts.value = res.data.products; 
    })
    .catch((err) => {
      console.error(err);
    });

  selectedProduct.value = null; 
  selectedCategory.value = category; 
}

function selectProduct(product) {
  selectedProduct.value = product; 
}

onMounted(()=>{
  axios.get('https://dummyjson.com/products/category-list')
  .then((res) => {
    categories.value = res.data.slice(0, 5);
  })
  .catch((err) => {
    console.error(err);
  });
})
</script>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }

  .categ ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
  }

  .categ li {
    float: left;
    margin: 5px;
    border: 2px solid green;
    border-radius: 5px;
  }

  .categ li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    background-color: #333;
    border-radius: inherit;
  }

  .categ li.selected a {
    background-color: black;
  }

  .categ li a:hover {
    background-color: black;
  }
}
</style>
