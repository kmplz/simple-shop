import React from 'react';
import ProductCard from './ProductCard';

export default ({products}) => (
  <div className="row py-3">
    {products.map(product => <ProductCard key={product.id} product={product} />)}
  </div>
);