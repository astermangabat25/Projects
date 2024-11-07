"use client";
import Axios from 'axios';
import { useState, useEffect, Key } from 'react';

export default function Home() {
  const [categories, setCategories] = useState<string[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<string | null>(null);
  const [filteredProducts, setFilteredProducts] = useState<any[]>([]);
  const [selectedProduct, setSelectedProduct] = useState<any | null>(null);

  useEffect(() => {
    Axios.get('https://dummyjson.com/products/category-list')
      .then((res) => {
        setCategories(res.data.slice(0, 5));
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  useEffect(() => {
    if (selectedCategory) {
      Axios.get(`https://dummyjson.com/products/category/${selectedCategory}`)
        .then((res) => {
          setFilteredProducts(res.data.products);
        })
        .catch((err) => {
          console.error(err);
        });
    } else {
      setFilteredProducts([]);
    }
  }, [selectedCategory]);

  const handleCategoryClick = (category: string) => {
    setSelectedCategory(category);
    setSelectedProduct(null);
  };

  const handleProductClick = (product: any) => {
    setSelectedProduct(product);
  };

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-4">CATEGORIES</h1>
      <ul className="flex flex-wrap gap-4 mb-8">
        {categories.map((category, index) => (
          <li key={index} className="border-2 border-green-500 rounded-lg">
            <a
              href="#"
              className="block px-4 py-2 bg-gray-800 text-white rounded-lg hover:bg-black"
              onClick={() => handleCategoryClick(category)}
            >
              {category}
            </a>
          </li>
        ))}
      </ul>

      {selectedCategory && (
        <div>
          <h1 className="text-3xl font-bold mb-4">PRODUCTS</h1>
          {filteredProducts.map((product, index) => (
            <li key={index}>
              <a href="#" onClick={() => handleProductClick(product)}>{product.title}</a>
            </li>
          ))}
        </div>
      )}

      {selectedProduct && (
        <div>
          <h2 className="text-2xl font-bold mb-4">{selectedProduct.title}</h2>
          <div className="flex gap-4 mb-4">
            {selectedProduct.images && selectedProduct.images.map((image: string, index: Key) => (
              <img key={index} src={image} className="w-48" />
            ))}
          </div>
          <p>{selectedProduct.description}</p>
          <p>${selectedProduct.price}</p>
          <p>{selectedProduct.rating}✬</p>
          <p>{selectedProduct.stock} stocks left</p>
          <p>{selectedProduct.brand}</p>
          <p>Tags: {selectedProduct.tags.join(', ')}</p>
          <p>{selectedProduct.returnPolicy}</p>
          <p>{selectedProduct.shippingInformation}</p>
          <p>{selectedProduct.availabilityStatus}</p>
        </div>
      )}
    </div>
  );
}
